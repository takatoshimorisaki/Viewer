package mori.voronoi;

import static java.lang.System.out;

public class VertexManager {

	public final static int VERTEX_NUM = 4;
	
	public C2D[] mV = new C2D[VERTEX_NUM];
	
	private CRandom mRandom;
	
	private Drawer mDrawer;
	
	public VertexManager(CRandom aRandom, Drawer aDrawer){
		
		mRandom = aRandom;
		
		mDrawer = aDrawer;
		
		// ç∂è„
		mV[0] = new C2D();
		
		mV[0].mX = -145.466103; //// mRandom.mNextDouble(-300.0, -100.0);

		mV[0].mY = 276.651103; //// mRandom.mNextDouble(300.0, 100.0);

		// ç∂â∫
		mV[1] = new C2D();
		
		mV[1].mX = -185.964491; //// mRandom.mNextDouble(-300.0, -100.0);

		mV[1].mY = -219.695598; //// mRandom.mNextDouble(-300.0, -100.0);

		// âEâ∫
		mV[2] = new C2D();
		
		mV[2].mX = 298.572829; //// mRandom.mNextDouble(300.0, 100.0);

		mV[2].mY = -279.788161; //// mRandom.mNextDouble(-300.0, -100.0);

		// âEè„
		mV[3] = new C2D();
		
		mV[3].mX = 164.157271; //// mRandom.mNextDouble(300.0, 100.0);

		mV[3].mY = 170.913593; //// mRandom.mNextDouble(300.0, 100.0);
		
		mPrint();
	}
	
	public void mPrint(){
		
		out.println("Vertex");
		
		for(int vct = 0; vct < VERTEX_NUM; vct++){		
	
			out.printf("%d, %f, %f\n", vct, mV[vct].mX, mV[vct].mY);
		}
	}
		
	public void mDraw(){
		for(int vct = 0; vct < VERTEX_NUM; vct++){
		
			mDrawer.mCircle(mV[vct].mX, mV[vct].mY, 3.0);
		}
	}
	
	public C2D[] mGetVertex(){
		
		return mV;
	}
}
