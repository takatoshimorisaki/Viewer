package mori;

public class C_AverageContractor extends C_RawData implements I_ImageProcessor{

	//! X•ûŒü‚Ìk¬—¦
	private double mRatioX;

	//! Y•ûŒü‚Ìk¬—¦
	private double mRatioY;

	public C_AverageContractor(
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

	public C_AverageContractor(
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
		int nx = (int)(1.0 / mRatioX);
		int ny = (int)(1.0 / mRatioY);

		int dst_width = (int)( (double)mWidth * mRatioX );
		int dst_height = (int)( (double)mHeight * mRatioY );

		C_RawData dstRaw = new C_RawData(dst_width, dst_height);

		for(int src_y = 0; src_y < (mHeight - ny); src_y += ny){
			for(int src_x = 0; src_x < (mWidth - nx); src_x += nx){

				int lumi_r = 0;
				int lumi_g = 0;
				int lumi_b = 0;

				for(int yct = 0; yct < ny; yct++){
					for(int xct = 0; xct < nx; xct++){

						int src_pos = (src_x + xct) + (src_y + yct) * mWidth;

						lumi_r += mRed[src_pos];

						lumi_g += mGreen[src_pos];

						lumi_b += mBlue[src_pos];

					}
				}

				double area = nx * ny;

				lumi_r = (int)( (double)lumi_r / area );

				lumi_g = (int)( (double)lumi_g / area );

				lumi_b = (int)( (double)lumi_b / area );

				int dst_x = (int)( (double)src_x * mRatioX );
				int dst_y = (int)( (double)src_y * mRatioY );

				int dst_pos = dst_x + dst_y * dstRaw.mWidth;

				dstRaw.mRed[dst_pos]   = lumi_r;
				dstRaw.mGreen[dst_pos] = lumi_g;
				dstRaw.mBlue[dst_pos]  = lumi_b;

			}
		}

		return dstRaw;
	}
}

