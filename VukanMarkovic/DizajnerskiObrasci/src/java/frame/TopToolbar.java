package frame;

import java.awt.event.*;
import javax.swing.*;

import controller.DrawingController;

public class TopToolbar implements Toolbar {
	private JToolBar toolBar;
	private DrawingController controller;
	private JToggleButton btnPoint;
	private JToggleButton btnLine;
	private JToggleButton btnRectangle;
	private JToggleButton btnCircle;
	private JToggleButton btnDonut;
	private JToggleButton btnHexagon;
	private JToggleButton btnSelect;
	private JButton btnModify;
	private JButton btnDelete;
	private ButtonGroup btnGroup;

	public TopToolbar(DrawingController controller) {
		this.controller = controller;
		toolBar = new JToolBar();
		btnGroup = new ButtonGroup();

		initializeButtons();
		addButtonsToToolbar();
		setButtonsToolTipText();
		addButtonsListeners();
		disableButtons();
		addButtonsToBtnGroup();
		setToolbar();
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

	@Override
	public void addButtonsListeners() {
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.btnModifyClicked();
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.btnRemoveClicked();
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

	@Override
	public void setButtonsToolTipText() {
		btnPoint.setToolTipText("Draw point");
		btnLine.setToolTipText("Draw line");
		btnRectangle.setToolTipText("Draw rectangle");
		btnCircle.setToolTipText("Draw circle");
		btnDonut.setToolTipText("Draw donut");
		btnHexagon.setToolTipText("Draw hexagon");
		btnSelect.setToolTipText("Select shape");
		btnModify.setToolTipText("Modify shape");
		btnDelete.setToolTipText("Delete shape");
	}

	@Override
	public void setToolbar() {
		toolBar.setBounds(3, 5, 429, 24);

	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
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
}
