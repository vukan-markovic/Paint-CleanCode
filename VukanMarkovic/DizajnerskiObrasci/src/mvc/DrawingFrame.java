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
	private final JToggleButton tglbtnPoint;
	private final JToggleButton tglbtnLine;
	private final JToggleButton tglbtnRectangle;
	private final JToggleButton tglbtnCircle;
	private final JToggleButton tglbtnDonut;
	private final JToggleButton tglbtnHexagon;
	private final JToggleButton tglbtnSelect;
	private final JButton btnModify;
	private final JButton btnDelete;
	private final JButton btnOuterCol;
	private final JButton btnInnerCol;
	private final JButton btnUndo;
	private final JButton btnRedo;
	private final JButton btnSendToBack;
	private final JButton btnBringToFront;
	private final JButton btnToBack;
	private final JButton btnToFront;
	private final JButton btnSave;
	private final JButton btnLoadPainting;
	private DefaultListModel<String> lModel;
	private JButton btnLoadLog;
	private JButton btnNext;
	private JTextField fileName;

	public DrawingFrame() {
		setTitle("Vukan Markovi� I7 18/2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 600);
		lModel = new DefaultListModel<>();
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
				if (tglbtnSelect.isSelected())
					controller.btnSelectClicked(click);
				else if (tglbtnPoint.isSelected())
					controller.btnPointClicked(click);
				else if (tglbtnLine.isSelected())
					controller.btnLineClicked(click);
				else if (tglbtnRectangle.isSelected())
					controller.btnRectangleClicked(click);
				else if (tglbtnCircle.isSelected())
					controller.btnCircleClicked(click);
				else if (tglbtnDonut.isSelected())
					controller.btnDonutClicked(click);
				else if (tglbtnHexagon.isSelected())
					controller.btnHexagonClicked(click);

				view.repaint();
				controller.fireUndoRedo();
			}
		});

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(3, 5, 429, 24);
		contentPane.add(toolBar, BorderLayout.NORTH);
		ButtonGroup buttonGroup = new ButtonGroup();

		tglbtnPoint = new JToggleButton("Point");
		toolBar.add(tglbtnPoint);
		buttonGroup.add(tglbtnPoint);
		tglbtnPoint.setToolTipText("Point");

		tglbtnLine = new JToggleButton("Line");
		toolBar.add(tglbtnLine);
		buttonGroup.add(tglbtnLine);
		tglbtnLine.setToolTipText("Line");

		tglbtnRectangle = new JToggleButton("Rectangle");
		toolBar.add(tglbtnRectangle);
		buttonGroup.add(tglbtnRectangle);
		tglbtnRectangle.setToolTipText("Rectangle");

		tglbtnCircle = new JToggleButton("Circle");
		toolBar.add(tglbtnCircle);
		buttonGroup.add(tglbtnCircle);
		tglbtnCircle.setToolTipText("Circle");

		tglbtnDonut = new JToggleButton("Donut");
		toolBar.add(tglbtnDonut);
		buttonGroup.add(tglbtnDonut);
		tglbtnDonut.setToolTipText("Donut");

		tglbtnHexagon = new JToggleButton("Hexagon");
		toolBar.add(tglbtnHexagon);
		buttonGroup.add(tglbtnHexagon);
		tglbtnHexagon.setToolTipText("Hexagon");

		tglbtnSelect = new JToggleButton("Select");
		toolBar.add(tglbtnSelect);
		buttonGroup.add(tglbtnSelect);
		tglbtnSelect.setToolTipText("Select");

		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);

		toolBar.add(btnModify);
		buttonGroup.add(btnModify);
		btnModify.setToolTipText("Modify");

		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}
		});

		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setToolTipText("Delete");

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});

		toolBar.add(btnDelete);
		buttonGroup.add(btnDelete);
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.setBounds(345, 21, 87, 288);
		contentPane.add(toolBar_1, BorderLayout.EAST);
		btnOuterCol = new JButton("Set outer color");

		btnOuterCol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.outerColor();
			}
		});

		btnOuterCol.setBackground(new Color(250, 128, 114));
		toolBar_1.add(btnOuterCol);
		btnInnerCol = new JButton("Set inner color");

		btnInnerCol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.innerColor();
			}
		});

		btnInnerCol.setBackground(new Color(255, 235, 205));
		toolBar_1.add(btnInnerCol);
		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);

		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});

		toolBar_1.add(btnUndo);
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);

		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});

		toolBar_1.add(btnRedo);
		btnSendToBack = new JButton("Send to back");
		btnSendToBack.setEnabled(false);

		btnSendToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.sendToBack();
			}
		});

		toolBar_1.add(btnSendToBack);
		btnBringToFront = new JButton("Bring to front");
		btnBringToFront.setEnabled(false);

		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});

		toolBar_1.add(btnBringToFront);
		btnToBack = new JButton("To back");
		btnToBack.setEnabled(false);

		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});

		toolBar_1.add(btnToBack);
		btnToFront = new JButton("To front");
		btnToFront.setEnabled(false);

		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});

		toolBar_1.add(btnToFront);
		btnSave = new JButton("Save");

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.save();
			}
		});

		toolBar_1.add(btnSave);
		btnLoadLog = new JButton("Load log");

		btnLoadLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadLog();
			}
		});

		toolBar_1.add(btnLoadLog);
		btnLoadPainting = new JButton("Load painting");

		btnLoadPainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadPainting();
			}
		});

		toolBar_1.add(btnLoadPainting);
		btnNext = new JButton("Next");
		btnNext.setEnabled(false);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.nextCommand();
			}
		});

		toolBar_1.add(btnNext);
		fileName = new JTextField();
		toolBar_1.add(fileName);
		fileName.setText("Saving");
		fileName.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(3, 256, 342, 53);
		contentPane.add(scrollPane, BorderLayout.PAGE_END);
		scrollPane.setPreferredSize(new Dimension(0, 140));

		JList<String> list = new JList<String>();
		scrollPane.setViewportView(list);
		list.setModel(lModel);
		setContentPane(contentPane);
	}

	public JTextField getFileName() {
		return fileName;
	}

	public DefaultListModel<String> getlModel() {
		return lModel;
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

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnOuterCol() {
		return btnOuterCol;
	}

	public JButton getBtnInnerCol() {
		return btnInnerCol;
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