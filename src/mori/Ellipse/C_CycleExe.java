package mori.Ellipse;

import mori.C_ImageProcessParameter;
import mori.C_RawData;
import mori.I_ImageProcessor;

public class C_CycleExe extends C_RawData implements I_ImageProcessor{

	private final static double mA = 200.0;

	private final static double mB = 100.0;

	private final static double mE2 = (mA * mA - mB * mB) / (mA * mA);

	private final static double mF = (mA - mB) / mA;

	private boolean mCalced = false;

	private mori.C_RawData mDstRaw;

	private Drawer mDrawer;

	private C2D mAttentionPoint = new C2D();
	
	private C2D mGeographicPoint = new C2D();
	
	private C2D mMysteryPoint = new C2D();
	
	private C2D mCG = new C2D(0.0, 0.0);
	
	//! 地理緯度[rad]
	private double mPhi;
	
	//! 地心緯度[deg]
	private double mTheta = Math.PI * 45.0 / 180.0;
	
	public C_CycleExe(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mDstRaw = new mori.C_RawData(mWidth, mHeight);

		mDrawer = new Drawer(mDstRaw);
	}

	public C_CycleExe(
		// 幅
		int aWidth,
		// 高さ
		int aHeight
	){
		super(aWidth, aHeight);
	}

	private void mDrawAxis(){

		double x0 = -0.5 * (double)mWidth;

		double y0 = 0.0;

		double x1 = -x0;

		double y1 = 0.0;

		mDrawer.mLine(x0, y0, x1, y1);

		x0 = 0.0;

		y0 = 0.5 * (double)mHeight;

		x1 = 0.0;

		y1 = -y0;

		mDrawer.mLine(x0, y0, x1, y1);
	}

	private void mDrawGeographicCenter(double theta){
		double sn = Math.sin(theta);
		
		double y = -(mA * mA - mB * mB) / mB;
		
		y = y * sn;
		
		mGeographicPoint.mX = 0.0;
		
		mGeographicPoint.mY = y;
		
		double radius = 5.0;
		
		mDrawer.mCircle(mGeographicPoint.mX, mGeographicPoint.mY, radius);
		
		mDrawer.mLine(mGeographicPoint.mX, mGeographicPoint.mY, mAttentionPoint.mX, mAttentionPoint.mY);

		mDrawer.mLine(mAttentionPoint.mX, mGeographicPoint.mY, mAttentionPoint.mX, mAttentionPoint.mY);

		mDrawer.mLine(mGeographicPoint.mX, mGeographicPoint.mY, mAttentionPoint.mX, mGeographicPoint.mY);
	}
		
	private void mCalcPhi(){
		mPhi = mAttentionPoint.mY / mAttentionPoint.mX;
		
		mPhi /= (1.0 - mE2);
		
		mPhi = Math.atan(mPhi);
	}

	private void mDrawAttention(double theta){

		mAttentionPoint.mX = mA * Math.cos(theta);
		
		mAttentionPoint.mY = mB * Math.cos(theta);
		
		mDrawer.mCircle(mAttentionPoint.mX, mAttentionPoint.mY, 5.0);
	}
	
	private void mDrawMystery(){
		C2D p = mAttentionPoint;
		
		double r = p.mX * p.mX + p.mY * p.mY;
		
		r = Math.sqrt(r);
		
		double y = 1.0 - mF + mE2 * mA / r;
		
		y = y * mAttentionPoint.mY;
		
		mMysteryPoint.mX = mAttentionPoint.mX;
		
		mMysteryPoint.mY = y;
		
		double radius = 5.0;
		
		mDrawer.mCircle(mMysteryPoint.mX, mMysteryPoint.mY, radius);
	}
	
	public C_RawData mExe(){

		if(mCalced == false){

			mDrawer.mClear();

			mDrawAxis();

			mDrawer.mEllipse(0.0, 0.0, mA, mB);

			mDrawer.mCircle(0.0, 0.0, mA);

			mDrawAttention(mTheta);

			mDrawGeographicCenter(mTheta);
			
			mDrawMystery();
			
			// 地理緯度を計算する。
			mCalcPhi();
			
			mCalced = true;
		}

		return mDstRaw;
	}

}

