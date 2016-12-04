package mori.Trochoid4;

public class C_Triangle{

	private C_Trochoid[] mTrochoid = new C_Trochoid[3];

	private C_HC[] mPos = new C_HC[3];

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

	public void mExe(
		double aT,
		mori.C_RawData aRaw
	){

		for(int cnt = 0; cnt < mPos.length; cnt++){
			mTrochoid[cnt].mExe(
				aT,
				mPos[cnt]);
		}

		C_HC v1 = new C_HC();
		
		for(int cnt = 0; cnt < C_HC.DIMENSION; cnt++){
			v1.mX[cnt] = mPos[1].mX[cnt] - mPos[0].mX[cnt];
		}

		C_HC v2 = new C_HC();

		for(int cnt = 0; cnt < C_HC.DIMENSION; cnt++){
			v2.mX[cnt] = mPos[2].mX[cnt] - mPos[1].mX[cnt];
		}

		double v1Length = v1.mLength();
		double v2Length = v2.mLength();
		double param1 = 1.0 / v1Length;
		double param2 = param1 / v2Length;

		C_HC point = new C_HC();

		for(double s = 0.0; s < v1Length; s += 0.125){
			for(double t = 0.0; t < v2Length; t += 0.125){
				for(int cnt = 0; cnt < C_HC.DIMENSION; cnt++){
					point.mX[cnt] = mPos[0].mX[cnt] + s * v1.mX[cnt] * param1 + s * t * v2.mX[cnt] * param2;
				}

				int lumi = (int)point.mX[2];

				if(lumi > 255){
					lumi = 255;
				}else if(lumi < 0){
					lumi = 0;
				}

				aRaw.mSetRGB(
					(int)point.mX[0],
					(int)point.mX[1],
					lumi,
					255,
					255);
			}
		}
	}
}

