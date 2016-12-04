package mori.Polygon;

public class EdgeManager{

	public EdgeManager(
		Edge[] aEdge
	){
		aEdge[0].mV[0] = 0;
		aEdge[0].mV[1] = 1;

		aEdge[1].mV[0] = 0;
		aEdge[1].mV[1] = 2;

		aEdge[2].mV[0] = 0;
		aEdge[2].mV[1] = 3;

		aEdge[3].mV[0] = 0;
		aEdge[3].mV[1] = 4;

		aEdge[4].mV[0] = 1;
		aEdge[4].mV[1] = 2;

		aEdge[5].mV[0] = 2;
		aEdge[5].mV[1] = 3;

		aEdge[6].mV[0] = 3;
		aEdge[6].mV[1] = 4;

		aEdge[7].mV[0] = 4;
		aEdge[7].mV[1] = 1;
	}

	public void mReady(
		Edge[] aEdge
	){
		for(int cnt = 0; cnt < PRMTR.EDGE_NUM; cnt++){

			aEdge[cnt].mOneChkPtNum = 1;

			aEdge[cnt].mAnoChkPtNum = 1;
		}

		aEdge[0].mOneChkPtNo[0] = 3;
		aEdge[0].mAnoChkPtNo[0] = 0;

		aEdge[1].mOneChkPtNo[0] = 0;
		aEdge[1].mAnoChkPtNo[0] = 1;

		aEdge[2].mOneChkPtNo[0] = 1;
		aEdge[2].mAnoChkPtNo[0] = 2;

		aEdge[3].mOneChkPtNo[0] = 2;
		aEdge[3].mAnoChkPtNo[0] = 3;

		aEdge[4].mOneChkPtNo[0] = 0;
		aEdge[4].mAnoChkPtNo[0] = -1;

		aEdge[5].mOneChkPtNo[0] = 1;
		aEdge[5].mAnoChkPtNo[0] = -1;

		aEdge[6].mOneChkPtNo[0] = 2;
		aEdge[6].mAnoChkPtNo[0] = -1;

		aEdge[7].mOneChkPtNo[0] = 3;
		aEdge[7].mAnoChkPtNo[0] = -1;

		if(false){
			aEdge[0].mEna = false;

			aEdge[1].mEna = false;

			aEdge[2].mEna = false;

			aEdge[3].mEna = false;
		}else{
			aEdge[4].mEna = false;

			aEdge[6].mEna = false;
		}
	}

}

