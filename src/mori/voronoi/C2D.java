package mori.voronoi;

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
	
	public void mPrint(){
		out.printf("mX %f mY %f\n", mX, mY);
	}
}
