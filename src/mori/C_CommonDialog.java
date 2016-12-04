package mori;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class C_CommonDialog extends JDialog implements ActionListener{

	public C_CommonDialog(JFrame parent){

		super(parent, "COMMON", true);

		Container contentPane = getContentPane();

		JPanel p1 = new JPanel();

		p1.setLayout(new GridLayout(3, 2));

		p1.add(new JLabel("PARAM 1"));

		p1.add( mText1 = new JTextField("") );

		p1.add(new JLabel("PARAM 2"));

		p1.add( mText2 = new JTextField("") );

		p1.add(new JLabel("PARAM 3"));

		p1.add( mText3 = new JTextField("") );

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
				mParam1 = mText1.getText();

				mParam2 = mText2.getText();

				mParam3 = mText3.getText();
		}

		}else if (source == cancelButton){
			setVisible(false);

		}
   }

	public boolean showDialog(){

		mText1.setText(mParam1);

		mText2.setText(mParam2);

		mText3.setText(mParam3);

		ok = false;

		setVisible(true);

		return ok;
	}

	private JTextField mText1;

	private JTextField mText2;

	private JTextField mText3;

	private boolean ok;

	private JButton okButton;

	private JButton cancelButton;

	public String mParam1 = new String();

	public String mParam2 = new String();

	public String mParam3 = new String();
}

