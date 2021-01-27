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
	private JButton btnLoadPainting;
	private JButton btnLoadLog;
	private JButton btnNext;
	private OptionsController optionsController;
	private FileController fileController;
	private JTextField fileName;

	public RightToolbar() {
		toolBar = new JToolBar();

		initializeButtons();
		setButtonsColor();
		addButtonsListeners();
		setToolbar();
		addButtonsToToolbar();
		setButtonsToolTipText();
		disableButtons();
		setFileName();
	}

	@Override
	public void initializeButtons() {
		btnOuterColor = new JButton("Set outer color");
		btnInnerColor = new JButton("Set inner color");
		btnUndo = new JButton("Undo");
		btnRedo = new JButton("Redo");
		btnSendToBack = new JButton("Send to back");
		btnBringToFront = new JButton("Bring to front");
		btnToBack = new JButton("To back");
		btnToFront = new JButton("To front");
		btnSave = new JButton("FileStrategy");
		btnLoadLog = new JButton("Load log");
		btnLoadPainting = new JButton("Load painting");
		btnNext = new JButton("Next");
	}

	private void setButtonsColor() {
		btnOuterColor.setBackground(Color.BLACK);
		btnInnerColor.setBackground(Color.WHITE);
	}

	@Override
	public void addButtonsListeners() {
		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				optionsController.chooseOuterColor();
			}
		});

		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				optionsController.chooseInnerColor();
			}
		});

		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				optionsController.undoCommand();
			}
		});

		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				optionsController.redoCommand();
			}
		});

		btnSendToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				optionsController.bringShapeToBack();
			}
		});

		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				optionsController.bringShapeToFront();
			}
		});

		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				optionsController.moveShapeToBack();
			}
		});

		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				optionsController.moveShapeToFront();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fileController.save();
			}
		});

		btnLoadLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fileController.loadLog();
			}
		});

		btnLoadPainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fileController.loadDrawing();
			}
		});

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				optionsController.executeCommandFromLog();
			}
		});
	}

	@Override
	public void setToolbar() {
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setBounds(345, 21, 87, 288);
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
		toolBar.add(btnLoadPainting);
		toolBar.add(btnNext);
	}

	@Override
	public void setButtonsToolTipText() {
		btnOuterColor.setToolTipText("Set outer draw color");
		btnInnerColor.setToolTipText("Set inner draw color");
		btnUndo.setToolTipText("Undo command");
		btnRedo.setToolTipText("Redo command");
		btnSendToBack.setToolTipText("Send shape to back");
		btnBringToFront.setToolTipText("Bring shape to front");
		btnToBack.setToolTipText("Move shape to back");
		btnToFront.setToolTipText("Move shape to front");
		btnSave.setToolTipText("FileStrategy painting and log");
		btnLoadLog.setToolTipText("Load log file");
		btnLoadPainting.setToolTipText("Load painting file");
	}

	@Override
	public void disableButtons() {
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);
		btnSendToBack.setEnabled(false);
		btnBringToFront.setEnabled(false);
		btnToBack.setEnabled(false);
		btnToFront.setEnabled(false);
		btnNext.setEnabled(false);
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

	public JButton getBtnOuterColor() {
		return btnOuterColor;
	}

	public JButton getBtnInnerColor() {
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

	public JButton getBtnLoadPainting() {
		return btnLoadPainting;
	}

	public JButton getBtnLoadLog() {
		return btnLoadLog;
	}

	public JButton getBtnNext() {
		return btnNext;
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