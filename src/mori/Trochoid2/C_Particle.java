package mori.Trochoid2;

public class C_Particle{
	private double mRamda = 400.0;

	private double mCoef = 2.0 * Math.PI / mRamda;

	private double mC;

	private final static double mR0 = 33.0;

	private double mR;

	private double mA;

	private double mB;

	private double[] mMaxY = new double[WIDTH];

	private final static double mG = 9.8;

	private final static int WIDTH = 800;

	private C_Vertex mVertex = new C_Vertex();

	public C_Particle(
		double aA,
		double aB
	){
		mA = aA;

		mB = aB;

		mR = mR0 * Math.exp(2.0 * Math.PI * mB / mRamda);

		mC = mRamda * mG / (2.0 * Math.PI);
		mC = Math.sqrt(mC);

	}

	public void mExe(double aT){

		double theta = mCoef * (mC * aT - mA);

		mVertex.mX = mA + mR * Math.cos(theta);
		mVertex.mY = mB + mR * Math.sin(theta);
	}

	public void mGetVertex(
		C_Vertex aVertex
	){
		aVertex.mX = mVertex.mX;
		aVertex.mY = mVertex.mY;
	}
}


