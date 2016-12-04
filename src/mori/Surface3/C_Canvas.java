package mori.Surface3;

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
			int indexX = aX;
			int indexY = aY;

			if(aX < 0){
				indexX = 0;
			}else if(aX >= mWidth){
				indexX = mWidth - 1;
			}

			if(aY < 0){
				indexY = 0;
			}else if(aY >= mHeight){
				indexY = mHeight - 1;
			}

			return mZ[indexX][indexY];
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

