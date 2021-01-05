package dialogs;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogRectangle extends DialogSurfaceShape {
	private static final long serialVersionUID = 1L;
	private JTextField height;
	private JTextField width;

	public DialogRectangle() {
		buildDialogUI();
	}

	@Override
	public void buildDialogUI() {
		setTitle("Rectangle dialog");

		height = new JTextField();
		height.setColumns(10);
		height.addKeyListener(getListener());

		width = new JTextField();
		width.setColumns(10);
		width.addKeyListener(getListener());

		JLabel lblHeight = new JLabel("Height:");
		JLabel lblWidth = new JLabel("Width:");
		JLabel lblX = new JLabel("X:");
		JLabel lblY = new JLabel("Y:");

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(getClass().getResource("/rect.png")));

		GroupLayout glContentPanel = new GroupLayout(getContentPanel());

		glContentPanel.setHorizontalGroup(glContentPanel.createParallelGroup(Alignment.LEADING).addGroup(glContentPanel
				.createSequentialGroup().addGap(25)
				.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblX).addComponent(lblY)
						.addComponent(lblHeight).addComponent(lblWidth))
				.addGap(18)
				.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(glContentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(width, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(height, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE).addGroup(
						glContentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										glContentPanel
												.createSequentialGroup()
												.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 145,
														GroupLayout.PREFERRED_SIZE)
												.addGap(39))
								.addGroup(glContentPanel.createSequentialGroup().addGroup(glContentPanel
										.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(getBtnInnerColor(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(getBtnOuterColor(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(63)))));

		glContentPanel
				.setVerticalGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(glContentPanel.createSequentialGroup().addGap(37).addGroup(glContentPanel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(glContentPanel.createSequentialGroup()
										.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblX))
										.addGap(18)
										.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblY))
										.addGap(18)
										.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(height, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblHeight)))
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
								.addGroup(glContentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(glContentPanel.createSequentialGroup().addGap(18)
												.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(width, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblWidth)))
										.addGroup(glContentPanel.createSequentialGroup().addGap(3)
												.addComponent(getBtnOuterColor())
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getBtnInnerColor())))
								.addContainerGap(30, Short.MAX_VALUE)));

		getContentPanel().setLayout(glContentPanel);
	}
	
	@Override
	public boolean isInputValid() {
		if ((width.getText().isBlank() || height.getText().isBlank() || getXcoordinate().getText().isBlank()
				|| getYcoordinate().getText().isBlank()))
			return false;
		return true;
	}

	public JTextField getheight() {
		return height;
	}

	public JTextField getwidth() {
		return width;
	}
}