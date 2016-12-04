package mori.voronoi;

import static java.lang.System.out;

public class MidLine {

	Drawer mDrawer;
	
	C2D mRef = new C2D();
	
	C2D mVec = new C2D();
	
	public MidLine(
		Drawer aDrawer,
		C2D p0,
		C2D p1
	){
		mDrawer = aDrawer;
		
		mRef.mX = 0.5 * (p0.mX + p1.mX);
		
		mRef.mY = 0.5 * (p0.mY + p1.mY);
		
		double diffX = p1.mX - p0.mX;
		
		double diffY = p1.mY - p0.mY;
		
		double length = diffX * diffX + diffY * diffY;
		
		length = Math.sqrt(length);
		
		mVec.mX = diffY;
		
		mVec.mY = -diffX;
		
		mVec.mX = mVec.mX / length;
		
		mVec.mY = mVec.mY / length;
	}
	
	public void mDraw(){
		
		mDrawer.mVecLine(mRef, mVec, 0, 0, 0);
	}
}
