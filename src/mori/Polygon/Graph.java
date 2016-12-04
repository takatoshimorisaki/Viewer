package mori.Polygon;

public class Graph{

	boolean mValid = true;

	int[] mE = new int[PRMTR.EDGE_NUM];

	int mEdgeNum = 0;

	public Graph(){

	}

	public Graph mCopy(){
		Graph graph = new Graph();

		graph.mEdgeNum = this.mEdgeNum;

		for(int ect = 0; ect < graph.mEdgeNum; ect++){

			graph.mE[ect] = this.mE[ect];
		}

		return graph;
	}
}

