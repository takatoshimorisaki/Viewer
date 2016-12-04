package mori.Category;

public class C_Individual implements java.util.Comparator{

	private int[] mCategory;

	private C_Hue mHue;

	//! •]‰¿’l
	public double mEvaluation;

	public C_Individual(){
	}

	public C_Individual(
		C_Hue aHue,
		int aCategoryNum
	){
		java.util.Random random = new java.util.Random();
		mHue = aHue;

		mCategory = new int[aCategoryNum];

		for(int cnt = 0; cnt < mCategory.length; cnt++){
			mCategory[cnt] = random.nextInt(360);
		}
	}

	public double mEvaluate(){
		mEvaluation = 0;

		for(int pos = 0; pos < mHue.mGetLength(); pos++){
			int distance = Integer.MAX_VALUE;
			for(int cnt = 0; cnt < mCategory.length; cnt++){
				int abs = Math.abs(mHue.mGetHue(pos) - mCategory[cnt]);
				if(abs < distance){
					distance = abs;
					mHue.mCalcRGB((double)mCategory[cnt], pos);
				}
			}
			mEvaluation += distance;
		}

		return mEvaluation;
	}

	public int compare(Object o1, Object o2) {
		double left = ((C_Individual)o1).mEvaluation;
		double right = ((C_Individual)o2).mEvaluation;

		if(left < right){
			return -1;
		}else if(left > right){
			return 1;
		}else{
			return 0;
		}
	}

	public int[] mGetCategory(){
		return mCategory;
	}

	public C_Individual mCopy(){
		C_Individual ans = new C_Individual();

		ans.mHue = mHue;

		ans.mCategory = new int[mCategory.length];

		System.arraycopy(mCategory, 0, ans.mCategory, 0, mCategory.length);

		ans.mEvaluation = mEvaluation;

		return ans;
	}
}

