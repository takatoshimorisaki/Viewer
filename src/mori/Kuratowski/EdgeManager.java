package mori.Kuratowski;

public class EdgeManager {

	public final static int EDGE_NUM = 9;
	
	public Edge[] mEdge = new Edge[EDGE_NUM];
	
	public EdgeManager(){
		
		int cnt = 0;
		
		mEdge[cnt] = new Edge(0, 1);
		
		cnt++;
		
		mEdge[cnt] = new Edge(1, 2);
		
		cnt++;

		mEdge[cnt] = new Edge(2, 3);
		
		cnt++;

		mEdge[cnt] = new Edge(3, 4);
		
		cnt++;

		mEdge[cnt] = new Edge(4, 5);
		
		cnt++;

		mEdge[cnt] = new Edge(5, 0);
		
		cnt++;

		mEdge[cnt] = new Edge(0, 3);
		
		cnt++;

		mEdge[cnt] = new Edge(1, 4);
		
		cnt++;

		mEdge[cnt] = new Edge(2, 5);
		
		cnt++;
		
	}
	
	public Edge[] mGetEdge(){
		return mEdge;
	}
}
