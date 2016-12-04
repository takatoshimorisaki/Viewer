package mori;

public class C_RGBSwapper extends C_RawData implements I_ImageProcessor{

	private C_ImageProcessParameter mPrmtr;

	public C_RGBSwapper(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mPrmtr = aPrmtr;
	}

	public C_RGBSwapper(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		int[] swapper;

		if(mPrmtr.mSwapRed.equals("R")){
			swapper = mRed;
		}else if(mPrmtr.mSwapRed.equals("G")){
			swapper = mGreen;
		}else if(mPrmtr.mSwapRed.equals("B")){
			swapper = mBlue;
		}else{
			swapper = new int[mWidth * mHeight];
		}

		dstRaw.mRed = swapper.clone();

		if(mPrmtr.mSwapGreen.equals("R")){
			swapper = mRed;
		}else if(mPrmtr.mSwapGreen.equals("G")){
			swapper = mGreen;
		}else if(mPrmtr.mSwapRed.equals("B")){
			swapper = mBlue;
		}else{
			swapper = new int[mWidth * mHeight];
		}

		dstRaw.mGreen = swapper.clone();

		if(mPrmtr.mSwapBlue.equals("R")){
			swapper = mRed;
		}else if(mPrmtr.mSwapBlue.equals("G")){
			swapper = mGreen;
		}else if(mPrmtr.mSwapRed.equals("B")){
			swapper = mBlue;
		}else{
			swapper = new int[mWidth * mHeight];
		}

		dstRaw.mBlue = swapper.clone();

		return dstRaw;
	}
}

