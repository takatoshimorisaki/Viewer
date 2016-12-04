package mori;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class C_CategoryDialog extends JDialog implements ActionListener{

	public C_CategoryDialog(JFrame parent){

		super(parent, "カテゴリー", true);

		Container contentPane = getContentPane();

		JPanel p1 = new JPanel();

		p1.setLayout(new GridLayout(3, 2));

		p1.add(new JLabel("カテゴリー数"));

		p1.add( mRedText = new JTextField("") );

		p1.add(new JLabel("N/A"));

		p1.add( mGreenText = new JTextField("") );

		p1.add(new JLabel("N/A"));

		p1.add( mBlueText = new JTextField("") );

		contentPane.add("Center", p1);

		Panel p2 = new Panel();
		okButton = addButton(p2, "OK");
		cancelButton = addButton(p2, "CANCEL");
		contentPane.add("South", p2);
		setSize(240, 120);

		mPrmtr.mMethod = E_IMAGE_PROCESS_METHOD.E_CATEGORY;
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
				mPrmtr.mCategoryNum = Integer.parseInt( mRedText.getText() );

				//// mPrmtr.mGreenGradation = Integer.parseInt( mGreenText.getText() );

				//// mPrmtr.mBlueGradation = Integer.parseInt( mBlueText.getText() );
		}

		}else if (source == cancelButton){
			setVisible(false);

		}
   }

	public boolean showDialog(){

		mRedText.setText(new Integer(mPrmtr.mCategoryNum).toString() );

		mGreenText.setText(new Integer(mPrmtr.mGreenGradation).toString() );

		mBlueText.setText(new Integer(mPrmtr.mBlueGradation).toString() );

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

	public C_ImageProcessParameter mPrmtr = new C_ImageProcessParameter();
}

