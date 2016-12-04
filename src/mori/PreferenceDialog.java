package mori;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PreferenceDialog extends JDialog implements ActionListener{

	public PreferenceDialog(JFrame parent){

		super(parent, "ê›íË", true);

		Container contentPane = getContentPane();

		JPanel p1 = new JPanel();

		p1.setLayout(new GridLayout(2, 2));

		p1.add(new JLabel("ïù"));

		p1.add( mWidthText = new JTextField("") );

		p1.add(new JLabel("çÇÇ≥"));

		p1.add( mHeightText = new JTextField("") );

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
				mPreference.mWidth = Integer.parseInt( mWidthText.getText() );

				if(mPreference.mWidth < 0){

					mPreference.mWidth = 0;
				}

				mPreference.mHeight = Integer.parseInt( mHeightText.getText() );

				if(mPreference.mHeight < 0){

					mPreference.mHeight = 0;
				}

		}

		}else if (source == cancelButton){
			setVisible(false);

		}
   }

	public boolean showDialog(Preference pre){

		mPreference = pre;

		mWidthText.setText( Integer.toString(mPreference.mWidth) );

		mHeightText.setText( Integer.toString(mPreference.mHeight) );

		ok = false;

		setVisible(true);

		return ok;
	}

	private Preference mPreference;

	private JTextField mWidthText;

	private JTextField mHeightText;

	private boolean ok;

	private JButton okButton;

	private JButton cancelButton;

}

