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
		setAccepted(false);
		HexagonAdapter hexagon = (HexagonAdapter) selectedShape;

		String xCoordinateValue = String.valueOf(hexagon.getXcoordinate());
		getXcoordinate().setText(xCoordinateValue);
		getXcoordinate().setEditable(true);

		String yCoordinateValue = String.valueOf(hexagon.getYcoordinate());
		getYcoordinate().setText(yCoordinateValue);
		getYcoordinate().setEditable(true);

		String radiusValue = String.valueOf(hexagon.getRadius());
		getRadius().setText(radiusValue);

		Color borderColor = hexagon.getBorderColor();
		setBorderColor(borderColor);
		getBtnOuterColor().setBackground(borderColor);
		getBtnOuterColor().setVisible(true);

		Color fillColor = hexagon.getFillColor();
		setFillColor(fillColor);
		getBtnFillColor().setBackground(fillColor);
		getBtnFillColor().setVisible(true);

		setVisible(true);
	}
}