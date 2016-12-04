package mori.EM;

import mori.C_RawData;

public class CElectroMagnetic extends C_RawData implements mori.I_ImageProcessor{

	private CSource SC = new CSource();

	private double Light = 20.0;

	private CVector Unit = new CVector();

	private CVector Obs = new CVector();

	public CElectroMagnetic(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);
	}

	public CElectroMagnetic(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		SC.Update();
		CVector scPos = SC.GetPos();
		CVector scVel = SC.GetVel();

		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){
				int pos = yct * mWidth + xct;

				Obs.mX[0] = (double)xct - 0.5 * (double)mWidth;
				Obs.mX[1] = 0.5 * (double)mHeight - (double)yct;

				double Rret = Unit.CalcUnit(Obs, scPos);
				double inner = Unit.InnerProduct(scVel);
				int denomi = (int)( Rret * (1.0 - inner / Light) );

				if(denomi <= 255){
					dstRaw.mRed[pos] = 255 - denomi;
					dstRaw.mGreen[pos] = denomi;
					dstRaw.mBlue[pos] = 0;
				}else if(denomi <= 510){
					dstRaw.mRed[pos] = 0;
					dstRaw.mGreen[pos] = 510 - denomi;
					dstRaw.mBlue[pos] = denomi - 255;
				}
			}
		}

		return dstRaw;
	}
}

