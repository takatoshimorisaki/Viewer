
package mori;

import java.awt.image.BufferedImage;

/*!
	@brief	ヒストグラム等価処理を行う
*/
public class EqualHist{

	//! 原画像
	private BufferedImage mOrgImage;

	//! 処理後の画像
	private BufferedImage mOutImage;

	//! 原画像のARGB値
	private int[] mOrgARGB;

	//! 原画像のアルファ値
	private int[] mOrgAlpha;

	//! 原画像の赤値
	private int[] mOrgRed;

	//! 原画像の緑値
	private int[] mOrgGreen;

	//! 原画像の青値
	private int[] mOrgBlue;

	//! 原画像の赤値のヒストグラム
	private int[] mOrgRedHist;

	//! 原画像の緑値のヒストグラム
	private int[] mOrgGreenHist;

	//! 原画像の青値のヒストグラム
	private int[] mOrgBlueHist;

	//! 処理後の画像のARGB値
	private int[] mOutARGB;

	//! 処理後の画像のアルファ値
	private int[] mOutAlpha;

	//! 処理後の画像の赤値
	private int[] mOutRed;

	//! 処理後の画像の緑値
	private int[] mOutGreen;

	//! 処理後の画像の青値
	private int[] mOutBlue;

	//! 画像処理する幅
	private int mWidth;

	//! 画像処理する高さ
	private int mHeight;

