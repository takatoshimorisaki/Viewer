package mori.Fractal;

import static java.lang.System.out;

import mori.C_ImageProcessParameter;
import mori.C_RawData;
import mori.I_ImageProcessor;

public class Fractal3 extends C_RawData implements I_ImageProcessor{

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

	public Fractal3(
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

	public Fractal3(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){

		if(mCalced == false){
			
			double scale = 0.6;
			double x0;
			double y0;
			int    MAX_LOOP = Integer.MAX_VALUE / 4096;
			int    red, green, blue;

			for(int yct = 0; yct < mHeight; yct++){
				for(int xct = 0; xct < mWidth; xct++){

					mDstRaw.mSetRGB(xct, yct, 255, 255, 255);
				}
			}

			for(int loop = 0; loop < MAX_LOOP; loop++){

				double random = mRandom.nextDouble();
	
				if(random < mThr1){
	
					x0 = scale * mX1;
					y0 = scale * mY1;

					red = 255;
					green = 0;
					blue = 0;

				}else if(random < mThr2){
	
					x0 = scale * mX1 + 300.0;
					y0 = scale * mY1;

					red = 0;
					green = 255;
					blue = 0;

				}else{
	
					x0 = scale * mX1 + 150.0;
					y0 = scale * mY1 + 150.0;

					red = 0;
					green = 0;
					blue = 255;
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

