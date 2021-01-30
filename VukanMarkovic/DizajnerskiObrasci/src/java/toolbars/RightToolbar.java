package toolbars;

import java.awt.event.*;
import javax.swing.*;
import controller.*;
import java.awt.Color;

public class RightToolbar implements Toolbar {
	private JToolBar toolBar;
	private JButton btnOuterColor;
	private JButton btnInnerColor;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnSendToBack;
	private JButton btnBringToFront;
	private JButton btnToBack;
	private JButton btnToFront;
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
		btnOuterColor = new JButton("Set border color");
		btnInnerColor = new JButton("Set fill color");
		btnUndo = new JButton("Undo");
		btnRedo = new JButton("Redo");
		btnSendToBack = new JButton("Send to back");
		btnBringToFront = new JButton("Bring to front");
		btnToBack = new JButton("To back");
		btnToFront = new JButton("To front");
		btnSave = new JButton("Save");
		btnLoadLog = new JButton("Load log");
		btnLoadDrawing = new JButton("Load painting");
		btnNextCommand = new JButton("Next");
	}

	private void setButtonsColor() {
		btnOuterColor.setBackground(Color.BLACK);
		btnInnerColor.setBackground(Color.WHITE);
	}

	@Override
	public void addButtonsListeners() {
		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				optionsController.setBorderColor();
			}
		});

		btnInnerColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.setFillColor();
			}
		});

		btnUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.undoCommand();
			}
		});

		btnRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				optionsController.redoCommand();
			}
		});

		btnSendToBack.addActionListener(new ActionListener() {
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

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				fileController.save();
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
		btnSendToBack.setEnabled(false);
		btnBringToFront.setEnabled(false);
		btnToBack.setEnabled(false);
		btnToFront.setEnabled(false);
		btnNextCommand.setEnabled(false);
	}

	@Override
	public void addButtonsToToolbar() {
		toolBar.add(btnOuterColor);
		toolBar.add(btnInnerColor);
		toolBar.add(btnUndo);
		toolBar.add(btnRedo);
		toolBar.add(btnSendToBack);
		toolBar.add(btnBringToFront);
		toolBar.add(btnToBack);
		toolBar.add(btnToFront);
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
		return btnOuterColor;
	}

	public JButton getBtnFillColor() {
		return btnInnerColor;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtnSendToBack() {
		return btnSendToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnToFront() {
		return btnToFront;
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