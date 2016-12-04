package mori;

public class Preference{

	//! 好みの固定値
	public final static int CANVAS_WIDTH = 800;

	public final static int CANVAS_HEIGHT = 600;

	//! 幅
	public int mWidth;

	//! 高さ
	public int mHeight;

	public Preference(
		int aWidth, 
		int aHeight
	){
		mWidth = aWidth;

		mHeight = aHeight;
	}

	private Preference(){}

}

