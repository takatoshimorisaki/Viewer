package mori.Fractal;

import mori.C_ImageProcessParameter;
import mori.C_RawData;
import mori.I_ImageProcessor;

public class Gasket2 extends C_RawData implements I_ImageProcessor{

	private java.util.Random mRandom;

	private C_ImageProcessParameter mPrmtr;

	private double mX1;

	private double mY1;

	private double mThr1 = 1.0/3.0;

	private double mThr2 = 2.0/3.0;

	private boolean mCalced = false;

	private mori.C_RawData mDstRaw;

	private int mBaseX;

	private int mBaseY;

	public Gasket2(
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

	public Gasket2(
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
			int    MAX_LOOP = 100000;

			for(int loop = 0; loop < MAX_LOOP; loop++){

				double random = mRandom.nextDouble();
	
				if(random < mThr1){
	
					x0 = 0.5 * mX1;
					y0 = 0.5 * mY1;

				}else if(random < mThr2){
	
					x0 = 0.5 * mX1 + 300.0;
					y0 = 0.5 * mY1;

				}else{
	
					x0 = 0.5 * mX1 + 150.0;
					y0 = 0.5 * mY1 + 150.0;

				}
	
				int ix = (int)x0 + mBaseX;
				int iy = (int)y0 + mBaseY;
	
				if(loop < (MAX_LOOP/2)){
					mDstRaw.mSetRGB(ix, iy, 255, 0, 0);
				}else{
					mDstRaw.mSetRGB(ix, iy, 0, 255, 0);
				}
				mX1 = x0;
				mY1 = y0;
			}

			mCalced = true;
		}

		return mDstRaw;
	}
}

