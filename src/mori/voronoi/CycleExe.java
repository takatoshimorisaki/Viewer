package mori.voronoi;

import mori.C_ImageProcessParameter;
import mori.C_RawData;
import mori.I_ImageProcessor;

public class CycleExe extends C_RawData implements I_ImageProcessor{

	private boolean mCalced = false;

	private mori.C_RawData mDstRaw;

	private Drawer mDrawer;
	
	private CRandom mRandom;
	
	private VertexManager mVertexManager;
	
	private GridManager mGridManager;
	
	private Voronoi0 mVoronoi0;

	private Voronoi1 mVoronoi1;
	
	private Voronoi2 mVoronoi2;
	
	private VoronoiMidLine mVoronoiMidLine;
	
	private boolean mGridCheck = false;
	
	private boolean mMethod0 = true;

	private boolean mMethod1 = false;

	private boolean mMethod2 = false;

	private boolean mMethod3 = false;
	
	public CycleExe(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mDstRaw = new mori.C_RawData(mWidth, mHeight);

		mDrawer = new Drawer(mDstRaw);
		
		mRandom = new CRandom();
	
		mVertexManager = new VertexManager(mRandom, mDrawer);
		
		mGridManager = new GridManager(mVertexManager, mDrawer);
		
		mVoronoi0 = new Voronoi0(mVertexManager, mDrawer);

		mVoronoi1 = new Voronoi1(mVertexManager, mGridManager, mDrawer);
		
		mVoronoi2 = new Voronoi2(mVertexManager, mGridManager, mDrawer);
		
		mVoronoiMidLine = new VoronoiMidLine(mVertexManager, mDrawer);
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

			mVertexManager.mDraw();
			
			if(mGridCheck){
				mGridManager.mExe();
			}
			
			if(mMethod0){
				mVoronoi0.mExe();
			}
			
			if(mMethod1){
				mVoronoi1.mExe();
			}
			
			if(mMethod2){
				mVoronoi2.mExe();
			}

			if(mMethod3){
				mVoronoiMidLine.mExe();
			}
			
			mCalced = true;
		}

		return mDstRaw;
	}

}

