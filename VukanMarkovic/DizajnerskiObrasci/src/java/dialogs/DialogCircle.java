package dialogs;

import java.awt.Image;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import shapes.*;

public class DialogCircle extends DialogSurfaceShape {
	private static final long serialVersionUID = 1L;
	private JTextField radius;
	private JLabel lblRadius;

	public DialogCircle() {
		radius = new JTextField();
		lblRadius = new JLabel("Radius:");
		setTitle("Circle dialog");
		setIcon();
		buildLayout();
	}

	@Override
	public void setIcon() {
		Image icon = new ImageIcon(this.getClass().getResource("/circle.png")).getImage();
		getLblIcon().setIcon(new ImageIcon(icon));
	}

	@Override
	public void buildLayout() {
		radius.setColumns(10);
		radius.addKeyListener(getListener());

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
														.addComponent(getLblYcoordinate()).addGap(18)
														.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)))))
						.addGap(72)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
								.addComponent(getLblIcon(), GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
										.addComponent(getBtnInnerColor()).addComponent(getBtnOuterColor())))
						.addGap(86)));

		getGlContentPanel().setVerticalGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
				.addGroup(getGlContentPanel().createSequentialGroup().addContainerGap(35, Short.MAX_VALUE).addGroup(
						getGlContentPanel().createParallelGroup(Alignment.TRAILING, false).addGroup(getGlContentPanel()
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
										.addGap(19)))
						.addGap(10).addComponent(getBtnOuterColor()).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(getBtnInnerColor()).addGap(21)));

		getContentPanel().setLayout(getGlContentPanel());
	}

	@Override
	public boolean isInputValid() {
		if ((radius.getText().isBlank() || getXcoordinate().getText().isBlank()
				|| getYcoordinate().getText().isBlank()))
			return false;
		return true;
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
		Circle circle = (Circle) selectedShape;
		getBtnOuterColor().setVisible(true);
		getBtnInnerColor().setVisible(true);
		getXcoordinate().setEditable(true);
		getYcoordinate().setEditable(true);
		getXcoordinate().setText(String.valueOf(circle.getCenter().getXcoordinate()));
		getYcoordinate().setText(String.valueOf(circle.getCenter().getYcoordinate()));
		radius.setText(String.valueOf(circle.getRadius()));
		setOuterColor(circle.getOuterColor());
		setInnerColor(circle.getInnerColor());
		getBtnOuterColor().setBackground(getOuterColor());
		getBtnInnerColor().setBackground(getInnerColor());
		setVisible(true);
	}

	public JTextField getRadius() {
		return radius;
	}

	public int getRadiusValue() {
		return Integer.parseInt(radius.getText());
	}

	public JLabel getLblRadius() {
		return lblRadius;
	}
}