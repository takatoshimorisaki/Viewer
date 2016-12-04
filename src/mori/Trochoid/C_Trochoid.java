package mori.Trochoid;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class C_Trochoid implements mori.Shape{
	private mori.Toda mToda = null;

	private Object mColorData;

	private Object mRed;

	private int mArgb = 0;

	private double mT = 0.0;

	private double mRamda = 400.0;

	private double mCoef = 2.0 * Math.PI / mRamda;

	private double mC;

	private double mR0 = 33.0;

	private double[] mMaxY = new double[WIDTH];

	private final static double mG = 9.8;

	private final static int WIDTH = 800;

	public C_Trochoid(mori.Toda in){
		this.mToda = in;

		mArgb = (new Color(0, 0, 255)).getRGB();

		mColorData = mToda.mO_ColorModel.getDataElements(mArgb, null);

		mArgb = (new Color(255, 0, 0)).getRGB();

		mRed = mToda.mO_ColorModel.getDataElements(mArgb, null);

		mC = mRamda * mG / (2.0 * Math.PI);
		mC = Math.sqrt(mC);

	}

	public void draw(){
		for(int xct = 0; xct < WIDTH; xct++){
			mMaxY[xct] = -10000.0;
		}

		double b = 0.0;
		double r = mR0 * Math.exp(2.0 * Math.PI * b / mRamda);

		for(double a = -mRamda; a < 800 + mRamda; a += 1.0){

			double theta = mCoef * (mC * mT - a);

			double x = a + r * Math.cos(theta);
			double y = b + r * Math.sin(theta);

			int xct = (int)x;
			int yct = (int)(300.0 + y);

			try{
				mToda.mO_Raster.setDataElements(xct, yct, mRed);
			}catch(Exception ex){
			}

			if(x >= 0 && x < 800.0){
				if(mMaxY[(int)x] < y){
					mMaxY[(int)x] = y;
				}
			}
		}

		for(int xct = 0; xct < WIDTH; xct++){
			try{
				int yct = 300 + (int)mMaxY[xct];
				mToda.mO_Raster.setDataElements(xct, yct, mColorData);
			}catch(Exception ex){
			}
		}

		try{
			Thread.sleep(100);
		}catch(Exception ex){
		}

		mT += 1.0;
	}
}


