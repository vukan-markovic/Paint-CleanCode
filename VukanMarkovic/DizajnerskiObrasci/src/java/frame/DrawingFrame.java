package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import view.DrawingView;
import javax.swing.border.EmptyBorder;

import controller.*;

public class DrawingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DrawingView view;
	private DrawingController controller;
	private DefaultListModel<String> commandsListModel;
	private JPanel contentPanel;
	private JScrollPane scrollPane;
	private JList<String> commandsList;
	private TopToolbar topToolbar;
	private RightToolbar rightToolbar;

	public DrawingFrame() {
		view = new DrawingView();
		contentPanel = new JPanel();
		scrollPane = new JScrollPane();
		commandsListModel = new DefaultListModel<>();
		commandsList = new JList<String>();
		topToolbar = new TopToolbar(controller);
		rightToolbar = new RightToolbar(controller);
		commandsList.setModel(commandsListModel);

		setScrollPane();
		setContentPanel();
		setFrame();
		addViewListener();
	}

	private void setScrollPane() {
		scrollPane.setBounds(3, 256, 342, 53);
		scrollPane.setPreferredSize(new Dimension(0, 140));
		scrollPane.setViewportView(commandsList);
	}

	private void setContentPanel() {
		contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(view, BorderLayout.CENTER);
		contentPanel.add(rightToolbar.getToolBar(), BorderLayout.EAST);
		contentPanel.add(topToolbar.getToolBar(), BorderLayout.NORTH);
		contentPanel.add(scrollPane, BorderLayout.PAGE_END);
	}

	private void setFrame() {
		setTitle("Vukan Markoviæ I7 18/2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 600);
		setContentPane(contentPanel);
	}

	private void addViewListener() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if (topToolbar.getBtnSelect().isSelected())
					controller.btnSelectClicked(click);
				else if (topToolbar.getBtnPoint().isSelected())
					controller.btnPointClicked(click);
				else if (topToolbar.getBtnLine().isSelected())
					controller.btnLineClicked(click);
				else if (topToolbar.getBtnRectangle().isSelected())
					controller.btnRectangleClicked(click);
				else if (topToolbar.getBtnCircle().isSelected())
					controller.btnCircleClicked(click);
				else if (topToolbar.getBtnDonut().isSelected())
					controller.btnDonutClicked(click);
				else if (topToolbar.getBtnHexagon().isSelected())
					controller.btnHexagonClicked(click);

				view.repaint();
				controller.fireEventsForUndoAndRedoButtons();
			}
		});
	}

	public TopToolbar getTopToolbar() {
		return topToolbar;
	}
	
	public RightToolbar getRightToolbar() {
		return rightToolbar;
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

	public void setController(DrawingController controller) {
		this.controller = controller;
	}
}