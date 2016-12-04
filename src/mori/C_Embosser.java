package mori;

public class C_Embosser extends C_RawData implements I_ImageProcessor{

	public C_Embosser(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);
	}

	public C_Embosser(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		for(int yct = 1; yct < (mHeight - 1); yct++){
			for(int xct = 1; xct < (mWidth - 1); xct++){

				int pos = yct * mWidth + xct;

				dstRaw.mRed[pos] = 
                                 - mRed[(yct - 1) * mWidth + (xct - 1)]
                                 + mRed[pos] + 128;

				if(dstRaw.mRed[pos] < 0){
					dstRaw.mRed[pos] = 0;
				}else if(dstRaw.mRed[pos] > 255){
					dstRaw.mRed[pos] = 255;
				}
			}
		}

		for(int yct = 1; yct < (mHeight - 1); yct++){
			for(int xct = 1; xct < (mWidth - 1); xct++){

				int pos = yct * mWidth + xct;

				dstRaw.mGreen[pos] = 
                                 - mGreen[(yct - 1) * mWidth + (xct - 1)]
                                 + mGreen[pos] + 128;

				if(dstRaw.mGreen[pos] < 0){
					dstRaw.mGreen[pos] = 0;
				}else if(dstRaw.mGreen[pos] > 255){
					dstRaw.mGreen[pos] = 255;
				}
			}
		}

		for(int yct = 1; yct < (mHeight - 1); yct++){
			for(int xct = 1; xct < (mWidth - 1); xct++){

				int pos = yct * mWidth + xct;

				dstRaw.mBlue[pos] = 
                                 - mBlue[(yct - 1) * mWidth + (xct - 1)]
                                 + mBlue[pos] + 128;

				if(dstRaw.mBlue[pos] < 0){
					dstRaw.mBlue[pos] = 0;
				}else if(dstRaw.mBlue[pos] > 255){
					dstRaw.mBlue[pos] = 255;
				}
			}
		}

		return dstRaw;
	}
}

