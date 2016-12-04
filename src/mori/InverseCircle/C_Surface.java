package mori.InverseCircle;

import mori.C_RawData;
import mori.I_ImageProcessor;

public class C_Surface extends C_RawData implements I_ImageProcessor{

	private C_RawData mDstRaw;

	//! Center of Lens
	private C2I mCenter = new C2I();

	//! Radius of Lens
	private int mL;

	//! Lens Ball
	private C_Bounce mBall;
	private C_InverseCircle mLens = new C_InverseCircle();

	private C2I mLensPos = new C2I();

	private mori.C_RGB DstRGB = new mori.C_RGB();

	public C_Surface(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);

		mDstRaw = new C_RawData(mWidth, mHeight);

		mL = mHeight / 6;

		mBall = new C_Bounce(mWidth, mHeight, mL);
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

		mBall.mExe(mCenter);

		mLens.mSetForm(mCenter, mL);

		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){
	
				mLens.mExe(xct, yct, mLensPos);

				boolean which = this.mGetRGB(mLensPos.mX, mLensPos.mY, DstRGB);

				int pos = yct * mWidth + xct;

				if(which == true){
					mDstRaw.mRed[pos] = DstRGB.Red;
					mDstRaw.mGreen[pos] = DstRGB.Green;
					mDstRaw.mBlue[pos] = DstRGB.Blue;
				}else{
					mDstRaw.mRed[pos] = mRed[pos];
					mDstRaw.mGreen[pos] = mGreen[pos];
					mDstRaw.mBlue[pos] = mBlue[pos];
				}

			}
		}

		return mDstRaw;
	}
}

