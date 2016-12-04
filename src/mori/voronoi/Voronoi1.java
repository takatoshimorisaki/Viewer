package mori.voronoi;

public class Voronoi1 {

	public C2D[] mV;
	
	private Grid[][] mGrid;

	private VertexManager mVertexManager;
	
	private GridManager mGridManager;
	
	private Drawer mDrawer;
	
	public Voronoi1(
			VertexManager aVertexManager,
			GridManager aGridManager,
			Drawer aDrawer
			){
		
		mVertexManager = aVertexManager;
		
		mGridManager = aGridManager;
		
		mDrawer = aDrawer;
	}
	
	private void mCalcNearest(){
		double minLength = Double.MAX_VALUE;
		double length = 0.0;
		int nearestId = -1;

		mV = mVertexManager.mGetVertex();
		
		mGrid = mGridManager.mGetGrid();
		
		// ç≈ãﬂñTÇÃIDÇë{Ç∑ÅB
		for(int yct = 0; yct < mGrid.length; yct++){
			for(int xct = 0; xct < mGrid[yct].length; xct++){
				
				minLength = Double.MAX_VALUE;
				nearestId = -1;
				
				for(int vct = 0; vct < mV.length; vct++){
					
					length = mGrid[yct][xct].mCalcLength(mV[vct]);
					
					if(length < minLength){
						
						minLength = length;
						
						nearestId = vct;
					}
				}// for vct
				
				mGrid[yct][xct].mNearestVertexId = nearestId;
			}
		}

		for(int yct = 0; yct < mGrid.length; yct++){
			
			// âEó◊Ç∆î‰ärÇ∑ÇÈÅB
			if(mGrid[yct][0].mNearestVertexId != mGrid[yct][1].mNearestVertexId){
				
				mGrid[yct][0].mChanged = true;
			}
			
			for(int xct = 1; xct < mGrid[yct].length; xct++){

				// ç∂ó◊Ç∆î‰ärÇ∑ÇÈÅB
				if(mGrid[yct][xct].mNearestVertexId != mGrid[yct][xct - 1].mNearestVertexId){
					
					mGrid[yct][xct].mChanged = true;
				}
			}// for xct
		}// for yct
		
		for(int xct = 0; xct < mGrid[0].length; xct++){

			// â∫Ç∆î‰ärÇ∑ÇÈÅB
			if(mGrid[0][xct].mNearestVertexId != mGrid[1][xct].mNearestVertexId){
				
				mGrid[0][xct].mChanged = true;
			}
			
			// è„Ç∆î‰ärÇ∑ÇÈÅB
			for(int yct = 1; yct < mGrid.length; yct++){

				if(mGrid[yct][xct].mNearestVertexId != mGrid[yct-1][xct].mNearestVertexId){

					mGrid[yct][xct].mChanged = true;
				}
			}// for yct
		}// for xct
	}

	private void mDraw(){

		for(int yct = 0; yct < mGrid.length; yct++){
			for(int xct = 0; xct < mGrid[yct].length; xct++){
				
				if(mGrid[yct][xct].mChanged){
					
					mDrawer.mCircle(mGrid[yct][xct].mX, mGrid[yct][xct].mY, 3.0, 0, 255, 0);
				}
			}
		}
	}

	private void mDrawLine(){

		for(int yct = 1; yct < mGrid.length-1; yct++){
			for(int xct = 1; xct < mGrid[yct].length-1; xct++){
		
				// ç∂è„
				if(mGrid[yct-1][xct-1].mChanged && mGrid[yct][xct].mChanged){

					mDrawer.mLine((C2D)mGrid[yct-1][xct-1], (C2D)mGrid[yct][xct], 0, 255, 0);
					
				}else
				// è„
				if(mGrid[yct-1][xct].mChanged && mGrid[yct][xct].mChanged){
					
					mDrawer.mLine((C2D)mGrid[yct-1][xct], (C2D)mGrid[yct][xct], 0, 255, 0);
				}

				// âEè„
				if(mGrid[yct-1][xct+1].mChanged && mGrid[yct][xct].mChanged){

					mDrawer.mLine((C2D)mGrid[yct-1][xct+1], (C2D)mGrid[yct][xct], 0, 255, 0);
					
				}
				
				// ç∂
				if(mGrid[yct][xct-1].mChanged && mGrid[yct][xct].mChanged){
					
					mDrawer.mLine((C2D)mGrid[yct][xct-1], (C2D)mGrid[yct][xct], 0, 255, 0);
				}

				// âE
				if(mGrid[yct][xct+1].mChanged && mGrid[yct][xct].mChanged){
					
					mDrawer.mLine((C2D)mGrid[yct][xct+1], (C2D)mGrid[yct][xct], 0, 255, 0);
				}
			}
		}
	}
	
	public void mExe(){
		mCalcNearest();
		
		mDraw();
		
		mDrawLine();
	}
}
