package mori;

public class C_EdgeExtractor extends C_RawData implements I_ImageProcessor{

	public C_EdgeExtractor(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);
	}

	public C_EdgeExtractor(
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
                                 - mRed[(yct - 1) * mWidth +  xct]
                                 - mRed[(yct - 1) * mWidth + (xct + 1)]
                                 - mRed[pos - 1]
                                 + 8 * mRed[pos]
                                 - mRed[pos + 1]
                                 - mRed[(yct + 1) * mWidth + (xct - 1)]
                                 - mRed[(yct + 1) * mWidth +  xct]
                                 - mRed[(yct + 1) * mWidth + (xct + 1)];

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
                                 - mGreen[(yct - 1) * mWidth +  xct]
                                 - mGreen[(yct - 1) * mWidth + (xct + 1)]
                                 - mGreen[pos - 1]
                                 + 8 * mGreen[pos]
                                 - mGreen[pos + 1]
                                 - mGreen[(yct + 1) * mWidth + (xct - 1)]
                                 - mGreen[(yct + 1) * mWidth +  xct]
                                 - mGreen[(yct + 1) * mWidth + (xct + 1)];

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
                                 - mBlue[(yct - 1) * mWidth +  xct]
                                 - mBlue[(yct - 1) * mWidth + (xct + 1)]
                                 - mBlue[pos - 1]
                                 + 8 * mBlue[pos]
                                 - mBlue[pos + 1]
                                 - mBlue[(yct + 1) * mWidth + (xct - 1)]
                                 - mBlue[(yct + 1) * mWidth +  xct]
                                 - mBlue[(yct + 1) * mWidth + (xct + 1)];

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

