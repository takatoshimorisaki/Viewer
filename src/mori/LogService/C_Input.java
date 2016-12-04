package mori.LogService;

import static java.lang.System.out;

import java.io.Reader;

/*
 * @brief	入力する
 */
public class C_Input implements Runnable{

	//! リーダ
	Reader mO_Reader;

	//! デバッグ・フラグ
	boolean mB_Debug = true;
	
	//! ログ・サービス
	private C_LogService mO_Log;

	/*
	 * @brief	コンストラクタ
	 */
	public C_Input(
			Reader aO_Reader,
			C_LogService aO_Log
	){
		mO_Reader = aO_Reader;

		mO_Log = aO_Log;
	}
	
	/*
	 * @brief	入力する
	 */
	public void run(){

		while(true){
			
			// 編集用入力文字列
			StringBuffer requestLine = new StringBuffer();
			
			// 入力文字
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
						out.printf("入力文字 = %d %c\n", lChar, lChar);
					}
					
					// 入力文字列を更新する
					requestLine.append((char)lChar);
					
				}catch(Exception ex){
					
					break;
				}
			}// while
	
			if(lChar == -1){
				break;
			}
			
			if(lChar != '\n'){

				// 入力文字列を取得する
				String get = requestLine.toString();

				// コンソールからの入力を設定する
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
