package toolbars;

import java.awt.event.*;
import javax.swing.*;
import controller.*;
import java.awt.Color;

public class RightToolbar implements Toolbar {
	private JToolBar toolBar;
	private JButton btnBorderColor;
	private JButton btnFillColor;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnToBack;
	private JButton btnToFront;
	private JButton btnBringToBack;
	private JButton btnBringToFront;
	private JButton btnSave;
	private JButton btnLoadLog;
	private JButton btnLoadDrawing;
	private JButton btnNextCommand;
	private JTextField fileName;
	private OptionsController optionsController;
	private FileController fileController;

	public RightToolbar() {
		toolBar = new JToolBar();

		setToolbar();
		initializeButtons();
		setButtonsColor();
		addButtonsListeners();
		disableButtons();
		addButtonsToToolbar();
		setFileName();
	}

	@Override
	public void setToolbar() {
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setBounds(345, 21, 87, 288);
	}

	@Override
	public void initializeButtons() {
		btnBorderColor = new JButton("Set border color");
		btnFillColor = new JButton("Set fill color");
		btnUndo = new JButton("Undo");
		btnRedo = new JButton("Redo");
		btnToBack = new JButton("To back");
		btnToFront = new JButton("To front");
		btnBringToBack = new JButton("Bring to back");
		btnBringToFront = new JButton("Bring to front");
		btnSave = new JButton("Save");
		btnLoadLog = new JButton("Load log");
		btnLoadDrawing = new JButton("Load painting");
		btnNextCommand = new JButton("Next command");
	}

	private void setButtonsColor() {
		btnBorderColor.setBackground(Color.BLACK);
		btnFillColor.setBackground(Color.WHITE);
	}

	@Override
	public void addButtonsListeners() {
		btnBorderColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.setBorderColorIfChoosen();
			}
		});

		btnFillColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.setFillColorIfChoosen();
			}
		});

		btnUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.undoExecutedCommand();
			}
		});

		btnRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.redoUnexecutedCommand();
			}
		});

		btnToBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.moveShapeToBack();
			}
		});

		btnToFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.moveShapeToFront();
			}
		});

		btnBringToBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.bringShapeToBack();
			}
		});

		btnBringToFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.bringShapeToFront();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				fileController.saveDrawingAndLog();
			}
		});

		btnLoadLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				fileController.loadLog();
			}
		});

		btnLoadDrawing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				fileController.loadDrawing();
			}
		});

		btnNextCommand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.executeCommandFromLog();
			}
		});
	}

	@Override
	public void disableButtons() {
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);
		btnToBack.setEnabled(false);
		btnToFront.setEnabled(false);
		btnBringToBack.setEnabled(false);
		btnBringToFront.setEnabled(false);
		btnNextCommand.setEnabled(false);
	}

	@Override
	public void addButtonsToToolbar() {
		toolBar.add(btnBorderColor);
		toolBar.add(btnFillColor);
		toolBar.add(btnUndo);
		toolBar.add(btnRedo);
		toolBar.add(btnToBack);
		toolBar.add(btnToFront);
		toolBar.add(btnBringToBack);
		toolBar.add(btnBringToFront);
		toolBar.add(btnSave);
		toolBar.add(btnLoadLog);
		toolBar.add(btnLoadDrawing);
		toolBar.add(btnNextCommand);
	}

	private void setFileName() {
		fileName = new JTextField();
		fileName.setText("Painting");
		fileName.setColumns(10);
		toolBar.add(fileName);
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public JButton getBtnFillColor() {
		return btnFillColor;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JButton getBtnLoadLog() {
		return btnLoadLog;
	}

	public JButton getBtnLoadDrawing() {
		return btnLoadDrawing;
	}

	public JButton getBtnNextCommand() {
		return btnNextCommand;
	}

	public JTextField getFileName() {
		return fileName;
	}

	public void setOptionsController(OptionsController optionsController) {
		this.optionsController = optionsController;
	}

	public void setFileController(FileController fileController) {
		this.fileController = fileController;
	}
}