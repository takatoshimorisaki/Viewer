package mori;

public class C_CloudErasure extends C_RawData implements I_ImageProcessor{

	private C_ImageProcessParameter mPrmtr;

	public C_CloudErasure(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mPrmtr = aPrmtr;
	}

	public C_CloudErasure(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		int size = mWidth * mHeight;

		for(int pos = 0; pos < size; pos++){

			if(mGreen[pos] > mRed[pos]){
				dstRaw.mRed[pos] = 0;
				dstRaw.mGreen[pos] = 128;
				dstRaw.mBlue[pos] = 0;
			}else if(mBlue[pos] > mRed[pos]){
				dstRaw.mRed[pos] = 0;
				dstRaw.mGreen[pos] = 0;
				dstRaw.mBlue[pos] = 128;
			}
		}

		return dstRaw;
	}
}

