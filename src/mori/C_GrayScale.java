package mori;

public class C_GrayScale extends C_RawData implements I_ImageProcessor{

	private C_ImageProcessParameter mPrmtr;

	public C_GrayScale(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mPrmtr = aPrmtr;
	}

	public C_GrayScale(
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

		for(int cnt = 0; cnt < size; cnt++){
			int y =  (int)(0.299 * mRed[cnt] + 0.587 * mGreen[cnt] + 0.114 * mBlue[cnt]);

			if(y > 255){
				y = 255;
			}

			dstRaw.mRed[cnt] = y;
			dstRaw.mGreen[cnt] = y;
			dstRaw.mBlue[cnt] = y;
		}

		return dstRaw;
	}
}

