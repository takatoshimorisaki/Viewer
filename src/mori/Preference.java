package mori;

public class Preference{

	//! �D�݂̌Œ�l
	public final static int CANVAS_WIDTH = 800;

	public final static int CANVAS_HEIGHT = 600;

	//! ��
	public int mWidth;

	//! ����
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

