package dialogs;

import javax.swing.*;

public class DialogHexagon extends DialogCircle {
	private static final long serialVersionUID = 1L;

	public DialogHexagon() {
		setTitle("Hexagon dialog");
	}

	@Override
	public void setIcon() {
		getLblIcon().setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/hexagon.png")).getImage()));
	}
}