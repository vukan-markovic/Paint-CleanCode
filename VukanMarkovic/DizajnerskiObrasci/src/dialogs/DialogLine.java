package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DialogLine extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private final JTextField xCoordinateOfStartPoint;
	private final JTextField yCoordinateOfStartPoint;
	private final JTextField xCoordinateOfEndPoint;
	private final JTextField yCoordinateOfEndPoint;
	private JButton btnOuterColor;
	private JButton btnOk;
	private JButton btnCancel;
	private Color outerColor;
	private boolean accepted;

	public DialogLine() {
		setTitle("Line dialog");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		outerColor = Color.BLACK;
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		xCoordinateOfStartPoint = new JTextField();
		xCoordinateOfStartPoint.setColumns(10);

		xCoordinateOfStartPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				preventInvalidChar(event);
			}
		});

		yCoordinateOfStartPoint = new JTextField();
		yCoordinateOfStartPoint.setColumns(10);

		yCoordinateOfStartPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				preventInvalidChar(event);
			}
		});

		xCoordinateOfEndPoint = new JTextField();
		xCoordinateOfEndPoint.setColumns(10);

		xCoordinateOfEndPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				preventInvalidChar(event);
			}
		});

		yCoordinateOfEndPoint = new JTextField();
		yCoordinateOfEndPoint.setColumns(10);

		yCoordinateOfEndPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				preventInvalidChar(event);
			}
		});

		btnOuterColor = new JButton("Outer color");

		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outerColor = JColorChooser.showDialog(getContentPane(), "Choose outer color", outerColor);
				btnOuterColor.setBackground(outerColor);
			}
		});

		JLabel lblXCoordinateOfStartPoint = new JLabel("X coordinate of start point:");
		JLabel lblYCoordinateOfStartPoint = new JLabel("Y coordinate of start point::");
		JLabel lblXCoordinateOfEndPoint = new JLabel("X coordinate of end point:");
		JLabel lblYCoordinateOfEndPoint = new JLabel("Y coordinate of end point:");

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/line.png")).getImage()));

		GroupLayout glContentPanel = new GroupLayout(contentPanel);

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
										.addComponent(btnOuterColor).addGap(77)))));

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
								.addComponent(lblYCoordinateOfEndPoint).addComponent(btnOuterColor))
						.addContainerGap(43, Short.MAX_VALUE)));

		contentPanel.setLayout(glContentPanel);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnOk = new JButton("OK");
		btnOk.setActionCommand("OK");
		buttonPane.add(btnOk);
		getRootPane().setDefaultButton(btnOk);

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (isInputValid()) {
					accepted = true;
					setVisible(false);

				} else
					JOptionPane.showMessageDialog(new JFrame(), "You have not filled in all the fields, try again!",
							"Error!", JOptionPane.ERROR_MESSAGE);
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("Cancel");
		buttonPane.add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
	}

	private void preventInvalidChar(KeyEvent event) {
		char charInput = event.getKeyChar();

		if (charInput <= '0' || charInput >= '9' || charInput == KeyEvent.VK_BACK_SPACE
				|| charInput == KeyEvent.VK_DELETE) {
			getToolkit().beep();
			event.consume();
		}
	}

	private boolean isInputValid() {
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

	public void setBtnOuterColor(JButton btnOuterColor) {
		this.btnOuterColor = btnOuterColor;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}