package dialogs;

import javax.swing.*;
import shapes.*;

public class DialogHexagon extends DialogCircle {
	private static final long serialVersionUID = 1L;

	public DialogHexagon() {
		setTitle("Hexagon dialog");
	}

	@Override
	public void setIcon() {
		getLblIcon().setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hexagon.png")).getImage()));
	}

	@Override
	public void setDialog(Point center) {
		getBtnOuterColor().setVisible(false);
		getBtnInnerColor().setVisible(false);
		getXcoordinate().setText(String.valueOf(center.getXcoordinate()));
		getYcoordinate().setText(String.valueOf(center.getYcoordinate()));
		getXcoordinate().setEditable(false);
		getYcoordinate().setEditable(false);
		setVisible(true);
	}

	@Override
	public void setModifyDialog(Shape selectedShape) {
		HexagonAdapter hexagon = (HexagonAdapter) selectedShape;
		getXcoordinate().setText(String.valueOf(hexagon.getXcoordinate()));
		getYcoordinate().setText(String.valueOf(hexagon.getYcoordinate()));
		getRadius().setText(String.valueOf(hexagon.getRadius()));
		setOuterColor(hexagon.getInnerColor());
		setInnerColor(hexagon.getInnerColor());
		getBtnOuterColor().setBackground(getOuterColor());
		getBtnInnerColor().setBackground(getInnerColor());
		setVisible(true);
	}
}