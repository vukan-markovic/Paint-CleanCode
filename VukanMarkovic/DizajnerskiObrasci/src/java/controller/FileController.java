package controller;

import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private FileDrawing fileDrawing;
	private JFileChooser fileChooser;
	private String filePath;
	private String fileName;
	private JButton btnNext;

	public FileController(DrawingModel model, DrawingFrame frame, Queue<String> commandsLog) {
		this.model = model;
		this.frame = frame;
		this.commandsLog = commandsLog;
	}

	public void saveDrawingAndLog() {
		if (model.getNumberOfShapes() == 0) {
			JOptionPane.showMessageDialog(frame, "You can't save because you haven't draw anythig yet!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (frame.getRightToolbar().getFileName().getText().isBlank()) {
			JOptionPane.showMessageDialog(frame, "Please specify file name!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify the location where you want to save your drawing");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			setFilePath();
			setFileName();
			saveLog();
			saveDrawing();
		}
	}

	private void saveLog() {
		fileLog = new FileLog(frame.getCommandsListModel(), commandsLog);
		strategy = new FileManager(fileLog);
		strategy.save(filePath + "\\" + fileName + ".txt");
	}

	private void saveDrawing() {
		fileDrawing = new FileDrawing(model, frame);
		strategy = new FileManager(fileDrawing);
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
		btnNext = toolbar.getBtnNextCommand();
		btnNext.setEnabled(true);
		commandsLog.clear();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify the log you want to open");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(filter);

		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION)
			openLog();
	}

	private void openLog() {
		fileLog = new FileLog(frame.getCommandsListModel(), commandsLog);
		strategy = new FileManager(fileLog);
		setFilePath();
		strategy.open(filePath);
	}

	public void loadDrawing() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Drawing files", "bin");
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify the painting you want to open");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(filter);

		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
			openDrawing();
	}

	private void openDrawing() {
		fileDrawing = new FileDrawing(model, frame);
		strategy = new FileManager(fileDrawing);
		setFilePath();
		strategy.open(filePath);
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

	public FileDrawing getFileDrawing() {
		return fileDrawing;
	}

	public String getFilePath() {
		return filePath;
	}

	public JButton getBtnNext() {
		return btnNext;
	}
}