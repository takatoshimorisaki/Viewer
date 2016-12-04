
package mori;

import java.awt.image.BufferedImage;

/*!
	@brief	�q�X�g�O���������������s��
*/
public class EqualHist{

	//! ���摜
	private BufferedImage mOrgImage;

	//! ������̉摜
	private BufferedImage mOutImage;

	//! ���摜��ARGB�l
	private int[] mOrgARGB;

	//! ���摜�̃A���t�@�l
	private int[] mOrgAlpha;

	//! ���摜�̐Ԓl
	private int[] mOrgRed;

	//! ���摜�̗Βl
	private int[] mOrgGreen;

	//! ���摜�̐l
	private int[] mOrgBlue;

	//! ���摜�̐Ԓl�̃q�X�g�O����
	private int[] mOrgRedHist;

	//! ���摜�̗Βl�̃q�X�g�O����
	private int[] mOrgGreenHist;

	//! ���摜�̐l�̃q�X�g�O����
	private int[] mOrgBlueHist;

	//! ������̉摜��ARGB�l
	private int[] mOutARGB;

	//! ������̉摜�̃A���t�@�l
	private int[] mOutAlpha;

	//! ������̉摜�̐Ԓl
	private int[] mOutRed;

	//! ������̉摜�̗Βl
	private int[] mOutGreen;

	//! ������̉摜�̐l
	private int[] mOutBlue;

	//! �摜�������镝
	private int mWidth;

	//! �摜�������鍂��
	private int mHeight;

	public EqualHist(
		BufferedImage aImage,
		int aWidth,
		int aHeight
	){

		// �摜�������镝
		mWidth = aWidth; // mOrgImage.getWidth();

		// �摜�������鍂��
		mHeight = aHeight; // mOrgImage.getHeight();

		// ���摜�̐Ԓl�̃q�X�g�O�����𐶐�����
		mOrgRedHist = new int[256];

		// ���摜�̐Ԓl�̃q�X�g�O����������������
		for(int ict = 0; ict < mOrgRedHist.length; ict++){
			mOrgRedHist[ict] = 0;
		}

		// ���摜�̗Βl�̃q�X�g�O�����𐶐�����
		mOrgGreenHist = new int[256];

		// ���摜�̗Βl�̃q�X�g�O����������������
		for(int ict = 0; ict < mOrgGreenHist.length; ict++){
			mOrgGreenHist[ict] = 0;
		}

		// ���摜�̐l�̃q�X�g�O�����𐶐�����
		mOrgBlueHist = new int[256];

		// ���摜�̐l�̃q�X�g�O����������������
		for(int ict = 0; ict < mOrgBlueHist.length; ict++){
			mOrgBlueHist[ict] = 0;
		}

		// ���摜��ޔ�����
		mOrgImage = aImage;

		// ���摜��ARGB�l�𐶐�����
		mOrgARGB = new int[mWidth * mHeight];

		// ���摜�̃A���t�@�l�𐶐�����
		mOrgAlpha = new int[ mOrgARGB.length ];

		// ���摜�̐Ԓl�𐶐�����
		mOrgRed = new int[ mOrgARGB.length ];

		// ���摜�̗Βl�𐶐�����
		mOrgGreen = new int[ mOrgARGB.length ];

		// ���摜�̐l�𐶐�����
		mOrgBlue = new int[ mOrgARGB.length ];

		// ���摜��RGB�l���擾����
		mOrgImage.getRGB(
			0,
			0,
			mWidth,
			mHeight,
			mOrgARGB,
			0,
			mWidth);

		// ���摜�̊eARGB�l���擾����
		for(int ict = 0; ict < mOrgARGB.length; ict++){

			// ���摜�̃A���t�@�l���擾����
			mOrgAlpha[ict] = (mOrgARGB[ict] & 0xff000000) / (0x1000000);

			// ���摜�̐Ԓl���擾����
			mOrgRed[ict] = (mOrgARGB[ict] & 0xff0000) / (0x10000);

			// ���摜�̗Βl���擾����
			mOrgGreen[ict] = (mOrgARGB[ict] & 0xff00) / (0x100);

			// ���摜�̐l���擾����
			mOrgBlue[ict] = (mOrgARGB[ict] & 0xff);

			// ���摜�̐Ԓl�̃q�X�g�O�������X�V����
			mOrgRedHist[ mOrgRed[ict] ]++;

			// ���摜�̗Βl�̃q�X�g�O�������X�V����
			mOrgGreenHist[ mOrgGreen[ict] ]++;

			// ���摜�̐l�̃q�X�g�O�������X�V����
			mOrgBlueHist[ mOrgBlue[ict] ]++;

		}

		// ������̉摜������������
		mOutImage = new BufferedImage(mOrgImage.getWidth(), mOrgImage.getHeight(), BufferedImage.TYPE_INT_RGB);

		// ������̉摜�𐶐�����
		mOutARGB = new int[mWidth * mHeight];

		// ������̉摜�̃A���t�@�l�𐶐�����
		mOutAlpha = new int[ mOutARGB.length ];

		// ������̉摜�̐Ԓl�𐶐�����
		mOutRed = new int[ mOutARGB.length ];

		//! ������̉摜�̗Βl�𐶐�����
		mOutGreen = new int[ mOutARGB.length ];

		//! ������̉摜�̐l�𐶐�����
		mOutBlue = new int[ mOutARGB.length ];

		// �q�X�g�O���������������s��
		this.run();

		// ������̉摜��ARGB�l��ݒ肷��
		for(int ict = 0; ict < mOutARGB.length; ict++){

			mOutARGB[ict] = mOutBlue[ict];

			mOutARGB[ict] += mOutGreen[ict] * 0x100;

			mOutARGB[ict] += mOutRed[ict] * 0x10000;

			mOutARGB[ict] += mOutAlpha[ict] * 0x1000000;

		}

		// ������̉摜��RGB�l��������̉摜�ɐݒ肷��
		mOutImage.setRGB(
			0,
			0,
			mWidth,
			mHeight,
			mOutARGB,
			0,
			mWidth);

	}

