package mori;

public class C_Subtractor extends C_RawData implements I_ImageProcessor{

	//! ê‘ÇÃäKí≤ìx
	public int mRedGradation;

	//! óŒÇÃäKí≤ìx
	public int mGreenGradation;

	//! ê¬ÇÃäKí≤ìx
	public int mBlueGradation;

	private int[] mRedMax;

	private int[] mGreenMax;

	private int[] mBlueMax;

	private int[] mRedColor;

	private int[] mGreenColor;

	private int[] mBlueColor;


	public C_Subtractor(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mRedGradation = aPrmtr.mRedGradation;
		mGreenGradation = aPrmtr.mGreenGradation;
		mBlueGradation = aPrmtr.mBlueGradation;

		mRedMax = new int[mRedGradation];

		mGreenMax = new int[mGreenGradation];

		mBlueMax = new int[mBlueGradation];

		mRedColor = new int[mRedGradation];

		mGreenColor = new int[mGreenGradation];

		mBlueColor = new int[mBlueGradation];
	}

	public C_Subtractor(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);

		int size = mWidth * mHeight;

		double redInterval = 255 / mRedGradation;
		double greenInterval = 255 / mGreenGradation;
		double blueInterval = 255 / mBlueGradation;

		double min = 0.0;

		for(int cnt = 0; cnt < mRedGradation; cnt++){
			mRedColor[cnt] = (int)( 0.5 * ( (cnt + 1) * redInterval + min ) );
			mRedMax[cnt] = (int)( (cnt + 1) * redInterval );
			min = (cnt + 1) * redInterval;
		}
		mRedMax[mRedGradation - 1] = 255;

		min = 0;

		for(int cnt = 0; cnt < mGreenGradation; cnt++){
			mGreenColor[cnt] = (int)( 0.5 * ( (cnt + 1) * greenInterval + min ) );
			mGreenMax[cnt] = (int)( (cnt + 1) * greenInterval );
			min = (cnt + 1) * greenInterval;
		}
		mGreenMax[mGreenGradation - 1] = 255;

		min = 0;

		for(int cnt = 0; cnt < mBlueGradation; cnt++){
			mBlueColor[cnt] = (int)( 0.5 * ( (cnt + 1) * blueInterval + min ) );
			mBlueMax[cnt] = (int)( (cnt + 1) * blueInterval );
			min = (cnt + 1) * blueInterval;
		}
		mBlueMax[mBlueGradation - 1] = 255;

		for(int pos = 0; pos < size; pos++){
			for(int cnt = 0; cnt < mRedGradation; cnt++){
				if(mRed[pos] <= mRedMax[cnt]){
					dstRaw.mRed[pos] = mRedColor[cnt];
					break;
				}
			}
		}

		for(int pos = 0; pos < size; pos++){
			for(int cnt = 0; cnt < mGreenGradation; cnt++){
				if(mGreen[pos] <= mGreenMax[cnt]){
					dstRaw.mGreen[pos] = mGreenColor[cnt];
					break;
				}
			}
		}

		for(int pos = 0; pos < size; pos++){
			for(int cnt = 0; cnt < mBlueGradation; cnt++){
				if(mBlue[pos] <= mBlueMax[cnt]){
					dstRaw.mBlue[pos] = mBlueColor[cnt];
					break;
				}
			}
		}

		return dstRaw;
	}

}

