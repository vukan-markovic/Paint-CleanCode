package mvc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DrawingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DrawingView view;
	private DrawingController controller;
	private DefaultListModel<String> commandsListModel;
	private JPanel contentPanel;
	private final JToggleButton btnPoint;
	private final JToggleButton btnLine;
	private final JToggleButton btnRectangle;
	private final JToggleButton btnCircle;
	private final JToggleButton btnDonut;
	private final JToggleButton btnHexagon;
	private final JToggleButton btnSelect;
	private final JButton btnModify;
	private final JButton btnDelete;
	private final JButton btnOuterColor;
	private final JButton btnInnerColor;
	private final JButton btnUndo;
	private final JButton btnRedo;
	private final JButton btnSendToBack;
	private final JButton btnBringToFront;
	private final JButton btnToBack;
	private final JButton btnToFront;
	private final JButton btnSave;
	private final JButton btnLoadPainting;
	private JButton btnLoadLog;
	private JButton btnNext;
	private JTextField fileName;

	public DrawingFrame() {
		setTitle("Vukan Markoviæ I7 18/2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 600);

		view = new DrawingView();
		view.setPreferredSize(new Dimension(600, 400));
		view.setBackground(SystemColor.controlLtHighlight);
		view.setBounds(10, 256, 325, -227);

		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if (btnSelect.isSelected())
					controller.btnSelectClicked(click);
				else if (btnPoint.isSelected())
					controller.btnPointClicked(click);
				else if (btnLine.isSelected())
					controller.btnLineClicked(click);
				else if (btnRectangle.isSelected())
					controller.btnRectangleClicked(click);
				else if (btnCircle.isSelected())
					controller.btnCircleClicked(click);
				else if (btnDonut.isSelected())
					controller.btnDonutClicked(click);
				else if (btnHexagon.isSelected())
					controller.btnHexagonClicked(click);

				view.repaint();
				controller.fireEventsForUndoAndRedoButtons();
			}
		});

		contentPanel = new JPanel();
		contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(view, BorderLayout.CENTER);

		JToolBar topToolBar = new JToolBar();
		topToolBar.setBounds(3, 5, 429, 24);
		contentPanel.add(topToolBar, BorderLayout.NORTH);

		ButtonGroup btnGroup = new ButtonGroup();

		btnPoint = new JToggleButton("Point");
		topToolBar.add(btnPoint);
		btnGroup.add(btnPoint);
		btnPoint.setToolTipText("Draw point");

		btnLine = new JToggleButton("Line");
		topToolBar.add(btnLine);
		btnGroup.add(btnLine);
		btnLine.setToolTipText("Draw line");

		btnRectangle = new JToggleButton("Rectangle");
		topToolBar.add(btnRectangle);
		btnGroup.add(btnRectangle);
		btnRectangle.setToolTipText("Draw rectangle");

		btnCircle = new JToggleButton("Circle");
		topToolBar.add(btnCircle);
		btnGroup.add(btnCircle);
		btnCircle.setToolTipText("Draw circle");

		btnDonut = new JToggleButton("Donut");
		topToolBar.add(btnDonut);
		btnGroup.add(btnDonut);
		btnDonut.setToolTipText("Draw donut");

		btnHexagon = new JToggleButton("Hexagon");
		topToolBar.add(btnHexagon);
		btnGroup.add(btnHexagon);
		btnHexagon.setToolTipText("Draw hexagon");

		btnSelect = new JToggleButton("Select");
		topToolBar.add(btnSelect);
		btnGroup.add(btnSelect);
		btnSelect.setToolTipText("Select shape");

		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);
		topToolBar.add(btnModify);
		btnGroup.add(btnModify);
		btnModify.setToolTipText("Modify shape");

		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.btnModifyClicked();
			}
		});

		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setToolTipText("Delete shape");
		topToolBar.add(btnDelete);
		btnGroup.add(btnDelete);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.btnRemoveClicked();
			}
		});

		JToolBar rightToolBar = new JToolBar();
		rightToolBar.setOrientation(SwingConstants.VERTICAL);
		rightToolBar.setBounds(345, 21, 87, 288);
		contentPanel.add(rightToolBar, BorderLayout.EAST);

		btnOuterColor = new JButton("Set outer color");
		btnOuterColor.setBackground(Color.BLACK);
		btnOuterColor.setToolTipText("Set outer draw color");
		rightToolBar.add(btnOuterColor);

		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.chooseOuterColor();
			}
		});

		btnInnerColor = new JButton("Set inner color");
		btnInnerColor.setBackground(Color.WHITE);
		btnInnerColor.setToolTipText("Set inner draw color");
		rightToolBar.add(btnInnerColor);

		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.chooseInnerColor();
			}
		});

		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.setToolTipText("Undo command");
		rightToolBar.add(btnUndo);

		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.btnUndoClicked();
			}
		});

		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.setToolTipText("Redo command");
		rightToolBar.add(btnRedo);

		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.btnRedoClicked();
			}
		});

		btnSendToBack = new JButton("Send to back");
		btnSendToBack.setEnabled(false);
		btnSendToBack.setToolTipText("Send shape to back");
		rightToolBar.add(btnSendToBack);

		btnSendToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.btnSendToBackClicked();
			}
		});

		btnBringToFront = new JButton("Bring to front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.setToolTipText("Bring shape to front");
		rightToolBar.add(btnBringToFront);

		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.btnBringToFrontClicked();
			}
		});

		btnToBack = new JButton("To back");
		btnToBack.setEnabled(false);
		btnToBack.setToolTipText("Move shape to back");
		rightToolBar.add(btnToBack);

		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.btnToBackClicked();
			}
		});

		btnToFront = new JButton("To front");
		btnToFront.setEnabled(false);
		btnToFront.setToolTipText("Move shape to front");
		rightToolBar.add(btnToFront);

		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.btnToFrontClicked();
			}
		});

		btnSave = new JButton("Save");
		btnSave.setToolTipText("Save painting and log");
		rightToolBar.add(btnSave);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.save();
			}
		});

		btnLoadLog = new JButton("Load log");
		btnLoadLog.setToolTipText("Load log file");
		rightToolBar.add(btnLoadLog);

		btnLoadLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.loadLog();
			}
		});

		btnLoadPainting = new JButton("Load painting");
		btnLoadPainting.setToolTipText("Load painting file");
		rightToolBar.add(btnLoadPainting);

		btnLoadPainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.loadPainting();
			}
		});

		btnNext = new JButton("Next");
		btnNext.setEnabled(false);
		rightToolBar.add(btnNext);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.executeCommandFromLog();
			}
		});

		fileName = new JTextField();
		rightToolBar.add(fileName);
		fileName.setText("Painting");
		fileName.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(3, 256, 342, 53);
		contentPanel.add(scrollPane, BorderLayout.PAGE_END);
		scrollPane.setPreferredSize(new Dimension(0, 140));

		commandsListModel = new DefaultListModel<>();
		JList<String> commandsList = new JList<String>();
		scrollPane.setViewportView(commandsList);
		commandsList.setModel(commandsListModel);

		setContentPane(contentPanel);
	}

	public DrawingView getView() {
		return view;
	}

	public DrawingController getController() {
		return controller;
	}

	public DefaultListModel<String> getCommandsListModel() {
		return commandsListModel;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
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

	public void setController(DrawingController controller) {
		this.controller = controller;
	}
}