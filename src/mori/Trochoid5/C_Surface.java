package mori.Trochoid5;

import mori.C_RawData;
import mori.I_ImageProcessor;

public class C_Surface extends C_RawData implements I_ImageProcessor{

	private final static int TRIANGLE_NUM = 8;
	private final static int TROCHOID_NUM = 9;

	private C_Trochoid[] mTrochoid = new C_Trochoid[TROCHOID_NUM];

	private C_Triangle[] mTriangle = new C_Triangle[TRIANGLE_NUM];

	private double mT;

	private C_Value mAlt = new C_Value();

	private C_RawData mDstRaw;

	public C_Surface(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);

		for(int yct = 0; yct < 3; yct++){
			for(int xct = 0; xct < 3; xct++){

				int arrayNo = yct * 3 + xct;

				mTrochoid[arrayNo] = new C_Trochoid(
										2.0,
										155.0,
										100.0,
										(double)(xct * 400),
										(double)(yct * 300));
			}
		}

		for(int yct = 0; yct < 2; yct++){
			for(int xct = 0; xct < 2; xct++){

				int arrayNo = yct * 2 + xct;

				mTriangle[arrayNo] = new C_Triangle(
										mTrochoid[xct + yct * 3],
										mTrochoid[xct + 1 + yct * 3],
										mTrochoid[xct + 3 + yct * 3]);

				mTriangle[arrayNo + 4] = new C_Triangle(
										mTrochoid[xct + 1 + yct * 3],
										mTrochoid[xct + 3 + yct * 3],
										mTrochoid[xct + 4 + yct * 3]);
			}
		}

		mDstRaw = new C_RawData(mWidth, mHeight);
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

		for(int cnt = 0; cnt < TROCHOID_NUM; cnt++){
			mTrochoid[cnt].mExe(mT);
		}

		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){

				mAlt.mValue = 0.0;
				mAlt.mValid = false;
				double x = xct;
				double y = yct;
				for(int cnt = 0; cnt < TRIANGLE_NUM; cnt++){
					mTriangle[cnt].mGetAlt(x, y, mAlt);

					if(mAlt.mValid == true){
						break;
					}
				}

				int lumi = (int)mAlt.mValue;

				if(lumi > 255){
					lumi = 255;
				}else if(lumi < 0){
					lumi = 0;
				}

				mDstRaw.mSetRGB(xct, yct, lumi, 255, 255);
			}
		}

		mT += 0.1;

		return mDstRaw;
	}
}

