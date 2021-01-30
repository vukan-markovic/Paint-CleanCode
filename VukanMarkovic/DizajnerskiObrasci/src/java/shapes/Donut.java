package shapes;

import java.awt.*;
import java.awt.geom.*;

public class Donut extends Circle {
	private static final long serialVersionUID = 1L;
	private int innerRadius;
	private Area area;
	private Graphics2D graphics2d;

	public Donut(Point center, int outerRadius, int innerRadius) {
		setCenter(center);
		setRadius(outerRadius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int outerRadius, int innerRadius, boolean selected, Color borderColor, Color fillColor) {
		this(center, outerRadius, innerRadius);
		setSelected(selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}

	@Override
	public void draw(Graphics graphics) {
		calculateArea();
		graphics.setColor(getBorderColor());
		graphics2d = (Graphics2D) graphics;
		graphics2d.draw(area);
		fillShape(graphics);

		if (isSelected())
			drawSelection(graphics);
	}

	public void calculateArea() {
		int outerAreaXcoordinate = getCenter().getXcoordinate() - getRadius();
		int outerAreaYcoordinate = getCenter().getYcoordinate() - getRadius();

		area = new Area(
				new Ellipse2D.Double(outerAreaXcoordinate, outerAreaYcoordinate, getRadius() * 2, getRadius() * 2));

		int innerAreaXcoordinate = getCenter().getXcoordinate() - innerRadius;
		int innerAreaYcoordinate = getCenter().getYcoordinate() - innerRadius;

		area.subtract(new Area(
				new Ellipse2D.Double(innerAreaXcoordinate, innerAreaYcoordinate, innerRadius * 2, innerRadius * 2)));
	}

	@Override
	protected void fillShape(Graphics graphics) {
		graphics.setColor(getFillColor());
		graphics2d.fill(area);
	}

	@Override
	public boolean contains(int xCoordinate, int yCoordinate) {
		double centerDistance = getCenter().calculateDistance(xCoordinate, yCoordinate);
		return super.contains(xCoordinate, yCoordinate) && centerDistance > innerRadius;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Donut) {
			Donut donut = (Donut) object;

			return getCenter().equals(donut.getCenter()) && getRadius() == donut.getRadius()
					&& innerRadius == donut.innerRadius;
		}

		return false;
	}

	@Override
	public Donut clone() {
		return new Donut(getCenter().clone(), getRadius(), innerRadius, isSelected(), getBorderColor(), getFillColor());
	}

	@Override
	public String toString() {
		return super.toString() + " , inner radius: " + innerRadius + " , Border color: " + getBorderColor().getRGB()
				+ " , Fill color: " + getFillColor().getRGB();
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