package mori.Category;

import static mori.E_RGB.*;

import mori.E_RGB;

public class C_Hue extends mori.C_RawData{

	private E_RGB mLargest;

	private E_RGB mSecondLargest;

	private E_RGB mSmallest;

	private double mPhase;

	private int[] mHue;

	private int[] mMax;

	private int[] mMin;

	private mori.C_RawData mDstRaw;

	public C_Hue(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);

		mDstRaw = new mori.C_RawData(mWidth, mHeight);

		int size = mWidth * mHeight;

		mHue = new int[size];
		mMax = new int[size];
		mMin = new int[size];
	}

	public C_Hue(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public void mExe(){
		int size = mWidth * mHeight;

		for(int pos = 0; pos < size; pos++){

			mRank(mRed[pos], mGreen[pos], mBlue[pos]);

			mCalcHue(mRed[pos], mGreen[pos], mBlue[pos], pos);
		}
	}

	public int mGetLength(){
		return mHue.length;
	}

	public int mGetHue(int pos){
		return mHue[pos];
	}

	public void mCalcRGB(
		double aHue,
		int pos
	){
		if(aHue < 0){
			aHue = 0;
		}
		aHue = (int)aHue % 360;

		if(aHue >= 0.0 && aHue < 60.0){

			mDstRaw.mRed[pos] = mMax[pos];
			mDstRaw.mGreen[pos] = (int)(mMin[pos] + aHue * (mMax[pos] - mMin[pos]) / 60.0);
			mDstRaw.mBlue[pos] = mMin[pos];

		}else if(aHue < 120.0){

			mDstRaw.mRed[pos] = (int)(mMax[pos] - (aHue - 60.0) * (mMax[pos] - mMin[pos]) / 60.0);
			mDstRaw.mGreen[pos] = mMax[pos];
			mDstRaw.mBlue[pos] = mMin[pos];

		}else if(aHue < 180.0){

			mDstRaw.mRed[pos] = mMin[pos];
			mDstRaw.mGreen[pos] = mMax[pos];
			mDstRaw.mBlue[pos] = (int)(mMin[pos] + (aHue - 120.0) * (mMax[pos] - mMin[pos]) / 60.0);

		}else if(aHue < 240.0){

			mDstRaw.mRed[pos] = mMin[pos];
			mDstRaw.mGreen[pos] = (int)(mMax[pos] - (aHue - 180.0) * (mMax[pos] - mMin[pos]) / 60.0);
			mDstRaw.mBlue[pos] = mMax[pos];

		}else if(aHue < 300.0){

			mDstRaw.mRed[pos] = (int)(mMin[pos] + (aHue - 240.0) * (mMax[pos] - mMin[pos]) / 60.0);
			mDstRaw.mGreen[pos] = mMin[pos];
			mDstRaw.mBlue[pos] = mMax[pos];

		}else{

			mDstRaw.mRed[pos] = mMax[pos];
			mDstRaw.mGreen[pos] = mMin[pos];
			mDstRaw.mBlue[pos] = (int)(mMax[pos] - (aHue - 300.0) * (mMax[pos] - mMin[pos]) / 60.0);

		}
	}

	public mori.C_RawData mGetDstRaw(){
		return mDstRaw;
	}

	public void mCalcHue(
		int aRed,
		int aGreen,
		int aBlue,
		int pos
	){
		// êFëä[deg]
		double hue = Double.MAX_VALUE;

		// if the largest color is red,
		if(mLargest == E_RED){

			// if the second largest is green
			if(mSecondLargest == E_GREEN){

				// If the largest is as the same luminance as the smallest.
				if(aRed == aBlue){
					// nothing to do
				}else{

					hue = 60.0 * (aGreen - aBlue) / (aRed - aBlue);
				}

				mMax[pos] = aRed;
				mMin[pos] = aBlue;

			}else
			// The second largest is blue
			{

				// If the largest is as the same luminance as the smallest.
				if(aRed == aGreen){
					// nothing to do
				}else{

					hue = 60.0 * ( 5.0 + (aBlue - aGreen) / (aRed - aGreen) );
				}

				mMax[pos] = aRed;
				mMin[pos] = aGreen;
			}

		}else
		// if the largest color is green,
		if(mLargest == E_GREEN){

			// if the second largest is red
			if(mSecondLargest == E_RED){

				// If the largest is as the same luminance as the smallest.
				if(aGreen == aBlue){
					// nothing to do
				}else{
					hue = 60.0 * ( 1.0 + (aRed - aBlue) / (aGreen - aBlue) );
				}

				mMax[pos] = aGreen;
				mMin[pos] = aBlue;

			}else
			// the second largest is blue
			{

				// If the largest is as the same luminance as the smallest.
				if(aGreen == aRed){
					// nothing to do
				}else{
					hue = 60.0 * ( 2.0 + (aBlue - aRed) / (aGreen - aRed) );
				}

				mMax[pos] = aGreen;
				mMin[pos] = aRed;
			}
		}else
		// The largest color is blue.
		{

			// if the second largest is green
			if(mSecondLargest == E_GREEN){

				// If the largest is as the same luminance as the smallest.
				if(aBlue == aRed){
					// nothing to do
				}else{
					hue = 60.0 * ( 3.0 + (aGreen - aRed) / (aBlue - aRed) );
				}

				mMax[pos] = aBlue;
				mMin[pos] = aRed;

			}else
			// the second largest is red
			{

				// If the largest is as the same luminance as the smallest.
				if(aBlue == aGreen){
					// nothing to do
				}else{
					hue = 60.0 * ( 4.0 + (aRed - aGreen) / (aBlue - aGreen) );
				}

				mMax[pos] = aBlue;
				mMin[pos] = aGreen;
			}
		}

		mHue[pos] = (int)hue;
	}

	private void mRank(
		int aR,
		int aG,
		int aB
	){
		if(aR > aG){

			if(aR > aB){

				mLargest = E_RED;

				if(aG > aB){

					mSecondLargest = E_GREEN;
					mSmallest      = E_BLUE;
				}else{

					mSecondLargest = E_BLUE;
					mSmallest      = E_GREEN;
				}
			}else{

				mLargest       = E_BLUE;
				mSecondLargest = E_RED;
				mSmallest      = E_GREEN;
			}
		}else if(aG > aB){

			mLargest = E_GREEN;

			if(aR > aB){

				mSecondLargest = E_RED;
				mSmallest      = E_BLUE;
			}else{

				mSecondLargest = E_BLUE;
				mSmallest      = E_RED;
			}

		}else{

			mLargest = E_BLUE;

			if(aR > aG){

				mSecondLargest = E_RED;

			}else{

				mSecondLargest = E_GREEN;
			}
		}
	}

}

