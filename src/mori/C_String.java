
package mori;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class C_String implements Shape{
	private Toda mToda = null;

	private Object mColorData = null;

	private int mArgb = 0;

	private double mT = 0.0;

	private double mD = 100.0;

	private double mA = 80.0;

	private double mV = 10.0;

	private double mCoef1;

	private double mCoef2;

	public C_String(Toda in){
		this.mToda = in;

		mArgb = (new Color(0, 0, 0)).getRGB();

		mColorData = mToda.mO_ColorModel.getDataElements(mArgb, null);

		mCoef1 = 8.0 * mD / (Math.PI * Math.PI);

		mCoef2 = 1.0 / 9.0;
	}

	private double mPsi(
		double aX
	){
		double pivt = Math.PI * mV * mT / mA;

		double pix = Math.PI * aX / mA;

		double ans = mCoef1 * ( Math.cos(pivt) * Math.sin(pix) - mCoef2 * Math.cos(3.0 * pivt) * Math.sin(3.0 * pix) );

		return ans;
	}

	public void draw(){

		for(double x = 0; x < 800; x += 1.0){
			double y = 300 - mPsi(x);
			mToda.mO_Raster.setDataElements((int)x, (int)y, mColorData);
		}
		
		mT += 1.0;
	}
}


