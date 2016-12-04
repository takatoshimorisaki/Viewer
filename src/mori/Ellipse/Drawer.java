package mori.Ellipse;

public class Drawer{

	private final static double PI2 = 2.0 * Math.PI;

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

	public void mEllipse(double refX, double refY, double a, double b){

		for(double theta = 0.0; theta < PI2; theta += 0.01){

			double cs = Math.cos(theta);
			double sn = Math.sin(theta);

			double x = a * cs + refX;

			double y = b * sn + refY;

			int xPxl = (int)(x + cx);

			int yPxl = (int)(-y + cy);

			mCanvas.mSetRGB( xPxl, yPxl, 0, 0, 0);
		}
	}

	public void mCircle(double refX, double refY, double radius){

		for(double theta = 0.0; theta < PI2; theta += 0.01){

			double cs = Math.cos(theta);
			double sn = Math.sin(theta);

			double x = radius * cs + refX;

			double y = radius * sn + refY;

			int xPxl = (int)(x + cx);

			int yPxl = (int)(-y + cy);

			mCanvas.mSetRGB( xPxl, yPxl, 0, 0, 0);
		}
	}

	public void mLine(
		C2D ref,
		C2D vec
	){
		double x = ref.mX + cx;
		double y = -ref.mY + cy;
		double t = 0.0;
		
		while(x >= 0.0 && x <= mCanvas.mWidth
		   && y >= 0.0 && y <= mCanvas.mHeight){
		
			mCanvas.mSetRGB( (int)x, (int)y, 0, 0, 0);
			
			t = 1.0;
			
			x = x + t * vec.mX;
			y = y - t * vec.mY;
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

