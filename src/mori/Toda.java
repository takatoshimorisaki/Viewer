package mori;

import static java.lang.System.out;
import static mori.E_IMAGE_PROCESS_METHOD.*;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.awt.Graphics2D;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.zip.GZIPInputStream;

import javax.imageio.ImageIO;

import mori.Surface.C_Surface;
/*
	@brief	画像を読み込み処理する
*/
public class Toda extends Thread{

	//! パネル
	private ImagePanel mO_ImagePanel = null;

	//! 点及び線描画のための画像
	private BufferedImage   mO_Image;

	//! 前描画　画像
	private C_RawData mRaw1;

	//! 点及び線描画のための軌跡図
	public WritableRaster  mO_Raster;

	//! 画像に対応した色モデル
	public ColorModel      mO_ColorModel;

	//! 
	private   Object          mO_ColorData;

	//! グラフィックス・コンテキスト
	Graphics2D mO_GC;

	//! ファイルから読み込んだ画像
	BufferedImage mO_LoadImage;

	//! 点及び線描画の形
	protected String mO_Type = null;

	//! 点及び線描画の生成
	protected Shape mO_Shape = null;

	//! ファイル名
	String mO_FileName;

	private I_ImageProcessor mI_ImageProcess;

	private boolean mPreferRead = false;

	private Mouse mMouse;

	/*!
		@brief	属性を初期化する

	*/
	public Toda(ImagePanel in){
		mO_ImagePanel = in;

		// パネルのサイズに合わせて画像を生成する
		mO_Image = new BufferedImage(in.MAX_XWIDTH, in.MAX_YHEIGHT, BufferedImage.TYPE_INT_RGB);
		mO_Raster = mO_Image.getRaster();
		mO_ColorModel = mO_Image.getColorModel();
		mRaw1 = new C_RawData(mO_Image);

		mGenarate();

	}

	private Toda(){

	}
	
	public void mSetMouse(
		Mouse aMouse
	){
		mMouse = aMouse;
	}

	public void mClear(){
		mO_Shape = null;

		mO_GC = null;

		mO_FileName = null;

		mGenarate();

		mO_ImagePanel.repaint();
	}

	/*!
		@brief	背景が黒の画像を描画する

	*/
	public void mGenarate(){
		int xct, yct, argb;
		int height = mO_Image.getHeight();
		int width  = mO_Image.getWidth();

		argb = (new Color(0, 0, 0)).getRGB();

		mO_ColorData = mO_ColorModel.getDataElements(argb, null);
		for(yct = 0; yct < height; yct++){
			for(xct = 0; xct < width; xct++){
				mO_Raster.setDataElements(xct, yct, mO_ColorData);
			}
		}
	}

	public void mSetType(String in){
		mO_Type = in;

		if(mO_Type.equals("STOP")){
			mO_Type = null;
			mO_Shape = null;
			mI_ImageProcess = null;
		}else if(mO_Type.equals("CIRCLE")){
			mO_Shape = new Circle(this);
		}else if(mO_Type.equals("STRING")){
			mO_Shape = new C_String(this);
		}else if(mO_Type.equals("TROCHOID")){
			mO_Shape = new mori.Trochoid.C_Trochoid(this);
		}else if(mO_Type.equals("TROCHOID2")){
			mO_Shape = new mori.Trochoid2.C_Trochoid(this);
		}else if(mO_Type.equals("TROCHOID3")){
			mI_ImageProcess = new mori.Trochoid3.C_Surface(mO_Image);
		}else if(mO_Type.equals("TROCHOID4")){
			mI_ImageProcess = new mori.Trochoid4.C_Surface(mO_Image);
		}else if(mO_Type.equals("TROCHOID5")){
			mI_ImageProcess = new mori.Trochoid5.C_Surface(mO_Image);
		}else if(mO_Type.equals("MAGMA")){
			mI_ImageProcess = new mori.Magma.C_Surface(mO_Image);
		}else if(mO_Type.equals("HUE_TRANSITION")){
			mI_ImageProcess = new C_HueTransformer( mO_Image );
		}else if(mO_Type.equals("WAVE")){
			mI_ImageProcess = new C_Wave( mO_Image );
		}else if(mO_Type.equals("EM")){
			mI_ImageProcess = new mori.EM.CElectroMagnetic( mO_Image );
		}else if(mO_Type.equals("LENS")){
			mI_ImageProcess = new mori.Lens.C_Surface( mO_Image );
		}else if(mO_Type.equals("INVERSE_CIRCLE")){
			mI_ImageProcess = new mori.InverseCircle.C_Surface( mO_Image );
		}else if(mO_Type.equals("SURFACE")){
			mI_ImageProcess = new C_Surface( mO_Image );
		}else if(mO_Type.equals("SURFACE2")){
			mI_ImageProcess = new mori.Surface2.C_Surface( mO_Image );
		}else if(mO_Type.equals("SURFACE3")){
			mI_ImageProcess = new mori.Surface3.C_Surface( mO_Image );
		}else if(mO_Type.equals("MANY_CHORD")){
			mI_ImageProcess = new mori.ManyChord.C_ManyChord( mO_Image );
		}else if(mO_Type.equals("MANY_CHORD3")){
			mI_ImageProcess = new mori.ManyChord3.C_ManyChord( mO_Image );
		}else if(mO_Type.equals("TRACK")){
			mO_Shape = new mori.Track.C_Track(this);
		}else if(mO_Type.equals("TRACK_N")){
			mO_Shape = new mori.Track.C_TrackN(this);
		}

		System.out.println(mO_Type);
	}

