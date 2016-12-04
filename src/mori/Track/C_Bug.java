package mori.Track;

public class C_Bug{

	private double mX;

	private double mY;

	private double mVx;

	private double mVy;

	private double mVx1;

	private double mVy1;

	private double mAx;

	private double mAy;

	private double mDeltaT;

	private double mDeltaT2;

	private final static double mThreshold = 1.0;

	public C_Bug(
		double aX,
		double aY,
		double aDeltaT
	){
		mX = aX;
		mY = aY;
		mDeltaT = aDeltaT;
		mDeltaT2 = mDeltaT * mDeltaT;
	}

	public void mSimulate(
		double aTgtX,
		double aTgtY
	){
		double diffX = aTgtX - mX;
		double diffY = aTgtY - mY;

		double dtg = diffX * diffX + diffY * diffY;
		dtg = Math.sqrt(dtg);

		if(dtg <= mThreshold){
			// ‰½‚à‚µ‚È‚¢
		}else{
			mVx = diffX / dtg;
			mVy = diffY / dtg;

			mX = mX + mVx * mDeltaT + 0.5 * mAx * mDeltaT2;
			mY = mY + mVy * mDeltaT + 0.5 * mAy * mDeltaT2;

			mAx = (mVx - mVx1) / mDeltaT;
			mAy = (mVy - mVy1) / mDeltaT;

			mVx1 = mVx;
			mVy1 = mVy;
		}

	}

	public double mGetX(){
		return mX;
	}

	public double mGetY(){
		return mY;
	}

}

