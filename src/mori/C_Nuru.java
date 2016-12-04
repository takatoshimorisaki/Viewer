package mori;

public class C_Nuru extends C_RawData implements I_ImageProcessor{

	public C_Nuru(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);
	}

	public C_Nuru(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		for(int pos = 0; pos < (mWidth * mHeight); pos++){

			if(mRed[pos] > 200 || mGreen[pos] > 200 || mBlue[pos] > 200){

				dstRaw.mRed[pos] = 255;
				dstRaw.mGreen[pos] = 255;
				dstRaw.mBlue[pos] = 255;
			}else{

				dstRaw.mRed[pos] = mRed[pos];
				dstRaw.mGreen[pos] = mGreen[pos];
				dstRaw.mBlue[pos] = mBlue[pos];
			}
		}

		return dstRaw;
	}

}

