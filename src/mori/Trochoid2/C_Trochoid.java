package mori.Trochoid2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class C_Trochoid implements mori.Shape{
	private mori.Toda mToda = null;

	private Object[] mColorData = new Object[LAYER_NUM];

	private int mArgb = 0;

	private double mT = 0.0;

	private final static int WIDTH = 800;

	private int mIntervalX = 50;

	private final static int PARTICLE_NUM = 512;

	private final static int LAYER_NUM = 20;

	private C_Particle[][] mParticle = new C_Particle[LAYER_NUM][PARTICLE_NUM];

	public C_Trochoid(mori.Toda in){
		this.mToda = in;

		int pct = 0;
		for(int layer = 0; layer < LAYER_NUM; layer++){

			mArgb = (new Color(10 * layer, 10 * layer, 255)).getRGB();

			mColorData[layer] = mToda.mO_ColorModel.getDataElements(mArgb, null);

			pct = 0;
			for(int xct = - mIntervalX; xct <= (WIDTH + mIntervalX); xct += mIntervalX){

				mParticle[layer][pct] = new C_Particle(
											(double)xct,
											-0.5 * (double)layer);
				pct++;
			}
		}
	}

	public void draw(){
		C_Vertex v0 = new C_Vertex();
		C_Vertex vp1 = new C_Vertex();

		for(int layer = 0; layer < LAYER_NUM; layer++){
			for(int pct = 0; mParticle[layer][pct] != null; pct++){
				mParticle[layer][pct].mExe(mT);
			}
		}

		for(int layer = 0; layer < LAYER_NUM; layer++){
			for(int pct = 0; mParticle[layer][pct + 1] != null; pct++){

				mParticle[layer][pct].mGetVertex(v0);
				mParticle[layer][pct + 1].mGetVertex(vp1);

				mDrawLine(
					v0.mX,
					300.0 + v0.mY,
					vp1.mX,
					300.0 + vp1.mY,
					mColorData[layer]);
			}
		}

		try{
			Thread.sleep(100);
		}catch(Exception ex){
		}

		mT += 1.0;
	}

	private void mDrawLine(
		double aX0,
		double aY0,
		double aX1,
		double aY1,
		Object aColor
	){
		double absX = Math.abs(aX1 - aX0);
		double absY = Math.abs(aY1 - aY0);

		if(absX > absY){

			if(aX0 < aX1){
				double slope = (aY1 - aY0) / absX;

				for(double length = 0; length < absX; length += 1.0){
					double xPos = aX0 + length;
					double yPos = aY0 + slope * length;
					try{
						mToda.mO_Raster.setDataElements( (int)xPos, (int)yPos, aColor);
					}catch(Exception ex){
					}
				}
			}else{
				double slope = - (aY1 - aY0) / absX;

				for(double length = 0; length < absX; length += 1.0){
					double xPos = aX1 + length;
					double yPos = aY1 + slope * length;
					try{
						mToda.mO_Raster.setDataElements( (int)xPos, (int)yPos, aColor);
					}catch(Exception ex){
					}
				}
			}
		}else{

			if(aY0 < aY1){
				double slope = (aX1 - aX0) / absY;

				for(double length = 0; length < absY; length += 1.0){
					double xPos = aX0 + slope * length;
					double yPos = aY0 + length;
					try{
						mToda.mO_Raster.setDataElements( (int)xPos, (int)yPos, aColor);
					}catch(Exception ex){
					}
				}
			}else{
				double slope = - (aX1 - aX0) / absY;

				for(double length = 0; length < absY; length += 1.0){
					double xPos = aX1 + slope * length;
					double yPos = aY1 + length;
					try{
						mToda.mO_Raster.setDataElements( (int)xPos, (int)yPos, aColor);
					}catch(Exception ex){
					}
				}
			}
		}
	}
}

