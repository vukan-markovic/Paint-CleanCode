package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable<Object>, Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean selected;
	private Color border_Color;
	private int id;
	
	

	public Shape() {
		
	}
	
	public Shape(boolean selected,Color b) {
		this.selected = selected;
		this.border_Color=b;
	}
	
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getBorder_Color() {
		return border_Color;
	}

	public void setBorder_Color(Color border_Color) {
		this.border_Color = border_Color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	
	
}
