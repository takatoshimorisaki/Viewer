package mori;

public class C_CloudExtractor extends C_RawData implements I_ImageProcessor{

	private C_ImageProcessParameter mPrmtr;

	public C_CloudExtractor(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mPrmtr = aPrmtr;
	}

	public C_CloudExtractor(
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

			dstRaw.mRed[pos] = mRed[pos];
			dstRaw.mGreen[pos] = mRed[pos];
			dstRaw.mBlue[pos] = mRed[pos];
		}

		return dstRaw;
	}
}

