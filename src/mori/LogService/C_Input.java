package mori.LogService;

import static java.lang.System.out;

import java.io.Reader;

/*
 * @brief	���͂���
 */
public class C_Input implements Runnable{

	//! ���[�_
	Reader mO_Reader;

	//! �f�o�b�O�E�t���O
	boolean mB_Debug = true;
	
	//! ���O�E�T�[�r�X
	private C_LogService mO_Log;

	/*
	 * @brief	�R���X�g���N�^
	 */
	public C_Input(
			Reader aO_Reader,
			C_LogService aO_Log
	){
		mO_Reader = aO_Reader;

		mO_Log = aO_Log;
	}
	
	/*
	 * @brief	���͂���
	 */
	public void run(){

		while(true){
			
			// �ҏW�p���͕�����
			StringBuffer requestLine = new StringBuffer();
			
			// ���͕���
			int lChar = -1;
			while(true){
				try{
					lChar = mO_Reader.read();

					mO_Log.printf("%c", lChar);

					if(lChar == '\r'){

						if(mB_Debug == true){
							out.println("r break");
						}

						break;
					}else
					if(lChar == '\n'){

						if(mB_Debug == true){
							out.println("n break");
						}

						break;
					}else
					if(lChar == -1){

						if(mB_Debug == true){
							out.println("-1 break");
						}

						break;
					}
					
					if(mB_Debug == true){
						out.printf("���͕��� = %d %c\n", lChar, lChar);
					}
					
					// ���͕�������X�V����
					requestLine.append((char)lChar);
					
				}catch(Exception ex){
					
					break;
				}
			}// while
	
			if(lChar == -1){
				break;
			}
			
			if(lChar != '\n'){

				// ���͕�������擾����
				String get = requestLine.toString();

				// �R���\�[������̓��͂�ݒ肷��
				mO_Log.mSetReq(get);

				if(mB_Debug == true){
					out.println("get = " + get);
				}
			}

		}// while
		
		if(mB_Debug == true){
			out.println("Exit Input Thread");
		}
	}
}
