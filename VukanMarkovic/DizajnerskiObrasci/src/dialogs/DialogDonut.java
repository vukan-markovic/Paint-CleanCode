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

public class DialogDonut extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JTextField xCoordinate;
	private JTextField yCoordinate;
	private JTextField outerRadius;
	private JTextField innerRadius;
	private JButton btnOuterColor;
	private JButton btnInnerColor;
	private JButton btnOk;
	private JButton btnCancel;
	private Color outerColor;
	private Color innerColor;
	private boolean accepted;

	public DialogDonut() {
		setTitle("Donut dialog");
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

		outerRadius = new JTextField();
		outerRadius.setColumns(10);

		outerRadius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char outerRadiusInputChar = event.getKeyChar();

				if (!((outerRadiusInputChar >= '0') && (outerRadiusInputChar <= '9')
						|| (outerRadiusInputChar == KeyEvent.VK_BACK_SPACE)
						|| (outerRadiusInputChar == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});

		innerRadius = new JTextField();
		innerRadius.setColumns(10);

		innerRadius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char innerRadiusInputChar = event.getKeyChar();

				if (!((innerRadiusInputChar >= '0') && (innerRadiusInputChar <= '9')
						|| (innerRadiusInputChar == KeyEvent.VK_BACK_SPACE)
						|| (innerRadiusInputChar == KeyEvent.VK_DELETE))) {
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

		JLabel lblXcoordinate = new JLabel("X coordinate:");
		JLabel lblYcoordinate = new JLabel("Y coordinate:");
		JLabel lblOuterRadius = new JLabel("Outer radius:");
		JLabel lblInnerRadius = new JLabel("Inner radius:");

		JLabel lblIcon = new JLabel("");
		lblIcon.setVerticalAlignment(SwingConstants.BOTTOM);
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/donut.png")).getImage()));

		GroupLayout glContentPanel = new GroupLayout(contentPanel);

		glContentPanel.setHorizontalGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glContentPanel.createSequentialGroup().addContainerGap()
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(glContentPanel.createSequentialGroup().addComponent(lblXcoordinate).addGap(18)
										.addComponent(xCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
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
																yCoordinate, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(glContentPanel.createSequentialGroup()
														.addComponent(lblInnerRadius).addGap(18)
														.addComponent(innerRadius, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)))))
						.addGap(78)
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING).addComponent(btnOuterColor)
								.addComponent(btnInnerColor)
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGap(102)));

		glContentPanel
				.setVerticalGroup(glContentPanel.createParallelGroup(Alignment.LEADING).addGroup(glContentPanel
						.createSequentialGroup().addContainerGap(31, Short.MAX_VALUE)
						.addGroup(glContentPanel.createParallelGroup(Alignment.TRAILING).addGroup(glContentPanel
								.createSequentialGroup()
								.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(xCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblXcoordinate))
								.addGap(18)
								.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(yCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
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
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnOuterColor)
										.addGap(9).addComponent(btnInnerColor)))
						.addGap(33)));

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
				if (innerRadius.getText().isBlank() || outerRadius.getText().isBlank()
						|| xCoordinate.getText().isBlank() || yCoordinate.getText().isBlank())
					JOptionPane.showMessageDialog(new JFrame(), "You have not filled in all the fields, try again!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				else if (Integer.parseInt(innerRadius.getText()) >= Integer.parseInt(outerRadius.getText()))
					JOptionPane.showMessageDialog(new JFrame(), "The inner radius must be larger than the outer one!",
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

	public JTextField getOuterRadius() {
		return outerRadius;
	}

	public JTextField getInnerRadius() {
		return innerRadius;
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

	public void setOuterRadius(JTextField outerRadius) {
		this.outerRadius = outerRadius;
	}

	public void setInnerRadius(JTextField innerRadius) {
		this.innerRadius = innerRadius;
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