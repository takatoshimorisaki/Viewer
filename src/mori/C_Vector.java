package mori;

public class C_Vector{

	private double[] mX = new double[3];

	public C_Vector(){
	}

	public C_Vector(
		double aX,
		double aY,
		double aZ
	){
		mX[0] = aX;
		mX[1] = aY;
		mX[2] = aZ;
	}

	public double mLength(){
		double length = mX[0] * mX[0] + mX[1] * mX[1] + mX[2] * mX[2];
		length = Math.sqrt(length);

		return length;
	}

	public double mInnerProduct(
		C_Vector aOther
	){
		double product = mX[0] * aOther.mX[0] + mX[1] * aOther.mX[1] + mX[2] * aOther.mX[2];

		return product;
	}

	public double mAngle(
		C_Vector aOther
	){
		double theta = mInnerProduct(aOther) / ( mLength() * aOther.mLength() );

		theta = Math.acos(theta);

		return theta;
	}

	public C_Vector mSubtract(
		C_Vector aOther
	){
		C_Vector ans = new C_Vector();

		for(int cnt = 0; cnt < mX.length; cnt++){
			ans.mX[cnt] = mX[cnt] - aOther.mX[cnt];
		}

		return ans;
	}

}

