package dialogs;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogCircle extends DialogSurfaceShape {
	private static final long serialVersionUID = 1L;
	private JTextField radius;

	public DialogCircle() {
		buildDialogUI();
	}

	public void buildDialogUI() {
		setTitle("Circle dialog");

		radius = new JTextField();
		radius.setColumns(10);
		radius.addKeyListener(getListener());

		JLabel lblXcoodinate = new JLabel("X coodinate:");
		JLabel lblYcoordinate = new JLabel("Y coodinate:");
		JLabel lblRadius = new JLabel("Radius:");

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/circle.png")).getImage()));

		GroupLayout glContentPanel = new GroupLayout(getContentPanel());

		glContentPanel.setHorizontalGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glContentPanel.createSequentialGroup().addContainerGap()
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(glContentPanel.createSequentialGroup().addComponent(lblXcoodinate).addGap(18)
										.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(glContentPanel.createSequentialGroup().addGap(48)
										.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(glContentPanel.createSequentialGroup()
														.addComponent(lblRadius, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGap(18).addComponent(radius, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(glContentPanel.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
														.addComponent(lblYcoordinate).addGap(18)
														.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)))))
						.addGap(72)
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addGroup(glContentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(getBtnInnerColor()).addComponent(getBtnOuterColor())))
						.addGap(86)));

		glContentPanel.setVerticalGroup(glContentPanel.createParallelGroup(Alignment.LEADING).addGroup(glContentPanel
				.createSequentialGroup().addContainerGap(35, Short.MAX_VALUE)
				.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING, false).addGroup(glContentPanel
						.createSequentialGroup()
						.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblXcoodinate))
						.addGap(18)
						.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblYcoordinate))
						.addGap(14)
						.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(radius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRadius)))
						.addGroup(
								glContentPanel.createSequentialGroup()
										.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addGap(19)))
				.addGap(10).addComponent(getBtnOuterColor()).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(getBtnInnerColor()).addGap(21)));

		getContentPanel().setLayout(glContentPanel);
	}

	@Override
	public boolean isInputValid() {
		if ((radius.getText().isBlank() || getXcoordinate().getText().isBlank()
				|| getYcoordinate().getText().isBlank()))
			return false;
		return true;
	}

	public JTextField getRadius() {
		return radius;
	}
}