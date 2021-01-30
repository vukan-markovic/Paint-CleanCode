package dialogs;

import java.awt.Color;
import javax.swing.*;
import shapes.*;

public class DialogHexagon extends DialogCircle {
	private static final long serialVersionUID = 1L;

	public DialogHexagon() {
		setTitle("Hexagon dialog");
	}

	@Override
	public void setIcon() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/hexagon.png"));
		getLblIcon().setIcon(icon);
	}

	@Override
	public void setModifyDialog(Shape selectedShape) {
		HexagonAdapter hexagon = (HexagonAdapter) selectedShape;

		String xCoordinateValue = String.valueOf(hexagon.getXcoordinate());
		getXcoordinate().setText(xCoordinateValue);

		String yCoordinateValue = String.valueOf(hexagon.getYcoordinate());
		getYcoordinate().setText(yCoordinateValue);

		String radiusValue = String.valueOf(hexagon.getRadius());
		getRadius().setText(radiusValue);

		Color outerColor = hexagon.getBorderColor();
		setBorderColor(outerColor);
		getBtnOuterColor().setBackground(outerColor);

		Color innerColor = hexagon.getFillColor();
		setFillColor(innerColor);
		getBtnFillColor().setBackground(innerColor);

		setVisible(true);
	}
}