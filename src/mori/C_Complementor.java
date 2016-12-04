package mori;

public class C_Complementor extends C_RawData implements I_ImageProcessor{

	public C_Complementor(
		java.awt.image.BufferedImage aImage
	){
		super(aImage);
	}

	public C_Complementor(
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

		for(int pos = 0; pos < size; pos++){

			int max = mMax(mRed[pos], mGreen[pos], mBlue[pos]);

			int min = mMin(mRed[pos], mGreen[pos], mBlue[pos]);

			int sum = max + min;

			dstRaw.mRed[pos] = sum - mRed[pos];
			dstRaw.mGreen[pos] = sum - mGreen[pos];
			dstRaw.mBlue[pos] = sum - mBlue[pos];
		}

		return dstRaw;
	}

	private int mMax(
		int aR,
		int aG,
		int aB
	){
		int ans = 0;

		if(aR > aG){

			if(aR > aB){

				ans = aR;

			}else{

				ans = aB;
			}
		}else if(aG > aB){

			ans = aG;

		}else{

			ans = aB;
		}

		return ans;
	}

	private int mMin(
		int aR,
		int aG,
		int aB
	){
		int ans = 0;

		if(aR < aG){

			if(aR < aB){

				ans = aR;

			}else{

				ans = aB;
			}
		}else if(aG < aB){

			ans = aG;

		}else{

			ans = aB;
		}

		return ans;
	}
}

