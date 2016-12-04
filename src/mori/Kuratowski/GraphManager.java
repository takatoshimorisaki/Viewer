package mori.Kuratowski;

public class GraphManager {
	
	private VertexManager mVertexManager;
	
	private EdgeManager mEdgeManager;
	
	private Drawer mDrawer;
	
	public GraphManager(
		VertexManager aVertexManager,
		EdgeManager aEdgeManager,
		Drawer aDrawer
	){
		mVertexManager = aVertexManager;
		
		mEdgeManager = aEdgeManager;
		
		mDrawer = aDrawer;
		
	}
	
	public void mDraw(){
		C2D[] vtx = mVertexManager.mGetVertex();
		Edge[] edge = mEdgeManager.mGetEdge();
		
		for(int cnt = 0; cnt < EdgeManager.EDGE_NUM; cnt++){

			int vtx0 = edge[cnt].mVtx0;
			
			int vtx1 = edge[cnt].mVtx1;
			
			mDrawer.mLine(vtx[vtx0], vtx[vtx1], 0, 0, 0);
		}
	}
	
	public boolean mCheck(){
		C2D[] vtx = mVertexManager.mGetVertex();
		Edge[] edge = mEdgeManager.mGetEdge();
		boolean rtn = false;
		
		for(int eId0 = 0; eId0 < edge.length; eId0++){
			
			int vId0 = edge[eId0].mVtx0;
			
			int vId1 = edge[eId0].mVtx1;
			
			C2D p0 = vtx[vId0];
			
			C2D p1 = vtx[vId1];
			
			for(int eId1 = 0; eId1 < edge.length; eId1++){
			
				int vId2 = edge[eId1].mVtx0;
				
				int vId3 = edge[eId1].mVtx1;
				
				C2D q0 = vtx[vId2];
				
				C2D q1 = vtx[vId3];
				
				if(vId0 != vId1 && vId0 != vId2 && vId0 != vId3
				&& vId1 != vId2 && vId1 != vId3
				&& vId2 != vId3){
					rtn = mCheckXpt(p0, p1, q0, q1);
					
					if(rtn){
						return rtn;
					}
				}
			}
		}
		return rtn;
	}
	
	private boolean mCheckXpt(C2D p0, C2D p1, C2D q0, C2D q1){
		C2D dp = p1.mSub(p0);
		
		C2D dq = q1.mSub(q0);
		
		C2D l = q0.mSub(p0);
		
		double det = dp.mX * (-dq.mY) - (-dq.mX) * (dp.mY);
		
		if( Math.abs(det) < 1.0e-7 ){
			return false;
		}else{
			double t = (-dq.mY) * l.mX + dq.mX * l.mY;
			t /= det;
			
			double s = (-dp.mY) * l.mX + dp.mX * l.mY;
			s /= det;
			
			if(s > 0.0 && s < 1.0 && t > 0.0 && t < 1.0){
				return true;
			}else{
				return false;
			}
		}
	}
}

