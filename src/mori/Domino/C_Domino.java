package mori.Domino;

import mori.C_RawData;
import mori.I_ImageProcessor;
import mori.C_ImageProcessParameter;

public class C_Domino extends C_RawData implements I_ImageProcessor{

	private C_Pattern[] mPattern;

	private C_GrayScale mGrayScale = new C_GrayScale();

	public C_Domino(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mPattern = new C_Pattern[8];
		for(int cnt = 0; cnt < mPattern.length; cnt++){
			mPattern[cnt] = new C_Pattern(cnt);
		}
	}

	public C_Domino(
		// •
		int aWidth,
		// ‚‚³
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);
		int[][] piese = new int[C_Pattern.HEIGHT][C_Pattern.WIDTH];

		for(int yct = 0; yct < mHeight; yct += C_Pattern.HEIGHT){
			for(int xct = 0; xct < mWidth; xct += C_Pattern.WIDTH){

				for(int py = 0; py < C_Pattern.HEIGHT; py++){

					if(yct + py >= mHeight){
						break;
					}

					for(int px = 0; px < C_Pattern.WIDTH; px++){

						if(xct + px >= mWidth){
							break;
						}

						int pos = (yct + py) * mWidth + (xct + px);

						piese[py][px] = mGrayScale.mExe(mRed[pos], mGreen[pos], mBlue[pos]);
					}
				}// for py

				double min = Double.MAX_VALUE;
				for(int pct = 0; pct < mPattern.length; pct++){
					double similarity = mPattern[pct].mCalcSimilarity(piese);
					if(min > similarity){
						min = similarity;
						int[][] pattern = mPattern[pct].mGetPattern();

						for(int py = 0; py < C_Pattern.HEIGHT; py++){

							if(yct + py >= mHeight){
								break;
							}

							for(int px = 0; px < C_Pattern.WIDTH; px++){

								if(xct + px >= mWidth){
									break;
								}

								int pos = (yct + py) * mWidth + (xct + px);

								dstRaw.mRed[pos]   = pattern[py][px];
								dstRaw.mGreen[pos] = pattern[py][px];
								dstRaw.mBlue[pos]  = pattern[py][px];
							}// for py
						}// for px
					}// if min
				}// for pct
			}
		}

		return dstRaw;
	}
}

