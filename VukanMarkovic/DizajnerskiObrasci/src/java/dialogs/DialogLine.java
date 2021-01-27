package dialogs;

import shapes.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogLine extends DialogShape {
	private static final long serialVersionUID = 1L;
	private JTextField xCoordinateOfEndPoint;
	private JLabel lblXCoordinateOfEndPoint;
	private JTextField yCoordinateOfEndPoint;
	private JLabel lblYCoordinateOfEndPoint;

	public DialogLine() {
		xCoordinateOfEndPoint = new JTextField();
		lblXCoordinateOfEndPoint = new JLabel("X coordinate of end point:");
		yCoordinateOfEndPoint = new JTextField();
		lblYCoordinateOfEndPoint = new JLabel("Y coordinate of end point:");
		setTitle("Line dialog");
		setIcon();
		buildLayout();
	}

	@Override
	public void setIcon() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/line.png"));
		getLblIcon().setIcon(icon);
	}

	@Override
	public void buildLayout() {
		xCoordinateOfEndPoint.setColumns(10);
		xCoordinateOfEndPoint.addKeyListener(getInputListener());

		yCoordinateOfEndPoint.setColumns(10);
		yCoordinateOfEndPoint.addKeyListener(getInputListener());

		// Automatically generated code by Java Swing, GUI modification is recommended

		getGlContentPanel().setHorizontalGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
				.addGroup(getGlContentPanel().createSequentialGroup().addGap(41)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
								.addComponent(lblXCoordinateOfEndPoint).addComponent(lblYCoordinateOfEndPoint)
								.addComponent(getLblYcoordinate()).addComponent(getLblXcoodinate()))
						.addGap(26)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
								.addComponent(yCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(xCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										getGlContentPanel().createSequentialGroup()
												.addComponent(getLblIcon(), GroupLayout.PREFERRED_SIZE, 140,
														GroupLayout.PREFERRED_SIZE)
												.addGap(49))
								.addGroup(Alignment.TRAILING, getGlContentPanel().createSequentialGroup()
										.addComponent(getBtnOuterColor()).addGap(77)))));

		getGlContentPanel().setVerticalGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
				.addGroup(getGlContentPanel().createSequentialGroup().addGap(36)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
								.addGroup(getGlContentPanel().createSequentialGroup()
										.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
												.addComponent(getLblXcoodinate())
												.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
												.addComponent(getLblYcoordinate())
												.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
												.addComponent(lblXCoordinateOfEndPoint)
												.addComponent(xCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(getLblIcon(), GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(yCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblYCoordinateOfEndPoint).addComponent(getBtnOuterColor()))
						.addContainerGap(43, Short.MAX_VALUE)));

		getContentPanel().setLayout(getGlContentPanel());
	}

	@Override
	public boolean isInputValid() {
		String xCoordinateOfStartPointValue = getXcoordinate().getText();
		String yCoordinateOfStartPointValue = getYcoordinate().getText();
		String xCoordinateOfEndPointValue = xCoordinateOfEndPoint.getText();
		String yCoordinateOfEndPointValue = yCoordinateOfEndPoint.getText();

		if (xCoordinateOfStartPointValue.isBlank() || yCoordinateOfStartPointValue.isBlank()
				|| xCoordinateOfEndPointValue.isBlank() || yCoordinateOfEndPointValue.isBlank())
			return false;
		return true;
	}

	@Override
	public void setModifyDialog(Shape selectedShape) {
		Line line = (Line) selectedShape;
		Point startPoint = line.getStartPoint();
		Point endPoint = line.getEndPoint();

		String xCoordinateOfStartPointValue = String.valueOf(startPoint.getXcoordinate());
		getXcoordinate().setText(xCoordinateOfStartPointValue);

		String yCoordinateOfStartPointValue = String.valueOf(startPoint.getYcoordinate());
		getYcoordinate().setText(yCoordinateOfStartPointValue);

		String xCoordinateOfEndPointValue = String.valueOf(endPoint.getXcoordinate());
		getXCoordinateOfEndPoint().setText(xCoordinateOfEndPointValue);

		String yCoordinateOfEndPointValue = String.valueOf(endPoint.getYcoordinate());
		getYCoordinateOfEndPoint().setText(yCoordinateOfEndPointValue);

		Color outerColor = line.getOuterColor();
		setOuterColor(outerColor);
		getBtnOuterColor().setBackground(outerColor);

		setVisible(true);
	}

	public JTextField getXCoordinateOfEndPoint() {
		return xCoordinateOfEndPoint;
	}

	public int getXCoordinateOfEndPointValue() {
		return Integer.parseInt(xCoordinateOfEndPoint.getText());
	}

	public JTextField getYCoordinateOfEndPoint() {
		return yCoordinateOfEndPoint;
	}

	public int getYCoordinateOfEndPointValue() {
		return Integer.parseInt(yCoordinateOfEndPoint.getText());
	}
}