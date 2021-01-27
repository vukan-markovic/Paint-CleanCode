package dialogs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public abstract class DialogShape extends JDialog implements Dialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private GroupLayout glContentPanel;
	private JLabel lblIcon;
	private JTextField xCoordinate;
	private JLabel lblXcoodinate;
	private JTextField yCoordinate;
	private JLabel lblYcoordinate;
	private KeyAdapter inputListener;
	private JButton btnOuterColor;
	private JButton btnOk;
	private JButton btnCancel;
	private Color outerColor = Color.BLACK;
	private boolean accepted;

	public DialogShape() {
		contentPanel = new JPanel();
		lblIcon = new JLabel("");
		xCoordinate = new JTextField();
		lblXcoodinate = new JLabel("X coodinate:");
		yCoordinate = new JTextField();
		lblYcoordinate = new JLabel("Y coodinate:");
		btnOuterColor = new JButton("Outer color");
		btnOk = new JButton("OK");
		btnCancel = new JButton("Cancel");
		buildBasicLayout();
		initializeCharListener();
		addBtnOuterColorListener();
		addBtnOkListener();
		addBtnCancelListener();
	}

	private void buildBasicLayout() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		glContentPanel = new GroupLayout(getContentPanel());
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);

		xCoordinate.setColumns(10);
		xCoordinate.addKeyListener(getInputListener());

		yCoordinate.setColumns(10);
		yCoordinate.addKeyListener(getInputListener());

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(btnPanel, BorderLayout.SOUTH);

		btnOk.setActionCommand("OK");
		btnPanel.add(btnOk);
		getRootPane().setDefaultButton(btnOk);

		btnCancel.setActionCommand("Cancel");
		btnPanel.add(btnCancel);
	}

	private void initializeCharListener() {
		inputListener = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				preventInvalidChar(event);
			}
		};
	}

	public void preventInvalidChar(KeyEvent event) {
		char charInput = event.getKeyChar();

		if (isCharInvalid(charInput)) {
			getToolkit().beep();
			event.consume();
		}
	}

	private boolean isCharInvalid(char charInput) {
		return charInput < '0' || charInput > '9' || charInput == KeyEvent.VK_BACK_SPACE
				|| charInput == KeyEvent.VK_DELETE;
	}

	private void addBtnOuterColorListener() {
		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				outerColor = JColorChooser.showDialog(getContentPane(), "Choose outer color", outerColor);
				btnOuterColor.setBackground(outerColor);
			}
		});
	}

	private void addBtnOkListener() {
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (isInputValid()) {
					setAccepted(true);
					setVisible(false);
				} else
					JOptionPane.showMessageDialog(new JFrame(), "You have not filled in all the fields, try again!",
							"Error!", JOptionPane.ERROR_MESSAGE);
			}
		});

	}

	private void addBtnCancelListener() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public GroupLayout getGlContentPanel() {
		return glContentPanel;
	}

	public JLabel getLblIcon() {
		return lblIcon;
	}

	public JTextField getXcoordinate() {
		return xCoordinate;
	}

	public int getXcoordinateValue() {
		return Integer.parseInt(xCoordinate.getText());
	}

	public JLabel getLblXcoodinate() {
		return lblXcoodinate;
	}

	public JTextField getYcoordinate() {
		return yCoordinate;
	}

	public int getYcoordinateValue() {
		return Integer.parseInt(yCoordinate.getText());
	}

	public JLabel getLblYcoordinate() {
		return lblYcoordinate;
	}

	public KeyAdapter getInputListener() {
		return inputListener;
	}

	public JButton getBtnOuterColor() {
		return btnOuterColor;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public Color getOuterColor() {
		return outerColor;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}