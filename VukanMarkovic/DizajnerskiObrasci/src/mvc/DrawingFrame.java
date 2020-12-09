package mvc;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class DrawingFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DrawingView view = new DrawingView();
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
	
	private DefaultListModel<String> lModel = new DefaultListModel<>();
	private JButton btnLoadLog;
	private JButton btnNext;
	private JTextField fileName;
	
	
	
	

	

	public JTextField getFileName() {
		return fileName;
	}



	public void setFileName(JTextField fileName) {
		this.fileName = fileName;
	}



	public DefaultListModel<String> getlModel() {
		return lModel;
	}



	public void setlModel(DefaultListModel<String> lModel) {
		this.lModel = lModel;
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



	public void setView(DrawingView view) {
		this.view = view;
	}



	/**
	 * Create the frame.
	 */
	public DrawingFrame() {
		setTitle("Mina Topalovic IT28/2017");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BorderLayout());
		//FlowLayout flowLayout = (FlowLayout) view.getLayout();
		
		
		
		view.setPreferredSize(new Dimension(600, 400));
		contentPane.add(view, BorderLayout.CENTER);
		view.setBackground(SystemColor.controlLtHighlight);
		view.setBounds(10, 256, 325, -227);
		

		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(3, 5, 429, 24);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		ButtonGroup buttonGroup=new ButtonGroup();
		
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
		
		
		btnModify.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.Modify();
			}
			
		});
		
		
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setToolTipText("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.Delete();
			}
		});
		toolBar.add(btnDelete);
		buttonGroup.add(btnDelete);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.setBounds(345, 21, 87, 288);
		contentPane.add(toolBar_1, BorderLayout.EAST);
		
		btnOuterCol = new JButton("Set outer color");
		btnOuterCol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.outerColor();
			}
		});
		btnOuterCol.setBackground(new Color(250, 128, 114));
		toolBar_1.add(btnOuterCol);
		
		btnInnerCol = new JButton("Set inner color");
		btnInnerCol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.innerColor();
			}
		});
		btnInnerCol.setBackground(new Color(255, 235, 205));
		toolBar_1.add(btnInnerCol);
		
		
		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.undo();
			}
		});
		toolBar_1.add(btnUndo);
		
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.redo();
			}
		});
		toolBar_1.add(btnRedo);
		
		btnSendToBack = new JButton("Send to back");
		btnSendToBack.setEnabled(false);
		btnSendToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.sendToBack();
			}
		});
		toolBar_1.add(btnSendToBack);
		
		btnBringToFront = new JButton("Bring to front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.bringToFront();
			}
		});
		toolBar_1.add(btnBringToFront);
		
		btnToBack = new JButton("To back");
		btnToBack.setEnabled(false);
		btnToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.toBack();
			}
		});
		toolBar_1.add(btnToBack);
		
		btnToFront = new JButton("To front");
		btnToFront.setEnabled(false);
		btnToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.toFront();
			}
		});
		toolBar_1.add(btnToFront);
		
		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.save();
			}
		});
		toolBar_1.add(btnSave);
		
		btnLoadLog = new JButton("Load log");
		btnLoadLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.loadLog();
			}
		});
		toolBar_1.add(btnLoadLog);
		
		btnLoadPainting = new JButton("Load painting");
		btnLoadPainting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.loadPainting();
			}
		});
		toolBar_1.add(btnLoadPainting);
		
		btnNext = new JButton("Next");
		btnNext.setEnabled(false);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.nextComm();
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
		scrollPane.setPreferredSize(new Dimension(0,140));
		
		JList<String> list = new JList<String>();
		scrollPane.setViewportView(list);
		list.setModel(lModel);
		
		setContentPane(contentPane);
		
		
	}

	


	public JButton getBtnNext() {
		return btnNext;
	}



	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
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



	



	public JButton getBtnLoadPainting() {
		return btnLoadPainting;
	}



	
}
