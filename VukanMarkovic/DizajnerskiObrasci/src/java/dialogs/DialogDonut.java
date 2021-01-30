package dialogs;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import shapes.*;

public class DialogDonut extends DialogSurfaceShape {
	private static final long serialVersionUID = 1L;
	private JTextField radius;
	private JLabel lblRadius;
	private JTextField innerRadius;
	private JLabel lblInnerRadius;

	public DialogDonut() {
		radius = new JTextField();
		lblRadius = new JLabel("Radius:");
		innerRadius = new JTextField();
		lblInnerRadius = new JLabel("Inner radius:");
		setTitle("Donut dialog");
		setIcon();
		buildLayout();
		addBtnOkListener();
	}

	@Override
	public void setIcon() {
		getLblIcon().setVerticalAlignment(SwingConstants.BOTTOM);
		ImageIcon icon = new ImageIcon(getClass().getResource("/donut.png"));
		getLblIcon().setIcon(icon);
	}

	@Override
	public void buildLayout() {
		radius.setColumns(10);
		radius.addKeyListener(getInputListener());

		innerRadius.setColumns(10);
		innerRadius.addKeyListener(getInputListener());

		// Automatically generated code by Java Swing, GUI modification is recommended

		getGlContentPanel().setHorizontalGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
				.addGroup(getGlContentPanel().createSequentialGroup().addContainerGap()
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
								.addGroup(getGlContentPanel().createSequentialGroup().addComponent(getLblXcoodinate())
										.addGap(18).addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(getGlContentPanel().createSequentialGroup().addGap(48)
										.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
												.addGroup(getGlContentPanel().createSequentialGroup()
														.addComponent(lblRadius, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGap(18).addComponent(radius, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(getGlContentPanel().createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
														.addComponent(getLblYcoordinate()).addGap(18).addComponent(
																getYcoordinate(), GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(getGlContentPanel().createSequentialGroup()
														.addComponent(lblInnerRadius).addGap(18)
														.addComponent(innerRadius, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)))))
						.addGap(78)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
								.addComponent(getBtnOuterColor()).addComponent(getBtnFillColor())
								.addComponent(getLblIcon(), GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGap(102)));

		getGlContentPanel().setVerticalGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
				.addGroup(getGlContentPanel().createSequentialGroup().addContainerGap(31, Short.MAX_VALUE).addGroup(
						getGlContentPanel().createParallelGroup(Alignment.TRAILING).addGroup(getGlContentPanel()
								.createSequentialGroup()
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
										.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(getLblXcoodinate()))
								.addGap(18)
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
										.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(getLblYcoordinate()))
								.addGap(14)
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
										.addComponent(radius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRadius)))
								.addGroup(getGlContentPanel().createSequentialGroup()
										.addComponent(getLblIcon(), GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)))
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
								.addGroup(getGlContentPanel().createSequentialGroup().addGap(18)
										.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
												.addComponent(innerRadius, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblInnerRadius)))
								.addGroup(getGlContentPanel().createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(getBtnOuterColor())
										.addGap(9).addComponent(getBtnFillColor())))
						.addGap(33)));

		getContentPanel().setLayout(getGlContentPanel());
	}

	private void addBtnOkListener() {
		getBtnOk().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (!isInputValid())
					JOptionPane.showMessageDialog(new JFrame(), "You have not filled in all the fields, try again!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				else if (isInnerRadiusInputInvalid())
					JOptionPane.showMessageDialog(new JFrame(), "The inner radius must be larger than the outer one!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				else {
					setAccepted(true);
					setVisible(false);
				}
			}
		});
	}

	@Override
	public boolean isInputValid() {
		String innerRadiusValue = innerRadius.getText();
		String radiusValue = radius.getText();
		String xCoordinateValue = getXcoordinate().getText();
		String yCoordinateValue = getYcoordinate().getText();

		if (xCoordinateValue.isBlank() || yCoordinateValue.isBlank() || innerRadiusValue.isBlank()
				|| radiusValue.isBlank())
			return false;
		return true;
	}

	private boolean isInnerRadiusInputInvalid() {
		int innerRadiusValue = Integer.parseInt(innerRadius.getText());
		int radiusValue = Integer.parseInt(radius.getText());

		if (innerRadiusValue >= radiusValue)
			return true;
		return false;
	}

	@Override
	public void setCreateDialog(Point center) {
		String xCoordinateValue = String.valueOf(center.getXcoordinate());
		getXcoordinate().setText(xCoordinateValue);
		getXcoordinate().setEditable(false);

		String yCoordinateValue = String.valueOf(center.getYcoordinate());
		getYcoordinate().setText(yCoordinateValue);
		getYcoordinate().setEditable(false);

		getBtnOuterColor().setVisible(false);
		getBtnFillColor().setVisible(false);

		setVisible(true);
	}

	@Override
	public void setModifyDialog(Shape selectedShape) {
		Donut donut = (Donut) selectedShape;
		Point center = donut.getCenter();

		String xCoordinateValue = String.valueOf(center.getXcoordinate());
		getXcoordinate().setText(xCoordinateValue);
		getXcoordinate().setEditable(true);

		String yCoordinateValue = String.valueOf(center.getYcoordinate());
		getYcoordinate().setText(yCoordinateValue);
		getYcoordinate().setEditable(true);

		String radiusValue = String.valueOf(donut.getRadius());
		getRadius().setText(radiusValue);

		String innerRadiusValue = String.valueOf(donut.getInnerRadius());
		getInnerRadius().setText(innerRadiusValue);

		Color outerColor = donut.getBorderColor();
		setBorderColor(outerColor);
		getBtnOuterColor().setBackground(outerColor);
		getBtnOuterColor().setVisible(true);

		Color innerColor = donut.getFillColor();
		setFillColor(innerColor);
		getBtnFillColor().setBackground(innerColor);
		getBtnFillColor().setVisible(true);

		setVisible(true);
	}

	public JTextField getRadius() {
		return radius;
	}

	public int getRadiusValue() {
		return Integer.parseInt(radius.getText());
	}

	public JTextField getInnerRadius() {
		return innerRadius;
	}

	public int getInnerRadiusValue() {
		return Integer.parseInt(innerRadius.getText());
	}
}