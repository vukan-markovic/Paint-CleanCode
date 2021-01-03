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

public class DialogRectangle extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JTextField xCoordinateOfUpperLeftPoint;
	private JTextField yCoordinateOfUpperLeftPoint;
	private JTextField height;
	private JTextField width;
	private boolean accepted = false;
	private JButton btnOuterColor;
	private JButton btnInnerColor;
	private JButton btnCancel;
	private JButton btnOk;
	private Color outerColor;
	private Color innerColor;

	public DialogRectangle() {
		setTitle("Rectangle dialog");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		outerColor = Color.BLACK;
		innerColor = Color.WHITE;
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			height = new JTextField();

			height.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent event) {
					char heightInputChar = event.getKeyChar();

					if (!((heightInputChar >= '0') && (heightInputChar <= '9')
							|| (heightInputChar == KeyEvent.VK_BACK_SPACE)
							|| (heightInputChar == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						event.consume();
					}
				}
			});

			height.setColumns(10);
		}

		width = new JTextField();

		width.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char widthInputChar = event.getKeyChar();

				if (!((widthInputChar >= '0') && (widthInputChar <= '9') || (widthInputChar == KeyEvent.VK_BACK_SPACE)
						|| (widthInputChar == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});

		width.setColumns(10);
		xCoordinateOfUpperLeftPoint = new JTextField();
		xCoordinateOfUpperLeftPoint.setColumns(10);
		yCoordinateOfUpperLeftPoint = new JTextField();
		yCoordinateOfUpperLeftPoint.setColumns(10);
		JLabel lblHeight = new JLabel("Height:");
		JLabel lblWidth = new JLabel("Width:");
		JLabel lblX = new JLabel("X:");
		JLabel lblY = new JLabel("Y:");
		btnOuterColor = new JButton("Outer color");

		xCoordinateOfUpperLeftPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char xCoordinateOfUpperLeftPointInputChar = event.getKeyChar();

				if (!((xCoordinateOfUpperLeftPointInputChar >= '0') && (xCoordinateOfUpperLeftPointInputChar <= '9')
						|| (xCoordinateOfUpperLeftPointInputChar == KeyEvent.VK_BACK_SPACE)
						|| (xCoordinateOfUpperLeftPointInputChar == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});

		yCoordinateOfUpperLeftPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				char yCoordinateOfUpperLeftPointInputChar = event.getKeyChar();

				if (!((yCoordinateOfUpperLeftPointInputChar >= '0') && (yCoordinateOfUpperLeftPointInputChar <= '9')
						|| (yCoordinateOfUpperLeftPointInputChar == KeyEvent.VK_BACK_SPACE)
						|| (yCoordinateOfUpperLeftPointInputChar == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					event.consume();
				}
			}
		});

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

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/rect.png")).getImage()));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);

		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(25)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblX)
								.addComponent(lblY).addComponent(lblHeight).addComponent(lblWidth))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(yCoordinateOfUpperLeftPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(width, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(xCoordinateOfUpperLeftPoint, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(height, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addGap(39))
								.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
										.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnInnerColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnOuterColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(63)))));

		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(37).addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(xCoordinateOfUpperLeftPoint, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblX))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(yCoordinateOfUpperLeftPoint, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblY))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(height, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblHeight)))
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup().addGap(18)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(width, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblWidth)))
										.addGroup(gl_contentPanel.createSequentialGroup().addGap(3)
												.addComponent(btnOuterColor).addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnInnerColor)))
								.addContainerGap(30, Short.MAX_VALUE)));

		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOk = new JButton("OK");

				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if ((width.getText().isBlank() || height.getText().isBlank()
								|| xCoordinateOfUpperLeftPoint.getText().isBlank()
								|| yCoordinateOfUpperLeftPoint.getText().isBlank()))
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

	public JTextField getXcoordinateOfUpperLeftPoint() {
		return xCoordinateOfUpperLeftPoint;
	}

	public JTextField getYcoordinateOfUpperLeftPoint() {
		return yCoordinateOfUpperLeftPoint;
	}

	public JTextField getheight() {
		return height;
	}

	public void setHeight(JTextField height) {
		this.height = height;
	}

	public JTextField getwidth() {
		return width;
	}

	public void setWidth(JTextField width) {
		this.width = width;
	}

	public JButton getBtnOuterColor() {
		return btnOuterColor;
	}

	public void setBtnOuterColor(JButton btnOuterColor) {
		this.btnOuterColor = btnOuterColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
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

	public void setOkButton(JButton btnOk) {
		this.btnOk = btnOk;
	}

	public Color getOuterColor() {
		return outerColor;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}