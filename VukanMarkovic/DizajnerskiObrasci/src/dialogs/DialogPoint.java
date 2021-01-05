package dialogs;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogPoint extends DialogShape {
	private static final long serialVersionUID = 1L;
	private JTextField xCoordinate;
	private JTextField yCoordinate;

	public DialogPoint() {
		buildDialogUI();
	}

	@Override
	public void buildDialogUI() {
		setTitle("Point dialog");

		xCoordinate = new JTextField();
		xCoordinate.setColumns(10);
		xCoordinate.addKeyListener(getListener());

		yCoordinate = new JTextField();
		yCoordinate.setColumns(10);
		yCoordinate.addKeyListener(getListener());

		JLabel lblXcoordinate = new JLabel("X coordinate:");
		JLabel lblYcoordinate = new JLabel("Y coordinate:");

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/point3.png")).getImage()));

		GroupLayout glContentPanel = new GroupLayout(getContentPanel());

		glContentPanel.setHorizontalGroup(glContentPanel.createParallelGroup(Alignment.TRAILING).addGroup(glContentPanel
				.createSequentialGroup().addContainerGap(46, Short.MAX_VALUE)
				.addGroup(glContentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(glContentPanel.createSequentialGroup()
								.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblXcoordinate).addComponent(lblYcoordinate))
								.addGap(30)
								.addGroup(glContentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(xCoordinate, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(yCoordinate, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addComponent(getBtnOuterColor(), Alignment.TRAILING))
				.addGap(58)));

		glContentPanel.setVerticalGroup(glContentPanel.createParallelGroup(Alignment.TRAILING).addGroup(glContentPanel
				.createSequentialGroup().addContainerGap(24, Short.MAX_VALUE)
				.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(glContentPanel.createSequentialGroup()
								.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(xCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblXcoordinate))
								.addGap(45)
								.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(yCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblYcoordinate))
								.addGap(18))
						.addGroup(
								glContentPanel.createSequentialGroup()
										.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 88,
												GroupLayout.PREFERRED_SIZE)
										.addGap(42)))
				.addComponent(getBtnOuterColor()).addGap(44)));

		getContentPanel().setLayout(glContentPanel);
	}

	public boolean isInputValid() {
		if (xCoordinate.getText().isBlank() || yCoordinate.getText().isBlank())
			return false;
		return true;
	}

	public JTextField getXcoordinate() {
		return xCoordinate;
	}

	public JTextField getYcoordinate() {
		return yCoordinate;
	}
}