package mori.Polygon;

import static java.lang.System.out;

public class Drawer{

	private mori.C_RawData mCanvas;

	private double cx;

	private double cy;

	public Drawer(
		mori.C_RawData aCanvas
	){
		mCanvas = aCanvas;

		cx = 0.5 * (double)(aCanvas.mWidth);

		cy = 0.5 * (double)(aCanvas.mHeight);
	}

	public void mClear(){

		for(int yct = 0; yct < mCanvas.mHeight; yct++){
			for(int xct = 0; xct < mCanvas.mWidth; xct++){

				mCanvas.mSetRGB(xct, yct, 255, 255, 255);
			}
		}
	}

	public void mLine(
		double aX0,
		double aY0,
		double aX1,
		double aY1
	){
		double diffX = (aX1 - aX0);

		double diffY = (aY1 - aY0);

		double length = diffX * diffX + diffY * diffY;

		length = Math.sqrt(length);

		double ux = diffX / length;

		double uy = diffY / length;

		for(double t = 0.0; t <= length; t += 1.0){

			double posX = aX0 + t * ux + cx;

			double posY = -(aY0 + t * uy) + cy;

			mCanvas.mSetRGB( (int)posX, (int)posY, 0, 0, 0);
		}
	}
}

