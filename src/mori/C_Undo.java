package mori;

public class C_Undo implements I_ImageProcessor{

	private C_RawData mRaw;

	public C_Undo(
		C_RawData aRaw
	){
		mRaw = aRaw;
	}

	public C_RawData mExe(){
		return mRaw;
	}
}

