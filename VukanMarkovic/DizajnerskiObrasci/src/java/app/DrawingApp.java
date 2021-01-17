package app;

import javax.swing.JFrame;
import controller.*;
import frame.DrawingFrame;
import model.DrawingModel;

public class DrawingApp {

	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		DrawingController controller = new DrawingController();
		controller.setController(model, frame);
		createAndShowGUI(model, frame, controller);
	}

	public static void createAndShowGUI(DrawingModel model, DrawingFrame frame, DrawingController controller) {
		frame.getView().setModel(model);
		frame.setController(controller);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}