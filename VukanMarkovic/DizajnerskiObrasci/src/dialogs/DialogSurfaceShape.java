package dialogs;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public abstract class DialogSurfaceShape extends DialogShape {
	private static final long serialVersionUID = 1L;
	private JTextField xCoordinate;
	private JTextField yCoordinate;
	private JButton btnInnerColor;
	private Color innerColor = Color.WHITE;

	public DialogSurfaceShape() {
		buildUI();
	}

	public void buildUI() {
		xCoordinate = new JTextField();
		xCoordinate.setColumns(10);
		xCoordinate.addKeyListener(getListener());

		yCoordinate = new JTextField();
		yCoordinate.setColumns(10);
		yCoordinate.addKeyListener(getListener());

		btnInnerColor = new JButton("Inner color");

		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				innerColor = JColorChooser.showDialog(getContentPane(), "Choose inner color", innerColor);
				btnInnerColor.setBackground(innerColor);
			}
		});
	}

	public JTextField getXcoordinate() {
		return xCoordinate;
	}

	public JTextField getYcoordinate() {
		return yCoordinate;
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