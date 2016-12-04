package mori.LogService;

/*
 * @brief	�����O�o�b�t�@
 */
public class C_RingBuffer {

	//! �l�̊i�[��
	private Object[] mObj;
	
	//! ���͈ʒu
	private int mInPos;
	
	//! �o�͈ʒu
	private int mOutPos;
	
	/*
	 * @brief	�R���X�g���N�^
	 */
	public C_RingBuffer(
			int aSize
	){
		
		// �l�̊i�[��𐶐�����
		mObj = new Object[aSize];
		
		// ���͈ʒu������������
		mInPos = 0;
	
		// �o�͈ʒu������������
		mOutPos = 0;
		
	}
	
	/*
	 * @brief	�ǉ�����
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
	 * @brief	�擾����
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


