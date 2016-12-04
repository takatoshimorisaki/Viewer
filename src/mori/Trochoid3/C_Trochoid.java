package mori.Trochoid3;

public class C_Trochoid{

	private double mOmega;

	private double mRamda;

	private double mA;

	private double mTheta;

	private double mCS;

	private double mSN;

	C_Trochoid(
		double aTheta,
		double aCycle,
		double aWaveLength,
		double aA
	){
		mTheta = aTheta;

		mCS = Math.cos(mTheta);

		mSN = Math.sin(mTheta);

		mOmega = 2.0 * Math.PI / aCycle;

		mRamda = 2.0 * Math.PI / aWaveLength;

		mA = aA;
	}

	public double mExe(
		double aT,
		double aX,
		double aY
	){
		double x = aX * mCS - aY * mSN;
		double y = aX * mSN + aY * mCS;

		double height = mA * Math.cos(mOmega * aT - mRamda * x);

		return height;
	}

}

