package mori.Polygon;

public class VertexManager{

	public VertexManager(
		Vertex[] aVertex
	){
		aVertex[0].mX = 0.0;
		aVertex[0].mY = 0.0;

		aVertex[1].mX = 0.0;
		aVertex[1].mY = 100.0;

		aVertex[2].mX = 100.0;
		aVertex[2].mY = 0.0;

		aVertex[3].mX = 0.0;
		aVertex[3].mY = -100.0;

		aVertex[4].mX = -100.0;
		aVertex[4].mY = 0.0;
	}
}

