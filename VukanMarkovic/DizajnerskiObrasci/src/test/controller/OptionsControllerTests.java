package controller;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import frame.DrawingFrame;
import model.DrawingModel;
import stack.CommandsStack;

public class OptionsControllerTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsStack = new CommandsStack();
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsStack, commandsLog);
	}
	
	@Test
	public void test() {
		
	}
}