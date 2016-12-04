package mori.Fractal;

import mori.C_ImageProcessParameter;
import mori.C_RawData;
import mori.I_ImageProcessor;

public class Fractal2 extends C_RawData implements I_ImageProcessor{

	private java.util.Random mRandom;

	private C_ImageProcessParameter mPrmtr;

	private double mX1;

	private double mY1;

	private double mThr1 = 0.25;

	private double mThr2 = 0.5;

	private double mThr3 = 0.75;

	private boolean mCalced = false;

	private mori.C_RawData mDstRaw;

	private int mBaseX;

	private int mBaseY;

	private double A00 = 0.4;

	private double A01 = 0.0;

	private double A10 = 0.0;

	private double A11 = 0.5;

	public Fractal2(
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

	public Fractal2(
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
			int    MAX_LOOP = Integer.MAX_VALUE / 2048;
			int    red, green, blue;

			for(int yct = 0; yct < mHeight; yct++){
				for(int xct = 0; xct < mWidth; xct++){

					mDstRaw.mSetRGB(xct, yct, 255, 255, 255);
				}
			}

			for(int loop = 0; loop < MAX_LOOP; loop++){

				double random = mRandom.nextDouble();
	
				if(random < mThr1){
	
					x0 = A00 * mX1 + A01 * mY1 + 300.0;
					y0 = A10 * mX1 + A11 * mY1;

				}else if(random < mThr2){
	
					x0 = A00 * mX1 + A01 * mY1 + 300.0;
					y0 = A10 * mX1 + A11 * mY1 + 300.0;

				}else if(random < mThr3){
	
					x0 = A00 * mX1 + A01 * mY1;
					y0 = A10 * mX1 + A11 * mY1 + 300.0;

				}else{
	
					x0 = A00 * mX1 + A01 * mY1;
					y0 = A10 * mX1 + A11 * mY1;

				}
	
				if(loop < MAX_LOOP/2){
					red = 0;
					green = 0;
					blue = 0;
				}else{
					red = 255;
					green = 0;
					blue = 0;
				}

				int ix = (int)x0 + mBaseX;
				int iy = (int)y0 + mBaseY;
				iy = mHeight - iy;
	
				mDstRaw.mSetRGB(ix, iy, red, green, blue);

				mX1 = x0;
				mY1 = y0;
			}

			mCalced = true;
		}

		return mDstRaw;
	}
}

