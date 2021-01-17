package shapes;

import java.awt.*;
import java.awt.geom.*;

public class Donut extends Circle {
	private static final long serialVersionUID = 1L;
	private int innerRadius;
	private Area area;
	private Graphics2D graphics2d;

	public Donut(Point center, int outerRadius, int innerRadius, boolean selected, Color outerColor, Color innerColor) {
		super(center, outerRadius, selected, outerColor, innerColor);
		this.innerRadius = innerRadius;
	}

	public void draw(Graphics graphics) {
		calculateArea();
		graphics.setColor(getOuterColor());
		graphics2d = (Graphics2D) graphics;
		graphics2d.draw(area);
		fillShape(graphics);

		if (isSelected())
			drawSelection(graphics);
	}

	protected void fillShape(Graphics graphics) {
		graphics.setColor(getInnerColor());
		graphics2d.fill(area);
	}

	public void calculateArea() {
		area = new Area(new Ellipse2D.Double(getCenter().getXcoordinate() - getRadius(),
				getCenter().getYcoordinate() - getRadius(), getRadius() * 2, getRadius() * 2));

		area.subtract(new Area(new Ellipse2D.Double((getCenter().getXcoordinate() - innerRadius),
				(getCenter().getYcoordinate() - innerRadius), innerRadius * 2, innerRadius * 2)));
	}

	public boolean contains(int xCoordinate, int yCoordinate) {
		return super.contains(xCoordinate, yCoordinate)
				&& getCenter().calculateDistance(xCoordinate, yCoordinate) > innerRadius;
	}

	public boolean equals(Object object) {
		if (object instanceof Donut) {
			Donut donut = (Donut) object;

			if (getCenter().equals(donut.getCenter()) && getRadius() == donut.getRadius()
					&& innerRadius == donut.innerRadius)
				return true;
		}

		return false;
	}

	public Donut clone() {
		return new Donut(getCenter().clone(), getRadius(), innerRadius, isSelected(), getOuterColor(), getInnerColor());
	}

	public String toString() {
		return super.toString() + " , inner radius: " + innerRadius + " , Outer color: " + getOuterColor().getRGB()
				+ " , Inner color: " + getInnerColor().getRGB();
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public Area getArea() {
		return area;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
}