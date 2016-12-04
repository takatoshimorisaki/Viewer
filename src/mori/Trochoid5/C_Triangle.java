package mori.Trochoid5;

public class C_Triangle{

	private C_Trochoid[] mTrochoid = new C_Trochoid[3];

	private C_HC[] mPos = new C_HC[3];

	private C_HC mV1 = new C_HC();

	private C_HC mV2 = new C_HC();

	public C_Triangle(
		C_Trochoid aTrochoid0,
		C_Trochoid aTrochoid1,
		C_Trochoid aTrochoid2
	){
		mTrochoid[0] = aTrochoid0;
		mTrochoid[1] = aTrochoid1;
		mTrochoid[2] = aTrochoid2;

		for(int cnt = 0; cnt < mPos.length; cnt++){
			mPos[cnt] = new C_HC();
		}
	}

	public void mGetAlt(
		double aX,
		double aY,
		C_Value aAlt
	){

		for(int cnt = 0; cnt < mPos.length; cnt++){
			mTrochoid[cnt].mGetPos(mPos[cnt]);
		}
		
		for(int cnt = 0; cnt < C_HC.DIMENSION; cnt++){
			mV1.mX[cnt] = mPos[1].mX[cnt] - mPos[0].mX[cnt];
		}

		for(int cnt = 0; cnt < C_HC.DIMENSION; cnt++){
			mV2.mX[cnt] = mPos[2].mX[cnt] - mPos[1].mX[cnt];
		}

		try{
			double dx = aX - mPos[0].mX[0];
			double dy = aY - mPos[0].mX[1];
			double s  = (dy * mV1.mX[0] - dx * mV1.mX[1]) / (- dy * mV2.mX[0] + dx * mV2.mX[1]);
			double t  = dx / (mV1.mX[0] + s * mV2.mX[0]);

			if(s >= 0.0 && s <= 1.0 && t >= 0.0 && t <= 1.0){
				aAlt.mValue = mPos[0].mX[2] + t * mV1.mX[2] + t * s * mV2.mX[2];
				aAlt.mValid = true;
			}else{
				aAlt.mValid = false;
			}
		}catch(Exception ex){
			aAlt.mValid = false;
		}
	}
}

