package mori;

import static java.lang.System.out;
import static mori.E_IMAGE_PROCESS_METHOD.*;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.util.Properties;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import mori.LogService.C_LogService;

public class MainFrame extends JFrame implements ActionListener, MenuListener, Runnable{

	//! 画像処理をするパネル
	private ImagePanel mO_ImagePanel;

	private JMenuItem  mO_SaveItem;

	//! ステータスバーのテキスト表示
	protected JTextField mO_Text;
	
	//! 基本設定
	private Properties mProp = new Properties();

	//! ファイル名
	private String mFileName;

	public MainFrame(){
		mProp.setProperty("home", ".");

		// タイトルを設定する
		setTitle("画像処理します");

		// パソコンのスクリーンのサイズを取得する
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();
		System.out.println(bounds);

		// パソコンのスクリーンのサイズと同じフレームにする
		setSize(bounds.width, bounds.height);

		// 画像処理をするパネルを生成する
		mO_ImagePanel = new ImagePanel(this);

		// 右上の×を押した時の処理
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});	

		// メニューバーを生成する
		JMenuBar mbar = new JMenuBar();
		setJMenuBar(mbar);

		// File メニューを作成する
		JMenu fileMenu = new JMenu("File");
		fileMenu.addMenuListener(this);
		fileMenu.setMnemonic('F');
		
		mO_SaveItem = new JMenuItem("Save_Jpeg", 'S');
			
		mbar.add(makeMenu(fileMenu,
			new Object[]{"New", 
				null, 
				"CANVAS",
				"Open", 
				mO_SaveItem, 
				"Save_Bmp", 
				null, 
				"Exit"}, 
				this));
			
		JMenu analizeMenu = new JMenu("Edit");
		analizeMenu.setMnemonic('P');
		mbar.add(makeMenu(
			analizeMenu,
			new Object[]{	
							new JMenuItem("UNDO", 'U'),
                            new JMenuItem("FOG", 'I'),
						 	new JMenuItem("HISTOGRAM EQUIVALENCE", 'H'),
							new JMenuItem("CONTRACTION_AVERAGE", 'T'),
							new JMenuItem("CONTRACTION_RANDOM", 'T'),
							new JMenuItem("背景を塗る", 'H'),
							new JMenuItem("EXTRACT EDGE LUMI", 'J'),
							new JMenuItem("EXTRACT EDGE RGB", 'J'),
							new JMenuItem("COLOR_SUBTRACTION", 'J'),
							new JMenuItem("COMPLEMENTARY_COLOR", 'M'),
							new JMenuItem("EMBOSS", 'E'),
							new JMenuItem("SWAP_RGB", 'E'),
							new JMenuItem("CATEGORY", 'E'),
							new JMenuItem("GRAY_SCALE", 'E'),
							new JMenuItem("DOMINO", 'E'),
							new JMenuItem("DOMINO_RGB", 'E'),
							new JMenuItem("CLOUD_EXTRACTION", 'E'),
							new JMenuItem("CLOUD_ERASURE", 'E'),
							new JMenuItem("RAIN_EXTRACTION", 'E'),
							new JMenuItem("RAIN_ERASURE", 'E'),
							new JMenuItem("90 DEGREES CLOCKWISE", 'E'),
						 }, this));

		JMenu optionMenu = new JMenu("Option");
		optionMenu.setMnemonic('O');
		mbar.add(makeMenu(optionMenu,
			new Object[]{ 
                            new JMenuItem("POLYGON_CONNECT", 'L'),
                            new JMenuItem("INTERVAL", 'I'),
     					    new JMenuItem("STOP", 'E'),
     					    new JMenuItem("VORONOI", 'E'),    					    
                            new JMenuItem("CIRCLE", 'C'), 
                            new JMenuItem("TRACK", 'C'), 
                            new JMenuItem("TRACK_N", 'C'),  
							new JMenuItem("HUE_TRANSITION", 'H'),
                            new JMenuItem("WAVE", 'W'), 
                            new JMenuItem("SURFACE", 'W'), 
                            new JMenuItem("SURFACE2", 'W'), 
                            new JMenuItem("SURFACE3", 'W'), 
                            new JMenuItem("STRING", 'W'), 
                            new JMenuItem("TROCHOID", 'W'), 
                            new JMenuItem("TROCHOID2", 'W'), 
                            new JMenuItem("TROCHOID3", 'T'), 
                            new JMenuItem("TROCHOID4", 'T'), 
                            new JMenuItem("TROCHOID5", 'T'), 
                            new JMenuItem("MAGMA", 'T'), 
                            new JMenuItem("MANY_CHORD", 'W'), 
                            new JMenuItem("MANY_CHORD3", 'W'), 
                            new JMenuItem("LENS", 'L'), 
                            new JMenuItem("EM", 'L'), 
                            new JMenuItem("ELLIPSE_1_1", 'L'), 
                            new JMenuItem("INVERSE_CIRCLE", 'L'), 
						}, this));

		JMenu fractalMenu = new JMenu("Fractal");
		fractalMenu.setMnemonic('F');
		mbar.add(makeMenu(fractalMenu,
			new Object[]{ 
                            new JMenuItem("Gasket", 'G'), 
                            new JMenuItem("Gasket2", 'G'), 
                            new JMenuItem("Koch", 'G'), 
                            new JMenuItem("Ssangyong", 'G'), 
                            new JMenuItem("Fern", 'G'), 
                            new JMenuItem("Fractal2", 'G'), 
                            new JMenuItem("Fractal3", 'G'), 
						}, this));

		JMenu graphMenu = new JMenu("Graph");
		fractalMenu.setMnemonic('G');
		mbar.add(makeMenu(graphMenu,
			new Object[]{ 
                            new JMenuItem("Kuratowski", 'K'),  
                            new JMenuItem("Symmetry", 'K'),  
						}, this));
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		mbar.add(makeMenu(helpMenu,
			new Object[]{new JMenuItem("Index", 'I'), 
						 new JMenuItem("About", 'A')}, this));
						 
		Container contentPane = getContentPane();
		GridBagLayout gbl = new GridBagLayout();
		contentPane.setLayout(gbl);
		
		JScrollPane sp = new JScrollPane(mO_ImagePanel);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 100;
		gbc.weighty = 100;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		add(sp, gbc, 1, 0, 4, 3);

		mO_Text = new JTextField();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.weighty = 0;

		add(mO_Text, gbc, 0, 3, 5, 1);
		mO_Text.setText("The quick brown fox");

	}

	public void actionPerformed(ActionEvent evt){

		String arg = evt.getActionCommand();
		Object src = evt.getSource();
		
		if(arg.equals("Exit")){

			System.exit(0);

		}else if(src == mO_SaveItem){

			imageFile("Save");

		}else if(arg.equals("CANVAS")){

			mO_ImagePanel.mOpenImage(null);

		}else if(arg.equals("Open")){

			imageFile("Open");

		}else if( arg.equals("Save_Bmp") ){

			imageFile("Save_Bmp");

		}else if(arg.equals("New")){

			mO_ImagePanel.mClear();

		}else if(arg.equals("CIRCLE")){

			mO_ImagePanel.mSetType("CIRCLE");

		}else if(arg.equals("ELLIPSE_1_1")){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();

			prmtr.mMethod = E_ELLIPSE;
			mO_ImagePanel.mExe(prmtr);

		}else if(arg.equals("TRACK")){

			mO_ImagePanel.mSetType("TRACK");

		}else if(arg.equals("TRACK_N")){

			mO_ImagePanel.mSetType("TRACK_N");

		}else if(arg.equals("STRING")){

			mO_ImagePanel.mSetType("STRING");

		}else if(arg.equals("TROCHOID")){

			mO_ImagePanel.mSetType("TROCHOID");

		}else if(arg.equals("TROCHOID2")){

			mO_ImagePanel.mSetType("TROCHOID2");

		}else if(arg.equals("TROCHOID3")){

			mO_ImagePanel.mSetType("TROCHOID3");

		}else if(arg.equals("TROCHOID4")){

			mO_ImagePanel.mSetType("TROCHOID4");

		}else if(arg.equals("TROCHOID5")){

			mO_ImagePanel.mSetType("TROCHOID5");

		}else if(arg.equals("MAGMA")){

			mO_ImagePanel.mSetType("MAGMA");

		}else if(arg.equals("WAVE")){

			mO_ImagePanel.mSetType("WAVE");

		}else if(arg.equals("EM")){

			mO_ImagePanel.mSetType("EM");

		}else if(arg.equals("MANY_CHORD")){

			mO_ImagePanel.mSetType("MANY_CHORD");

		}else if(arg.equals("MANY_CHORD3")){

			mO_ImagePanel.mSetType("MANY_CHORD3");

		}else if(arg.equals("HUE_TRANSITION")){

			mO_ImagePanel.mSetType("HUE_TRANSITION");

		}else if(arg.equals("LENS")){

			mO_ImagePanel.mSetType("LENS");

		}else if(arg.equals("INVERSE_CIRCLE")){

			mO_ImagePanel.mSetType("INVERSE_CIRCLE");

		}else if(arg.equals("SURFACE")){

			mO_ImagePanel.mSetType("SURFACE");

		}else if(arg.equals("SURFACE2")){

			mO_ImagePanel.mSetType("SURFACE2");

		}else if(arg.equals("SURFACE3")){

			mO_ImagePanel.mSetType("SURFACE3");

		}else if(arg.equals("STOP")){

			mO_ImagePanel.mSetType("STOP");

		}else if(arg.equals("HISTOGRAM EQUIVALENCE")){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();

			prmtr.mMethod = E_EQUIVALENCE_HISTOGRAM;

			mO_ImagePanel.mExe(prmtr);

		}else if( arg.equals("CONTRACTION_AVERAGE") ){

			ContractionDialog dlg = new ContractionDialog(this);

			if( dlg.showDialog() ){
				C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
				prmtr.mMethod = E_CONTRACT_AVERAGE;
				prmtr.mRatioX = dlg.mCont.mX;
				prmtr.mRatioY = dlg.mCont.mY;

				mO_ImagePanel.mExe(prmtr);
			}

		}else if( arg.equals("CONTRACTION_RANDOM") ){

			ContractionDialog dlg = new ContractionDialog(this);

			if( dlg.showDialog() ){
				C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
				prmtr.mMethod = E_CONTRACT_RANDOM;
				prmtr.mRatioX = dlg.mCont.mX;
				prmtr.mRatioY = dlg.mCont.mY;

				mO_ImagePanel.mExe(prmtr);
			}

		}else if( arg.equals("背景を塗る") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_NURU;

			mO_ImagePanel.mExe(prmtr);

		}else if( arg.equals("EXTRACT EDGE LUMI") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_EXTRACT_EDGE_LUMI;

			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("UNDO") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_UNDO;

			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("EXTRACT EDGE RGB") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_EXTRACT_EDGE_RGB;

			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("COMPLEMENTARY_COLOR") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_COMPLEMENTRAY_COLOR;

			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("COLOR_SUBTRACTION") ){
			C_SubtractiveDialog dlg = new C_SubtractiveDialog(this);

			if( dlg.showDialog() ){
				C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
				prmtr.mMethod = E_SUBTRACT_COLOR;
				prmtr.mRedGradation = dlg.mRedGradation;
				prmtr.mGreenGradation = dlg.mGreenGradation;
				prmtr.mBlueGradation = dlg.mBlueGradation;

				mO_ImagePanel.mExe(prmtr);
			}

		}else if( arg.equals("EMBOSS") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_EMBOSS;

			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("SWAP_RGB") ){
			C_RGBSwapDialog dlg = new C_RGBSwapDialog(this);

			if( dlg.showDialog() ){
				C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
				prmtr.mMethod = E_SWAP_RGB;
				prmtr.mSwapRed = dlg.mSwapRed;
				prmtr.mSwapGreen = dlg.mSwapGreen;
				prmtr.mSwapBlue = dlg.mSwapBlue;

				mO_ImagePanel.mExe(prmtr);
			}

		}else if( arg.equals("FOG") ){
			C_FogDialog dlg = new C_FogDialog(this);

			if( dlg.showDialog() ){
				C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
				prmtr.mMethod = E_FOG;
				prmtr.mFogDensity = dlg.mFogDensity;
				prmtr.mFogRange = dlg.mFogRange;

				mO_ImagePanel.mExe(prmtr);
			}
		}else if( arg.equals("CATEGORY") ){
			C_CategoryDialog dlg = new C_CategoryDialog(this);

			if(dlg.showDialog() ){
				mO_ImagePanel.mExe(dlg.mPrmtr);
			}
		}else if( arg.equals("GRAY_SCALE") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_GRAY_SCALSE;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("DOMINO") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_DOMINO;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("DOMINO_RGB") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_DOMINO_RGB;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("CLOUD_EXTRACTION") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_CLOUD_EXTRACTION;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("CLOUD_ERASURE") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_CLOUD_ERASURE;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("RAIN_EXTRACTION") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_RAIN_EXTRACTION;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("RAIN_ERASURE") ){
			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_RAIN_ERASURE;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("90 DEGREES CLOCKWISE") ){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_90_DEGREES_CLOCKWISE;
			mO_ImagePanel.mExe(prmtr);

		}else if( arg.equals("Gasket")){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_GASKET;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("Gasket2")){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_GASKET2;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("Koch")){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_KOCH;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("Ssangyong")){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_SSANGYONG;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("Fern")){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_FERN;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("Fractal2")){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_FRACTAL2;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("Fractal3")){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_FRACTAL3;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("POLYGON_CONNECT")){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_POLYGON_CONNECT;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("VORONOI")){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_VORONOI;
			mO_ImagePanel.mExe(prmtr);
		}else if( arg.equals("Kuratowski")){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_KURATOWSKI;
			mO_ImagePanel.mExe(prmtr);
			
		}else if( arg.equals("Symmetry")){

			C_ImageProcessParameter prmtr = new C_ImageProcessParameter();
			prmtr.mMethod = E_SYMMETRY;
			mO_ImagePanel.mExe(prmtr);
		}
	}

	public void imageFile(String in){
        JFileChooser d = new JFileChooser();
        d.setCurrentDirectory(new File(mProp.getProperty("home", ".")));
        d.setSelectedFile(new File(""));

		if(in.equals("Open")){
	        d.setFileFilter(new Open());
		}else{
			d.setFileFilter(new Save());
		}

        // d.addChoosableFileFilter(d.getAcceptAllFileFilter());
        d.setMultiSelectionEnabled(false);
		int result = 0;
		if(in.equals("Open")){
			result = d.showOpenDialog(this);
		}else{
			result = d.showSaveDialog(this);
		}

        if(result == JFileChooser.APPROVE_OPTION){

            mFileName = d.getSelectedFile().getPath();
			String dir = d.getCurrentDirectory().getAbsolutePath();

			System.out.println(dir);

			mProp.setProperty("home", dir);

            System.out.println(mFileName);

			if(in.equals("Open")){
				if(mFileName.endsWith("raw")
				|| mFileName.endsWith("RAW") ){
					Preference pre = new Preference(640, 480);
					PreferenceDialog dlg = new PreferenceDialog(this);
					boolean rtn = dlg.showDialog(pre);
					if(rtn){
						mO_ImagePanel.mOpenRawImage(mFileName, pre.mWidth, pre.mHeight);
					}
				}else{
					mO_ImagePanel.mOpenImage(mFileName);
				}
			}else{
				mO_ImagePanel.mSaveImage(mFileName, in);
			}
        }
	}

	public void menuSelected(MenuEvent evt){
	}

	public void menuDeselected(MenuEvent evt){
	}

	public void menuCanceled(MenuEvent evt){
	}
	
	public void add(Component c, GridBagConstraints gbc,
		int x, int y, int w, int h){
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		getContentPane().add(c, gbc);
	}

	public static JMenu makeMenu(Object parent, Object[] items, Object target){

		JMenu m = null;
		if(parent instanceof JMenu){
			m = (JMenu)parent;
		}else if(parent instanceof String){
			m = new JMenu((String)parent);
		}else{
			return null;
		}

		for(int i = 0; i < items.length; i++){
			if(items[i] == null){
				m.addSeparator();
			}else{
				m.add(makeMenuItem(items[i], target));
			}
		}

		return m;
	}

	public static JMenuItem makeMenuItem(Object item, Object target){

		JMenuItem r = null;
		if(item instanceof String){
			r = new JMenuItem((String)item);
		}else if(item instanceof JMenuItem){
			r = (JMenuItem)item;
		}else{
			return null;
		}

		if(target instanceof ActionListener){
			r.addActionListener((ActionListener)target);
		}

		return r;
	}

	public void run(){
		for(;;){
			Point point = mO_ImagePanel.mouse.getPoint();

			int rgb = mO_ImagePanel.mGetRGB(
						point.x,
						point.y);

			int red = (rgb & 0xff0000) / (256*256);
			int green = (rgb & 0xff00) / 256;
			int blue = rgb & 0xff;

			String string = String.format("X %03d Y %03d Red %03d Green %03d Blue %03d %s",
								point.x, point.y, red, green, blue, mFileName);
			mO_Text.setText(string);

			try{
				Thread.sleep(100);
			}catch(Exception e){
				out.println(e);
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args){
		C_LogService log       = null;

		try{
			log = new C_LogService();
			log.mStartService();
		}catch(Exception ex){
			ex.printStackTrace();
			System.err.println(ex);
		}

		try{

			Frame f = new MainFrame();
			Runnable r = (Runnable)f;
			(new Thread(r)).start();
			f.setVisible(true);  

		}catch(Exception e){

		}
	}
}

