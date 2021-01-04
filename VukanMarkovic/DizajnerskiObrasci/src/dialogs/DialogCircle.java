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

public class DialogCircle extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JTextField xCoordinate;
	private JTextField yCoordinate;
	private JTextField radius;
	private JButton btnOuterColor;
	private JButton btnInnerColor;
	private JButton btnOk;
	private JButton btnCancel;
	private Color outerColor;
	private Color innerColor;
	private boolean accepted;

	public DialogCircle() {
		setTitle("Circle dialog");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		outerColor = Color.BLACK;
		innerColor = Color.WHITE;
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);

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

		radius = new JTextField();
		radius.setColumns(10);

		radius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char radiusInput = event.getKeyChar();

				if (!((radiusInput >= '0') && (radiusInput <= '9') || (radiusInput == KeyEvent.VK_BACK_SPACE)
						|| (radiusInput == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});

		btnOuterColor = new JButton("Outer color");

		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				outerColor = JColorChooser.showDialog(getContentPane(), "Choose outer color", outerColor);
				btnOuterColor.setBackground(outerColor);
			}
		});

		btnInnerColor = new JButton("Inner color");

		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				innerColor = JColorChooser.showDialog(getContentPane(), "Choose inner color", innerColor);
				btnInnerColor.setBackground(innerColor);
			}
		});

		JLabel lblXcoodinate = new JLabel("X coodinate:");
		JLabel lblYcoordinate = new JLabel("Y coodinate:");
		JLabel lblRadius = new JLabel("Radius:");

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/circle.png")).getImage()));

		GroupLayout glContentPanel = new GroupLayout(contentPanel);

		glContentPanel.setHorizontalGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glContentPanel.createSequentialGroup().addContainerGap()
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(glContentPanel.createSequentialGroup().addComponent(lblXcoodinate).addGap(18)
										.addComponent(xCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
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
														.addComponent(yCoordinate, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)))))
						.addGap(72)
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addGroup(glContentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnInnerColor).addComponent(btnOuterColor)))
						.addGap(86)));

		glContentPanel.setVerticalGroup(glContentPanel.createParallelGroup(Alignment.LEADING).addGroup(glContentPanel
				.createSequentialGroup().addContainerGap(35, Short.MAX_VALUE)
				.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(glContentPanel.createSequentialGroup()
								.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(xCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblXcoodinate))
								.addGap(18)
								.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(yCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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
				.addGap(10).addComponent(btnOuterColor).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnInnerColor).addGap(21)));

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
				if ((radius.getText().isBlank() || xCoordinate.getText().isBlank() || yCoordinate.getText().isBlank()))
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

	public JTextField getRadius() {
		return radius;
	}

	public JButton getBtnOuterColor() {
		return btnOuterColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
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

	public Color getInnerColor() {
		return innerColor;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setRadius(JTextField radius) {
		this.radius = radius;
	}

	public void setBtnOuterColor(JButton btnOuterColor) {
		this.btnOuterColor = btnOuterColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
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

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}