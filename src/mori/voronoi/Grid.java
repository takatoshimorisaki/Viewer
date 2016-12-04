package mori.voronoi;

public class Grid extends C2D{

	public boolean mChanged;
	
	public int mNearestVertexId;
	
	public Grid(){
	}

	public Grid(
	    double aX,
	    double aY
	){
		super(aX, aY);
	}
	
}
