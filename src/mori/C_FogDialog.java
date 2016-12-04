package mori;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class C_FogDialog extends JDialog implements ActionListener{

	public C_FogDialog(JFrame parent){

		super(parent, "FOG DENSITY", true);

		Container contentPane = getContentPane();

		JPanel p1 = new JPanel();

		p1.setLayout(new GridLayout(3, 2));

		p1.add(new JLabel("FOG DENSITY"));

		p1.add( mRedText = new JTextField("") );

		p1.add(new JLabel("FOG RANGE"));

		p1.add( mGreenText = new JTextField("") );

		p1.add(new JLabel("N/A"));

		p1.add( mBlueText = new JTextField("") );

		contentPane.add("Center", p1);

		Panel p2 = new Panel();
		okButton = addButton(p2, "OK");
		cancelButton = addButton(p2, "CANCEL");
		contentPane.add("South", p2);
		setSize(240, 120);
   }

   JButton addButton(Container c, String name){
		JButton button = new JButton(name);
		button.addActionListener(this);
		c.add(button);
		return button;
   }

   public void actionPerformed(ActionEvent evt){
		Object source = evt.getSource();

		if(source == okButton){

			ok = true;

			setVisible(false);

			if (ok){
				mFogDensity = Double.parseDouble( mRedText.getText() );

				mFogRange = Integer.parseInt( mGreenText.getText() );

				mBlueGradation = Integer.parseInt( mBlueText.getText() );
		}

		}else if (source == cancelButton){
			setVisible(false);

		}
   }

	public boolean showDialog(){

		mRedText.setText(new Double(mFogDensity).toString() );

		mGreenText.setText(new Integer(mFogRange).toString() );

		mBlueText.setText(new Integer(mBlueGradation).toString() );

		ok = false;

		setVisible(true);

		return ok;
	}

	private JTextField mRedText;

	private JTextField mGreenText;

	private JTextField mBlueText;

	private boolean ok;

	private JButton okButton;

	private JButton cancelButton;

	//! FOG DENSITY
	public double mFogDensity = 0.3;

	//! óŒÇÃäKí≤ìx
	public int mFogRange = 0;

	//! ê¬ÇÃäKí≤ìx
	public int mBlueGradation = 10;
}

