package mori.Polygon;

import static java.lang.System.out;

public class Connector{

	private void mDeleteEdge(
		int aEdgeNo,
		int aGraphNo,
		Graph[] aGraph
	){
		Graph graph = new Graph();

		for(int ect = 0; ect < aGraph[aGraphNo].mEdgeNum; ect++){

			if(aGraph[aGraphNo].mE[ect] != aEdgeNo){

				graph.mE[ graph.mEdgeNum ] = aGraph[aGraphNo].mE[ect];

				graph.mEdgeNum++;
			}
		}

		aGraph[aGraphNo].mEdgeNum = 0;

		for(int ect = 0; ect < graph.mEdgeNum; ect++){

			aGraph[ aGraphNo ].mE[ aGraph[aGraphNo].mEdgeNum ] = graph.mE[ect];

			aGraph[aGraphNo].mEdgeNum++;
		}
	}

	private void mAttach(
		int aEdgeNo,
		int aGraphNo1,
		int aGraphNo2,
		Graph[] aGraph
	){
		if(aGraphNo1 == aGraphNo2){

			mDeleteEdge(aEdgeNo, aGraphNo1, aGraph);

			return;
		}

		Graph graph = new Graph();

		for(int ect = 0; ect < aGraph[aGraphNo1].mEdgeNum; ect++){

			if(aGraph[aGraphNo1].mE[ect] != aEdgeNo){

				graph.mE[ graph.mEdgeNum ] = aGraph[aGraphNo1].mE[ect];

				graph.mEdgeNum++;
			}
		}

		for(int ect = 0; ect < aGraph[aGraphNo2].mEdgeNum; ect++){

			if(aGraph[aGraphNo2].mE[ect] != aEdgeNo){

				boolean already = false;

				for(int jct = 0; jct < graph.mEdgeNum; jct++){

					if(graph.mE[jct] == aGraph[aGraphNo2].mE[ect]){

						already = true;

						break;
					}
				}

				if(already == false){

					graph.mE[ graph.mEdgeNum ] = aGraph[aGraphNo2].mE[ect];

					graph.mEdgeNum++;
				}
			}
		}

		aGraph[aGraphNo2].mValid = false;

		aGraph[aGraphNo1].mEdgeNum = 0;

		for(int ect = 0; ect < graph.mEdgeNum; ect++){

			aGraph[ aGraphNo1 ].mE[ aGraph[aGraphNo1].mEdgeNum ] = graph.mE[ect];

			aGraph[aGraphNo1].mEdgeNum++;
		}
	}

	private void mAddChkPt(
		Edge[] aEdge,
		int aSrcChkPtNo,
		int aDstChkPtNo
	){
		for(int ect = 0 ; ect < PRMTR.EDGE_NUM; ect++){

			boolean haveSrcChkPt = false;
			boolean already      = false;

			for(int one_cnt = 0; one_cnt < aEdge[ect].mOneChkPtNum; one_cnt++){

				if(aEdge[ect].mOneChkPtNo[one_cnt] == aSrcChkPtNo){

					haveSrcChkPt = true;

					break;
				}
			}

			if(haveSrcChkPt == true){
				for(int one_cnt = 0; one_cnt < aEdge[ect].mOneChkPtNum; one_cnt++){
	
					if(aEdge[ect].mOneChkPtNo[one_cnt] == aDstChkPtNo){
	
						already = true;

						break;
					}
				}

				if(already == false){
					aEdge[ect].mOneChkPtNo[aEdge[ect].mOneChkPtNum] = aDstChkPtNo;

					aEdge[ect].mOneChkPtNum++;
				}
			}

			haveSrcChkPt = false;
			already      = false;

			for(int ano_cnt = 0; ano_cnt < aEdge[ect].mAnoChkPtNum; ano_cnt++){

				if( aEdge[ect].mAnoChkPtNo[ano_cnt] == aSrcChkPtNo){

					haveSrcChkPt = true;

					break;
				}
			}

			if(haveSrcChkPt == true){
				for(int ano_cnt = 0; ano_cnt < aEdge[ect].mAnoChkPtNum; ano_cnt++){

					if( aEdge[ect].mAnoChkPtNo[ano_cnt] == aDstChkPtNo){

						already = true;

						break;
					}
				}

				if(already == false){
					aEdge[ect].mAnoChkPtNo[aEdge[ect].mAnoChkPtNum] = aDstChkPtNo;

					aEdge[ect].mAnoChkPtNum++;
				}
			}
		}
	}

	public void mExe(
		Graph[] aGraph,
		Edge[] aEdge
	){
		for(int ect = 0 ; ect < PRMTR.EDGE_NUM; ect++){

			if(aEdge[ect].mEna == false){

				int gNo1 = 0;
				int gNo2 = 0;

				for(int one_cnt = 0; one_cnt < aEdge[ect].mOneChkPtNum; one_cnt++){

					gNo1 = aEdge[ect].mOneChkPtNo[one_cnt];

					if(gNo1 != -1 && aGraph[gNo1].mValid == true){
						break;
					}
				}

				for(int ano_cnt = 0; ano_cnt < aEdge[ect].mAnoChkPtNum; ano_cnt++){

					gNo2 = aEdge[ect].mAnoChkPtNo[ano_cnt];

					if(gNo2 != -1 && aGraph[gNo2].mValid == true){
						break;
					}
				}

				out.printf("connect gNo1 %d gNo2 %d\n", gNo1, gNo2);

				if(gNo1 == -1){
					aGraph[gNo2].mValid = false;
				}else
				if(gNo2 == -1){
					aGraph[gNo1].mValid = false;
				}else{
	
					this.mAddChkPt(aEdge, gNo1, gNo2);
	
					this.mAddChkPt(aEdge, gNo2, gNo1);
	
					this.mAttach(ect, gNo1, gNo2, aGraph);
				}
			}

			out.printf("ect %d\n", ect);

			this.mPrint(aGraph, aEdge);

		}// for ect
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