	public EqualHist(
		BufferedImage aImage,
		int aWidth,
		int aHeight
	){

		// 画像処理する幅
		mWidth = aWidth; // mOrgImage.getWidth();

		// 画像処理する高さ
		mHeight = aHeight; // mOrgImage.getHeight();

		// 原画像の赤値のヒストグラムを生成する
		mOrgRedHist = new int[256];

		// 原画像の赤値のヒストグラムを初期化する
		for(int ict = 0; ict < mOrgRedHist.length; ict++){
			mOrgRedHist[ict] = 0;
		}

		// 原画像の緑値のヒストグラムを生成する
		mOrgGreenHist = new int[256];

		// 原画像の緑値のヒストグラムを初期化する
		for(int ict = 0; ict < mOrgGreenHist.length; ict++){
			mOrgGreenHist[ict] = 0;
		}

		// 原画像の青値のヒストグラムを生成する
		mOrgBlueHist = new int[256];

		// 原画像の青値のヒストグラムを初期化する
		for(int ict = 0; ict < mOrgBlueHist.length; ict++){
			mOrgBlueHist[ict] = 0;
		}

		// 原画像を退避する
		mOrgImage = aImage;

		// 原画像のARGB値を生成する
		mOrgARGB = new int[mWidth * mHeight];

		// 原画像のアルファ値を生成する
		mOrgAlpha = new int[ mOrgARGB.length ];

		// 原画像の赤値を生成する
		mOrgRed = new int[ mOrgARGB.length ];

		// 原画像の緑値を生成する
		mOrgGreen = new int[ mOrgARGB.length ];

		// 原画像の青値を生成する
		mOrgBlue = new int[ mOrgARGB.length ];

		// 原画像のRGB値を取得する
		mOrgImage.getRGB(
			0,
			0,
			mWidth,
			mHeight,
			mOrgARGB,
			0,
			mWidth);

		// 原画像の各ARGB値を取得する
		for(int ict = 0; ict < mOrgARGB.length; ict++){

			// 原画像のアルファ値を取得する
			mOrgAlpha[ict] = (mOrgARGB[ict] & 0xff000000) / (0x1000000);

			// 原画像の赤値を取得する
			mOrgRed[ict] = (mOrgARGB[ict] & 0xff0000) / (0x10000);

			// 原画像の緑値を取得する
			mOrgGreen[ict] = (mOrgARGB[ict] & 0xff00) / (0x100);

			// 原画像の青値を取得する
			mOrgBlue[ict] = (mOrgARGB[ict] & 0xff);

			// 原画像の赤値のヒストグラムを更新する
			mOrgRedHist[ mOrgRed[ict] ]++;

			// 原画像の緑値のヒストグラムを更新する
			mOrgGreenHist[ mOrgGreen[ict] ]++;

			// 原画像の青値のヒストグラムを更新する
			mOrgBlueHist[ mOrgBlue[ict] ]++;

		}

		// 処理後の画像を初期化する
		mOutImage = new BufferedImage(mOrgImage.getWidth(), mOrgImage.getHeight(), BufferedImage.TYPE_INT_RGB);

		// 処理後の画像を生成する
		mOutARGB = new int[mWidth * mHeight];

		// 処理後の画像のアルファ値を生成する
		mOutAlpha = new int[ mOutARGB.length ];

		// 処理後の画像の赤値を生成する
		mOutRed = new int[ mOutARGB.length ];

		//! 処理後の画像の緑値を生成する
		mOutGreen = new int[ mOutARGB.length ];

		//! 処理後の画像の青値を生成する
		mOutBlue = new int[ mOutARGB.length ];

		// ヒストグラム等価処理を行う
		this.run();

		// 処理後の画像のARGB値を設定する
		for(int ict = 0; ict < mOutARGB.length; ict++){

			mOutARGB[ict] = mOutBlue[ict];

			mOutARGB[ict] += mOutGreen[ict] * 0x100;

			mOutARGB[ict] += mOutRed[ict] * 0x10000;

			mOutARGB[ict] += mOutAlpha[ict] * 0x1000000;

		}

		// 処理後の画像のRGB値を処理後の画像に設定する
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
		@brief	ヒストグラム等価処理を行う

	*/
	public void run(){
		// 赤の分布
		double[] rDist = new double[256];

		// 赤の分布を設定する
		rDist[0] = 255.0 * (double)mOrgRedHist[0] / (double)mOrgRed.length;
		for(int ict = 1; ict < rDist.length; ict++){
			rDist[ict] = rDist[ict-1] + 255.0 * (double)mOrgRedHist[ict] / (double)mOrgRed.length;
		}

		// 緑の分布
		double[] gDist = new double[256];

		// 緑の分布を設定する
		gDist[0] = 255.0 * (double)mOrgGreenHist[0] / (double)mOrgGreen.length;
		for(int ict = 1; ict < gDist.length; ict++){
			gDist[ict] = gDist[ict-1] + 255.0 * (double)mOrgGreenHist[ict-1] / (double)mOrgGreen.length;
		}

		// 青の分布
		double[] bDist = new double[256];

		// 青の分布を設定する
		bDist[0] = 255.0 * (double)mOrgBlueHist[0] / (double)mOrgBlue.length;
		for(int ict = 1; ict < bDist.length; ict++){
			bDist[ict] = bDist[ict-1] + 255.0 * (double)mOrgBlueHist[ict-1] / (double)mOrgBlue.length;
		}

		// アルファ値を設定する
		for(int ict = 0; ict < mOutAlpha.length; ict++){
			mOutAlpha[ict] = mOrgAlpha[ict];
		}

		// 赤値を設定する
		for(int ict = 0; ict < mOutRed.length; ict++){
			mOutRed[ict] = (int)rDist[ mOrgRed[ict] ];

			if(mOutRed[ict] > 255){
				mOutRed[ict] = 255;
			}
		}

		// 緑値を設定する
		for(int ict = 0; ict < mOutGreen.length; ict++){
			mOutGreen[ict] = (int)gDist[ mOrgGreen[ict] ];

			if(mOutGreen[ict] > 255){
				mOutGreen[ict] = 255;
			}
		}

		// 青値を設定する
		for(int ict = 0; ict < mOutBlue.length; ict++){
			mOutBlue[ict] = (int)bDist[ mOrgBlue[ict] ];

			if(mOutBlue[ict] > 255){
				mOutBlue[ict] = 255;
			}
		}

	}

	/*!
		@brief	処理後の画像を取得する

	*/
	public BufferedImage get(){
		return mOutImage;
	}
}


