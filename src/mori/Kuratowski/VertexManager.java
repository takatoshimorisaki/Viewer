package mori.Kuratowski;

import static java.lang.System.out;

public class VertexManager {

	public final static int VERTEX_NUM = 6;
	
	public C2D[] mV = new C2D[VERTEX_NUM];
	
	private CRandom mRandom;
	
	private Drawer mDrawer;
	
	public VertexManager(CRandom aRandom, Drawer aDrawer){
		
		mRandom = aRandom;
		
		mDrawer = aDrawer;
	}
	
	public void mRelocate(){

		for(int cnt = 0; cnt < VERTEX_NUM; cnt++){
			mV[cnt] = new C2D();
			
			mV[cnt].mX = mRandom.mNextDouble(Prmtr.MIN_X, Prmtr.MAX_X);
	
			mV[cnt].mY = mRandom.mNextDouble(Prmtr.MIN_Y, Prmtr.MAX_Y);
		}
		
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
