package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

import shapes.Donut;
import shapes.Shape;

public class DrawingView extends JPanel {
	private static final long serialVersionUID = 1L;
	private DrawingModel model;

	public DrawingView() {
		model = new DrawingModel();
		setBackground(Color.WHITE);
		setVisible(true);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paintComponent(graphics);
		Iterator<Shape> shapesIterator = model.getShapes().iterator();

		while (shapesIterator.hasNext()) {
			Shape drawingShape = shapesIterator.next();

			if (drawingShape instanceof Donut)
				((Donut) drawingShape).draw(graphics, (Graphics2D) graphics);
			else
				drawingShape.draw(graphics);
		}
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public DrawingModel getModel() {
		return model;
	}
}