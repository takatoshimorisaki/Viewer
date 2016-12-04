package mori.Surface;

public class C_Canvas{

	private int mWidth;

	private int mHeight;

	private double[][] mZ;

	public C_Canvas(){
	}

	public C_Canvas(
		int aWidth,
		int aHeight
	){
		mWidth = aWidth;
		mHeight = aHeight;

		mZ = new double[mWidth][mHeight];

		java.util.Random random = new java.util.Random();
		double lumi = 200;
		for(int yct = 0; yct < mHeight/20; yct++){
			for(int xct = 0; xct < mWidth/20; xct++){

				mZ[xct][yct] = lumi;
			}
		}

	}

	public C_Canvas mCopy(){
		C_Canvas canvas = new C_Canvas();
		canvas.mWidth = mWidth;
		canvas.mHeight = mHeight;

		canvas.mZ = new double[mWidth][mHeight];
		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){
				canvas.mZ[xct][yct] = mZ[xct][yct];
			}
		}

		return canvas;
	}

	public double mGetZ(
		int aX,
		int aY
	){
		if(aX >= 0 && aX < mWidth
		&& aY >= 0 && aY < mHeight){

			return mZ[aX][aY];

		}else{

			return 0;
		}
	}

	public void mSetZ(
		int aX,
		int aY,
		double aZ
	){
		if(aX >= 0 && aX < mWidth
		&& aY >= 0 && aY < mHeight){

			mZ[aX][aY] = aZ;
		}
	}
}

