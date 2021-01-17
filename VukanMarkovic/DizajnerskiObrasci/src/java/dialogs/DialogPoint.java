package dialogs;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogPoint extends DialogShape {
	private static final long serialVersionUID = 1L;

	public DialogPoint() {
		setTitle("Point dialog");
		setIcon();
		buildLayout();
	}

	@Override
	public void setIcon() {
		getLblIcon().setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/point3.png")).getImage()));
	}

	@Override
	public void buildLayout() {
		getGlContentPanel().setHorizontalGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
				.addGroup(getGlContentPanel().createSequentialGroup().addContainerGap(46, Short.MAX_VALUE).addGroup(
						getGlContentPanel().createParallelGroup(Alignment.LEADING).addGroup(getGlContentPanel()
								.createSequentialGroup()
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
										.addComponent(getLblXcoodinate()).addComponent(getLblYcoordinate()))
								.addGap(30)
								.addGroup(getGlContentPanel().createParallelGroup(Alignment.LEADING)
										.addComponent(getXcoordinate(), Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(getYcoordinate(), Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE).addComponent(
										getLblIcon(), GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
								.addComponent(getBtnOuterColor(), Alignment.TRAILING))
						.addGap(58)));

		getGlContentPanel().setVerticalGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
				.addGroup(getGlContentPanel().createSequentialGroup().addContainerGap(24, Short.MAX_VALUE)
						.addGroup(getGlContentPanel().createParallelGroup(Alignment.TRAILING)
								.addGroup(getGlContentPanel().createSequentialGroup()
										.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
												.addComponent(getXcoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(getLblXcoodinate()))
										.addGap(45)
										.addGroup(getGlContentPanel().createParallelGroup(Alignment.BASELINE)
												.addComponent(getYcoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(getLblYcoordinate()))
										.addGap(18))
								.addGroup(getGlContentPanel().createSequentialGroup()
										.addComponent(getLblIcon(), GroupLayout.PREFERRED_SIZE, 88,
												GroupLayout.PREFERRED_SIZE)
										.addGap(42)))
						.addComponent(getBtnOuterColor()).addGap(44)));

		getContentPanel().setLayout(getGlContentPanel());
	}

	public boolean isInputValid() {
		if (getXcoordinate().getText().isBlank() || getYcoordinate().getText().isBlank())
			return false;
		return true;
	}
}