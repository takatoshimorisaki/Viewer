package mori;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class C_SubtractiveDialog extends JDialog implements ActionListener{

	public C_SubtractiveDialog(JFrame parent){

		super(parent, "äKí≤ìx", true);

		Container contentPane = getContentPane();

		JPanel p1 = new JPanel();

		p1.setLayout(new GridLayout(3, 2));

		p1.add(new JLabel("ê‘"));

		p1.add( mRedText = new JTextField("") );

		p1.add(new JLabel("óŒ"));

		p1.add( mGreenText = new JTextField("") );

		p1.add(new JLabel("ê¬"));

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
				mRedGradation = Integer.parseInt( mRedText.getText() );

				mGreenGradation = Integer.parseInt( mGreenText.getText() );

				mBlueGradation = Integer.parseInt( mBlueText.getText() );
		}

		}else if (source == cancelButton){
			setVisible(false);

		}
   }

	public boolean showDialog(){

		mRedText.setText(new Integer(mRedGradation).toString() );

		mGreenText.setText(new Integer(mGreenGradation).toString() );

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

	//! ê‘ÇÃäKí≤ìx
	public int mRedGradation = 3;

	//! óŒÇÃäKí≤ìx
	public int mGreenGradation = 3;

	//! ê¬ÇÃäKí≤ìx
	public int mBlueGradation = 3;
}

