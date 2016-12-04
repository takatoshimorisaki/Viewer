package mori.Fractal;

import mori.C_ImageProcessParameter;
import mori.C_RawData;
import mori.I_ImageProcessor;

public class Fern extends C_RawData implements I_ImageProcessor{

	private java.util.Random mRandom;

	private C_ImageProcessParameter mPrmtr;

	private double mX1;

	private double mY1;

	private double mThr1 = 0.73;

	private double mThr2 = 0.86;

	private double mThr3 = 0.99;

	private boolean mCalced = false;

	private mori.C_RawData mDstRaw;

	private int mBaseX;

	private int mBaseY;

	public Fern(
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

	public Fern(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){

		if(mCalced == false){
			double x0;
			double y0;
			int    MAX_LOOP = 800000;
			int    red, green, blue;

			for(int yct = 0; yct < mHeight; yct++){
				for(int xct = 0; xct < mWidth; xct++){

					mDstRaw.mSetRGB(xct, yct, 255, 255, 255);
				}
			}

			for(int loop = 0; loop < MAX_LOOP; loop++){

				double random = mRandom.nextDouble();
	
				if(random < mThr1){
	
					x0 =  0.8560 * mX1 + 0.0414 * mY1 + 0.07;
					y0 = -0.0205 * mX1 + 0.8580 * mY1 + 0.147;

					red = 255;
					green = 0;
					blue = 0;

				}else if(random < mThr2){
	
					x0 = 0.244 * mX1 - 0.385 * mY1 + 0.393;
					y0 = 0.176 * mX1 + 0.224 * mY1 + 0.102;

					red = 0;
					green = 255;
					blue = 0;

				}else if(random < mThr3){
	
					x0 = -0.144 * mX1 + 0.390 * mY1 + 0.527;
					y0 =  0.181 * mX1 + 0.259 * mY1 - 0.014;

					red = 0;
					green = 0;
					blue = 255;
				}else{
	
					x0 =  0.486;
					y0 =  0.355 * mX1 + 0.216 * mY1 + 0.05;

					red = 0;
					green = 0;
					blue = 0;
				}
	
				int ix = (int)(600.0 * x0) + mBaseX;
				int iy = (int)(600.0 * y0) + mBaseY;
				//// iy = mHeight/2 - iy;
	
				mDstRaw.mSetRGB(ix, iy, red, green, blue);

				mX1 = x0;
				mY1 = y0;
			}

			mCalced = true;
		}

		return mDstRaw;
	}
}

