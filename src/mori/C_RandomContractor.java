package mori;

public class C_RandomContractor extends C_RawData implements I_ImageProcessor{

	//! X•ûŒü‚Ìk¬—¦
	private double mRatioX;

	//! Y•ûŒü‚Ìk¬—¦
	private double mRatioY;

	public C_RandomContractor(
		java.awt.image.BufferedImage aImage,
		// X•ûŒü‚Ìk¬—¦
		double aRatioX,
		// Y•ûŒü‚Ìk¬—¦
		double aRatioY
	){
		super(aImage);

		mRatioX = aRatioX;

		mRatioY = aRatioY;
	}

	public C_RandomContractor(
		// •
		int aWidth,
		// ‚‚³
		int aHeight,
		// X•ûŒü‚Ìk¬—¦
		double aRatioX,
		// Y•ûŒü‚Ìk¬—¦
		double aRatioY
	){
		super(aWidth, aHeight);

		mRatioX = aRatioX;

		mRatioY = aRatioY;
	}

	public C_RawData mExe(){
		C_Random random = new C_Random();

		double scale_x = 1.0 / mRatioX;
		double scale_y = 1.0 / mRatioY;

		int dst_width = (int)( (double)mWidth * mRatioX );
		int dst_height = (int)( (double)mHeight * mRatioY );

		C_RawData dstRaw = new C_RawData(dst_width, dst_height);

		for(int dst_y = 0; dst_y < dst_height; dst_y++){
			for(int dst_x = 0; dst_x < dst_width; dst_x++){

				int src_x = (int)(scale_x * dst_x + random.mNextDouble(0.0, scale_x) );
				int src_y = (int)(scale_y * dst_y + random.mNextDouble(0.0, scale_y) );

				int src_pos = src_y * mWidth + src_x;
				int dst_pos = dst_y * dst_width + dst_x;

				dstRaw.mRed[dst_pos]   = mRed[src_pos];
				dstRaw.mGreen[dst_pos] = mGreen[src_pos];
				dstRaw.mBlue[dst_pos]  = mBlue[src_pos];
			}
		}

		return dstRaw;
	}
}

