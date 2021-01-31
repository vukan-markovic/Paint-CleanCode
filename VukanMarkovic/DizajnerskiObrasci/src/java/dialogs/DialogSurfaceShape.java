package dialogs;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import shapes.Point;

public abstract class DialogSurfaceShape extends DialogShape {
	private static final long serialVersionUID = 1L;
	private Color fillColor;
	private JButton btnFillColor;

	public DialogSurfaceShape() {
		fillColor = Color.WHITE;
		btnFillColor = new JButton("Fill color");
		addBtnFillColorListener();
	}

	private void addBtnFillColorListener() {
		btnFillColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				fillColor = JColorChooser.showDialog(getContentPane(), "Choose fill color", fillColor);
				btnFillColor.setBackground(fillColor);
			}
		});
	}

	public abstract void setCreateDialog(Point point);

	public Color getFillColor() {
		return fillColor;
	}

	public JButton getBtnFillColor() {
		return btnFillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
}