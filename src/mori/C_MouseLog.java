package mori;

public class C_MouseLog extends C_RawData implements I_ImageProcessor{

	private java.awt.Point mStartPoint;

	private java.awt.Point mEndPoint;

	public C_MouseLog(
		java.awt.image.BufferedImage aImage,
		java.awt.Point aStartPoint,
		java.awt.Point aEndPoint
	){
		super(aImage);

		mStartPoint = aStartPoint;

		mEndPoint = aEndPoint;
	}

	public C_MouseLog(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		try{
			java.io.PrintWriter pw = new java.io.PrintWriter("mouseLog.csv");

			pw.printf("R, G, B, dR, dG, dB\n");

			double diffX = mEndPoint.x - mStartPoint.x;
			double diffY = mEndPoint.y - mStartPoint.y;
	
			double length = diffX * diffX + diffY * diffY;
			length = Math.sqrt(length);
	
			double vx = diffX / length;
			double vy = diffY / length;

			int r1 = 0;
			int g1 = 0;
			int b1 = 0;

			for(double t = 0.0; t < length; t += 1.0){
	
				int x = (int)(mStartPoint.x + t * vx);
				int y = (int)(mStartPoint.y + t * vy);
	
				int pos = y * mWidth + x;
	
				int dR = mRed[pos] - r1;
				int dG = mGreen[pos] - g1;
				int dB = mBlue[pos] - b1;

				pw.printf("%d, %d, %d, %d, %d, %d\n", mRed[pos], mGreen[pos], mBlue[pos], dR, dG, dB);

				r1 = mRed[pos];
				g1 = mGreen[pos];
				b1 = mBlue[pos];
			}
	
			pw.close();
		}catch(Exception ex){
		}

		return null;
	}
}

