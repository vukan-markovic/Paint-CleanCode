package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
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
	private JTextField xCoord;
	private JTextField yCoord;
	private JTextField height;
	private JTextField width;
	private boolean accepted = false;
	private JButton btnSetOuterColor;
	private JButton btnSetInnerColor;
	private JButton cancelButton;
	private JButton okButton;
	private Color outerColor;
	private Color innerColor;

	public DialogRectangle() {
		setTitle("Rectangle dialog");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		outerColor = Color.BLACK;
		innerColor = new Color(0, 0, 0, 0);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			height = new JTextField();

			height.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();

					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});

			height.setColumns(10);
		}

		width = new JTextField();

		width.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		width.setColumns(10);
		xCoord = new JTextField();
		xCoord.setColumns(10);
		yCoord = new JTextField();
		yCoord.setColumns(10);
		JLabel lblHeight = new JLabel("Height:");
		JLabel lblWidth = new JLabel("Width:");
		JLabel lblX = new JLabel("X:");
		JLabel lblY = new JLabel("Y:");
		btnSetOuterColor = new JButton("Outer color");

		xCoord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		yCoord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		btnSetOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outerColor = JColorChooser.showDialog(getContentPane(), "Choose Border Color", outerColor);
				btnSetOuterColor.setBackground(outerColor);
			}
		});

		btnSetInnerColor = new JButton("Inner color");

		btnSetInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(getContentPane(), "Choose Fill Color", innerColor);
				btnSetInnerColor.setBackground(innerColor);
			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/rect.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);

		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(25)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblX)
								.addComponent(lblY).addComponent(lblHeight).addComponent(lblWidth))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(yCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(width, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(xCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(height, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addGap(39))
								.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
										.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnSetInnerColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnSetOuterColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(63)))));

		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(37).addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(xCoord, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblX))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(yCoord, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblY))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(height, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblHeight)))
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup().addGap(18)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(width, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblWidth)))
										.addGroup(gl_contentPanel.createSequentialGroup().addGap(3)
												.addComponent(btnSetOuterColor)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnSetInnerColor)))
								.addContainerGap(30, Short.MAX_VALUE)));

		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");

				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if ((width.getText().isBlank() || height.getText().isBlank() || xCoord.getText().isBlank()
								|| yCoord.getText().isBlank()))
							JOptionPane.showMessageDialog(new JFrame(), "Niste popunili sva polja, pokušajte ponovo!",
									"Greška!", JOptionPane.ERROR_MESSAGE);
						else {
							accepted = true;
							setVisible(false);
						}
					}
				});

				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");

				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});

				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getXcoordinate() {
		return xCoord;
	}

	public JTextField getYcoordinate() {
		return yCoord;
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

	public JButton getBtnSetBorderColor() {
		return btnSetOuterColor;
	}

	public void setBtnSetBorderColor(JButton btnSetBorderColor) {
		this.btnSetOuterColor = btnSetBorderColor;
	}

	public JButton getBtnSetFillColor() {
		return btnSetInnerColor;
	}

	public void setBtnSetFillColor(JButton btnSetFillColor) {
		this.btnSetInnerColor = btnSetFillColor;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
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