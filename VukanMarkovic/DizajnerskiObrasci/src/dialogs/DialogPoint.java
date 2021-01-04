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

public class DialogPoint extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private final JTextField xCoordinate;
	private final JTextField yCoordinate;
	private final JButton btnOuterColor;
	private JButton btnOk;
	private JButton btnCancel;
	private Color outerColor;
	private boolean accepted;

	public DialogPoint() {
		setTitle("Point dialog");
		setBounds(100, 100, 450, 303);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		outerColor = Color.BLACK;
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		xCoordinate = new JTextField();
		xCoordinate.setColumns(10);

		xCoordinate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char xCoordinateInputChar = event.getKeyChar();

				if (!((xCoordinateInputChar >= '0') && (xCoordinateInputChar <= '9')
						|| (xCoordinateInputChar == KeyEvent.VK_BACK_SPACE)
						|| (xCoordinateInputChar == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});

		yCoordinate = new JTextField();
		yCoordinate.setColumns(10);

		yCoordinate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char yCoordinateInputChar = event.getKeyChar();

				if (!((yCoordinateInputChar >= '0') && (yCoordinateInputChar <= '9')
						|| (yCoordinateInputChar == KeyEvent.VK_BACK_SPACE)
						|| (yCoordinateInputChar == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});

		btnOuterColor = new JButton("Outer color");
		btnOuterColor.setHorizontalAlignment(SwingConstants.RIGHT);

		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				outerColor = JColorChooser.showDialog(getContentPane(), "Choose outer color", new Color(250, 128, 114));
				btnOuterColor.setBackground(outerColor);
			}
		});

		JLabel lblXcoordinate = new JLabel("X coordinate:");
		JLabel lblYcoordinate = new JLabel("Y coordinate:");

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/point3.png")).getImage()));

		GroupLayout glContentPanel = new GroupLayout(contentPanel);

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
						.addComponent(btnOuterColor, Alignment.TRAILING))
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
				.addComponent(btnOuterColor).addGap(44)));

		contentPanel.setLayout(glContentPanel);

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(btnPanel, BorderLayout.SOUTH);

		btnOk = new JButton("OK");
		btnOk.setActionCommand("OK");
		btnPanel.add(btnOk);
		getRootPane().setDefaultButton(btnOk);

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (xCoordinate.getText().isBlank() || yCoordinate.getText().isBlank())
					JOptionPane.showMessageDialog(new JFrame(), "You have not filled in all the fields, try again!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				else {
					accepted = true;
					setVisible(false);
				}
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

	public JTextField getXcoordinate() {
		return xCoordinate;
	}

	public JTextField getYcoordinate() {
		return yCoordinate;
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