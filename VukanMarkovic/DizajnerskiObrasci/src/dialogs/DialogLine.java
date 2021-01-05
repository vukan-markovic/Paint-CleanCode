package dialogs;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogLine extends DialogShape {
	private static final long serialVersionUID = 1L;
	private JTextField xCoordinateOfStartPoint;
	private JTextField yCoordinateOfStartPoint;
	private JTextField xCoordinateOfEndPoint;
	private JTextField yCoordinateOfEndPoint;

	public DialogLine() {
		buildDialogUI();
	}

	@Override
	public void buildDialogUI() {
		setTitle("Line dialog");

		xCoordinateOfStartPoint = new JTextField();
		xCoordinateOfStartPoint.setColumns(10);
		xCoordinateOfStartPoint.addKeyListener(getListener());

		yCoordinateOfStartPoint = new JTextField();
		yCoordinateOfStartPoint.setColumns(10);
		yCoordinateOfStartPoint.addKeyListener(getListener());

		xCoordinateOfEndPoint = new JTextField();
		xCoordinateOfEndPoint.setColumns(10);
		xCoordinateOfEndPoint.addKeyListener(getListener());

		yCoordinateOfEndPoint = new JTextField();
		yCoordinateOfEndPoint.setColumns(10);
		yCoordinateOfEndPoint.addKeyListener(getListener());

		JLabel lblXCoordinateOfStartPoint = new JLabel("X coordinate of start point:");
		JLabel lblYCoordinateOfStartPoint = new JLabel("Y coordinate of start point::");
		JLabel lblXCoordinateOfEndPoint = new JLabel("X coordinate of end point:");
		JLabel lblYCoordinateOfEndPoint = new JLabel("Y coordinate of end point:");

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/line.png")).getImage()));

		GroupLayout glContentPanel = new GroupLayout(getContentPanel());

		glContentPanel.setHorizontalGroup(glContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glContentPanel.createSequentialGroup().addGap(41)
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblXCoordinateOfEndPoint).addComponent(lblYCoordinateOfEndPoint)
								.addComponent(lblYCoordinateOfStartPoint).addComponent(lblXCoordinateOfStartPoint))
						.addGap(26)
						.addGroup(glContentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(yCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(yCoordinateOfStartPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(xCoordinateOfStartPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(xCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
						.addGroup(glContentPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
								glContentPanel.createSequentialGroup()
										.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 140,
												GroupLayout.PREFERRED_SIZE)
										.addGap(49))
								.addGroup(Alignment.TRAILING, glContentPanel.createSequentialGroup()
										.addComponent(getBtnOuterColor()).addGap(77)))));

		glContentPanel.setVerticalGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glContentPanel.createSequentialGroup().addGap(36)
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(glContentPanel.createSequentialGroup()
										.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblXCoordinateOfStartPoint)
												.addComponent(xCoordinateOfStartPoint, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblYCoordinateOfStartPoint)
												.addComponent(yCoordinateOfStartPoint, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblXCoordinateOfEndPoint)
												.addComponent(xCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(yCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblYCoordinateOfEndPoint).addComponent(getBtnOuterColor()))
						.addContainerGap(43, Short.MAX_VALUE)));

		getContentPanel().setLayout(glContentPanel);
	}

	@Override
	public boolean isInputValid() {
		if (xCoordinateOfStartPoint.getText().isBlank() || yCoordinateOfStartPoint.getText().isBlank()
				|| xCoordinateOfEndPoint.getText().isBlank() || yCoordinateOfEndPoint.getText().isBlank())
			return false;
		return true;
	}

	public JTextField getXCoordinateOfStartPoint() {
		return xCoordinateOfStartPoint;
	}

	public JTextField getYCoordinateOfStartPoint() {
		return yCoordinateOfStartPoint;
	}

	public JTextField getXCoordinateOfEndPoint() {
		return xCoordinateOfEndPoint;
	}

	public JTextField getYCoordinateOfEndPoint() {
		return yCoordinateOfEndPoint;
	}
}