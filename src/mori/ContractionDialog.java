package mori;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ContractionDialog extends JDialog implements ActionListener{

	public ContractionDialog(JFrame parent){

		super(parent, "ê›íË", true);

		Container contentPane = getContentPane();

		JPanel p1 = new JPanel();

		p1.setLayout(new GridLayout(2, 2));

		p1.add(new JLabel("êÖïΩï˚å¸"));

		p1.add( mContX = new JTextField("") );

		p1.add(new JLabel("çÇÇ≥ï˚å¸"));

		p1.add( mContY = new JTextField("") );

		contentPane.add("Center", p1);

		Panel p2 = new Panel();

		mOkButton = addButton(p2, "OK");

		mCancelButton = addButton(p2, "CANCEL");

		contentPane.add("South", p2);

		setSize(240, 120);

		mCont = new Contraction();

		mCont.mX = 1.0;

		mCont.mY = 1.0;

   }

   JButton addButton(Container c, String name){

		JButton button = new JButton(name);

		button.addActionListener(this);

		c.add(button);

		return button;
   }

   public void actionPerformed(ActionEvent evt){
		Object source = evt.getSource();

		if(source == mOkButton){

			mOk = true;

			setVisible(false);

			if (mOk){

				mCont.mX = Double.parseDouble( mContX.getText() );

				if(mCont.mX < 0.0){

					mCont.mX = 0.0;
				}

				mCont.mY = Double.parseDouble( mContY.getText() );

				if(mCont.mY < 0.0){

					mCont.mY = 0.0;
				}

			}

		}else if (source == mCancelButton){
			setVisible(false);

		}
   }

	public boolean showDialog(){

		mContX.setText( Double.toString(mCont.mX) );

		mContY.setText( Double.toString(mCont.mY) );

		mOk = false;

		setVisible(true);

		return mOk;
	}

	public Contraction mCont;

	private JTextField mContX;

	private JTextField mContY;

	private boolean mOk;

	private JButton mOkButton;

	private JButton mCancelButton;

}

