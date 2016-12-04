package mori.Domino;

public class C_GrayScale{

	public int mExe(
		int aRed,
		int aGreen,
		int aBlue
	){
		double y =  0.299 * aRed + 0.587 * aGreen + 0.114 * aBlue;
		return (int)y;
	}
}