	/*!
		引数で指定された位置の色を返す
	*/
	int mGetRGB(
		int x,
		int y
	){
		int ans = 0;
		try{
			ans = mO_Image.getRGB(
					x,
					y);
		}catch(Exception e){
		}
		return ans;
	}

	//! Raw画像ファイルを開く
	public void mOpenRawImage(
		String filename,
		int width,
		int height
	)throws Exception{
		InputStream in = new BufferedInputStream(new FileInputStream(filename));

		mO_Image = new BufferedImage(
							width, 
							height, 
							BufferedImage.TYPE_INT_RGB);
		mO_Raster = mO_Image.getRaster();
		mO_ColorModel = mO_Image.getColorModel();
		mO_GC = mO_Image.createGraphics();

		for(int yct = 0; yct < height; yct++){
			for(int xct = 0; xct < width; xct++){

				int lumi = in.read();

				int argb = (new Color(lumi, lumi, lumi)).getRGB();

				mO_ColorData = mO_ColorModel.getDataElements(argb, null);

				mO_Raster.setDataElements(xct, yct, mO_ColorData);
			}
		}

		in.close();

		mRaw1 = new C_RawData(mO_Image);
		mO_ImagePanel.repaint();
	}

	/*!
		@brief	ファイルから画像を読み込む
	*/
	public void mOpenImage(
		String filename
	)throws Exception{

		if(filename == null){
			mO_Image = new BufferedImage(Preference.CANVAS_WIDTH, Preference.CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
			mO_Raster = mO_Image.getRaster();
			mO_ColorModel = mO_Image.getColorModel();
			mO_GC = mO_Image.createGraphics();

			int argb = (new Color(255, 255, 255)).getRGB();

			mO_ColorData = mO_ColorModel.getDataElements(argb, null);
			for(int yct = 0; yct < mO_Image.getHeight(null); yct++){
				for(int xct = 0; xct < mO_Image.getWidth(null); xct++){
					mO_Raster.setDataElements(xct, yct, mO_ColorData);
				}
			}
		}else{
			mO_FileName = new String(filename);
			try{
				mO_LoadImage = ImageIO.read(new File(mO_FileName));
			}catch(Exception e){
				out.println(e);
				e.printStackTrace();
			}
			mO_Image = new BufferedImage(
								mO_LoadImage.getWidth(null), 
								mO_LoadImage.getHeight(null), 
								BufferedImage.TYPE_INT_RGB);

			mO_Raster = mO_Image.getRaster();
			mO_ColorModel = mO_Image.getColorModel();
			mO_GC = mO_Image.createGraphics();
			mO_GC.drawImage(mO_LoadImage, 0, 0, null);
		}

		mRaw1 = new C_RawData(mO_Image);
		mO_ImagePanel.repaint();
	}

	/*!
		@brief	ファイルに画像を保存する

	*/
	public void mSaveImage(String filename, String in){
        try{
			if( in.equals("Save") ){
				ImageIO.write(mO_Image, "jpeg", new File(filename));
			}else if( in.equals("Save_Bmp") ){
				ImageIO.write(mO_Image, "bmp", new File(filename));
			}
        }catch(Exception e){
            System.out.println(e);
			e.printStackTrace();
        }
	}

	public BufferedImage mGetImage(){

		if(mPreferRead == true){
			mPreferRead = false;
		}

		return mO_Image;
	}

	private void mCycleExe() throws Exception{

		if(mO_Type == null) return;

		if(mO_Shape != null){

			mO_Image = mRaw1.mGetBufferedImage();
			mO_Raster = mO_Image.getRaster();
			mO_ColorModel = mO_Image.getColorModel();
			mO_GC = mO_Image.createGraphics();

			mO_Shape.draw();

		}else if(mI_ImageProcess != null){
			
			C_RawData raw = mI_ImageProcess.mExe();

			mO_Image = raw.mGetBufferedImage();
			mO_Raster = mO_Image.getRaster();
			mO_ColorModel = mO_Image.getColorModel();
			mO_GC = mO_Image.createGraphics();
		}
	}

	/*
		@brief	周期処理を行う
	*/
	public void run(){
		try{
			Thread.sleep(100);

			for(;;){

				Thread.sleep(10);

				if(mO_Image != null
				&& mPreferRead == false){

					mCycleExe();

					mMouse.mExe(mO_Image);

					mPreferRead = true;

					mO_ImagePanel.repaint();

				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	//! 画像処理する
	public void mExe(
		C_ImageProcessParameter aPrmtr
	)throws Exception{

		if(aPrmtr.mMethod == E_CONTRACT_AVERAGE){
			mI_ImageProcess = new C_AverageContractor( mO_Image, aPrmtr.mRatioX, aPrmtr.mRatioY);
		}else if(aPrmtr.mMethod == E_CONTRACT_RANDOM){
			mI_ImageProcess = new C_RandomContractor( mO_Image, aPrmtr.mRatioX, aPrmtr.mRatioY);
		}else if(aPrmtr.mMethod == E_EQUIVALENCE_HISTOGRAM){
			mI_ImageProcess = new C_HistgramEquilizer( mO_Image );
		}else if(aPrmtr.mMethod == E_NURU){
			mI_ImageProcess = new C_Nuru(mO_Image);
		}else if(aPrmtr.mMethod == E_EXTRACT_EDGE_LUMI){
			mI_ImageProcess = new C_EdgeExtractor( mO_Image );
		}else if(aPrmtr.mMethod == E_UNDO){
			mI_ImageProcess = new C_Undo(mRaw1);
		}else if(aPrmtr.mMethod == E_EXTRACT_EDGE_RGB){
			mI_ImageProcess = new C_RGBExtractor( mO_Image );
		}else if(aPrmtr.mMethod == E_COMPLEMENTRAY_COLOR){
			mI_ImageProcess = new C_Complementor( mO_Image );
		}else if(aPrmtr.mMethod == E_SUBTRACT_COLOR){
			mI_ImageProcess = new C_Subtractor( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_EMBOSS){
			mI_ImageProcess = new C_Embosser( mO_Image );
		}else if(aPrmtr.mMethod == E_SWAP_RGB){
			mI_ImageProcess = new C_RGBSwapper( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_FOG){
			mI_ImageProcess = new C_Fog( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_CATEGORY){
			mI_ImageProcess = new mori.Category.C_Category( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_GRAY_SCALSE){
			mI_ImageProcess = new C_GrayScale( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_DOMINO){
			mI_ImageProcess = new mori.Domino.C_Domino( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_DOMINO_RGB){
			mI_ImageProcess = new mori.Domino.C_DominoRGB( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_CLOUD_EXTRACTION){
			mI_ImageProcess = new C_CloudExtractor( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_CLOUD_ERASURE){
			mI_ImageProcess = new C_CloudErasure( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_RAIN_EXTRACTION){
			mI_ImageProcess = new C_RainExtractor( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_RAIN_ERASURE){
			mI_ImageProcess = new C_RainErasure( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_90_DEGREES_CLOCKWISE){
			mI_ImageProcess = new C_Rotator( mO_Image , aPrmtr);
		}else if(aPrmtr.mMethod == E_GASKET){
			mI_ImageProcess = new mori.Fractal.Gasket( mO_Image, aPrmtr);
		}else if(aPrmtr.mMethod == E_GASKET2){
			mI_ImageProcess = new mori.Fractal.Gasket2( mO_Image, aPrmtr);
		}else if(aPrmtr.mMethod == E_KOCH){
			mI_ImageProcess = new mori.Fractal.Koch( mO_Image, aPrmtr);
		}else if(aPrmtr.mMethod == E_SSANGYONG){
			mI_ImageProcess = new mori.Fractal.Ssangyong( mO_Image, aPrmtr);
		}else if(aPrmtr.mMethod == E_FERN){
			mI_ImageProcess = new mori.Fractal.Fern( mO_Image, aPrmtr);
		}else if(aPrmtr.mMethod == E_FRACTAL2){
			mI_ImageProcess = new mori.Fractal.Fractal2( mO_Image, aPrmtr);
		}else if(aPrmtr.mMethod == E_FRACTAL3){
			mI_ImageProcess = new mori.Fractal.Fractal3( mO_Image, aPrmtr);
		}else if(aPrmtr.mMethod == E_POLYGON_CONNECT){
			mI_ImageProcess = new mori.Polygon.Polygon( mO_Image, aPrmtr);
		}else if(aPrmtr.mMethod == E_ELLIPSE){
			mI_ImageProcess = new mori.Ellipse.C_CycleExe( mO_Image, aPrmtr);
		}else if(aPrmtr.mMethod == E_VORONOI){
			mI_ImageProcess = new mori.voronoi.CycleExe( mO_Image, aPrmtr);
		}else if(aPrmtr.mMethod == E_KURATOWSKI){
			mO_Type = new String("Kuratowski");
			mI_ImageProcess = new mori.Kuratowski.CycleExe( mO_Image, aPrmtr);
		}else if(aPrmtr.mMethod == E_SYMMETRY){
			mO_Type = new String("Kuratowski");
			mI_ImageProcess = new mori.Symmetry.CycleExe( mO_Image, aPrmtr);
		}

		C_RawData raw = mI_ImageProcess.mExe();

		mRaw1 = new C_RawData(mO_Image);
		mO_Image = raw.mGetBufferedImage();

		mO_Raster = mO_Image.getRaster();
		mO_ColorModel = mO_Image.getColorModel();
		mO_GC = mO_Image.createGraphics();

		mPreferRead = true;

		mO_ImagePanel.repaint();
	}

}


