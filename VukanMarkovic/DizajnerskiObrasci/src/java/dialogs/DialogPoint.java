package dialogs;

import shapes.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogPoint extends DialogShape {
	private static final long serialVersionUID = 1L;

	public DialogPoint() {
		setTitle("Point dialog");
		setIcon();
		buildLayout();
	}

	@Override
	public void setIcon() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/point3.png"));
		getLblIcon().setIcon(icon);
	}

	@Override
	public void buildLayout() {
		// Automatically generated code by Java Swing, GUI modification is recommended

		getGlContentPanel().setHorizontalGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
				.addGroup(getGlContentPanel().createSequentialGroup().addContainerGap(46, Short.MAX_VALUE).addGroup(
						getGlContentPanel().createParallelGroup(Alignment.LEADING).addGroup(getGlContentPanel()
								.createSequentialGroup()
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
										.addComponent(getLblXcoodinate()).addComponent(getLblYcoordinate()))
								.addGap(30)
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
										.addComponent(getXcoordinate(), Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(getYcoordinate(), Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE).addComponent(
										getLblIcon(), GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
								.addComponent(getBtnOuterColor(), Alignment.TRAILING))
						.addGap(58)));

		getGlContentPanel().setVerticalGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
				.addGroup(getGlContentPanel().createSequentialGroup().addContainerGap(24, Short.MAX_VALUE)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
								.addGroup(getGlContentPanel().createSequentialGroup()
										.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
												.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(getLblXcoodinate()))
										.addGap(45)
										.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
												.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(getLblYcoordinate()))
										.addGap(18))
								.addGroup(getGlContentPanel().createSequentialGroup()
										.addComponent(getLblIcon(), GroupLayout.PREFERRED_SIZE, 88,
												GroupLayout.PREFERRED_SIZE)
										.addGap(42)))
						.addComponent(getBtnOuterColor()).addGap(44)));

		getContentPanel().setLayout(getGlContentPanel());
	}
	
	@Override
	public boolean areAllFieldsFilled() {
		String xCoordinateValue = getXcoordinate().getText();
		String yCoordinateValue = getYcoordinate().getText();

		if (xCoordinateValue.isBlank() || yCoordinateValue.isBlank())
			return false;
		return true;
	}

	@Override
	public boolean areValuesValid() {
		return true;
	}

	@Override
	public void setModifyDialog(Shape selectedShape) {
		setAccepted(false);
		Point point = (Point) selectedShape;

		String xCoordinateValue = String.valueOf(point.getXcoordinate());
		getXcoordinate().setText(xCoordinateValue);

		String yCoordinateValue = String.valueOf(point.getYcoordinate());
		getYcoordinate().setText(yCoordinateValue);

		Color borderColor = point.getBorderColor();
		setBorderColor(borderColor);
		getBtnOuterColor().setBackground(borderColor);

		setVisible(true);
	}
}