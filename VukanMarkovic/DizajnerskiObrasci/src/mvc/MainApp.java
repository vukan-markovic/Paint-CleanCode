package mvc;

import javax.swing.JFrame;

public class MainApp {

	public static void createAndShowGUI(DrawingModel model, DrawingFrame frame, DrawingController controller) {
		frame.getView().setModel(model);
		frame.setController(controller);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		DrawingModel drawingModel = new DrawingModel();
		DrawingFrame drawingFrame = new DrawingFrame();
		createAndShowGUI(drawingModel, drawingFrame, new DrawingController(drawingModel, drawingFrame));
	}
}