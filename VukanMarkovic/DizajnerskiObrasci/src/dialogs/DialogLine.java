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

public class DialogLine extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private final JTextField x1Coord;
	private final JTextField y1Coord;
	private final JTextField x2Coord;
	private final JTextField y2Coord;
	private boolean accepted = false;
	private JButton btnSetOuterColor;
	private JButton cancelButton;
	private JButton okButton;
	private Color outerColor;

	public DialogLine() {
		setTitle("Line dialog");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		outerColor = new Color(250, 128, 114);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		x1Coord = new JTextField();
		x1Coord.setColumns(10);
		y1Coord = new JTextField();
		y1Coord.setColumns(10);
		x2Coord = new JTextField();
		x2Coord.setColumns(10);
		y2Coord = new JTextField();
		y2Coord.setColumns(10);
		JLabel lblX = new JLabel("X1:");
		JLabel lblY = new JLabel("Y1:");
		JLabel lblX_1 = new JLabel("X2");
		JLabel lblY_1 = new JLabel("Y2");
		btnSetOuterColor = new JButton("Outer color");

		x1Coord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		y1Coord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		x2Coord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		y2Coord.addKeyListener(new KeyAdapter() {
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

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/line.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);

		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(41)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblX_1)
								.addComponent(lblY_1).addComponent(lblY).addComponent(lblX))
						.addGap(26)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(y2Coord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(y1Coord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(x1Coord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(x2Coord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										gl_contentPanel.createSequentialGroup()
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 140,
														GroupLayout.PREFERRED_SIZE)
												.addGap(49))
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
										.addComponent(btnSetOuterColor).addGap(77)))));

		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(36)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblX).addComponent(x1Coord, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblY).addComponent(y1Coord, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblX_1).addComponent(x2Coord, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(y2Coord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblY_1).addComponent(btnSetOuterColor))
						.addContainerGap(43, Short.MAX_VALUE)));

		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");

				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (x1Coord.getText().isBlank() || y1Coord.getText().isBlank() || x2Coord.getText().isBlank()
								|| y2Coord.getText().isBlank())
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

	public JTextField getX1coordinate() {
		return x1Coord;
	}

	public JTextField getY1coordinate() {
		return y1Coord;
	}

	public JTextField getX2coordinate() {
		return x2Coord;
	}

	public JTextField getY2coordinate() {
		return y2Coord;
	}
}