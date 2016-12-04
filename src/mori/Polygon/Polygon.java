package mori.Polygon;

import static java.lang.System.out;

import mori.C_ImageProcessParameter;
import mori.C_RawData;
import mori.I_ImageProcessor;

public class Polygon extends C_RawData implements I_ImageProcessor{

	private mori.C_RawData mDstRaw;

	private Drawer mDrawer;

	private boolean mCalced = false;

	private Vertex[] mVertex = new Vertex[PRMTR.VERTEX_NUM];

	private Edge[] mEdge = new Edge[PRMTR.EDGE_NUM];

	private Graph[] mGraph = new Graph[PRMTR.INITIAL_GRAPH_NUM];

	private VertexManager mVertexManager;

	private EdgeManager mEdgeManager;

	private GraphManager mGraphManager;

	private Connector mConnector = new Connector();

	public Polygon(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mDstRaw = new mori.C_RawData(mWidth, mHeight);

		mDrawer = new Drawer(mDstRaw);

		for(int cnt = 0; cnt < PRMTR.VERTEX_NUM; cnt++){

			mVertex[cnt] = new Vertex();
		}

		for(int cnt = 0; cnt < PRMTR.EDGE_NUM; cnt++){

			mEdge[cnt] = new Edge();
		}

		for(int cnt = 0; cnt < PRMTR.INITIAL_GRAPH_NUM; cnt++){

			mGraph[cnt] = new Graph();
		}

		mVertexManager = new VertexManager(mVertex);

		mEdgeManager = new EdgeManager(mEdge);

		mEdgeManager.mReady(mEdge);

		mGraphManager = new GraphManager(mGraph);

	}

	public Polygon(
		// •
		int aWidth,
		// ‚‚³
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){

		if(mCalced == false){

			mConnector.mExe(mGraph, mEdge);

			mCalced = true;
		}

		mDrawer.mClear();

		mGraphManager.mDraw(mDrawer, mGraph, mEdge, mVertex);

		return mDstRaw;
	}
}

