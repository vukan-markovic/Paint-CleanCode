package app;

import java.util.*;
import controller.*;
import javax.swing.JFrame;
import frame.DrawingFrame;
import model.DrawingModel;
import stack.CommandsStack;
import view.DrawingView;

public class DrawingApp {

	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		Queue<String> commandsLog = new LinkedList<String>();
		CommandsStack commandsStack = new CommandsStack();
		FileController fileController = new FileController(model, frame, commandsLog);
		OptionsController optionsController = new OptionsController(model, frame, commandsStack, commandsLog);
		DrawingController drawingController = new DrawingController(model, frame, commandsStack, optionsController);

		DrawingView view = frame.getView();
		view.setModel(model);
		frame.setController(drawingController);
		frame.setFileController(fileController);
		frame.setOptionsController(optionsController);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}