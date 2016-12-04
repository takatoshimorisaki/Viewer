package mori.Trochoid3;

import mori.C_RawData;
import mori.I_ImageProcessor;

public class C_Surface extends C_RawData implements I_ImageProcessor{

	private final static int TROCHOID_NUM = 30;

	private C_Trochoid[] mTrochoid = new C_Trochoid[TROCHOID_NUM];

	private double mT;

	public C_Surface(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);

		for(int cnt = 0; cnt < TROCHOID_NUM; cnt++){

			double theta = 2.0 * Math.PI * (10.0 * (double)cnt - 90.0) / 180.0;

			double cycle = 1.0 + (double)cnt;

			double waveLength = 100.0 + 100.0 * (double)cnt;

			double amp = 30.0;

			mTrochoid[cnt] = new C_Trochoid(
									theta,
									cycle,
									waveLength,
									amp);
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
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){

				double alt = 0.0;
				for(int cnt = 0; cnt < TROCHOID_NUM; cnt++){
					alt += mTrochoid[cnt].mExe(mT, (double)xct, (double)yct);
				}

				int lumi = (int)alt;

				if(lumi > 255){
					lumi = 255;
				}else if(lumi < -255){
					lumi = -255;
				}

				int pos = mWidth * yct + xct;

				if(lumi > 0){
					dstRaw.mRed[pos] = lumi;
					dstRaw.mGreen[pos] = lumi;
					dstRaw.mBlue[pos] = 255;
				}else{
					dstRaw.mRed[pos] = -lumi;
					dstRaw.mGreen[pos] = -lumi;
					dstRaw.mBlue[pos] = 255;
				}
			}
		}

		mT += 0.1;

		return dstRaw;
	}
}

