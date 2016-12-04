package mori.ManyChord3;

import mori.C_RawData;
import mori.I_ImageProcessor;

public class C_ManyChord extends C_RawData implements I_ImageProcessor{

	private double mT = 0.0;

	private final static int CHORD_NUM = 5;

	private C_Psi[] mPsi;

	public C_ManyChord(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);

		mPsi = new C_Psi[CHORD_NUM];
	}

	public C_ManyChord(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		for(int cnt = 0; cnt < CHORD_NUM; cnt++){
			mPsi[cnt] = new C_Psi();
		}

		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){

				int lumi = 0;
				for(int cnt = 0; cnt < mPsi.length; cnt++){
					lumi += (int)mPsi[cnt].mExe((double)xct, (double)yct, mT);
				}

				if(xct == mWidth/2 && yct == mHeight/2){
					System.out.printf("lumi %d\n", lumi);
				}

				int pos = yct * mWidth + xct;

				if(lumi > 0){
					dstRaw.mRed[pos] = 175 + lumi;
					dstRaw.mGreen[pos] = 223 + lumi;
					dstRaw.mBlue[pos] = 228 + lumi;
				}else{
					dstRaw.mRed[pos] = 175;
					dstRaw.mGreen[pos] = 223;
					dstRaw.mBlue[pos] = 228 - lumi;
				}

				if(dstRaw.mRed[pos] > 255){
					dstRaw.mRed[pos] = 255;
				}else if(dstRaw.mRed[pos] < 0){
					dstRaw.mRed[pos] = 0;
				}

				if(dstRaw.mGreen[pos] > 255){
					dstRaw.mGreen[pos] = 255;
				}else if(dstRaw.mGreen[pos] < 0){
					dstRaw.mGreen[pos] = 0;
				}

				if(dstRaw.mBlue[pos] > 255){
					dstRaw.mBlue[pos] = 255;
				}else if(dstRaw.mBlue[pos] < 0){
					dstRaw.mBlue[pos] = 0;
				}

			}
		}

		mT += 1.0;

		return dstRaw;
	}
}

