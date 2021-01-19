package dialogs;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class DialogRectangle extends DialogSurfaceShape {
	private static final long serialVersionUID = 1L;
	private JTextField height;
	private JTextField width;
	private JLabel lblHeight;
	private JLabel lblWidth;

	public DialogRectangle() {
		height = new JTextField();
		width = new JTextField();
		lblHeight = new JLabel("Height:");
		lblWidth = new JLabel("Width:");

		setTitle("Rectangle dialog");
		setIcon();
		buildLayout();
	}

	@Override
	public void setIcon() {
		getLblIcon().setIcon(new ImageIcon(getClass().getResource("/rect.png")));
	}

	@Override
	public void buildLayout() {
		height.setColumns(10);
		height.addKeyListener(getListener());
		width.setColumns(10);
		width.addKeyListener(getListener());

		getGlContentPanel().setHorizontalGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
				.addGroup(getGlContentPanel().createSequentialGroup().addGap(25)
						.addGroup(getGlContentPanel()
								.createParallelGroup(Alignment.TRAILING).addComponent(getLblXcoodinate())
								.addComponent(getLblYcoordinate()).addComponent(lblHeight).addComponent(lblWidth))
						.addGap(18)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
								.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
										.addComponent(width, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(height, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
								.addGroup(getGlContentPanel().createSequentialGroup()
										.addComponent(getLblIcon(), GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addGap(39))
								.addGroup(getGlContentPanel().createSequentialGroup().addGroup(getGlContentPanel()
										.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(getBtnInnerColor(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(getBtnOuterColor(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(63)))));

		getGlContentPanel().setVerticalGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
				.addGroup(getGlContentPanel().createSequentialGroup().addGap(37).addGroup(getGlContentPanel()
						.createParallelGroup(Alignment.LEADING)
						.addGroup(getGlContentPanel().createSequentialGroup()
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
										.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(getLblXcoodinate()))
								.addGap(18)
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
										.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(getLblYcoordinate()))
								.addGap(18)
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
										.addComponent(height, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHeight)))
						.addComponent(getLblIcon(), GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
								.addGroup(getGlContentPanel().createSequentialGroup().addGap(18)
										.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
												.addComponent(width, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblWidth)))
								.addGroup(getGlContentPanel().createSequentialGroup().addGap(3)
										.addComponent(getBtnOuterColor()).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getBtnInnerColor())))
						.addContainerGap(30, Short.MAX_VALUE)));

		getContentPanel().setLayout(getGlContentPanel());
	}

	@Override
	public boolean isInputValid() {
		if ((width.getText().isBlank() || height.getText().isBlank() || getXcoordinate().getText().isBlank()
				|| getYcoordinate().getText().isBlank()))
			return false;
		return true;
	}

	@Override
	public void setDialog(Point upperLeftPoint) {
		getBtnOuterColor().setVisible(false);
		getBtnInnerColor().setVisible(false);
		getXcoordinate().setText(String.valueOf(upperLeftPoint.getXcoordinate()));
		getYcoordinate().setText(String.valueOf(upperLeftPoint.getYcoordinate()));
		getXcoordinate().setEditable(false);
		getYcoordinate().setEditable(false);
		setVisible(true);
	}

	@Override
	public void setModifyDialog(Shape selectedShape) {
		Rectangle rectangle = (Rectangle) selectedShape;
		getXcoordinate().setText(String.valueOf(rectangle.getUpperLeftPoint().getXcoordinate()));
		getYcoordinate().setText(String.valueOf(rectangle.getUpperLeftPoint().getYcoordinate()));
		getheight().setText(String.valueOf(rectangle.getHeight()));
		getwidth().setText(String.valueOf(rectangle.getWidth()));
		setOuterColor(rectangle.getOuterColor());
		setInnerColor(rectangle.getInnerColor());
		getBtnOuterColor().setBackground(getOuterColor());
		getBtnInnerColor().setBackground(getInnerColor());
		setVisible(true);
	}

	public JTextField getheight() {
		return height;
	}

	public JTextField getwidth() {
		return width;
	}
}