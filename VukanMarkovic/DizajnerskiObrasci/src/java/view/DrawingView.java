package view;

import java.awt.*;
import javax.swing.JPanel;
import model.DrawingModel;

public class DrawingView extends JPanel {
	private static final long serialVersionUID = 1L;
	private DrawingModel model;

	public DrawingView() {
		model = new DrawingModel();
		setView();
	}
	
	private void setView() {
		setBackground(Color.WHITE);
		setVisible(true);
		setPreferredSize(new Dimension(600, 400));
		setBackground(SystemColor.controlLtHighlight);
		setBounds(10, 256, 325, -227);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paintComponent(graphics);
		model.getShapes().stream().forEach(shape -> shape.draw(graphics));
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
}