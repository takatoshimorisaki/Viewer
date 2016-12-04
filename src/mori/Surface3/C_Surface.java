package mori.Surface3;

import mori.C_RawData;
import mori.I_ImageProcessor;

public class C_Surface extends C_RawData implements I_ImageProcessor{

	private C_Interpolator mScreen;

	public C_Surface(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);

		mScreen = new C_Interpolator(20, 15, 800, 600);

		for(int yct = 0; yct < 100; yct++){
			for(int xct = 0; xct < 100; xct++){

				mScreen.mSetZ(xct, yct, 255.0);
			}
		}
	}

	public C_Surface(
		// •
		int aWidth,
		// ‚‚³
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){

		mScreen.mExe();

		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){

				double next = mScreen.mGetZ(xct, yct);

				int lumi = (int)next;

				int pos = mWidth * yct + xct;

				if(next <= 0){
					dstRaw.mRed[pos]   = 0;
					dstRaw.mGreen[pos] = -lumi;
					dstRaw.mBlue[pos]  = 255;
				}else if(next > 0){
					dstRaw.mRed[pos]   = lumi;
					dstRaw.mGreen[pos] = lumi;
					dstRaw.mBlue[pos]  = 255;
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

		return dstRaw;
	}
}

