package mori.Symmetry;

public class MathLib {

	public static boolean mCalcXpt(Xpt aXpt){
		boolean isX = true;
		double  lx  = aXpt.mP1.mX - aXpt.mP0.mX;
		double  ly  = aXpt.mP1.mY - aXpt.mP0.mY;
		double  dx  = aXpt.mP0.mX - aXpt.mRef.mX;
		double  dy  = aXpt.mP0.mY - aXpt.mRef.mY;
		
		double det = aXpt.mInVec.mX * (-ly)
				   - (-lx) * aXpt.mInVec.mY;
		
		if(Math.abs(det) > 1.0e-07){
		
			aXpt.t = -ly * dx + lx * dy;
			
			aXpt.t /= det;
			
			aXpt.s = -aXpt.mInVec.mY * dx + aXpt.mInVec.mX * dy;
			
			aXpt.s /= det;
			
			if(aXpt.s >= 0.0 && aXpt.s <= 1.0){
				
			}else{
				isX = false;
			}
		}else{
			isX = false;
		}
		
		return isX;
	}
}
