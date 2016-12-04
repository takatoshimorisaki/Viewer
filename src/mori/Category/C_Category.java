package mori.Category;

import mori.C_RawData;
import mori.I_ImageProcessor;
import mori.C_ImageProcessParameter;

public class C_Category extends C_RawData implements I_ImageProcessor{

	private int mCategoryNum;

	private C_Hue mHue;

	public C_Category(
		java.awt.image.BufferedImage aImage,
		C_ImageProcessParameter aPrmtr
	){
		super(aImage);

		mCategoryNum = aPrmtr.mCategoryNum;

		// ‰æ‘œ‚©‚çF‘Š‚ğæ“¾‚·‚é
		mHue = new C_Hue(aImage);
		mHue.mExe();
	}

	public C_Category(
		// •
		int aWidth,
		// ‚‚³
		int aHeight
	){
		super(aWidth, aHeight);
	}

	public C_RawData mExe(){
		double min = Double.MAX_VALUE;
		C_Individual elite = null;

		// ‰ŠúŒÂ‘Ì‚Ì¶¬
		for(int cnt = 0; cnt < C_GeneticPrmtr.INIT_INDIVIDUAL_NUM; cnt++){

			C_Individual individual = new C_Individual(mHue, mCategoryNum);
			
			double evaluation = individual.mEvaluate();

			if(min > evaluation){
				min = evaluation;

				elite = individual.mCopy();
			}
		}

		int[] category = elite.mGetCategory();

		return mHue.mGetDstRaw();
	}
}

