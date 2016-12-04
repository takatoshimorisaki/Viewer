package mori;

public class C_RGBExtractor extends C_RawData implements I_ImageProcessor{

	public C_RGBExtractor(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);
	}

	public C_RGBExtractor(
		// ïù
		int aWidth,
		// çÇÇ≥
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		C_RawData dstRaw = new C_RawData(mWidth, mHeight);
		double    thr    = 64.0;

		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){

				int pos = yct * mWidth + xct;
				int left = pos - 1;
				int right = pos + 1;
				int top = pos - mWidth;
				int bottom = pos + mWidth;

				C_Vector vec = new C_Vector( (double)mRed[pos], (double)mGreen[pos], (double)mBlue[pos]);

				if(xct > 0){
					C_Vector otherVec = new C_Vector( (double)mRed[left], (double)mGreen[left], (double)mBlue[left]);

					double length = otherVec.mSubtract(vec).mLength();

					if(length > thr){
						dstRaw.mRed[pos] = mRed[pos];
						dstRaw.mGreen[pos] = mGreen[pos];
						dstRaw.mBlue[pos] = mBlue[pos];
					}
				}

				if(xct < (mWidth - 1)){
					C_Vector otherVec = new C_Vector( (double)mRed[right], (double)mGreen[right], (double)mBlue[right]);

					double length = otherVec.mSubtract(vec).mLength();

					if(length > thr){
						dstRaw.mRed[pos] = mRed[pos];
						dstRaw.mGreen[pos] = mGreen[pos];
						dstRaw.mBlue[pos] = mBlue[pos];
					}
				}

				if(yct > 0){
					C_Vector otherVec = new C_Vector( (double)mRed[top], (double)mGreen[top], (double)mBlue[top]);

					double length = otherVec.mSubtract(vec).mLength();

					if(length > thr){
						dstRaw.mRed[pos] = mRed[pos];
						dstRaw.mGreen[pos] = mGreen[pos];
						dstRaw.mBlue[pos] = mBlue[pos];
					}
				}

				if(yct < (mHeight - 1)){
					C_Vector otherVec = new C_Vector( (double)mRed[bottom], (double)mGreen[bottom], (double)mBlue[bottom]);

					double length = otherVec.mSubtract(vec).mLength();

					if(length > thr){
						dstRaw.mRed[pos] = mRed[pos];
						dstRaw.mGreen[pos] = mGreen[pos];
						dstRaw.mBlue[pos] = mBlue[pos];
					}
				}
			}
		}

		return dstRaw;
	}
}

