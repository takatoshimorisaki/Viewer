package mori.Trochoid5;

public class C_HC{

	public final static int DIMENSION = 4;

	public double[] mX = new double[DIMENSION];

	public double mLength(){
		double length = mX[0] * mX[0] + mX[1] * mX[1] + mX[2] * mX[2];

		length = Math.sqrt(length);

		return length;
	}

}

