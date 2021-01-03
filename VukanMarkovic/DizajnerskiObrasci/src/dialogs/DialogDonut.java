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

public class DialogDonut extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JTextField xCoord;
	private JTextField yCoord;
	private JTextField radius;
	private JTextField smallRadius;
	private boolean accepted = false;
	private JButton btnSetOuterColor;
	private JButton btnSetInnerColor;
	private JButton cancelButton;
	private JButton okButton;
	private Color outerColor;
	private Color innerColor;

	public DialogDonut() {
		setTitle("Donut dialog");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		outerColor = Color.BLACK;
		innerColor = new Color(0, 0, 0, 0);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.WEST);
		{
			xCoord = new JTextField();
			xCoord.setColumns(10);
		}

		yCoord = new JTextField();
		yCoord.setColumns(10);
		radius = new JTextField();

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

		radius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		radius.setColumns(10);
		smallRadius = new JTextField();

		smallRadius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		smallRadius.setColumns(10);
		JLabel lblX = new JLabel("X:");
		JLabel lblY = new JLabel("Y:");
		JLabel lblR = new JLabel("r1:");
		JLabel lblR_1 = new JLabel("r2:");
		btnSetOuterColor = new JButton("Outer color");

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
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/donut.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);

		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblX).addGap(18)
										.addComponent(xCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(48)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addComponent(lblR, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGap(18).addComponent(radius, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
														.addComponent(lblY).addGap(18).addComponent(
																yCoord, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblR_1)
														.addGap(18).addComponent(smallRadius,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)))))
						.addGap(78)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(btnSetOuterColor)
								.addComponent(btnSetInnerColor)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGap(102)));

		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap(31, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(xCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblX))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(yCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblY))
								.addGap(14)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(radius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblR)))
						.addGroup(
								gl_contentPanel.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)))
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(smallRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblR_1)))
						.addGroup(gl_contentPanel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSetOuterColor).addGap(9).addComponent(btnSetInnerColor)))
				.addGap(33)));

		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");

				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (smallRadius.getText().isBlank() || radius.getText().isBlank() || xCoord.getText().isBlank()
								|| yCoord.getText().isBlank()) {
							JOptionPane.showMessageDialog(new JFrame(), "Niste popunili sva polja, pokušajte ponovo!",
									"Greška!", JOptionPane.ERROR_MESSAGE);
						} else if (Integer.parseInt(smallRadius.getText()) >= Integer.parseInt(radius.getText())) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Unutrašnji radius mora biti veæi od spoljašnjeg!", "Greška!",
									JOptionPane.ERROR_MESSAGE);
						} else {
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

	public JTextField getRadius() {
		return radius;
	}

	public void setRadius(JTextField radius) {
		this.radius = radius;
	}

	public JTextField getSmallRadius() {
		return smallRadius;
	}

	public void setSmallRadius(JTextField smallRadius) {
		this.smallRadius = smallRadius;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public JButton getBtnSetOuterColor() {
		return btnSetOuterColor;
	}

	public void setBtnSetOuterColor(JButton btnSetOuterColor) {
		this.btnSetOuterColor = btnSetOuterColor;
	}

	public JButton getBtnSetInnerColor() {
		return btnSetInnerColor;
	}

	public void setBtnSetInnerColor(JButton btnSetInnerColor) {
		this.btnSetInnerColor = btnSetInnerColor;
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
}