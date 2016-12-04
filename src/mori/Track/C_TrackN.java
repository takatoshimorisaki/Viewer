
package mori.Track;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class C_TrackN implements mori.Shape{
	private mori.Toda mToda = null;

	private Object mBlack = null;

	private Object[] mColor = new Object[NUM_OF_BUGS];

	private double mRadius = 150.0;

	private double mDeltaT = 0.1;

	private double mCx = 400.0;

	private double mCy = 300.0;

	private double mDeltaRad = 2.0 * Math.PI / (double)NUM_OF_BUGS;

	private C_Bug[] mBug = new C_Bug[NUM_OF_BUGS];

	private final static int NUM_OF_BUGS = 24;

	private C_Hue mHue = new C_Hue();

	public C_TrackN(mori.Toda in){
		this.mToda = in;

		int argb = (new Color(0, 0, 0)).getRGB();
		mBlack = mToda.mO_ColorModel.getDataElements(argb, null);

		for(int cnt = 0; cnt < mBug.length; cnt++){

			argb = mHue.mGetRGB( (double)cnt * mDeltaRad );

			mColor[cnt] = mToda.mO_ColorModel.getDataElements(argb, null);
		}

	}

	public void draw(){
		double elapsed = 0.0;

		for(int cnt = 0; cnt < mBug.length; cnt++){

			double initX = mCx + mRadius * Math.cos( (double)cnt * mDeltaRad );
			double initY = mCy - mRadius * Math.sin( (double)cnt * mDeltaRad );

			mBug[cnt] = new C_Bug(
							initX,
							initY,
							mDeltaT);
		}

		while(elapsed < 2000.0){

			double[] tgtX = new double[NUM_OF_BUGS], tgtY = new double[NUM_OF_BUGS];
	
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

			elapsed += mDeltaT;
		}
	}

}


