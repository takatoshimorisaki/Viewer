package mori.EM;

public class CVector{

	public double[] mX = new double[3];

	public CVector(){

	}

	public double CalcUnit(
		CVector aPos,
		CVector aRef
	){
		mX[0] = (aPos.mX[0] - aRef.mX[0]);
        mX[1] = (aPos.mX[1] - aRef.mX[1]);
        mX[2] = (aPos.mX[2] - aRef.mX[2]);

		double dis = mX[0] * mX[0] + mX[1] * mX[1] + mX[2] * mX[2];
		dis = Math.sqrt(dis);

		mX[0] /= dis;
		mX[1] /= dis;
		mX[2] /= dis;

		return dis;
	}

	public double InnerProduct(CVector arg){
		double ans = mX[0] * arg.mX[0] + mX[1] * arg.mX[1] + mX[2] * arg.mX[2];
		return ans;
	}
}

