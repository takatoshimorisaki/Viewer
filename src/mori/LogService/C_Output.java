package mori.LogService;

import static java.lang.System.out;

import java.io.PrintStream;
import java.net.Socket;

/*
 * @brief	���O���o�͂���
 */
public class C_Output implements Runnable{

	//! �o�̓X�g���[��
	private PrintStream mO_Out;
	
	//! ���O�E�T�[�r�X
	private C_LogService mO_Log;
	
	//! �I���t���O
	private boolean mB_Exit;
	
	//! �f�o�b�O�E�t���O
	private boolean mB_Debug = true;
	
	/*
	 * @brief	�R���X�g���N�^
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
	 * @brief	���O���o�͂���
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		// �o�͕�����
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
			
			// �I���t���O���^�̏ꍇ�A
			if(mB_Exit == true){
				
				// �X���b�h���I������
				break;
			}
		}
		
		if(mB_Debug == true){
			out.println("Exit Output Thread");
		}
	}
	
	/*
	 * @brief	�I������
	 */
	public void mExit(){
		mB_Exit = true;
	}

	/*
		@brief	�o�̓X�g���[�����擾����
	*/
	public PrintStream mGetPrintStream(){
		return mO_Out;
	}

}