	/*!
		@brief	�q�X�g�O���������������s��

	*/
	public void run(){
		// �Ԃ̕��z
		double[] rDist = new double[256];

		// �Ԃ̕��z��ݒ肷��
		rDist[0] = 255.0 * (double)mOrgRedHist[0] / (double)mOrgRed.length;
		for(int ict = 1; ict < rDist.length; ict++){
			rDist[ict] = rDist[ict-1] + 255.0 * (double)mOrgRedHist[ict] / (double)mOrgRed.length;
		}

		// �΂̕��z
		double[] gDist = new double[256];

		// �΂̕��z��ݒ肷��
		gDist[0] = 255.0 * (double)mOrgGreenHist[0] / (double)mOrgGreen.length;
		for(int ict = 1; ict < gDist.length; ict++){
			gDist[ict] = gDist[ict-1] + 255.0 * (double)mOrgGreenHist[ict-1] / (double)mOrgGreen.length;
		}

		// �̕��z
		double[] bDist = new double[256];

		// �̕��z��ݒ肷��
		bDist[0] = 255.0 * (double)mOrgBlueHist[0] / (double)mOrgBlue.length;
		for(int ict = 1; ict < bDist.length; ict++){
			bDist[ict] = bDist[ict-1] + 255.0 * (double)mOrgBlueHist[ict-1] / (double)mOrgBlue.length;
		}

		// �A���t�@�l��ݒ肷��
		for(int ict = 0; ict < mOutAlpha.length; ict++){
			mOutAlpha[ict] = mOrgAlpha[ict];
		}

		// �Ԓl��ݒ肷��
		for(int ict = 0; ict < mOutRed.length; ict++){
			mOutRed[ict] = (int)rDist[ mOrgRed[ict] ];

			if(mOutRed[ict] > 255){
				mOutRed[ict] = 255;
			}
		}

		// �Βl��ݒ肷��
		for(int ict = 0; ict < mOutGreen.length; ict++){
			mOutGreen[ict] = (int)gDist[ mOrgGreen[ict] ];

			if(mOutGreen[ict] > 255){
				mOutGreen[ict] = 255;
			}
		}

		// �l��ݒ肷��
		for(int ict = 0; ict < mOutBlue.length; ict++){
			mOutBlue[ict] = (int)bDist[ mOrgBlue[ict] ];

			if(mOutBlue[ict] > 255){
				mOutBlue[ict] = 255;
			}
		}

	}

	/*!
		@brief	������̉摜���擾����

	*/
	public BufferedImage get(){
		return mOutImage;
	}
}


