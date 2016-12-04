package mori;

import java.util.Random;

public class C_Random{

	private Random mRandom = new Random();

	/*!
		@brief	greater than or equal to aMin,<br>
				less than aMax

		@param aMin minimum value
		@param aMax maximum value
	*/
	public double mNextDouble(
		double aMin,
		double aMax
	){
		double ans = aMin + (aMax - aMin) * mRandom.nextDouble();

		return ans;
	}

}

