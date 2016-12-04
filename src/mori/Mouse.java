package mori;

import static java.awt.event.InputEvent.*;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JMenuItem;

public class Mouse extends MouseAdapter implements  ActionListener, MouseMotionListener{

	protected Point point    = new Point(0,0); 
	private JPopupMenu popup;

	private Point mStartPoint = new Point();

	private Point mEndPoint = new Point();

	private boolean mLog = false;

	public Mouse(MainFrame aMainFrame){
		popup = makePopupMenu(new Object[]{"Cut", "Copy", "Paste"}, this);
	}
	
	public void setDelay(int in){
	}
	
	public void actionPerformed(ActionEvent evt){
		String arg = evt.getActionCommand();

		if(arg.equals("Cut")){
		}
	}
	
	public void mousePressed(MouseEvent evt){
		point = evt.getPoint();
		
		String text = evt.getMouseModifiersText(SHIFT_MASK);

		if(text.equals("Shift")){
			mStartPoint.x = point.x;
			mStartPoint.y = point.y;
			mLog = true;
		}

		/* LINUX */
		if(evt.isPopupTrigger()){
			popup.show(evt.getComponent(), evt.getX(), evt.getY());
		}
	}
	
	public void mouseReleased(MouseEvent evt) {	
		Point point = evt.getPoint();
		
		String text = evt.getMouseModifiersText(SHIFT_MASK);

		mEndPoint.x = point.x;
		mEndPoint.y = point.y;

		/* WINDOWS */
		if(evt.isPopupTrigger()){
			popup.show(evt.getComponent(), evt.getX(), evt.getY());
		}
		
	}
	
	public void mouseClicked(MouseEvent evt){
		point = evt.getPoint();
		
	}
	
	public void mouseMoved(MouseEvent evt){
		point = evt.getPoint();

	}
	
	public void mouseDragged(MouseEvent evt){
		point = evt.getPoint();
	}
	
	public Point getPoint(){
		return point;
	}

	public static JPopupMenu makePopupMenu(Object[] items, Object target){
		JPopupMenu m = new JPopupMenu();
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

	public void mExe(
		java.awt.image.BufferedImage aImage
	){
		if(mLog == true){
			I_ImageProcessor mI_ImageProcess = new C_MouseLog(aImage, mStartPoint, mEndPoint);
			mI_ImageProcess.mExe();
		}

		mLog = false;
	}

}


