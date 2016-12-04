package mori.LogService;

import java.net.Socket;
import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class C_RequestProcessor implements Runnable{
	
	//! クライアントからのリクエストを保持する
	private static List<Socket> msO_Pool = new LinkedList<Socket>();
	
	//! 出力
	private C_Output mO_Output;
	
	//! 入力
	private C_Input mO_Input;
	
	//! 出力スレッド
	private Thread mO_OutThread;
	
	//! 入力スレッド
	private Thread mO_InThread;
	
	//! LogService
	private C_LogService mO_LogService;

	/*
	 * @brief	コンストラクタ
	 */
	public C_RequestProcessor(C_LogService aO_LogService){
		
		mO_LogService = aO_LogService;
	}

	/*
	 * ＠brief	通信処理を要求する
	 */
	public static void processRequest(Socket aO_Request){
		synchronized(msO_Pool){
			msO_Pool.add(
					msO_Pool.size(), 
					aO_Request);
			
			msO_Pool.notifyAll();
		}
	}

	/*
	 * @brief	クライアント・サーバ通信を行う
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		
		while(true){
			Socket connection;
			synchronized(msO_Pool){
				while(msO_Pool.isEmpty()){
					try{
						msO_Pool.wait();
					}catch(InterruptedException e){
					}
				}
				connection = (Socket)msO_Pool.remove(0);
				
				System.out.println("new connection");
			}

			try{
				OutputStream raw = 
					new BufferedOutputStream(connection.getOutputStream());
				PrintStream out = new PrintStream(raw);
				
				System.setErr(out);
				System.setOut(out);

				mO_Output = new C_Output(out, mO_LogService);
				
				mO_OutThread = new Thread(mO_Output);
				
				mO_OutThread.start();
				
				InputStream is = new BufferedInputStream(connection.getInputStream());

				System.setIn(is);

				Reader in = new InputStreamReader(is, "ASCII");
				
				mO_Input = new C_Input(in, mO_LogService);
				
				mO_InThread = new Thread(mO_Input);
				
				mO_InThread.start();
				
				try{
					// 入力スレッドが終了するのを待つ
					mO_InThread.join();

					// 入力スレッドが終了したら、出力スレッドも終了する
					mO_Output.mExit();
					
					mO_OutThread.join();
					
				}catch(Exception ex){
					
				}
								
			}catch(IOException e){
			}finally{
				try{
					connection.close();
					
					System.out.println("connection closed");
					
				}catch(IOException e){
					
				}
			}
		}/* end of while */
	}

}




