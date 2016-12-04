package mori;

public class C_Wave extends C_RawData implements I_ImageProcessor{

	private double mAmp = 20.0;

	private double mRamda;

	private double mOmega;

	private double mTime;

	public C_Wave(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);

		mRamda = mWidth / 2.0;

		mOmega = 1.0/5.0;

	}

	public C_Wave(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){

				int pos = yct * mWidth + xct;
	
				double radius = xct;

				double phase = 2.0 * Math.PI * (mRamda * radius - mOmega * mTime);

				int lumi = (int)( mAmp * Math.sin(phase) );

				dstRaw.mRed[pos] = mRed[pos] + lumi;

				if(dstRaw.mRed[pos] < 0){
					dstRaw.mRed[pos] = 0;
				}else if(dstRaw.mRed[pos] > 255){
					dstRaw.mRed[pos] = 255;
				}

				dstRaw.mGreen[pos] = mGreen[pos] + lumi;

				if(dstRaw.mGreen[pos] < 0){
					dstRaw.mGreen[pos] = 0;
				}else if(dstRaw.mGreen[pos] > 255){
					dstRaw.mGreen[pos] = 255;
				}

				dstRaw.mBlue[pos] = mBlue[pos] + lumi;

				if(dstRaw.mBlue[pos] < 0){
					dstRaw.mBlue[pos] = 0;
				}else if(dstRaw.mBlue[pos] > 255){
					dstRaw.mBlue[pos] = 255;
				}
			}
		}

		mTime += 1.0;

		return dstRaw;
	}
}

