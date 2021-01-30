package controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.Before;
import org.junit.Test;
import files.FileDrawing;
import files.FileLog;
import frame.DrawingFrame;
import model.DrawingModel;
import stack.CommandsStack;

public class FileControllerTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private Queue<String> commandsLog;
	private FileController fileController;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsLog = new LinkedList<String>();
		fileController = new FileController(model, frame, commandsLog);
	}
	
	@Test
	public void testSaveDrawing() throws IOException {
//		FileDrawing savePainting = new FileDrawing();
//		fileController.setStrategy(files);
//		fileController.setSavePainting(savePainting);
//		File file = tempFolder.newFile(frame.getFileName().getText() + ".bin");
//		fileChooser.setSelectedFile(file);
//		fileController.setFileChooser(fileChooser);
//		fileController.saveDrawing();
//		assertEquals(model.getShapes(), savePainting.getShapes());
//		verify(files).setStrategy(savePainting);
//		verify(files).save(fileChooser.getSelectedFile().getAbsolutePath() + "\\" + file.getName());
	}

	@Test
	public void testSaveLog() throws IOException {
//		FileLog saveLog = new FileLog();
//		fileController.setStrategy(files);
//		fileController.setSaveLog(saveLog);
//		File file = tempFolder.newFile(frame.getFileName().getText() + ".txt");
//		fileChooser.setSelectedFile(file);
//		fileController.setFileChooser(fileChooser);
//		fileController.saveLog();
//		assertEquals(fileController.getLog(), saveLog.getCommandsLog());
//		verify(files).setStrategy(saveLog);
//		verify(files).save(fileChooser.getSelectedFile().getAbsolutePath() + "\\" + file.getName());
	}
}