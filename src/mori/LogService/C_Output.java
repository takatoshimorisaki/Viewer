package mori.LogService;

import static java.lang.System.out;

import java.io.PrintStream;
import java.net.Socket;

/*
 * @brief	ログを出力する
 */
public class C_Output implements Runnable{

	//! 出力ストリーム
	private PrintStream mO_Out;
	
	//! ログ・サービス
	private C_LogService mO_Log;
	
	//! 終了フラグ
	private boolean mB_Exit;
	
	//! デバッグ・フラグ
	private boolean mB_Debug = true;
	
	/*
	 * @brief	コンストラクタ
	 */
	C_Output(
		PrintStream aO_Out,
		C_LogService aO_LogService
	){
		mO_Out = aO_Out;
		
		mO_Log = aO_LogService;
	
		mB_Exit = false;
	}
	
	/*
	 * @brief	ログを出力する
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		// 出力文字列
		String lO_String = null;
		
		while(true){
			int lCnt = 0;
	
			while((lO_String = mO_Log.mGet()) != null){
			
				mO_Out.printf("%s", lO_String);

			}
			
			mO_Out.flush();
			
			try{
				//// Thread.sleep(1000);
			}catch(Exception ex){
				
			}
			
			// 終了フラグが真の場合、
			if(mB_Exit == true){
				
				// スレッドを終了する
				break;
			}
		}
		
		if(mB_Debug == true){
			out.println("Exit Output Thread");
		}
	}
	
	/*
	 * @brief	終了する
	 */
	public void mExit(){
		mB_Exit = true;
	}

	/*
		@brief	出力ストリームを取得する
	*/
	public PrintStream mGetPrintStream(){
		return mO_Out;
	}

}
