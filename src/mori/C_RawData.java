package mori;

import static java.lang.System.out;
import java.awt.image.BufferedImage;

/*
	@brief	���摜�f�[�^����������
*/
public class C_RawData{

	//! ��
	public int mWidth;

	//! ����
	public int mHeight;

	//! �ԐF�̋P�x
	public int[] mRed;

	//! �ΐF�̋P�x
	public int[] mGreen;

	//! �F�̋P�x
	public int[] mBlue;

	//! �R���X�g���N�^
	public C_RawData(
		java.awt.image.BufferedImage aImage
	){
		mWidth = aImage.getWidth();

		mHeight = aImage.getHeight();

		mRed = new int[mWidth * mHeight];

		mGreen = new int[mWidth * mHeight];

		mBlue = new int[mWidth * mHeight];

		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){

				int pos = xct + yct * mWidth;

				int lumi = aImage.getRGB(xct, yct);

				mRed[pos] = (0x00ff0000 & lumi) >> 16;

				mGreen[pos] = (0x0000ff00 & lumi) >> 8;

				mBlue[pos] = (0x000000ff & lumi);

			}
		}

	}

	public BufferedImage mGetBufferedImage(){

		BufferedImage bi = new BufferedImage(
							mWidth, 
							mHeight, 
							BufferedImage.TYPE_INT_RGB);

		for(int yct = 0; yct < mHeight; yct++){
			for(int xct = 0; xct < mWidth; xct++){

				int pos = xct + yct * mWidth;

				int lumi = mBlue[pos];

				lumi += mGreen[pos] << 8;

				lumi += mRed[pos] << 16;

				bi.setRGB(xct, yct, lumi);

			}
		}

		return bi;
	}

	/*
		@brief	�R���X�g���N�^
	*/
	public C_RawData(
		// ��
		int aWidth,
		// ����
		int aHeight
	){

		// ����ݒ肷��
		mWidth = aWidth;

		// ������ݒ肷��
		mHeight = aHeight;

		// �ԐF�̋P�x�𐶐�����
		mRed = new int[mWidth * mHeight];

		// �ΐF�̋P�x�𐶐�����
		mGreen = new int[mWidth * mHeight];

		// �F�̋P�x�𐶐�����
		mBlue = new int[mWidth * mHeight];

	}

	/*
		@brief	(X, Y)���W����z��ԍ����v�Z����
	*/
	public int mCalcPos(
		// X���W
		int aX,
		// Y���W
		int aY
	){
		int pos = aY * mWidth + aX;

		return pos;
	}

	/*
		@brief	��������
	*/
	public C_RawData mCopy(){
		C_RawData lO_Rtn = new C_RawData(mWidth, mHeight);

		for(int pos = 0; pos < mWidth * mHeight; pos++){

			lO_Rtn.mRed[pos] = this.mRed[pos];

			lO_Rtn.mGreen[pos] = this.mGreen[pos];

			lO_Rtn.mBlue[pos] = this.mBlue[pos];
		}

		return lO_Rtn;
	}

	public void mSetRGB(
		int aX,
		int aY,
		int aR,
		int aG,
		int aB
	){
		if(aX >= 0 && aX < mWidth && aY >= 0 && aY < mHeight){
			int pos = aY * mWidth + aX;
			mRed[pos] = aR;
			mGreen[pos] = aG;
			mBlue[pos] = aB;
		}
	}

	public boolean mGetRGB(
		int aX,
		int aY,
		C_RGB aRGB
	){
		if(aX >= 0 && aX < mWidth && aY >= 0 && aY < mHeight){
			int pos = aY * mWidth + aX;
			aRGB.Red = mRed[pos];
			aRGB.Green = mGreen[pos];
			aRGB.Blue = mBlue[pos];

			return true;
		}else{
			aRGB.Red = 0;
			aRGB.Green = 0;
			aRGB.Blue = 0;

			return false;
		}
	}
}


