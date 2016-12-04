package mori.Polygon;

import static java.lang.System.out;

public class GraphManager{

	public GraphManager(
		Graph[] aGraph
	){
		aGraph[0].mE[0] = 0;
		aGraph[0].mE[1] = 4;
		aGraph[0].mE[2] = 1;

		aGraph[0].mEdgeNum = 3;

		aGraph[1].mE[0] = 1;
		aGraph[1].mE[1] = 5;
		aGraph[1].mE[2] = 2;

		aGraph[1].mEdgeNum = 3;

		aGraph[2].mE[0] = 2;
		aGraph[2].mE[1] = 6;
		aGraph[2].mE[2] = 3;

		aGraph[2].mEdgeNum = 3;

		aGraph[3].mE[0] = 3;
		aGraph[3].mE[1] = 7;
		aGraph[3].mE[2] = 0;

		aGraph[3].mEdgeNum = 3;
	}

	public void mDraw(
		Drawer aDrawer,
		Graph[] aGraph,
		Edge[] aEdge,
		Vertex[] aVertex
	){
		for(int gct = 0; gct < PRMTR.INITIAL_GRAPH_NUM; gct++){

			if(aGraph[gct].mValid == false){

				continue;
			}

			for(int ect = 0; ect < aGraph[gct].mEdgeNum; ect++){

				int edgeNo = aGraph[gct].mE[ect];

				int v0 = aEdge[edgeNo].mV[0];

				int v1 = aEdge[edgeNo].mV[1];

				aDrawer.mLine(aVertex[v0].mX, aVertex[v0].mY, aVertex[v1].mX, aVertex[v1].mY);
			}
		}
	}

	public void mPrint(
		Graph[] aGraph,
		Edge[] aEdge
	){
		for(int gct = 0; gct < PRMTR.INITIAL_GRAPH_NUM; gct++){

			if(aGraph[gct].mValid == true){

				out.printf("graphNo %d\n", gct);

				out.printf("	edgeNum %d\n", aGraph[gct].mEdgeNum);

				for(int ect = 0; ect < aGraph[gct].mEdgeNum; ect++){

					out.printf("	edgeNo %d\n", aGraph[gct].mE[ect]);
				}
			}
		}

		out.println();

		for(int ect = 0; ect < PRMTR.EDGE_NUM; ect++){

			out.printf("edgeNo %d\n", ect);

			out.printf("	v0 %d v1 %d\n", aEdge[ect].mV[0], aEdge[ect].mV[1]);

			out.printf("    mOneChkPtNum %d\n", aEdge[ect].mOneChkPtNum);

			for(int one_cnt = 0; one_cnt < aEdge[ect].mOneChkPtNum; one_cnt++){

				out.printf("    %d\n", aEdge[ect].mOneChkPtNo[one_cnt]);
			}

			out.printf("    mAnoChkPtNum %d\n", aEdge[ect].mAnoChkPtNum);

			for(int ano_cnt = 0; ano_cnt < aEdge[ect].mAnoChkPtNum; ano_cnt++){

				out.printf("    %d\n", aEdge[ect].mAnoChkPtNo[ano_cnt]);
			}
		}
	}
}

