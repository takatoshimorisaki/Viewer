package mori.LogService;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Locale;

/*
 * @brief	���O�E�T�[�r�X
 */
public class C_LogService implements Runnable{

	//! �T�[�o�E�\�P�b�g
	private ServerSocket mO_ServerSocket;
	
	//! �T�[�o�E�X���b�h�̐�
	private int mNumThreads = 1;
	
	//! �}�l�[�W���ւ̗v��
	private String mO_Req;

	//! �~���[�e�b�N�X�E�I�r�W�F�N�g
	private Object mO_Mutex;

	//!printf�`���̕�������쐬����
    private Formatter mO_Formatter;
    
	//! �����O�o�b�t�@
	private C_RingBuffer mO_RingBuffer;
	
	//! �|�[�g�ԍ�
	private int mPort = 8080;
	
	//! �X���b�h
	private Thread mO_Thread;

	/*
	 * @brief	�R���X�g���N�^
	 */
	public C_LogService(){
		
		mO_RingBuffer = new C_RingBuffer(10000);

		// �~���[�e�b�N�X�𐶐�����
		mO_Mutex = new Object();
		
	}

	/*
	 * @brief �T�[�r�X���J�n����(non-Javadoc)
	 * @see mori.I_Service#mStartService()
	 */
	public boolean mStartService(){

		mO_Thread = new Thread(this);

		mO_Thread.start();

		return true;
	}
	
	/*
	 * @brief �T�[�r�X���~����(non-Javadoc)
	 * @see mori.I_Service#mStopService()
	 */
	public boolean mStopService(){
		
		return true;
	}

	/*
		@brief	�T�[�r�X����String�^�̏����擾����
	*/
	public String mGetInfo(
		// ���
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
		@brief	�R���\�[������̗v����ݒ肷��
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
	 * @brief	���O��ǉ�����
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
	 * @brief	���O���擾����
	 */
	public String mGet(){
		return (String)mO_RingBuffer.mGet();
	}

}

