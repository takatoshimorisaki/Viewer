package mori;

public class C_ImageProcessParameter{

	//! 画像処理方法
	public E_IMAGE_PROCESS_METHOD mMethod;

	//! X方向拡大率
	public double mRatioX;

	//! Y方向拡大率
	public double mRatioY;

	//! 赤の階調度
	public int mRedGradation;

	//! 緑の階調度
	public int mGreenGradation;

	//! 青の階調度
	public int mBlueGradation;

	//! 赤の交換色
	public String mSwapRed;

	//! 緑の交換色
	public String mSwapGreen;

	//! 青の交換色
	public String mSwapBlue;

	//! FOG DENSITY
	public double mFogDensity;

	//! FOG RANGE
	public int mFogRange;

	public int mCategoryNum = 3;

	public int mDenominator = 3;
}

