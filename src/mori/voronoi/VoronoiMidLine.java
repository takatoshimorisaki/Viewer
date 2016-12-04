package mori.voronoi;

public class VoronoiMidLine {

	public MidLine[] mMidLine;
	
	public C2D[] mV;

	private VertexManager mVertexManager;
	
	private Drawer mDrawer;
	
	public VoronoiMidLine(
			VertexManager aVertexManager,
			Drawer aDrawer
			){
		
		mVertexManager = aVertexManager;
		
		mDrawer = aDrawer;
	}
	
	private int mCalcFactorial(int n){
		int ans = n;
		
		while(n > 1){
			
			n--;
			
			ans *= n;
		}
		
		return ans;
	}
	
	private void mCalcMidLine(){
		mV = mVertexManager.mGetVertex();
		
		int factorial = mCalcFactorial(mV.length - 1);
		
		mMidLine = new MidLine[factorial];
		
		int cnt = 0;
		
		for(int one = 0; one < mV.length; one++){
			for(int ano = one+1; ano < mV.length; ano++){
		
				mMidLine[cnt] = new MidLine(mDrawer, mV[one], mV[ano]);
				
				cnt++;
			}
		}
	}
	
	private void mDraw(){
		
		for(int cnt = 0; cnt < mMidLine.length; cnt++){
			
			mMidLine[cnt].mDraw();
		}
	}
	
	public void mExe(){
	
		mCalcMidLine();
		
		mDraw();
	}	
}

