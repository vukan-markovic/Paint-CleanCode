package dialogs;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import shapes.Line;
import shapes.Shape;

public class DialogLine extends DialogShape {
	private static final long serialVersionUID = 1L;
	private JTextField xCoordinateOfEndPoint;
	private JTextField yCoordinateOfEndPoint;
	private JLabel lblXCoordinateOfEndPoint;
	private JLabel lblYCoordinateOfEndPoint;

	public DialogLine() {
		xCoordinateOfEndPoint = new JTextField();
		yCoordinateOfEndPoint = new JTextField();
		lblXCoordinateOfEndPoint = new JLabel("X coordinate of end point:");
		lblYCoordinateOfEndPoint = new JLabel("Y coordinate of end point:");

		setTitle("Line dialog");
		setIcon();
		buildLayout();
	}

	@Override
	public void setIcon() {
		getLblIcon().setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/line.png")).getImage()));
	}

	@Override
	public void buildLayout() {
		xCoordinateOfEndPoint.setColumns(10);
		xCoordinateOfEndPoint.addKeyListener(getListener());
		yCoordinateOfEndPoint.setColumns(10);
		yCoordinateOfEndPoint.addKeyListener(getListener());

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
		if (getXcoordinate().getText().isBlank() || getYcoordinate().getText().isBlank()
				|| xCoordinateOfEndPoint.getText().isBlank() || yCoordinateOfEndPoint.getText().isBlank())
			return false;
		return true;
	}

	@Override
	public void setModifyDialog(Shape selectedShape) {
		Line line = (Line) selectedShape;
		getXcoordinate().setText(String.valueOf(line.getStartPoint().getXcoordinate()));
		getYcoordinate().setText(String.valueOf(line.getStartPoint().getYcoordinate()));
		getXCoordinateOfEndPoint().setText(String.valueOf(line.getEndPoint().getXcoordinate()));
		getYCoordinateOfEndPoint().setText(String.valueOf(line.getEndPoint().getYcoordinate()));
		setOuterColor(line.getOuterColor());
		getBtnOuterColor().setBackground(getOuterColor());
		setVisible(true);
	}

	public JTextField getXCoordinateOfEndPoint() {
		return xCoordinateOfEndPoint;
	}

	public JTextField getYCoordinateOfEndPoint() {
		return yCoordinateOfEndPoint;
	}
}