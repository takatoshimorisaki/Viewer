package mori;

public class C_RainErasure extends C_RawData implements I_ImageProcessor{

	private C_ImageProcessParameter mPrmtr;

	private final static int LEVEL = 8;

	private int[] mRainRed = new int[LEVEL];

	private int[] mRainGreen = new int[LEVEL];

	private int[] mRainBlue = new int[LEVEL];

	public C_RainErasure(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mPrmtr = aPrmtr;

		mRainRed[0] = 153;
		mRainGreen[0] = 204;
		mRainBlue[0] = 255;

		mRainRed[1] = 51;
		mRainGreen[1] = 102;
		mRainBlue[1] = 255;

		mRainRed[2] = 0;
		mRainGreen[2] = 0;
		mRainBlue[2] = 255;

		mRainRed[3] = 0;
		mRainGreen[3] = 255;
		mRainBlue[3] = 0;

		mRainRed[4] = 255;
		mRainGreen[4] = 255;
		mRainBlue[4] = 0;

		mRainRed[5] = 255;
		mRainGreen[5] = 153;
		mRainBlue[5] = 0;

		mRainRed[6] = 255;
		mRainGreen[6] = 0;
		mRainBlue[6] = 255;

		mRainRed[7] = 255;
		mRainGreen[7] = 0;
		mRainBlue[7] = 0;
	}

	public C_RainErasure(
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

			boolean ground = true;

			for(int level = 0; level < LEVEL; level++){

				if(mRed[pos] == mRainRed[level]
				&& mGreen[pos] == mRainGreen[level]
				&& mBlue[pos] == mRainBlue[level]){

					ground = false;
					break;
				}
			}// for level

			if(ground == true){
				dstRaw.mRed[pos] = mRed[pos];
				dstRaw.mGreen[pos] = mGreen[pos];
				dstRaw.mBlue[pos] = mBlue[pos];
			}
		}

		return dstRaw;
	}
}

