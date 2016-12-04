package mori;

public class C_Fog extends C_RawData implements I_ImageProcessor{

	private C_ImageProcessParameter mPrmtr;

	public C_Fog(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mPrmtr = aPrmtr;
	}

	public C_Fog(
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
			double y =  0.299 * mRed[cnt] + 0.587 * mGreen[cnt] + 0.114 * mBlue[cnt];
			y /= 255.0;

			dstRaw.mRed[cnt] = (int)( y * mRed[cnt] + (1- y) * mPrmtr.mFogDensity * 255 );

			if(dstRaw.mRed[cnt] > 255){
				dstRaw.mRed[cnt] = 255;
			}

			dstRaw.mGreen[cnt] = (int)( y * mGreen[cnt] + (1- y) * mPrmtr.mFogDensity * 255 );

			if(dstRaw.mGreen[cnt] > 255){
				dstRaw.mGreen[cnt] = 255;
			}

			dstRaw.mBlue[cnt] = (int)( y * mBlue[cnt] + (1- y) * mPrmtr.mFogDensity * 255 );

			if(dstRaw.mBlue[cnt] > 255){
				dstRaw.mBlue[cnt] = 255;
			}
		}

		return dstRaw;
	}
}

