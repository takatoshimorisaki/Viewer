package mori.ManyChord3;

public class C_Psi{

	private final static double WIDTH = 800;

	private final static double HEIGHT = 600;

	private double mA = 10.0;

	private double mCx;

	private double mCy;

	private double mRangeX;

	private double mRangeY;

	public C_Psi(){

		java.util.Random random = new java.util.Random();

		mCx = WIDTH * random.nextDouble();
		mCy = HEIGHT * random.nextDouble();

		mRangeX = 50 + (WIDTH - 50) * random.nextDouble();
		mRangeY = 50 + (HEIGHT - 50) * random.nextDouble();

		mA = 100 * random.nextDouble();
	}

	public double mExe(
		double aX,
		double aY,
		double aT
	){
		double phaseX = 2.0 * Math.PI * (aX - mCx) / mRangeX;
		double phaseY = 2.0 * Math.PI * (aY - mCy) / mRangeY;

		double ans = mA * Math.sin(phaseX) * Math.sin(phaseY);

		return ans;
	}

}


