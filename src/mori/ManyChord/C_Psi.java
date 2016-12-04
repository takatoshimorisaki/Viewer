package mori.ManyChord;

public class C_Psi{

	private final static double WIDTH = 800;

	private final static double HEIGHT = 600;

	private double mA = 10.0;

	private double mCx;

	private double mCy;

	private double mAlphaX;

	private double mAlphaY;

	public C_Psi(){

		java.util.Random random = new java.util.Random();

		mCx = WIDTH * random.nextDouble();
		mCy = HEIGHT * random.nextDouble();

		mAlphaX = 0.1 * random.nextDouble();
		mAlphaY = 0.1 * random.nextDouble();

		mA = 100 * random.nextDouble();
	}

	public double mExe(
		double aX,
		double aY,
		double aT
	){
		double phaseX = - mAlphaX * Math.abs(aX - mCx);
		double phaseY = - mAlphaY * Math.abs(aY - mCy);

		double ans = mA * Math.exp(phaseX) * Math.exp(phaseY);

		return ans;
	}

}


