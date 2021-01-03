package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DrawingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DrawingView view;
	private DrawingController controller;
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
	private DefaultListModel<String> commandsListModel;
	private JButton btnLoadLog;
	private JButton btnNext;
	private JTextField fileName;

	public DrawingFrame() {
		setTitle("Vukan Markoviæ I7 18/2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 600);
		commandsListModel = new DefaultListModel<>();
		view = new DrawingView();
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		view.setPreferredSize(new Dimension(600, 400));
		contentPane.add(view, BorderLayout.CENTER);
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

		JToolBar topToolBar = new JToolBar();
		topToolBar.setBounds(3, 5, 429, 24);
		contentPane.add(topToolBar, BorderLayout.NORTH);
		ButtonGroup buttonGroup = new ButtonGroup();

		btnPoint = new JToggleButton("Point");
		topToolBar.add(btnPoint);
		buttonGroup.add(btnPoint);
		btnPoint.setToolTipText("Draw point");

		btnLine = new JToggleButton("Line");
		topToolBar.add(btnLine);
		buttonGroup.add(btnLine);
		btnLine.setToolTipText("Draw line");

		btnRectangle = new JToggleButton("Rectangle");
		topToolBar.add(btnRectangle);
		buttonGroup.add(btnRectangle);
		btnRectangle.setToolTipText("Draw rectangle");

		btnCircle = new JToggleButton("Circle");
		topToolBar.add(btnCircle);
		buttonGroup.add(btnCircle);
		btnCircle.setToolTipText("Draw circle");

		btnDonut = new JToggleButton("Donut");
		topToolBar.add(btnDonut);
		buttonGroup.add(btnDonut);
		btnDonut.setToolTipText("Draw donut");

		btnHexagon = new JToggleButton("Hexagon");
		topToolBar.add(btnHexagon);
		buttonGroup.add(btnHexagon);
		btnHexagon.setToolTipText("Draw hexagon");

		btnSelect = new JToggleButton("Select");
		topToolBar.add(btnSelect);
		buttonGroup.add(btnSelect);
		btnSelect.setToolTipText("Select shape");

		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);
		topToolBar.add(btnModify);
		buttonGroup.add(btnModify);
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
		buttonGroup.add(btnDelete);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.btnRemoveClicked();
			}
		});
		
		JToolBar rightToolBar = new JToolBar();
		rightToolBar.setOrientation(SwingConstants.VERTICAL);
		rightToolBar.setBounds(345, 21, 87, 288);
		contentPane.add(rightToolBar, BorderLayout.EAST);
		
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
		contentPane.add(scrollPane, BorderLayout.PAGE_END);
		scrollPane.setPreferredSize(new Dimension(0, 140));

		JList<String> commandsList = new JList<String>();
		scrollPane.setViewportView(commandsList);
		commandsList.setModel(commandsListModel);
		setContentPane(contentPane);
	}

	public JTextField getFileName() {
		return fileName;
	}

	public DefaultListModel<String> getCommandsListModel() {
		return commandsListModel;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public DrawingView getView() {
		return view;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public JToggleButton getBtnPoint() {
		return btnPoint;
	}

	public JToggleButton getBtnLine() {
		return btnLine;
	}

	public JToggleButton getBtnRectangle() {
		return btnRectangle;
	}

	public JToggleButton getBtnCircle() {
		return btnCircle;
	}

	public JToggleButton getBtnDonut() {
		return btnDonut;
	}

	public JToggleButton getBtnHexagon() {
		return btnHexagon;
	}

	public JToggleButton getBtnSelect() {
		return btnSelect;
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

	public JButton getBtnLoadLog() {
		return btnLoadLog;
	}

	public JButton getBtnLoadPainting() {
		return btnLoadPainting;
	}
}