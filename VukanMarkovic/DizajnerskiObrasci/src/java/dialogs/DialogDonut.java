package dialogs;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import shapes.*;

public class DialogDonut extends DialogSurfaceShape {
	private static final long serialVersionUID = 1L;
	private JTextField radius;
	private JTextField innerRadius;
	private JLabel lblInnerRadius;
	private JLabel lblRadius;

	public DialogDonut() {
		radius = new JTextField();
		lblRadius = new JLabel("Radius:");
		innerRadius = new JTextField();
		lblInnerRadius = new JLabel("Inner radius:");
		setTitle("Donut dialog");
		setIcon();
		buildLayout();
		addListener();
	}

	@Override
	public void setIcon() {
		getLblIcon().setVerticalAlignment(SwingConstants.BOTTOM);
		getLblIcon().setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/donut.png")).getImage()));
	}

	@Override
	public void buildLayout() {
		radius.setColumns(10);
		radius.addKeyListener(getListener());

		innerRadius.setColumns(10);
		innerRadius.addKeyListener(getListener());

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
								.addComponent(getBtnOuterColor()).addComponent(getBtnInnerColor())
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
										.addGap(9).addComponent(getBtnInnerColor())))
						.addGap(33)));

		getContentPanel().setLayout(getGlContentPanel());
	}

	@Override
	public boolean isInputValid() {
		if (innerRadius.getText().isBlank() || radius.getText().isBlank() || getXcoordinate().getText().isBlank()
				|| getYcoordinate().getText().isBlank())
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
		Donut donut = (Donut) selectedShape;
		getXcoordinate().setText(String.valueOf(donut.getCenter().getXcoordinate()));
		getYcoordinate().setText(String.valueOf(donut.getCenter().getYcoordinate()));
		getRadius().setText(String.valueOf(donut.getRadius()));
		getInnerRadius().setText(String.valueOf(donut.getInnerRadius()));
		setOuterColor(donut.getOuterColor());
		setInnerColor(donut.getInnerColor());
		getBtnOuterColor().setBackground(getOuterColor());
		getBtnInnerColor().setBackground(getInnerColor());
		setVisible(true);
	}

	private boolean isInputInvalidRadius() {
		if (Integer.parseInt(innerRadius.getText()) >= Integer.parseInt(radius.getText()))
			return true;
		return false;
	}

	private void addListener() {
		getBtnOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!isInputValid())
					JOptionPane.showMessageDialog(new JFrame(), "You have not filled in all the fields, try again!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				else if (isInputInvalidRadius())
					JOptionPane.showMessageDialog(new JFrame(), "The inner radius must be larger than the outer one!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				else {
					setAccepted(true);
					setVisible(false);
				}
			}
		});
	}

	public JTextField getRadius() {
		return radius;
	}

	public int getRadiusValue() {
		return Integer.parseInt(radius.toString());
	}

	public JTextField getInnerRadius() {
		return innerRadius;
	}

	public int getInnerRadiusValue() {
		return Integer.parseInt(innerRadius.getText());
	}
}