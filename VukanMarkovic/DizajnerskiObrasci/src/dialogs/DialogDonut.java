package dialogs;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogDonut extends DialogSurfaceShape {
	private static final long serialVersionUID = 1L;
	private JTextField outerRadius;
	private JTextField innerRadius;

	public DialogDonut() {
		buildDialogUI();
		addListener();
	}

	@Override
	public void buildDialogUI() {
		setTitle("Donut dialog");

		outerRadius = new JTextField();
		outerRadius.setColumns(10);
		outerRadius.addKeyListener(getListener());

		innerRadius = new JTextField();
		innerRadius.setColumns(10);
		innerRadius.addKeyListener(getListener());

		JLabel lblXcoordinate = new JLabel("X coordinate:");
		JLabel lblYcoordinate = new JLabel("Y coordinate:");
		JLabel lblOuterRadius = new JLabel("Outer radius:");
		JLabel lblInnerRadius = new JLabel("Inner radius:");

		JLabel lblIcon = new JLabel("");
		lblIcon.setVerticalAlignment(SwingConstants.BOTTOM);
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/donut.png")).getImage()));

		GroupLayout glContentPanel = new GroupLayout(getContentPanel());

		glContentPanel.setHorizontalGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glContentPanel.createSequentialGroup().addContainerGap()
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(glContentPanel.createSequentialGroup().addComponent(lblXcoordinate).addGap(18)
										.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(glContentPanel.createSequentialGroup().addGap(48)
										.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(glContentPanel.createSequentialGroup()
														.addComponent(lblOuterRadius, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGap(18)
														.addComponent(outerRadius, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(glContentPanel.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
														.addComponent(lblYcoordinate).addGap(18).addComponent(
																getYcoordinate(), GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(glContentPanel.createSequentialGroup()
														.addComponent(lblInnerRadius).addGap(18)
														.addComponent(innerRadius, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)))))
						.addGap(78)
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(getBtnOuterColor()).addComponent(getBtnInnerColor())
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGap(102)));

		glContentPanel
				.setVerticalGroup(glContentPanel.createParallelGroup(Alignment.LEADING).addGroup(glContentPanel
						.createSequentialGroup().addContainerGap(31, Short.MAX_VALUE)
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING).addGroup(glContentPanel
								.createSequentialGroup()
								.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblXcoordinate))
								.addGap(18)
								.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblYcoordinate))
								.addGap(14)
								.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(outerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblOuterRadius)))
								.addGroup(glContentPanel
										.createSequentialGroup()
										.addComponent(
												lblIcon, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addGap(18)))
						.addGroup(glContentPanel.createParallelGroup(Alignment.LEADING).addGroup(glContentPanel
								.createSequentialGroup().addGap(18)
								.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(innerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblInnerRadius)))
								.addGroup(glContentPanel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(getBtnOuterColor())
										.addGap(9).addComponent(getBtnInnerColor())))
						.addGap(33)));

		getContentPanel().setLayout(glContentPanel);
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

	@Override
	public boolean isInputValid() {
		if (innerRadius.getText().isBlank() || outerRadius.getText().isBlank() || getXcoordinate().getText().isBlank()
				|| getYcoordinate().getText().isBlank())
			return false;
		return true;
	}

	private boolean isInputInvalidRadius() {
		if (Integer.parseInt(innerRadius.getText()) >= Integer.parseInt(outerRadius.getText()))
			return true;
		return false;
	}

	public JTextField getOuterRadius() {
		return outerRadius;
	}

	public JTextField getInnerRadius() {
		return innerRadius;
	}
}