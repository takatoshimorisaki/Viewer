package mori.Trochoid5;

public class C_Trochoid{

	private double mOmega;

	private double mR;

	private double mDepth;

	private double mTheta;

	private double mCS;

	private double mSN;

	private double mCx;

	private double mCy;

	private double mDelta;

	private java.util.Random mRandom = new java.util.Random();

	private C_HC mPos = new C_HC();

	C_Trochoid(
		double aCycle,
		double aDepth,
		double aR,
		double aCx,
		double aCy
	){
		mOmega = 2.0 * Math.PI / aCycle;

		mDepth = aDepth;

		mR = aR;

		mCx = aCx;

		mCy = aCy;

		mDelta = 2.0 * Math.PI * mRandom.nextDouble();
	}

	public void mExe(
		double aT
	){
		double phase = mOmega * aT + mDelta;

		double offset =       mR * Math.sin(phase);
		double alt = mDepth - mR * Math.sin(phase);

		mTheta += 0.01 * (2.0 * mRandom.nextDouble() - 1.0);

		mCS = Math.cos(mTheta);
		mSN = Math.sin(mTheta);

		double x = offset * mCS + mCx;
		double y = offset * mSN + mCy;

		mPos.mX[0] = x;
		mPos.mX[1] = y;
		mPos.mX[2] = alt;

	}

	public void mGetPos(
		C_HC aPos
	){
		aPos.mX[0] = mPos.mX[0];
		aPos.mX[1] = mPos.mX[1];
		aPos.mX[2] = mPos.mX[2];
	}

}

