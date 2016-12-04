package mori.Symmetry;

public class RefPoint {

	private Triangle mTriangle;
	
	private CRandom mRandom;
	
	private Drawer mDrawer;
	
	public C2D mPos = new C2D();
	
	public RefPoint(
		Triangle aTriangle,
		CRandom aRandom,
		Drawer aDrawer
	){
		mTriangle = aTriangle;
		
		mRandom = aRandom;
		
		mDrawer = aDrawer;
		
		boolean isInner = false;
		
		do{
			mPos.mX = mRandom.mNextDouble(Prmtr.MIN_X, Prmtr.MAX_X);
			
			mPos.mY = mRandom.mNextDouble(Prmtr.MIN_Y, Prmtr.MAX_Y);
			
			isInner = mTriangle.mIsInner(mPos);
			
		}while(isInner == false);
	}
	
	public void mDraw(){
		
		mDrawer.mCircle(mPos.mX, mPos.mY, 5, 0, 0, 0);
	}
}
