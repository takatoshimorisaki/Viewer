package mori.voronoi;

import static java.lang.System.out;

public class GridManager {

	private Grid[][] mGrid;
	
	private C2I mSize = new C2I();
	
	private C2D[] mV;
	
	private C2D mInterval = new C2D();
	
	private Drawer mDrawer;
	
	public GridManager(
	    VertexManager aVertexManager,
	    Drawer aDrawer
	){
		mV = aVertexManager.mGetVertex();
		
		mDrawer = aDrawer;
	}
	
	private void mMakeGrid(){
		double lengthX = 0.0;
		double lengthY = 0.0;
		
		mInterval.mX = Double.MAX_VALUE;
		mInterval.mY = Double.MAX_VALUE;
		
		for(int one = 0; one < mV.length; one++){
			for(int ano = (one + 1); ano < mV.length; ano++){
				
				lengthX = Math.abs(mV[one].mX - mV[ano].mX);
				
				if(lengthX < mInterval.mX){
					
					mInterval.mX = lengthX;
				}
				
				lengthY = Math.abs(mV[one].mY - mV[ano].mY);

				if(lengthY < mInterval.mY){
					
					mInterval.mY = lengthY;
				}
			}
		}
		
		mInterval.mX = (double)(Math.floor(mInterval.mX)) - 1.0;
		
		mSize.mX = (int)(Prmtr.WIDTH / mInterval.mX) + 2;
		
		mSize.mY = (int)(Prmtr.HEIGHT / mInterval.mY) + 2;
		
		mGrid = new Grid[mSize.mY][mSize.mX];
		
		for(int yct = 0; yct < mSize.mY; yct++){
			for(int xct = 0; xct < mSize.mX; xct++){
				
				mGrid[yct][xct] = new Grid();
				
				mGrid[yct][xct].mX = Prmtr.MIN_X + mInterval.mX * (double)xct;

				mGrid[yct][xct].mY = Prmtr.MAX_Y - mInterval.mY * (double)yct;
				
			}
		}
	}

	private void mDraw(){

		for(int yct = 0; yct < mSize.mY; yct++){
			for(int xct = 0; xct < mSize.mX; xct++){
				
				mDrawer.mCircle(mGrid[yct][xct].mX, mGrid[yct][xct].mY, 3.0, 255, 0, 0);
			}
		}
	}
	
	public Grid[][] mGetGrid(){
	
		return mGrid;
	}
	
	public void mExe(){
		
		mMakeGrid();
		
		mDraw();
	}
	
}
