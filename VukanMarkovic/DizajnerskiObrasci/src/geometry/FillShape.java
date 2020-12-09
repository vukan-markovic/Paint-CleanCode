package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class FillShape extends Shape {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Color fill_Color = new Color(0,0,0,0);
	public abstract void fill_shape(Graphics g);

	public Color getFill_Color() {
		return fill_Color;
	}

	public void setFill_Color(Color fill_Color) {
		this.fill_Color = fill_Color;
	}
	
	

}
