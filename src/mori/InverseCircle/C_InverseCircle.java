package mori.InverseCircle;

public class C_InverseCircle{

	private C2D mCenter = new C2D();;

	private double mR;

	private double mR2;

	public C_InverseCircle(){
	}

	public void mExe(
		int aX,
		int aY,
		C2I aPos
	){
		double x = (double)aX;
		double y = (double)aY;

		double diffX = x - mCenter.mX;
		double diffY = y - mCenter.mY;
		double diff  = diffX * diffX + diffY * diffY;
		       diff  = Math.sqrt(diff);

		if(diff > 1.0){
			double length = mR2 / diff;

			x = mCenter.mX + length * diffX / diff;
			y = mCenter.mY + length * diffY / diff;
		}

		aPos.mX = (int)x;
		aPos.mY = (int)y;
	}

	public void mSetForm(
		C2I aCenter,
		int aR
	){
		mCenter.mX = (double)aCenter.mX;
		mCenter.mY = (double)aCenter.mY;

		mR = (double)aR;

		mR2 = mR * mR;
	}

}

