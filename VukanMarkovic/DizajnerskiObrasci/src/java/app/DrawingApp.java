package app;

import java.util.*;
import controller.*;
import javax.swing.WindowConstants;
import commandsHandler.CommandsHandler;
import frame.DrawingFrame;
import model.DrawingModel;
import view.DrawingView;

public class DrawingApp {

	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		Queue<String> commandsLog = new LinkedList<String>();
		CommandsHandler commandsHandler = new CommandsHandler();
		FileController fileController = new FileController(model, frame, commandsLog);
		OptionsController optionsController = new OptionsController(model, frame, commandsHandler, commandsLog);
		DrawingController drawingController = new DrawingController(optionsController);

		DrawingView view = frame.getView();
		view.setModel(model);
		frame.setController(drawingController);
		frame.setFileController(fileController);
		frame.setOptionsController(optionsController);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}