package dialogs;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public abstract class DialogSurfaceShape extends DialogShape {
	private static final long serialVersionUID = 1L;
	private JButton btnInnerColor = new JButton("Inner color");
	private Color innerColor = Color.WHITE;

	public DialogSurfaceShape() {
		addBtnListener();
	}

	public void addBtnListener() {
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				innerColor = JColorChooser.showDialog(getContentPane(), "Choose inner color", innerColor);
				btnInnerColor.setBackground(innerColor);
			}
		});
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
}