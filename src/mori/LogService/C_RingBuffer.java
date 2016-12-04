package mori.LogService;

/*
 * @brief	リングバッファ
 */
public class C_RingBuffer {

	//! 値の格納先
	private Object[] mObj;
	
	//! 入力位置
	private int mInPos;
	
	//! 出力位置
	private int mOutPos;
	
	/*
	 * @brief	コンストラクタ
	 */
	public C_RingBuffer(
			int aSize
	){
		
		// 値の格納先を生成する
		mObj = new Object[aSize];
		
		// 入力位置を初期化する
		mInPos = 0;
	
		// 出力位置を初期化する
		mOutPos = 0;
		
	}
	
	/*
	 * @brief	追加する
	 */
	public void mAdd(
			Object aObj
	){
		mObj[mInPos] = aObj;
		
		mInPos++;
		
		if(mInPos >= mObj.length){
			mInPos = 0;
		}
	}
	
	/*
	 * @brief	取得する
	 */
	public Object mGet(){
		Object lO_Rtn = null;
		
		if(mInPos != mOutPos){
				
			lO_Rtn = mObj[mOutPos];
			
			mOutPos++;
			
			if(mOutPos >= mObj.length){
				mOutPos = 0;
			}
		}
		
		return lO_Rtn;
	}
	
}


