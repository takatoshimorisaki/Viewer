package mori.Surface;

import mori.C_RawData;
import mori.I_ImageProcessor;

public class C_Surface extends C_RawData implements I_ImageProcessor{

	private C_Canvas[] mCanvas;

	private int[] mFrame = new int[3];

	private double mC = 1.0;

	private double mH = 1.0;

	private double mDeltaT = 0.1;

	private double mCoef1;

	private double mCoef2;

	public C_Surface(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);

		mCanvas = new C_Canvas[3];
		mCanvas[0] = new C_Canvas(mWidth, mHeight);
		mCanvas[1] = new C_Canvas(mWidth, mHeight);
		mCanvas[2] = mCanvas[1].mCopy();

		mFrame[0] = 0;
		mFrame[1] = 1;
		mFrame[2] = 2;

		mCoef1 = mC * mC * mDeltaT * mDeltaT / (mH * mH);

		mCoef2 = 2.0 - 4.0 * mCoef1;
	}

	public C_Surface(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){

				double left   = mCanvas[mFrame[1]].mGetZ(xct-1, yct);
				double right  = mCanvas[mFrame[1]].mGetZ(xct+1, yct);
				double top    = mCanvas[mFrame[1]].mGetZ(xct, yct-1);
				double bottom = mCanvas[mFrame[1]].mGetZ(xct, yct+1);

				double now = mCanvas[mFrame[1]].mGetZ(xct, yct);

				double prev = mCanvas[mFrame[2]].mGetZ(xct, yct);

				double next = mCoef1 * (left + right + top + bottom)
                            + mCoef2 * now
                            - prev;

				if(next > 255){
					next = 255;
				}else if(next < -255){
					next = -255;
				}

				mCanvas[mFrame[0]].mSetZ(xct, yct, next);

				int pos = mWidth * yct + xct;
				int lumi = (int)next;

				if(next <= 0){
					dstRaw.mRed[pos]   = 0;
					dstRaw.mGreen[pos] = -lumi;
					dstRaw.mBlue[pos]  = 0;
				}else if(next > 0){
					dstRaw.mRed[pos]   = lumi;
					dstRaw.mGreen[pos] = 0;
					dstRaw.mBlue[pos]  = 0;
				}

				if(dstRaw.mRed[pos] < 0){
					dstRaw.mRed[pos] = 0;
				}else if(dstRaw.mRed[pos] > 255){
					dstRaw.mRed[pos] = 255;
				}

				if(dstRaw.mGreen[pos] < 0){
					dstRaw.mGreen[pos] = 0;
				}else if(dstRaw.mGreen[pos] > 255){
					dstRaw.mGreen[pos] = 255;
				}

				if(dstRaw.mBlue[pos] < 0){
					dstRaw.mBlue[pos] = 0;
				}else if(dstRaw.mBlue[pos] > 255){
					dstRaw.mBlue[pos] = 255;
				}

			}// for xct
		}// for yct

		int temp = mFrame[2];
		mFrame[2] = mFrame[1];
		mFrame[1] = mFrame[0];
		mFrame[0] = temp;

		return dstRaw;
	}
}

