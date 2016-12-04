package mori.voronoi;

public class TestCreator {

	private C2D[] mV = new C2D[VertexManager.VERTEX_NUM];

	private CRandom mRandom;
	
	public TestCreator(CRandom aRandom, int testId){

		mRandom = aRandom;
		
		if(testId == 0){
				
			// 左上
			mV[0] = new C2D();
			
			mV[0].mX = mRandom.mNextDouble(-300.0, -100.0);
	
			mV[0].mY = mRandom.mNextDouble(300.0, 100.0);
	
			// 左下
			mV[1] = new C2D();
			
			mV[1].mX = mRandom.mNextDouble(-300.0, -100.0);
	
			mV[1].mY = mRandom.mNextDouble(-300.0, -100.0);
	
			// 右下
			mV[2] = new C2D();
			
			mV[2].mX = mRandom.mNextDouble(300.0, 100.0);
	
			mV[2].mY = mRandom.mNextDouble(-300.0, -100.0);
	
			// 右上
			mV[3] = new C2D();
			
			mV[3].mX = mRandom.mNextDouble(300.0, 100.0);
	
			mV[3].mY = mRandom.mNextDouble(300.0, 100.0);
	
		}else
		if(testId == 1){
				
			// 左上
			mV[0] = new C2D();
			
			mV[0].mX = -145.466103;
	
			mV[0].mY = 276.651103;
	
			// 左下
			mV[1] = new C2D();
			
			mV[1].mX = -185.964491;
	
			mV[1].mY = -219.695598;
	
			// 右下
			mV[2] = new C2D();
			
			mV[2].mX = 298.572829;
	
			mV[2].mY = -279.788161;
	
			// 右上
			mV[3] = new C2D();
			
			mV[3].mX = 164.157271;
	
			mV[3].mY = 170.913593;
		}
	}

	public C2D[] mGetVertex(){
		
		return mV;
	}
}
