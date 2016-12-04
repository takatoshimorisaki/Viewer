package mori.Lens;

public class C_Lens{

	private C2D mCenter = new C2D();;

	private int mL;

	private double mR;

	/*! ã¸ê‹ó¶
	    ãÛãC	1.0
		êÖ		1.3
		É_ÉCÉÑÉÇÉìÉh	2.4
	*/
	private double mN1 = 1.3;
	private double mN2 = 1.0;

	private final static double mCst0 = 0.5 * Math.sqrt(3.0);

	public C_Lens(){
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
		double theta1 = Math.asin(diff / mR);
		double theta2 = Math.asin( mN1 * diff / (mN2 * mR) );
		double eta = mR * (Math.cos(theta1) - mCst0) * Math.tan(theta2);

		// ëÊÇPè€å¿
		if(x >= mCenter.mX && y >= mCenter.mY){
			double cs = diffX / diff;
			double sn = diffY / diff;

			x = x - eta * cs;
			y = y - eta * sn;
		}else
		// ëÊÇQè€å¿
		if(x < mCenter.mX && y >= mCenter.mY){
			double cs = - diffX / diff;
			double sn =   diffY / diff;

			x = x + eta * cs;
			y = y - eta * sn;
		}else
		// ëÊÇRè€å¿
		if(x < mCenter.mX && y < mCenter.mY){
			double cs = - diffX / diff;
			double sn = - diffY / diff;

			x = x + eta * cs;
			y = y + eta * sn;
		}else
		// ëÊÇSè€å¿
		if(x >= mCenter.mX && y < mCenter.mY){
			double cs =   diffX / diff;
			double sn = - diffY / diff;

			x = x - eta * cs;
			y = y + eta * sn;
		}

		aPos.mX = (int)x;
		aPos.mY = (int)y;
	}

	public void mSetForm(
		C2I aCenter,
		int aL
	){
		mCenter.mX = (double)aCenter.mX;
		mCenter.mY = (double)aCenter.mY;

		mL = aL;

		mR = 2.0 * mL;
	}

}

