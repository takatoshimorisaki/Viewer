package mori.Kuratowski;

import mori.C_ImageProcessParameter;
import mori.C_RawData;
import mori.I_ImageProcessor;

public class CycleExe extends C_RawData implements I_ImageProcessor{

	private boolean mCalced = false;

	private mori.C_RawData mDstRaw;

	private Drawer mDrawer;
	
	private CRandom mRandom;
	
	private VertexManager mVertexManager;
	
	private EdgeManager mEdgeManager;
	
	private GraphManager mGraphManager;
	
	public CycleExe(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mDstRaw = new mori.C_RawData(mWidth, mHeight);

		mDrawer = new Drawer(mDstRaw);
		
		mRandom = new CRandom();
	
		mVertexManager = new VertexManager(mRandom, mDrawer);

		mEdgeManager = new EdgeManager();
		
		mGraphManager = new GraphManager(mVertexManager, mEdgeManager, mDrawer);
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

			mVertexManager.mRelocate();
			
			mVertexManager.mDraw();
			
			mGraphManager.mDraw();
			
			mCalced = !mGraphManager.mCheck();
			
		}

		return mDstRaw;
	}

}

