package mori.Domino;

class C_Pattern{

	public final static int WIDTH = 2;

	public final static int HEIGHT = 2;

	public int[][] mGrayScale;

	public C_Pattern(
		int aNum
	){
		mGrayScale = new int[HEIGHT][WIDTH];

		int bit = 0;
		for(int yct = 0; yct < HEIGHT; yct++){
			for(int xct = 0; xct < WIDTH; xct++){
				mGrayScale[yct][xct] = (aNum >> bit) & 0b1;
				mGrayScale[yct][xct] *= 255;
				bit++;
			}
		}
	}

	public int[][] mGetPattern(){
			return mGrayScale;
	}

	public double mCalcSimilarity(
		int[][] aPiese
	){
		double ans = 0;

		for(int yct = 0; yct < HEIGHT; yct++){
			for(int xct = 0; xct < WIDTH; xct++){

				ans += Math.abs(mGrayScale[yct][xct] - aPiese[yct][xct]);
			}
		}
	
		return ans;
	}
}

