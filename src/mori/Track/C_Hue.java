package mori.Track;

import java.awt.Color;

public class C_Hue{

	private int mMax = 255;

	private int mMin = 0;

	public int mGetRGB(double aRad){
		int red = 0, green = 0, blue = 0;

		double hue = (int)(180.0 * aRad / Math.PI) % 360;

		if(hue >= 0.0 && hue < 60.0){

			red = mMax;
			green = (int)(mMin + hue * (mMax - mMin) / 60.0);
			blue = mMin;

		}else if(hue < 120.0){

			red = (int)(mMax - (hue - 60.0) * (mMax - mMin) / 60.0);
			green = mMax;
			blue = mMin;

		}else if(hue < 180.0){

			red = mMin;
			green = mMax;
			blue = (int)(mMin + (hue - 120.0) * (mMax - mMin) / 60.0);

		}else if(hue < 240.0){

			red = mMin;
			green = (int)(mMax - (hue - 180.0) * (mMax - mMin) / 60.0);
			blue = mMax;

		}else if(hue < 300.0){

			red = (int)(mMin + (hue - 240.0) * (mMax - mMin) / 60.0);
			green = mMin;
			blue = mMax;

		}else{

			red = mMax;
			green = mMin;
			blue = (int)(mMax - (hue - 300.0) * (mMax - mMin) / 60.0);

		}

		int argb = (new Color(red, green, blue)).getRGB();

		return argb;
	}
}

