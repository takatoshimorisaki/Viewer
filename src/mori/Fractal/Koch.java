package mori.Fractal;

import mori.C_ImageProcessParameter;
import mori.C_RawData;
import mori.I_ImageProcessor;

public class Koch extends C_RawData implements I_ImageProcessor{

	private java.util.Random mRandom;

	private C_ImageProcessParameter mPrmtr;

	private double mX1;

	private double mY1;

	private double mThr1 = 0.5;

	private boolean mCalced = false;

	private mori.C_RawData mDstRaw;

	private int mBaseX;

	private int mBaseY;

	public Koch(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mPrmtr = aPrmtr;

		int seed = (int)System.currentTimeMillis();

		mRandom = new java.util.Random(seed);

		mDstRaw = new mori.C_RawData(mWidth, mHeight);

		mBaseX = 0;

		mBaseY = 0;
	}

	public Koch(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){

		if(mCalced == false){
	
			double coef = Math.sqrt(3.0)/6.0;
			double x0;
			double y0;
			int    MAX_LOOP = 100000;
			int    red, green, blue;

			for(int loop = 0; loop < MAX_LOOP; loop++){

				double random = mRandom.nextDouble();
	
				if(random < mThr1){
	
					x0 = 0.5 * mX1 + coef * mY1;
					y0 = coef * mX1 - 0.5 * mY1;

					red = 255;
					green = 0;
					blue = 0;

				}else{
	
					x0 = 0.5 * mX1 - coef * mY1 + 0.5;
					y0 = - coef * mX1 - 0.5 * mY1 + coef;

					red = 0;
					green = 255;
					blue = 0;
				}
	
				int ix = (int)(600.0 * x0) + mBaseX;
				int iy = (int)(600.0 * y0) + mBaseY;
				iy = mHeight/2 - iy;
	
				mDstRaw.mSetRGB(ix, iy, red, green, blue);

				mX1 = x0;
				mY1 = y0;
			}

			mCalced = true;
		}

		return mDstRaw;
	}
}

