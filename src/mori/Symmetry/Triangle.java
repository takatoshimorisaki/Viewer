package mori.Symmetry;

import static java.lang.System.out;

public class Triangle {

	private Drawer mDrawer;
	
	private C2D mPosA = new C2D();
	
	private C2D mPosB = new C2D();
	
	private C2D mPosC = new C2D();
	
	private Edge[] mEdge = new Edge[3];
	
	public Triangle(
		Drawer aDrawer
	){
		mDrawer = aDrawer;
		
		double radius = 150.0;
		double deg = 0.0;
		
		mPosA.mX = 0.0;
		mPosA.mY = radius;
		
		deg = 120.0;
		
		double rad = Math.PI * deg / 180.0;
		
		mPosB.mX = radius * Math.sin(rad);
		mPosB.mY = radius * Math.cos(rad);
		
		deg = 240.0;
		
		rad = Math.PI * deg / 180.0;

		mPosC.mX = radius * Math.sin(rad);
		mPosC.mY = radius * Math.cos(rad);

		mEdge[0] = new Edge();
		
		mEdge[0].mVtx[0] = mPosA;
		mEdge[0].mVtx[1] = mPosB;
		
		mEdge[1] = new Edge();
		
		mEdge[1].mVtx[0] = mPosB;
		mEdge[1].mVtx[1] = mPosC;
		
		mEdge[2] = new Edge();
		
		mEdge[2].mVtx[0] = mPosC;
		mEdge[2].mVtx[1] = mPosA;
		
		mEdge[0].mInVec = mPosC;
		
		mEdge[1].mInVec = mPosA;
		
		mEdge[2].mInVec = mPosB;
		
	}
	
	public void mDraw(){
		mDrawer.mLine(mPosA, mPosB, 0, 0, 0);
		mDrawer.mLine(mPosB, mPosC, 0, 0, 0);
		mDrawer.mLine(mPosC, mPosA, 0, 0, 0);
	}
	
	public boolean mIsInner(C2D aPos){
		Xpt     xpt = new Xpt();
		
		out.printf("aPos %.1f %.1f\n", aPos.mX, aPos.mY);
		
		xpt.mRef = aPos;
		xpt.mP0 = mEdge[0].mVtx[0];
		xpt.mP1 = mEdge[0].mVtx[1];
		xpt.mInVec = mEdge[0].mInVec;
		
		boolean isX = MathLib.mCalcXpt(xpt);
		
		if(isX == false){
			return false;
		}else if(xpt.t > 0.0){
			return false;
		}
		
		xpt.mP0 = mEdge[1].mVtx[0];
		xpt.mP1 = mEdge[1].mVtx[1];
		xpt.mInVec = mEdge[1].mInVec;
		
		isX = MathLib.mCalcXpt(xpt);
		
		if(isX == false){
			return false;
		}else if(xpt.t > 0.0){
			return false;
		}

		xpt.mP0 = mEdge[2].mVtx[0];
		xpt.mP1 = mEdge[2].mVtx[1];
		xpt.mInVec = mEdge[2].mInVec;
		
		isX = MathLib.mCalcXpt(xpt);
		
		if(isX == false){
			return false;
		}else if(xpt.t > 0.0){
			return false;
		}
		
		return true;
	}
	
	public void mDrawAntiPoint(C2D aPos){
		C2D vec = new C2D();
		C2D antiA = new C2D();
		C2D antiB = new C2D();
		C2D antiC = new C2D();
		
		vec.mX = mPosA.mX - aPos.mX;
		vec.mY = mPosA.mY - aPos.mY;
		
		antiA.mX = aPos.mX - vec.mX;
		antiA.mY = aPos.mY - vec.mY;
		
		mDrawer.mCircle(antiA.mX, antiA.mY, 5, 255, 0, 0);
		
		vec.mX = mPosB.mX - aPos.mX;
		vec.mY = mPosB.mY - aPos.mY;
		
		antiB.mX = aPos.mX - vec.mX;
		antiB.mY = aPos.mY - vec.mY;
		
		mDrawer.mCircle(antiB.mX, antiB.mY, 5, 255, 0, 0);
		
		vec.mX = mPosC.mX - aPos.mX;
		vec.mY = mPosC.mY - aPos.mY;
		
		antiC.mX = aPos.mX - vec.mX;
		antiC.mY = aPos.mY - vec.mY;
		
		mDrawer.mCircle(antiC.mX, antiC.mY, 5, 255, 0, 0);
		
		mDrawer.mLine(antiA, antiB, 255, 0, 0);
		mDrawer.mLine(antiB, antiC, 255, 0, 0);
		mDrawer.mLine(antiC, antiA, 255, 0, 0);
	}
}
