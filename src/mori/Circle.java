
package mori;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Circle implements Shape{
	private Toda toda = null;
	private Object colorData = null;
	private int argb = 0;
	private double mElapsed = 0.0;

	public Circle(Toda in){
		this.toda = in;
	}
	
	public void draw(){
		double theta = 0.0;
		double tmp = 10.0 * Math.PI * mElapsed/180.0;
		int x = (int)(40.0*Math.cos(tmp));
		int y = (int)(40.0*Math.sin(tmp));
		
		argb = (new Color(255, 0, 0)).getRGB();
		colorData = toda.mO_ColorModel.getDataElements(argb, null);
		for(theta = 0.0; theta <= 2.0*Math.PI; theta += 0.01){
			toda.mO_Raster.setDataElements(300+x+(int)(100.0*Math.cos(theta)), 200+y+(int)(100.0*Math.sin(theta)), colorData);
			toda.mO_Raster.setDataElements(300+x+(int)(80.0*Math.cos(theta)), 200-y+(int)(80.0*Math.sin(theta)), colorData);
		}
		
		mElapsed += 1.0;
	}
}


