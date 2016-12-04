package mori.Lens;

import mori.C_RawData;
import mori.I_ImageProcessor;

public class C_Surface extends C_RawData implements I_ImageProcessor{

	private C_RawData mDstRaw;

	//! Center of Lens
	private C2I mCenter = new C2I();

	//! Radius of Lens
	private int mL;

	//! Determin if a position in the Lens
	private int mDetermine;

	//! Lens Ball
	private C_Bounce mBall;
	private C_Lens   mLens = new C_Lens();

	private C2I mLensPos = new C2I();

	public C_Surface(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);

		mDstRaw = new C_RawData(mWidth, mHeight);

		mL = mHeight / 3;

		mBall = new C_Bounce(mWidth, mHeight, mL);

		mDetermine = mL * mL;
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
				int diffX = xct - mCenter.mX;
				int diffY = yct - mCenter.mY;
				int radius = diffX * diffX + diffY * diffY;

				if(radius <= mDetermine){
					mLens.mExe(xct, yct, mLensPos);
					int pos = yct * mWidth + xct;
					int srcPos = mLensPos.mY * mWidth + mLensPos.mX;

					mDstRaw.mRed[pos] = mRed[srcPos];
					mDstRaw.mGreen[pos] = mGreen[srcPos];
					mDstRaw.mBlue[pos] = mBlue[srcPos];
				}else{
					int pos = yct * mWidth + xct;

					mDstRaw.mRed[pos] = mRed[pos];
					mDstRaw.mGreen[pos] = mGreen[pos];
					mDstRaw.mBlue[pos] = mBlue[pos];
				}
			}
		}

		return mDstRaw;
	}
}

