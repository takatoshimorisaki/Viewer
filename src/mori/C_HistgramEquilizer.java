package mori;

public class C_HistgramEquilizer extends C_RawData implements I_ImageProcessor{

	private int[] mHistRed = new int[256];

	private int[] mHistGreen = new int[256];

	private int[] mHistBlue = new int[256];

	private double[] mThrRed = new double[256];

	private double[] mThrGreen = new double[256];

	private double[] mThrBlue = new double[256];

	public C_HistgramEquilizer(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);
	}

	public C_HistgramEquilizer(
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
			mHistRed[ mRed[pos] ]++;
		}

		for(int pos = 0; pos < size; pos++){
			mHistGreen[ mGreen[pos] ]++;
		}

		for(int pos = 0; pos < size; pos++){
			mHistBlue[ mBlue[pos] ]++;
		}

		double invSize = 1.0 / (double)size;

		int length = mHistRed.length - 1;

		mThrRed[0] = length * (double)mHistRed[0] * invSize;

		for(int pos = 1; pos < mHistRed.length; pos++){

			mThrRed[pos] += mThrRed[pos - 1] + length * (double)mHistRed[pos] * invSize;
		}

		mThrGreen[0] = mHistGreen.length * (double)mHistGreen[0] * invSize;

		for(int pos = 1; pos < mHistGreen.length; pos++){

			mThrGreen[pos] += mThrGreen[pos - 1] + length * (double)mHistGreen[pos] * invSize;
		}

		mThrBlue[0] = mHistBlue.length * (double)mHistBlue[0] * invSize;

		for(int pos = 1; pos < mHistGreen.length; pos++){

			mThrBlue[pos] += mThrBlue[pos - 1] + length * (double)mHistBlue[pos] * invSize;
		}

		for(int pos = 0; pos < size; pos++){

			int lumi = mRed[pos];

			dstRaw.mRed[pos] = (int)mThrRed[lumi];
		}

		for(int pos = 0; pos < size; pos++){

			int lumi = mGreen[pos];

			dstRaw.mGreen[pos] = (int)mThrGreen[lumi];
		}

		for(int pos = 0; pos < size; pos++){

			int lumi = mBlue[pos];

			dstRaw.mBlue[pos] = (int)mThrBlue[lumi];
		}

		return dstRaw;
	}

}

