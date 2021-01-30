package dialogs;

import shapes.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogRectangle extends DialogSurfaceShape {
	private static final long serialVersionUID = 1L;
	private JTextField height;
	private JLabel lblHeight;
	private JTextField width;
	private JLabel lblWidth;

	public DialogRectangle() {
		height = new JTextField();
		lblHeight = new JLabel("Height:");
		width = new JTextField();
		lblWidth = new JLabel("Width:");
		setTitle("Rectangle dialog");
		setIcon();
		buildLayout();
	}

	@Override
	public void setIcon() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/rect.png"));
		getLblIcon().setIcon(icon);
	}

	@Override
	public void buildLayout() {
		height.setColumns(10);
		height.addKeyListener(getInputListener());

		width.setColumns(10);
		width.addKeyListener(getInputListener());

		// Automatically generated code by Java Swing, GUI modification is recommended

		getGlContentPanel().setHorizontalGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
				.addGroup(getGlContentPanel().createSequentialGroup().addGap(25)
						.addGroup(getGlContentPanel()
								.createParallelGroup(Alignment.TRAILING).addComponent(getLblXcoodinate())
								.addComponent(getLblYcoordinate()).addComponent(lblHeight).addComponent(lblWidth))
						.addGap(18)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
								.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
										.addComponent(width, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(height, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
								.addGroup(getGlContentPanel().createSequentialGroup()
										.addComponent(getLblIcon(), GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addGap(39))
								.addGroup(getGlContentPanel().createSequentialGroup().addGroup(getGlContentPanel()
										.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(getBtnFillColor(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(getBtnOuterColor(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(63)))));

		getGlContentPanel().setVerticalGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
				.addGroup(getGlContentPanel().createSequentialGroup().addGap(37).addGroup(getGlContentPanel()
						.createParallelGroup(Alignment.LEADING)
						.addGroup(getGlContentPanel().createSequentialGroup()
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
										.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(getLblXcoodinate()))
								.addGap(18)
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
										.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(getLblYcoordinate()))
								.addGap(18)
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
										.addComponent(height, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHeight)))
						.addComponent(getLblIcon(), GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
								.addGroup(getGlContentPanel().createSequentialGroup().addGap(18)
										.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
												.addComponent(width, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblWidth)))
								.addGroup(getGlContentPanel().createSequentialGroup().addGap(3)
										.addComponent(getBtnOuterColor()).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getBtnFillColor())))
						.addContainerGap(30, Short.MAX_VALUE)));

		getContentPanel().setLayout(getGlContentPanel());
	}

	@Override
	public boolean areAllFieldsFilled() {
		String xCoordinateValue = getXcoordinate().getText();
		String yCoordinateValue = getYcoordinate().getText();
		String heightValue = height.getText();
		String widthValue = width.getText();

		if (xCoordinateValue.isBlank() || yCoordinateValue.isBlank() || heightValue.isBlank() || widthValue.isBlank())
			return false;
		return true;
	}

	@Override
	public boolean areValuesValid() {
		return getwidthValue() > 0 && getheightValue() > 0;
	}

	@Override
	public void setCreateDialog(Point upperLeftPoint) {
		String xCoordinateValue = String.valueOf(upperLeftPoint.getXcoordinate());
		getXcoordinate().setText(xCoordinateValue);
		getXcoordinate().setEditable(false);

		String yCoordinateValue = String.valueOf(upperLeftPoint.getYcoordinate());
		getYcoordinate().setText(yCoordinateValue);
		getYcoordinate().setEditable(false);

		getBtnOuterColor().setVisible(false);
		getBtnFillColor().setVisible(false);

		setVisible(true);
	}

	@Override
	public void setModifyDialog(Shape selectedShape) {
		setAccepted(false);
		Rectangle rectangle = (Rectangle) selectedShape;
		Point upperLeftPoint = rectangle.getUpperLeftPoint();

		String xCoordinateValue = String.valueOf(upperLeftPoint.getXcoordinate());
		getXcoordinate().setText(xCoordinateValue);
		getXcoordinate().setEditable(true);

		String yCoordinateValue = String.valueOf(upperLeftPoint.getYcoordinate());
		getYcoordinate().setText(yCoordinateValue);
		getYcoordinate().setEditable(true);

		String heightValue = String.valueOf(rectangle.getHeight());
		height.setText(heightValue);

		String widthValue = String.valueOf(rectangle.getWidth());
		width.setText(widthValue);

		Color outerColor = rectangle.getBorderColor();
		setBorderColor(outerColor);
		getBtnOuterColor().setBackground(getBorderColor());
		getBtnOuterColor().setVisible(true);

		Color innerColor = rectangle.getFillColor();
		setFillColor(innerColor);
		getBtnFillColor().setBackground(innerColor);
		getBtnFillColor().setVisible(true);

		setVisible(true);
	}

	public JTextField getheight() {
		return height;
	}

	public int getheightValue() {
		return Integer.parseInt(height.getText());
	}

	public JTextField getwidth() {
		return width;
	}

	public int getwidthValue() {
		return Integer.parseInt(width.getText());
	}
}