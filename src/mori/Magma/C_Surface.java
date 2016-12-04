package mori.Magma;

import mori.C_RawData;
import mori.I_ImageProcessor;

public class C_Surface extends C_RawData implements I_ImageProcessor{

	private java.util.Random mRandom = new java.util.Random();

	public C_Surface(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);
	}

	public C_Surface(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

/*!
	162, 55, 12
*/
	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		double posX = mWidth / 2;
		for(int yct = 0; yct < mHeight; yct++){
			posX += 0.5 * (2.0 * mRandom.nextDouble() - 1.0);

			for(double offset = -100.0; offset <= 100.0; offset += 1.0){
				double gain = Math.exp(-0.001 * offset * offset);

				dstRaw.mSetRGB(
					(int)(posX + offset),
					yct,
					(int)(gain * 162.0),
					(int)(gain * 55.0),
					(int)(gain * 12.0));
			}
		}

		return dstRaw;
	}
}

