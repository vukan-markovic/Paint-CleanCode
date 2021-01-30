package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controller.*;
import toolbars.*;
import view.DrawingView;
import javax.swing.border.EmptyBorder;

public class DrawingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DrawingView view;
	private JPanel contentPanel;
	private JScrollPane scrollPane;
	private DefaultListModel<String> commandsListModel;
	private JList<String> commandsList;
	private TopToolbar topToolbar;
	private RightToolbar rightToolbar;
	private DrawingController controller;

	public DrawingFrame() {
		view = new DrawingView();
		contentPanel = new JPanel();
		scrollPane = new JScrollPane();
		commandsListModel = new DefaultListModel<>();
		commandsList = new JList<String>();
		commandsList.setModel(commandsListModel);
		topToolbar = new TopToolbar();
		rightToolbar = new RightToolbar();

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
					controller.selectOrDeselectShapes(click);
				else if (topToolbar.getBtnPoint().isSelected())
					controller.drawPoint(click);
				else if (topToolbar.getBtnLine().isSelected())
					controller.drawLine(click);
				else if (topToolbar.getBtnRectangle().isSelected())
					controller.drawRectangle(click);
				else if (topToolbar.getBtnCircle().isSelected())
					controller.drawCircle(click);
				else if (topToolbar.getBtnDonut().isSelected())
					controller.drawDonut(click);
				else if (topToolbar.getBtnHexagon().isSelected())
					controller.drawHexagon(click);

				view.repaint();
				controller.getOptionsController().fireEventsForUndoAndRedoButtons();;
			}
		});
	}

	public DrawingView getView() {
		return view;
	}

	public DefaultListModel<String> getCommandsListModel() {
		return commandsListModel;
	}

	public TopToolbar getTopToolbar() {
		return topToolbar;
	}

	public RightToolbar getRightToolbar() {
		return rightToolbar;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
		topToolbar.setController(controller);
	}

	public void setFileController(FileController fileController) {
		rightToolbar.setFileController(fileController);
	}

	public void setOptionsController(OptionsController optionsController) {
		rightToolbar.setOptionsController(optionsController);
	}
}