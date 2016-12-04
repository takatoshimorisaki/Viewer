package mori.Domino;

import static mori.E_RGB.*;

import mori.C_RawData;
import mori.C_ImageProcessParameter;
import mori.E_RGB;
import mori.I_ImageProcessor;

public class C_DominoRGB extends C_RawData implements I_ImageProcessor{

	private C_Pattern[] mPattern;

	private C_GrayScale mGrayScale = new C_GrayScale();

	private C_RawData mDstRaw;

	public C_DominoRGB(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mDstRaw = new C_RawData(mWidth, mHeight);

		mPattern = new C_Pattern[8];
		for(int cnt = 0; cnt < mPattern.length; cnt++){
			mPattern[cnt] = new C_Pattern(cnt);
		}
	}

	public C_DominoRGB(
		// •
		int aWidth,
		// ‚‚³
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
			mExe(E_RED);
			mExe(E_GREEN);
			mExe(E_BLUE);

			return mDstRaw;
	}

	public void mExe(E_RGB aColor){
		int[][] piese = new int[C_Pattern.HEIGHT][C_Pattern.WIDTH];

		int[] src = null;
		int[] dst = null;
		switch(aColor){
		case E_RED:
			src = mRed;
			dst = mDstRaw.mRed;
			break;
		case E_GREEN:
			src = mGreen;
			dst = mDstRaw.mGreen;
			break;
		case E_BLUE:
			src = mBlue;
			dst = mDstRaw.mBlue;
			break;
		}

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

						piese[py][px] = src[pos];
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

								dst[pos] = pattern[py][px];
							}// for py
						}// for px
					}// if min
				}// for pct
			}
		}

	}
}

