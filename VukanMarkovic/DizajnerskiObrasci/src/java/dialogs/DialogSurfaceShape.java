package dialogs;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import shapes.Point;

public abstract class DialogSurfaceShape extends DialogShape {
	private static final long serialVersionUID = 1L;
	private JButton btnFillColor = new JButton("Fill color");
	private Color fillColor = Color.WHITE;

	public DialogSurfaceShape() {
		addBtnFillColorListener();
	}

	public void addBtnFillColorListener() {
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fillColor = JColorChooser.showDialog(getContentPane(), "Choose fill color", fillColor);
				btnFillColor.setBackground(fillColor);
			}
		});
	}

	public abstract void setCreateDialog(Point point);

	public JButton getBtnFillColor() {
		return btnFillColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
}