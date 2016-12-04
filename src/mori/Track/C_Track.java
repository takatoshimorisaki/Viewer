
package mori.Track;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class C_Track implements mori.Shape{
	private mori.Toda mToda = null;

	private Object mBlack = null;

	private Object[] mColor = new Object[4];

	private int mArgb = 0;

	private double mRadius = 100.0;

	private double mDeltaT = 0.1;

	private double mCx = 400.0;

	private double mCy = 300.0;

	private C_Bug[] mBug = new C_Bug[4];

	private boolean mDebug = true;

	public C_Track(mori.Toda in){
		this.mToda = in;

		mArgb = (new Color(0, 0, 0)).getRGB();
		mBlack = mToda.mO_ColorModel.getDataElements(mArgb, null);

		mArgb = (new Color(255, 0, 0)).getRGB();
		mColor[0] = mToda.mO_ColorModel.getDataElements(mArgb, null);

		mArgb = (new Color(0, 255, 0)).getRGB();
		mColor[1] = mToda.mO_ColorModel.getDataElements(mArgb, null);

		mArgb = (new Color(0, 0, 255)).getRGB();
		mColor[2] = mToda.mO_ColorModel.getDataElements(mArgb, null);

		mArgb = (new Color(255, 255, 0)).getRGB();
		mColor[3] = mToda.mO_ColorModel.getDataElements(mArgb, null);

	}

	public void draw(){
		double elapsed = 0.0;

		mBug[0] = new C_Bug(
						mCx - mRadius,
						mCy - mRadius,
						mDeltaT);

		mBug[1] = new C_Bug(
						mCx - mRadius,
						mCy + mRadius,
						mDeltaT);

		mBug[2] = new C_Bug(
						mCx + mRadius,
						mCy + mRadius,
						mDeltaT);

		mBug[3] = new C_Bug(
						mCx + mRadius,
						mCy - mRadius,
						mDeltaT);

		double theta0 = 3.0 * Math.PI / 4.0 + Math.log(2.0 * mRadius);
		double sqrt2 = Math.sqrt(2.0);

		while(elapsed < 200.0){

			double[] tgtX = new double[4], tgtY = new double[4];
	
			for(int cnt = 0; cnt < mBug.length; cnt++){
				tgtX[cnt] = mBug[cnt].mGetX();
				tgtY[cnt] = mBug[cnt].mGetY();
	
				mToda.mO_Raster.setDataElements((int)tgtX[cnt], (int)tgtY[cnt], mColor[cnt]);
			}
	
			for(int cnt = 0; cnt < mBug.length; cnt++){
				int tgtCnt = cnt + 1;
				if(tgtCnt >= mBug.length){
					tgtCnt = 0;
				}
	
				mBug[cnt].mSimulate(tgtX[tgtCnt], tgtY[tgtCnt]);
			}

			if(mDebug){
				double radius = mRadius - 0.5 * elapsed;
				radius *= sqrt2;
				double theta = theta0 - Math.log(2.0 * mRadius - elapsed);
	
				double x = mCx + radius * Math.cos(theta);
				double y = mCy - radius * Math.sin(theta);
	
				mToda.mO_Raster.setDataElements((int)x, (int)y, mBlack);
			}

			elapsed += mDeltaT;
		}
	}

}


