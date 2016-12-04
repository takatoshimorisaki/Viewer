package mori;

import static mori.E_IMAGE_PROCESS_METHOD.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.swing.JPanel;

class ImagePanel extends JPanel{
    protected int MAX_XWIDTH;
    protected int MAX_YHEIGHT;
	protected BufferedImage mImage;

	private    Toda mO_Toda = null;

	protected Mouse mouse = null;

	private final static int mPanelScale = 2;

	public ImagePanel(MainFrame aMainFrame){

		// パソコンのスクリーンのサイズを取得する
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();
		System.out.println(bounds);

		// パソコンのスクリーンのサイズのmPanelScale倍の大きさにする
		MAX_XWIDTH = mPanelScale * bounds.width;
		MAX_YHEIGHT = mPanelScale * bounds.height;
		setPreferredSize(new Dimension(MAX_XWIDTH, MAX_YHEIGHT));

		mouse = new Mouse(aMainFrame);
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);

		mO_Toda = new Toda(this);
		mO_Toda.mSetMouse(mouse);
		mO_Toda.start();
	}

	public void mClear(){
		if(mO_Toda != null) mO_Toda.mClear();
	}
	
	public void mSetType(String in){
		if(mO_Toda != null) mO_Toda.mSetType(in);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(mO_Toda != null){
			mImage = mO_Toda.mGetImage();
			g.drawImage(mImage, 0, 0, null);
		}
	}

	//! Raw画像ファイルを開く
	public void mOpenRawImage(
		String filename,
		int width,
		int height
	){
		try{
			mO_Toda.mOpenRawImage(filename, width, height);
		}catch(Exception ex){
			ex.printStackTrace();
			System.err.println(ex);
		}
	}

	public void mOpenImage(String filename){
		if(mO_Toda != null){
			try{
				mO_Toda.mOpenImage(filename);
			}catch(Exception ex){
				ex.printStackTrace();
				System.err.println(ex);
			}
		}
	}
	
	public void mSaveImage(String filename, String in){
		if(mO_Toda != null) mO_Toda.mSaveImage(filename, in);
	}

	/*!
		引数で指定された位置の色を返す
	*/
	int mGetRGB(
		int x,
		int y
	){
		int ans = 0;
		if(mO_Toda == null){
			ans = 0;
		}else{
			ans = mO_Toda.mGetRGB(
					x,
					y);
		}
		return ans;
	}

	//! 画像処理する
	public void mExe(C_ImageProcessParameter aPrmtr){
		try{
			mO_Toda.mExe(aPrmtr);
		}catch(Exception ex){
			ex.printStackTrace();
			System.err.println(ex);
		}
	}

}



