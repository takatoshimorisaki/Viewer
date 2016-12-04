package mori.InverseCircle;

public class C_Bounce{

	private double mMinX;

	private double mMaxX;

	private double mMinY;

	private double mMaxY;

	private C2D mPos = new C2D();

	private double mVx;
	private double mVy;
	private double mG = 9.8;
	private double mAbsVelX = 4.0;
	private double mAbsVelY = 4.0;

	private long mT = System.currentTimeMillis();

	public C_Bounce(
		int aWidth,
		int aHeight,
		int aRadius
	){
		mMinX = aRadius;

		mMaxX = aWidth - aRadius;

		mMinY = aRadius;

		mMaxY = aHeight - aRadius;

		mPos.mX = mMinX;

		mPos.mY = mMinY;

		mVx = mAbsVelX;
	}

	public void mExe(
		C2I aPos
	){
		double dT = (double)( System.currentTimeMillis() - mT ) * 0.001;
		mT = System.currentTimeMillis();

		mPos.mX = mPos.mX + mVx * dT;

		if(mPos.mX >= mMaxX){
			mVx = - mAbsVelX;

			mPos.mX = mMaxX - (mPos.mX - mMaxX);
		}else
		if(mPos.mX <= mMinX){
			mVx = mAbsVelX;

			mPos.mX = mMinX + (mMinX - mPos.mX);
		}

		mVy = mVy + mG * dT;

		if(mVy < 0.0 && mVy > - mAbsVelY){
			mVy = - mAbsVelY;
		}

		mPos.mY = mPos.mY + mVy * dT + 0.5 * mG * dT * dT;

		if(mPos.mY >= mMaxY){
			mVy = - mVy;

			mPos.mY = mMaxY - (mPos.mY - mMaxY);
		}else if(mPos.mY <= mMinY){
			mVy = 0.0;

			mPos.mY = mMinY;
		}

		aPos.mX = (int)mPos.mX;
		aPos.mY = (int)mPos.mY;
	}
}

