package mori;

public class C_Rotator extends C_RawData implements I_ImageProcessor{

	private C_ImageProcessParameter mPrmtr;

	public C_Rotator(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mPrmtr = aPrmtr;
	}

	public C_Rotator(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mHeight, mWidth);

		int dstPos = 0;
		for(int xct = 0; xct < mWidth; xct++){
			for(int yct = (mHeight - 1); yct >= 0; yct--){

				int srcPos = yct * mWidth + xct;

				dstRaw.mRed[dstPos] = mRed[srcPos];
				dstRaw.mGreen[dstPos] = mGreen[srcPos];
				dstRaw.mBlue[dstPos] = mBlue[srcPos];

				dstPos++;
			}
		}

		return dstRaw;
	}
}

