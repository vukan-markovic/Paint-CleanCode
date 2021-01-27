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
	private JTextField yCoordinate;
	private JLabel lblXcoodinate;
	private JLabel lblYcoordinate;
	private JButton btnOk;
	private JButton btnCancel;
	private JButton btnOuterColor;
	private Color outerColor = Color.BLACK;
	private KeyAdapter listener;
	private boolean accepted;

	public DialogShape() {
		lblIcon = new JLabel("");
		contentPanel = new JPanel();
		xCoordinate = new JTextField();
		yCoordinate = new JTextField();
		lblXcoodinate = new JLabel("X coodinate:");
		lblYcoordinate = new JLabel("Y coodinate:");
		btnOuterColor = new JButton("Outer color");
		btnOk = new JButton("OK");
		buildBasicLayout();
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
		xCoordinate.addKeyListener(getListener());

		yCoordinate.setColumns(10);
		yCoordinate.addKeyListener(getListener());

		listener = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				preventInvalidChar(event);
			}
		};

		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				outerColor = JColorChooser.showDialog(getContentPane(), "Choose outer color", outerColor);
				btnOuterColor.setBackground(outerColor);
			}
		});

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(btnPanel, BorderLayout.SOUTH);

		btnOk.setActionCommand("OK");
		btnPanel.add(btnOk);
		getRootPane().setDefaultButton(btnOk);

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

		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("Cancel");
		btnPanel.add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
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

	public JTextField getXcoordinate() {
		return xCoordinate;
	}

	public int getXcoordinateValue() {
		return Integer.parseInt(xCoordinate.getText());
	}

	public JTextField getYcoordinate() {
		return yCoordinate;
	}

	public int getYcoordinateValue() {
		return Integer.parseInt(yCoordinate.getText());
	}

	public JLabel getLblXcoodinate() {
		return lblXcoodinate;
	}

	public JLabel getLblYcoordinate() {
		return lblYcoordinate;
	}

	public GroupLayout getGlContentPanel() {
		return glContentPanel;
	}

	public JLabel getLblIcon() {
		return lblIcon;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JButton getBtnOuterColor() {
		return btnOuterColor;
	}

	public Color getOuterColor() {
		return outerColor;
	}

	public KeyAdapter getListener() {
		return listener;
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

	public JPanel getContentPanel() {
		return contentPanel;
	}
}