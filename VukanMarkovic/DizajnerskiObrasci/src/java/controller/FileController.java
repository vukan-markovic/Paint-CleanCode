package controller;

import java.util.*;
import javax.swing.*;
import files.*;
import frame.DrawingFrame;
import model.DrawingModel;
import toolbars.RightToolbar;
import java.io.File;

public class FileController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Queue<String> commandsLog;
	private FileManager strategy;
	private FileLog fileLog;
	private String log;
	private FileDrawing fileDrawing;
	private JFileChooser fileChooser;
	private String filePath;
	private String fileName;

	public FileController(DrawingModel model, DrawingFrame frame, Queue<String> commandsLog) {
		this.model = model;
		this.frame = frame;
		this.commandsLog = commandsLog;
	}

	public void save() {
		if (model.getNumberOfShapes() == 0) {
			JOptionPane.showMessageDialog(frame, "You can't save because you haven't draw anythig yet!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		createAndSetFileChooser("Specify the location where you want to save your drawing",
				JFileChooser.DIRECTORIES_ONLY);

		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			saveLog();
			saveDrawing();
		}
	}

	public void saveLog() {
		fileLog = new FileLog(frame.getCommandsListModel(), commandsLog);
		strategy = new FileManager(fileLog);
		setFilePath();
		setFileName();
		strategy.save(filePath + "\\" + fileName + ".txt");
	}

	public void saveDrawing() {
		fileDrawing = new FileDrawing(model, frame);
		strategy = new FileManager(fileDrawing);
		setFilePath();
		setFileName();
		strategy.save(filePath + "\\" + fileName + ".bin");
	}

	private void setFilePath() {
		File file = fileChooser.getSelectedFile();
		filePath = file.getAbsolutePath();
	}

	private void setFileName() {
		RightToolbar toolbar = frame.getRightToolbar();
		fileName = toolbar.getFileName().getText();
	}

	public void loadLog() {
		RightToolbar toolbar = frame.getRightToolbar();
		JButton btnNext = toolbar.getBtnNextCommand();
		btnNext.setEnabled(true);
		commandsLog.clear();
		createAndSetFileChooser("Specify the log you want to open", JFileChooser.FILES_ONLY);

		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION)
			openLog();
	}

	public void openLog() {
		fileLog = new FileLog(frame.getCommandsListModel(), commandsLog);
		strategy = new FileManager(fileLog);
		setFilePath();
		strategy.open(filePath);
	}

	public void loadDrawing() {
		createAndSetFileChooser("Specify the painting you want to open", JFileChooser.FILES_ONLY);

		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
			openDrawing();
	}

	public void openDrawing() {
		fileDrawing = new FileDrawing(model, frame);
		strategy = new FileManager(fileDrawing);
		setFilePath();
		strategy.open(filePath);
	}

	public void createAndSetFileChooser(String title, int mode) {
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(title);
		fileChooser.setFileSelectionMode(mode);
	}

	public Queue<String> getCommandsLog() {
		return commandsLog;
	}

	public FileManager getStrategy() {
		return strategy;
	}

	public FileLog getFileLog() {
		return fileLog;
	}

	public String getLog() {
		return log;
	}

	public FileDrawing getFileDrawing() {
		return fileDrawing;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setCommandsLog(Queue<String> commandsLog) {
		this.commandsLog = commandsLog;
	}

	public void setStrategy(FileManager strategy) {
		this.strategy = strategy;
	}

	public void setFileLog(FileLog fileLog) {
		this.fileLog = fileLog;
	}

	public void setFileDrawing(FileDrawing fileDrawing) {
		this.fileDrawing = fileDrawing;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}
}