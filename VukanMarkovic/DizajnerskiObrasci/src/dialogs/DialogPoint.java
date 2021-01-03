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

public class DialogPoint extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private final JTextField xCoordinate;
	private final JTextField yCoordinate;
	private boolean accepted;
	private final JButton btnSetOuterColor;
	private JButton cancelButton;
	private JButton okButton;
	private Color outerColor;

	public DialogPoint() {
		setTitle("Point dialog");
		setBounds(100, 100, 450, 303);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		outerColor = new Color(250, 128, 114);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			xCoordinate = new JTextField();
			xCoordinate.setColumns(10);
		}
		{
			yCoordinate = new JTextField();
			yCoordinate.setColumns(10);
		}

		xCoordinate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		yCoordinate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		JLabel lblX = new JLabel("X:");
		JLabel lblY = new JLabel("Y:");
		btnSetOuterColor = new JButton("Outer color");
		btnSetOuterColor.setHorizontalAlignment(SwingConstants.RIGHT);

		btnSetOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outerColor = JColorChooser.showDialog(getContentPane(), "Choose Border Color",
						new Color(250, 128, 114));
				btnSetOuterColor.setBackground(outerColor);
			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/point3.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);

		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_contentPanel.createSequentialGroup().addContainerGap(46, Short.MAX_VALUE).addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel
										.createParallelGroup(Alignment.TRAILING).addComponent(lblX).addComponent(lblY))
								.addGap(30)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(xCoordinate, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(yCoordinate, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSetOuterColor, Alignment.TRAILING)).addGap(58)));

		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_contentPanel.createSequentialGroup().addContainerGap(24, Short.MAX_VALUE).addGroup(gl_contentPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(xCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblX))
								.addGap(45)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(yCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblY))
								.addGap(18))
						.addGroup(
								gl_contentPanel.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 88,
												GroupLayout.PREFERRED_SIZE)
										.addGap(42)))
						.addComponent(btnSetOuterColor).addGap(44)));

		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			{
				okButton = new JButton("OK");

				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (xCoordinate.getText().isBlank() || yCoordinate.getText().isBlank())
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
		return xCoordinate;
	}

	public JTextField getYcoordinate() {
		return yCoordinate;
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
}