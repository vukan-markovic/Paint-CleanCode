package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Donut;
import geometry.Shape;

public class DrawingView extends JPanel {
	private static final long serialVersionUID = 1L;
	private DrawingModel model = new DrawingModel();

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Iterator<Shape> iterator = model.getShapes().iterator();

		while (iterator.hasNext()) {
			Shape shape = iterator.next();

			if (shape instanceof Donut)
				((Donut) shape).draw(graphics, (Graphics2D) graphics);
			else
				shape.draw(graphics);
		}
	}

	/**
	 * Create the panel.
	 */
	public DrawingView() {
		setBackground(Color.WHITE);
		setVisible(true);
	}
}