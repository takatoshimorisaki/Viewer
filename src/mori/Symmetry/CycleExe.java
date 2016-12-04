package mori.Symmetry;

import mori.C_ImageProcessParameter;
import mori.C_RawData;
import mori.I_ImageProcessor;

public class CycleExe extends C_RawData implements I_ImageProcessor{

	private boolean mCalced = false;

	private mori.C_RawData mDstRaw;

	private Drawer mDrawer;
	
	private CRandom mRandom;
	
	private RefPoint mRefPoint;
	
	public CycleExe(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mDstRaw = new mori.C_RawData(mWidth, mHeight);

		mDrawer = new Drawer(mDstRaw);
		
		mRandom = new CRandom();
	}

	public CycleExe(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}
	
	public C_RawData mExe(){

		if(mCalced == false){
 
			mDrawer.mClear();
			
			Triangle tri = new Triangle(mDrawer);
			
			tri.mDraw();
			
			mRefPoint = new RefPoint(tri, mRandom, mDrawer);
			
			mRefPoint.mDraw();
			
			tri.mDrawAntiPoint(mRefPoint.mPos);
			
			mCalced = true;
		}

		return mDstRaw;
	}

}
