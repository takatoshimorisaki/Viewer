package mori.Surface3;

public class C_Interpolator{

	private C_Canvas[] mCanvas;

	private int[] mFrame = new int[3];

	private double mC = 1.0;

	private double mH = 1.0;

	private double mDeltaT = 0.1;

	private double mCoef1;

	private double mCoef2;

	private int mCanvasWidth = 20;

	private int mCanvasHeight = 15;

	private int mSurfaceWidth;

	private int mSurfaceHeight;

	private double mRatioX;

	private double mRatioY;

	public C_Interpolator(
		int aCanvasWidth,
		int aCanvasHeight,
		int aSurfaceWidth,
		int aSurfaceHeight
	){
		mCanvasWidth = aCanvasWidth;
		mCanvasHeight = aCanvasHeight;

		mSurfaceWidth = aSurfaceWidth;
		mSurfaceHeight = aSurfaceHeight;

		mRatioX = (double)mCanvasWidth / (double)aSurfaceWidth;
		mRatioY = (double)mCanvasHeight / (double)aSurfaceHeight;

		mCanvas = new C_Canvas[3];
		mCanvas[0] = new C_Canvas(mCanvasWidth, mCanvasHeight);
		mCanvas[1] = mCanvas[0].mCopy();
		mCanvas[2] = mCanvas[1].mCopy();

		mFrame[0] = 0;
		mFrame[1] = 1;
		mFrame[2] = 2;

		mCoef1 = mC * mC * mDeltaT * mDeltaT / (mH * mH);

		mCoef2 = 2.0 - 4.0 * mCoef1;
	}

	public void mExe(){
		for(int yct = 0; yct < mCanvasHeight; yct++){
			for(int xct = 0; xct < mCanvasWidth; xct++){

				double left   = mCanvas[mFrame[1]].mGetZ(xct-1, yct);
				double right  = mCanvas[mFrame[1]].mGetZ(xct+1, yct);
				double top    = mCanvas[mFrame[1]].mGetZ(xct, yct-1);
				double bottom = mCanvas[mFrame[1]].mGetZ(xct, yct+1);

				double now = mCanvas[mFrame[1]].mGetZ(xct, yct);

				double prev = mCanvas[mFrame[2]].mGetZ(xct, yct);

				double next = mCoef1 * (left + right + top + bottom)
                            + mCoef2 * now
                            - prev;

				if(next > 255){
					next = 255;
				}else if(next < -255){
					next = -255;
				}

				mCanvas[mFrame[0]].mSetZ(xct, yct, next);
			}// for xct
		}// for yct

		int temp = mFrame[2];
		mFrame[2] = mFrame[1];
		mFrame[1] = mFrame[0];
		mFrame[0] = temp;
	}

	public void mSetZ(
		int aX,
		int aY,
		double aZ
	){
		if(aX >= 0 && aX < mSurfaceWidth
		&& aY >= 0 && aY < mSurfaceHeight){

			double xPos = mRatioX * (double)aX;
			double yPos = mRatioY * (double)aY;

			int minX = (int)xPos;
			int minY = (int)yPos;

			mCanvas[mFrame[0]].mSetZ(minX, minY, aZ);
			mCanvas[mFrame[1]].mSetZ(minX, minY, aZ);
			mCanvas[mFrame[2]].mSetZ(minX, minY, aZ);
		}
	}

	public double mGetZ(
		int aX,
		int aY
	){
		if(aX >= 0 && aX < mSurfaceWidth
		&& aY >= 0 && aY < mSurfaceHeight){

			double xPos = mRatioX * (double)aX;
			double yPos = mRatioY * (double)aY;

			int minX = (int)xPos;
			int maxX = minX + 1;
			int minY = (int)yPos;
			int maxY = minY + 1;

			double A = mCanvas[mFrame[0]].mGetZ(minX, minY);
			double B = mCanvas[mFrame[0]].mGetZ(maxX, minY);
			double C = mCanvas[mFrame[0]].mGetZ(minX, maxY);
			double D = mCanvas[mFrame[0]].mGetZ(maxX, maxY);

			double tx = xPos - (double)minX;
			double ty = yPos - (double)minY;

			double ans = (1.0 - ty) * ( A * (1.0 - tx) + B * tx )
                       +        ty  * ( C * (1.0 - tx) + D * tx );

			return ans;

		}else{
			return 0;
		}
	}

}

