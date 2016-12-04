package mori;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class C_RGBSwapDialog extends JDialog implements ActionListener{

	public C_RGBSwapDialog(JFrame parent){

		super(parent, "ŠK’²“x", true);

		Container contentPane = getContentPane();

		JPanel p1 = new JPanel();

		p1.setLayout(new GridLayout(3, 2));

		p1.add(new JLabel("R"));

		p1.add( mRedText = new JTextField("") );

		p1.add(new JLabel("G"));

		p1.add( mGreenText = new JTextField("") );

		p1.add(new JLabel("B"));

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
				mSwapRed = mRedText.getText();

				mSwapGreen = mGreenText.getText();

				mSwapBlue = mBlueText.getText();
		}

		}else if (source == cancelButton){
			setVisible(false);

		}
   }

	public boolean showDialog(){

		mRedText.setText(mSwapRed);

		mGreenText.setText(mSwapGreen);

		mBlueText.setText(mSwapBlue);

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

	public String mSwapRed = "R";

	public String mSwapGreen = "G";

	public String mSwapBlue = "B";

}

