package mori.Symmetry;

import static java.lang.System.out;

public class C2D {

	public double mX;
	
	public double mY;
	
	public C2D(){
	}
	
	public C2D(
	    double aX,
	    double aY
	){
		mX = aX;
		
		mY = aY;
	}
	
	public double mCalcLength(C2D aV){
		double diffX = aV.mX - this.mX;
		
		double diffY = aV.mY - this.mY;
		
		double length = diffX * diffX + diffY * diffY;
		
		length = Math.sqrt(length);
		
		return length;
	}
	
	public C2D mSub(C2D aV){
		C2D ans = new C2D();
		
		ans.mX = this.mX - aV.mX;
		
		ans.mY = this.mY - aV.mY;
		
		return ans;
	}
	
	public void mPrint(){
		out.printf("mX %f mY %f\n", mX, mY);
	}
}
