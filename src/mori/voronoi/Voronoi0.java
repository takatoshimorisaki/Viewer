package mori.voronoi;

public class Voronoi0 {

	public C2D[] mV;

	private VertexManager mVertexManager;
	
	private mori.voronoi.Drawer mDrawer;
	
	public Voronoi0(
			VertexManager aVertexManager,
			Drawer aDrawer
	){
		
		mVertexManager = aVertexManager;
		
		mDrawer = aDrawer;
	}
	
	public void mExe(){
		C2D pos = new C2D();
		double minLength = Double.MAX_VALUE;
		double length = 0.0;
		int nearestId0 = -1;
		int nearestId1 = -1;
		
		mV = mVertexManager.mGetVertex();
		
		for(pos.mY = Prmtr.MIN_Y; pos.mY <= Prmtr.MAX_Y; pos.mY += 1.0){

			minLength = Double.MAX_VALUE;
			
			pos.mX = Prmtr.MIN_X;
			
			for(int vct = 0; vct < mV.length; vct++){
				
				length = pos.mCalcLength(mV[vct]);
				
				if(length < minLength){
					
					minLength = length;
					
					nearestId1 = vct;
				}
			}// for vct
			
			for(pos.mX = Prmtr.MIN_X; pos.mX <= Prmtr.MAX_X; pos.mX += 1.0){
				
				minLength = Double.MAX_VALUE;
				nearestId0 = -1;
				
				for(int vct = 0; vct < mV.length; vct++){
					
					length = pos.mCalcLength(mV[vct]);
					
					if(length < minLength){
						
						minLength = length;
						
						nearestId0 = vct;
					}
				}// for vct
				
				if(nearestId0 != nearestId1){
					
					mDrawer.mSetPoint(pos.mX, pos.mY, 0, 255, 0);
					
					nearestId1 = nearestId0; 
				}
			}
		}

		for(pos.mX = Prmtr.MIN_X; pos.mX <= Prmtr.MAX_X; pos.mX += 1.0){

			minLength = Double.MAX_VALUE;
			
			pos.mY = Prmtr.MIN_Y;
			
			for(int vct = 0; vct < mV.length; vct++){
				
				length = pos.mCalcLength(mV[vct]);
				
				if(length < minLength){
					
					minLength = length;
					
					nearestId1 = vct;
				}
			}// for vct
			
			for(pos.mY = Prmtr.MIN_Y; pos.mY <= Prmtr.MAX_Y; pos.mY += 1.0){
				
				minLength = Double.MAX_VALUE;
				nearestId0 = -1;
				
				for(int vct = 0; vct < mV.length; vct++){
					
					length = pos.mCalcLength(mV[vct]);
					
					if(length < minLength){
						
						minLength = length;
						
						nearestId0 = vct;
					}
				}// for vct
				
				if(nearestId0 != nearestId1){
					
					mDrawer.mSetPoint(pos.mX, pos.mY, 0, 255, 0);
					
					nearestId1 = nearestId0; 
				}
			}
		}
	}
}
