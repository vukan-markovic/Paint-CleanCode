package toolbars;

import java.awt.event.*;
import javax.swing.*;
import controller.DrawingController;

public class TopToolbar implements Toolbar {
	private JToolBar toolBar;
	private ButtonGroup btnGroup;
	private JToggleButton btnPoint;
	private JToggleButton btnLine;
	private JToggleButton btnRectangle;
	private JToggleButton btnCircle;
	private JToggleButton btnDonut;
	private JToggleButton btnHexagon;
	private JToggleButton btnSelect;
	private JButton btnModify;
	private JButton btnDelete;
	private DrawingController controller;

	public TopToolbar() {
		toolBar = new JToolBar();
		btnGroup = new ButtonGroup();

		setToolbar();
		initializeButtons();
		addButtonsListeners();
		disableButtons();
		addButtonsToToolbar();
		addButtonsToBtnGroup();
	}

	@Override
	public void setToolbar() {
		toolBar.setBounds(3, 5, 429, 24);
	}

	@Override
	public void initializeButtons() {
		btnPoint = new JToggleButton("Point");
		btnLine = new JToggleButton("Line");
		btnRectangle = new JToggleButton("Rectangle");
		btnCircle = new JToggleButton("Circle");
		btnDonut = new JToggleButton("Donut");
		btnHexagon = new JToggleButton("Hexagon");
		btnSelect = new JToggleButton("Select");
		btnModify = new JButton("Modify");
		btnDelete = new JButton("Delete");
	}

	@Override
	public void addButtonsListeners() {
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.modifyShape();
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.removeShapesIfUserConfirm();
			}
		});
	}

	@Override
	public void disableButtons() {
		btnModify.setEnabled(false);
		btnDelete.setEnabled(false);
	}

	@Override
	public void addButtonsToToolbar() {
		toolBar.add(btnPoint);
		toolBar.add(btnLine);
		toolBar.add(btnRectangle);
		toolBar.add(btnCircle);
		toolBar.add(btnDonut);
		toolBar.add(btnHexagon);
		toolBar.add(btnSelect);
		toolBar.add(btnModify);
		toolBar.add(btnDelete);
	}

	private void addButtonsToBtnGroup() {
		btnGroup.add(btnPoint);
		btnGroup.add(btnLine);
		btnGroup.add(btnRectangle);
		btnGroup.add(btnCircle);
		btnGroup.add(btnDonut);
		btnGroup.add(btnHexagon);
		btnGroup.add(btnSelect);
		btnGroup.add(btnModify);
		btnGroup.add(btnDelete);
	}

	public JToolBar getToolBar() {
		return toolBar;
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

	public void setController(DrawingController controller) {
		this.controller = controller;
	}
}
