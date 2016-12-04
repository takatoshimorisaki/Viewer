package mori.LogService;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Locale;

/*
 * @brief	ログ・サービス
 */
public class C_LogService implements Runnable{

	//! サーバ・ソケット
	private ServerSocket mO_ServerSocket;
	
	//! サーバ・スレッドの数
	private int mNumThreads = 1;
	
	//! マネージャへの要求
	private String mO_Req;

	//! ミューテックス・オビジェクト
	private Object mO_Mutex;

	//!printf形式の文字列を作成する
    private Formatter mO_Formatter;
    
	//! リングバッファ
	private C_RingBuffer mO_RingBuffer;
	
	//! ポート番号
	private int mPort = 8080;
	
	//! スレッド
	private Thread mO_Thread;

	/*
	 * @brief	コンストラクタ
	 */
	public C_LogService(){
		
		mO_RingBuffer = new C_RingBuffer(10000);

		// ミューテックスを生成する
		mO_Mutex = new Object();
		
	}

	/*
	 * @brief サービスを開始する(non-Javadoc)
	 * @see mori.I_Service#mStartService()
	 */
	public boolean mStartService(){

		mO_Thread = new Thread(this);

		mO_Thread.start();

		return true;
	}
	
	/*
	 * @brief サービスを停止する(non-Javadoc)
	 * @see mori.I_Service#mStopService()
	 */
	public boolean mStopService(){
		
		return true;
	}

	/*
		@brief	サービスからString型の情報を取得する
	*/
	public String mGetInfo(
		// 情報
		String aO_Info
	){
		String lO_Rtn = null;

		synchronized(mO_Mutex){

			if(mO_Req != null){

				lO_Rtn = new String(mO_Req);

				mO_Req = null;

			}else if(aO_Info != null){

				lO_Rtn = new String(aO_Info);

			}
		}

		return lO_Rtn;
	}

	/*
		@brief	コンソールからの要求を設定する
	*/
	public void mSetReq(
		String aO_Req
	){

		synchronized(mO_Mutex){

			mO_Req = new String(aO_Req);

		}
	}

	/*
	 * @brief	(non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		try{
			mO_ServerSocket = new ServerSocket(mPort);
		}catch(Exception e){
		
			e.printStackTrace();
			System.err.println(e);
		}
		
		for(int lCnt = 0; lCnt < mNumThreads; lCnt++){
			try{
				C_RequestProcessor lO_ReqProc = new C_RequestProcessor(this);

				Thread lO_ReqThread = new Thread(lO_ReqProc);
				lO_ReqThread.start();

			}catch(Exception ex){
				System.err.println(ex);
			}
		}

		while(true){
			try{
				System.out.println("Accepting connections on port: " + mO_ServerSocket.getLocalPort());

				Socket lO_Socket = mO_ServerSocket.accept();
				C_RequestProcessor.processRequest(lO_Socket);
			}catch(Exception e){

				e.printStackTrace();

				System.err.println(e);

				if(mO_ServerSocket == null){
					System.exit(1);
				}
			}
		}
	}
	
	/*
	 * @brief	ログを追加する
	 */
	public synchronized void printf(
			String format, 
			Object ... args
	){	
		mO_Formatter = new Formatter();
		
		try{
			mO_Formatter.format(Locale.getDefault(), format, args);
	
			String lO_Str = mO_Formatter.toString();
	
			mO_RingBuffer.mAdd(lO_Str);
		}catch(Throwable th){
			
			th.printStackTrace();
			System.out.println("C_LogService printf");
			//// System.exit(1);
		}
	}
	
	/*
	 * @brief	ログを取得する
	 */
	public String mGet(){
		return (String)mO_RingBuffer.mGet();
	}

}

