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
	private boolean accepted = false;
	private JButton btnOuterColor;
	private JButton btnCancel;
	private JButton btnOk;
	private Color outerColor;

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
		yCoordinateOfStartPoint = new JTextField();
		yCoordinateOfStartPoint.setColumns(10);
		xCoordinateOfEndPoint = new JTextField();
		xCoordinateOfEndPoint.setColumns(10);
		yCoordinateOfEndPoint = new JTextField();
		yCoordinateOfEndPoint.setColumns(10);

		JLabel lblXCoordinateOfStartPoint = new JLabel("X coordinate of start point:");
		JLabel lblYCoordinateOfStartPoint = new JLabel("Y coordinate of start point::");
		JLabel lblXCoordinateOfEndPoint = new JLabel("X coordinate of end point:");
		JLabel lblYCoordinateOfEndPoint = new JLabel("Y coordinate of end point:");
		btnOuterColor = new JButton("Outer color");

		xCoordinateOfStartPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char xCoordinateOfStartPointInputChar = event.getKeyChar();

				if (!((xCoordinateOfStartPointInputChar >= '0') && (xCoordinateOfStartPointInputChar <= '9')
						|| (xCoordinateOfStartPointInputChar == KeyEvent.VK_BACK_SPACE)
						|| (xCoordinateOfStartPointInputChar == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});

		yCoordinateOfStartPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char yCoordinateOfStartPointInputChar = event.getKeyChar();

				if (!((yCoordinateOfStartPointInputChar >= '0') && (yCoordinateOfStartPointInputChar <= '9')
						|| (yCoordinateOfStartPointInputChar == KeyEvent.VK_BACK_SPACE)
						|| (yCoordinateOfStartPointInputChar == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});

		xCoordinateOfEndPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char xCoordinateOfEndPointInputChar = event.getKeyChar();

				if (!((xCoordinateOfEndPointInputChar >= '0') && (xCoordinateOfEndPointInputChar <= '9')
						|| (xCoordinateOfEndPointInputChar == KeyEvent.VK_BACK_SPACE)
						|| (xCoordinateOfEndPointInputChar == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});

		yCoordinateOfEndPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char yCoordinateOfEndPointInputChar = event.getKeyChar();

				if (!((yCoordinateOfEndPointInputChar >= '0') && (yCoordinateOfEndPointInputChar <= '9')
						|| (yCoordinateOfEndPointInputChar == KeyEvent.VK_BACK_SPACE)
						|| (yCoordinateOfEndPointInputChar == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});

		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outerColor = JColorChooser.showDialog(getContentPane(), "Choose outer color", outerColor);
				btnOuterColor.setBackground(outerColor);
			}
		});

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/line.png")).getImage()));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);

		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(41)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblXCoordinateOfEndPoint).addComponent(lblYCoordinateOfEndPoint)
								.addComponent(lblYCoordinateOfStartPoint).addComponent(lblXCoordinateOfStartPoint))
						.addGap(26)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(yCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(yCoordinateOfStartPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(xCoordinateOfStartPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(xCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										gl_contentPanel.createSequentialGroup()
												.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 140,
														GroupLayout.PREFERRED_SIZE)
												.addGap(49))
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
										.addComponent(btnOuterColor).addGap(77)))));

		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(36)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblXCoordinateOfStartPoint)
												.addComponent(xCoordinateOfStartPoint, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblYCoordinateOfStartPoint)
												.addComponent(yCoordinateOfStartPoint, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblXCoordinateOfEndPoint)
												.addComponent(xCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(yCoordinateOfEndPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblYCoordinateOfEndPoint).addComponent(btnOuterColor))
						.addContainerGap(43, Short.MAX_VALUE)));

		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOk = new JButton("OK");

				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (xCoordinateOfStartPoint.getText().isBlank() || yCoordinateOfStartPoint.getText().isBlank()
								|| xCoordinateOfEndPoint.getText().isBlank()
								|| yCoordinateOfEndPoint.getText().isBlank())
							JOptionPane.showMessageDialog(new JFrame(),
									"You have not filled in all the fields, try again!", "Error!",
									JOptionPane.ERROR_MESSAGE);
						else {
							accepted = true;
							setVisible(false);

						}
					}
				});

				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCancel = new JButton("Cancel");

				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						dispose();
					}
				});

				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public JButton getBtnOuterColor() {
		return btnOuterColor;
	}

	public void setBtnOuterColor(JButton btnOuterColor) {
		this.btnOuterColor = btnOuterColor;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

	public Color getOuterColor() {
		return outerColor;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
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