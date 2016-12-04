package mori;

import static mori.E_RGB.*;

public class C_HueTransformer extends C_RawData implements I_ImageProcessor{

	private E_RGB mLargest;

	private E_RGB mSecondLargest;

	private E_RGB mSmallest;

	private double mPhase;

	private int mMax;

	private int mMin;

	public C_HueTransformer(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);
	}

	public C_HueTransformer(
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

		// êFëä[deg]
		double hue = Double.MAX_VALUE;

		for(int pos = 0; pos < size; pos++){

			mRank(mRed[pos], mGreen[pos], mBlue[pos]);

			hue = mCalcHue(mRed[pos], mGreen[pos], mBlue[pos]);

			if(hue == Double.MAX_VALUE){

				dstRaw.mRed[pos] = mRed[pos];
				dstRaw.mGreen[pos] = mGreen[pos];
				dstRaw.mBlue[pos] = mBlue[pos];
			}else{

				if(hue < 0.0){
					hue = 0.0;
				}

				hue += mPhase;

				hue = (int)hue % 360;

				if(hue >= 0.0 && hue < 60.0){

					dstRaw.mRed[pos] = mMax;
					dstRaw.mGreen[pos] = (int)(mMin + hue * (mMax - mMin) / 60.0);
					dstRaw.mBlue[pos] = mMin;

				}else if(hue < 120.0){

					dstRaw.mRed[pos] = (int)(mMax - (hue - 60.0) * (mMax - mMin) / 60.0);
					dstRaw.mGreen[pos] = mMax;
					dstRaw.mBlue[pos] = mMin;

				}else if(hue < 180.0){

					dstRaw.mRed[pos] = mMin;
					dstRaw.mGreen[pos] = mMax;
					dstRaw.mBlue[pos] = (int)(mMin + (hue - 120.0) * (mMax - mMin) / 60.0);

				}else if(hue < 240.0){

					dstRaw.mRed[pos] = mMin;
					dstRaw.mGreen[pos] = (int)(mMax - (hue - 180.0) * (mMax - mMin) / 60.0);
					dstRaw.mBlue[pos] = mMax;

				}else if(hue < 300.0){

					dstRaw.mRed[pos] = (int)(mMin + (hue - 240.0) * (mMax - mMin) / 60.0);
					dstRaw.mGreen[pos] = mMin;
					dstRaw.mBlue[pos] = mMax;

				}else{

					dstRaw.mRed[pos] = mMax;
					dstRaw.mGreen[pos] = mMin;
					dstRaw.mBlue[pos] = (int)(mMax - (hue - 300.0) * (mMax - mMin) / 60.0);

				}
			}
		}

		mPhase += 1.0;

		return dstRaw;
	}

	public double mCalcHue(
		int aRed,
		int aGreen,
		int aBlue
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

				mMax= aRed;
				mMin = aBlue;

			}else
			// The second largest is blue
			{

				// If the largest is as the same luminance as the smallest.
				if(aRed == aGreen){
					// nothing to do
				}else{

					hue = 60.0 * ( 5.0 + (aBlue - aGreen) / (aRed - aGreen) );
				}

				mMax = aRed;
				mMin = aGreen;
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

				mMax = aGreen;
				mMin = aBlue;

			}else
			// the second largest is blue
			{

				// If the largest is as the same luminance as the smallest.
				if(aGreen == aRed){
					// nothing to do
				}else{
					hue = 60.0 * ( 2.0 + (aBlue - aRed) / (aGreen - aRed) );
				}

				mMax = aGreen;
				mMin = aRed;
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

				mMax = aBlue;
				mMin = aRed;

			}else
			// the second largest is red
			{

				// If the largest is as the same luminance as the smallest.
				if(aBlue == aGreen){
					// nothing to do
				}else{
					hue = 60.0 * ( 4.0 + (aRed - aGreen) / (aBlue - aGreen) );
				}

				mMax = aBlue;
				mMin = aGreen;
			}
		}

		return hue;
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

