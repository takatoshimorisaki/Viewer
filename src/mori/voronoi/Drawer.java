package mori.voronoi;

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

	public void mCircle(
			double refX, 
			double refY, 
			double radius,
			int aR,
			int aG,
			int aB
			){

		for(double theta = 0.0; theta < PI2; theta += 0.01){

			double cs = Math.cos(theta);
			double sn = Math.sin(theta);

			double x = radius * cs + refX;

			double y = radius * sn + refY;

			int xPxl = (int)(x + cx);

			int yPxl = (int)(-y + cy);

			mCanvas.mSetRGB( xPxl, yPxl, aR, aG, aB);
		}
	}

	public void mSetPoint(
			double aX,
			double aY,
			int aR,
			int aG,
			int aB
			){
		double posX = aX + cx;

		double posY = -aY + cy;

		mCanvas.mSetRGB( (int)posX, (int)posY, aR, aG, aB);
	}
	
	public void mLine(
		C2D one,
		C2D ano,
		int aR,
		int aG,
		int aB
	){
		double diffX = (one.mX - ano.mX);

		double diffY = (one.mY - ano.mY);

		double length = diffX * diffX + diffY * diffY;

		length = Math.sqrt(length);

		double ux = diffX / length;

		double uy = diffY / length;

		for(double t = 0.0; t <= length; t += 1.0){

			double posX = ano.mX + t * ux + cx;

			double posY = -(ano.mY + t * uy) + cy;

			mCanvas.mSetRGB( (int)posX, (int)posY, aR, aG, aB);
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
	
	public void mVecLine(C2D aRef, C2D aVec, int aR, int aG, int aB){
				
		mCircle(aRef.mX, aRef.mY, 3.0, 255, 0, 0);
		
		for(double t = 0; t < Double.MAX_VALUE; t += 1.0){
	
			double x = aRef.mX + t * aVec.mX;
			
			double y = aRef.mY + t * aVec.mY;
			
			if(x >= Prmtr.MIN_X && x <= Prmtr.MAX_X
			&& y >= Prmtr.MIN_Y && y <= Prmtr.MAX_Y){
				
				double posX = x + cx;
				
				double posY = -y + cy;

				mCanvas.mSetRGB( (int)posX, (int)posY, aR, aG, aB);
			}else{
				break;
			}
		}

		for(double t = 0; t < Double.MAX_VALUE; t += 1.0){
	
			double x = aRef.mX - t * aVec.mX;
			
			double y = aRef.mY - t * aVec.mY;
			
			if(x >= Prmtr.MIN_X && x <= Prmtr.MAX_X
			&& y >= Prmtr.MIN_Y && y <= Prmtr.MAX_Y){
				
				double posX = x + cx;
				
				double posY = -y + cy;

				mCanvas.mSetRGB( (int)posX, (int)posY, aR, aG, aB);
			}else{
				break;
			}
		}
	}
}

