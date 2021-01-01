package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import command.CmdAdd;
import command.CmdBringToFront;
import command.CmdDeselect;
import command.CmdModifyCircle;
import command.CmdModifyDonut;
import command.CmdModifyHexagon;
import command.CmdModifyLine;
import command.CmdModifyPoint;
import command.CmdModifyRectangle;
import command.CmdRemove;
import command.CmdSelect;
import command.CmdSendToBack;
import command.CmdToBack;
import command.CmdToFront;
import command.Command;
import dialogs.DialogCircle;
import dialogs.DialogDonut;
import dialogs.DialogHexagon;
import dialogs.DialogLine;
import dialogs.DialogPoint;
import dialogs.DialogRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import observer.Observer;
import observer.PropertyManager;
import strategy.SaveLog;
import strategy.SavePainting;
import strategy.StrategyManager;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame mainFrame;
	private Color colInner;
	private Color colOuter;
	private Point startPoint;
	private Stack<Command> commandsNormal;
	private Stack<Command> commandsReverse;
	private Observer observer;
	private PropertyManager manager;
	private Queue<String> logCommands;
	private CmdAdd cmdAdd;
	private CmdRemove cmdRemove;
	private DialogRectangle dialogRectangle;
	private DialogCircle dialogCircle;
	private DialogDonut dialogDonut;
	private DialogHexagon dialogHexagon;
	private DialogPoint dialogPoint;
	private DialogLine dialogLine;
	private Point point;
	private CmdModifyCircle cmdModifyCircle;
	private CmdModifyDonut cmdModifyDonut;
	private CmdModifyLine cmdModifyLine;
	private CmdModifyHexagon cmdModifyHexagon;
	private CmdModifyPoint cmdModifyPoint;
	private CmdModifyRectangle cmdModifyRectangle;
	private CmdToBack cmdToBack;
	private CmdToFront cmdToFront;
	private CmdBringToFront cmdBringToFront;
	private CmdSendToBack cmdSendToBack;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private StrategyManager strategy;
	private SaveLog saveLog;
	private SavePainting painting;
	private JFileChooser fileChooser;
	private String log;

	public DrawingController() {
	}

	public void setController(DrawingModel model, DrawingFrame mainFrame) {
		this.model = model;
		this.mainFrame = mainFrame;
		colInner = new Color(255, 235, 205);
		colOuter = new Color(250, 128, 114);
		observer = new Observer();
		logCommands = new LinkedList<String>();
		manager = new PropertyManager(mainFrame);
		observer.addPropertyChangeListener(manager);
		commandsNormal = new Stack<Command>();
		commandsReverse = new Stack<Command>();
	}

	public void executeCommand(Command command) {
		command.execute();
		commandsNormal.push(command);
	}

	public void logCommand(String command) {
		mainFrame.getlModel().addElement(command);
	}

	public void btnSelectClicked(MouseEvent click) {
		if (model.getShapes().size() != 0) {
			for (int i = model.getShapes().size() - 1; i >= 0; i--) {
				Shape s = model.get(i);

				if (s.contains(click.getX(), click.getY()) && s.isSelected()) {
					executeCommand(new CmdDeselect(model.get(i), model));
					logCommand("Deselect - " + model.get(i).getClass().getSimpleName() + " " + model.get(i).toString());
					fireEvents();
					break;
				} else if (s.contains(click.getX(), click.getY()) && !s.isSelected()) {
					executeCommand(new CmdSelect(model.get(i), model));
					logCommand("Select - " + model.get(i).getClass().getSimpleName() + " " + model.get(i).toString());
					fireEvents();
					break;
				} else if (i == 0) {
					for (int j = 0; model.getSelectedShapes().size() > 0;) {
						Shape deselected = model.getSelectedShapes().get(j);
						executeCommand(new CmdDeselect(deselected, model));
						logCommand("Deselect - " + deselected.getClass().getSimpleName() + " " + deselected.toString());
						fireEvents();
					}
				}
			}
		}
	}

	public void btnPointClicked(MouseEvent click) {
		addPoint(click);
		clearStack();
	}

	public void addPoint(MouseEvent click) {
		Point point = new Point(click.getX(), click.getY(), false, colOuter);
		cmdAdd = new CmdAdd(model, point);
		executeCommand(cmdAdd);
		logCommand("Add - " + point.getClass().getSimpleName() + " " + point);
	}

	public void clearStack() {
		commandsReverse.clear();
	}

	public void addLine(MouseEvent click) {
		Point endPoint = new Point(click.getX(), click.getY(), false, colOuter);
		Line line = new Line(startPoint, endPoint, false, colOuter);
		cmdAdd = new CmdAdd(model, line);
		executeCommand(cmdAdd);
		logCommand("Add - " + line.getClass().getSimpleName() + " " + line);
	}

	public void btnLineClicked(MouseEvent click) {
		if (startPoint == null)
			startPoint = new Point(click.getX(), click.getY(), false, colOuter);
		else {
			addLine(click);
			clearStack();
			startPoint = null;
		}
	}

	public void addRectangle(DialogRectangle dialogRectangle, Point point) {
		Rectangle rectangle = new Rectangle(point, Integer.parseInt(dialogRectangle.getheight().getText()),
				Integer.parseInt(dialogRectangle.getwidth().getText()), false, colOuter, colInner);
		cmdAdd = new CmdAdd(model, rectangle);
		executeCommand(cmdAdd);
		clearStack();
		logCommand("Add - " + rectangle.getClass().getSimpleName() + " " + rectangle);
	}

	public void createRectangleDialog(Point point) {
		dialogRectangle.getBtnSetBorderColor().setVisible(false);
		dialogRectangle.getBtnSetFillColor().setVisible(false);
		dialogRectangle.getXcoordinate().setText(String.valueOf(point.getX()));
		dialogRectangle.getYcoordinate().setText(String.valueOf(point.getY()));
		dialogRectangle.getXcoordinate().setEditable(false);
		dialogRectangle.getYcoordinate().setEditable(false);
		dialogRectangle.setVisible(true);
	}

	public void btnRectangleClicked(MouseEvent click) {
		point = new Point(click.getX(), click.getY(), false, colOuter);
		dialogRectangle = new DialogRectangle();
		createRectangleDialog(point);

		if (dialogRectangle.isAccepted())
			addRectangle(dialogRectangle, point);
	}

	public void createCircleDialog(Point center) {
		dialogCircle.getBtnSetOuterColor().setVisible(false);
		dialogCircle.getBtnSetInnerColor().setVisible(false);
		dialogCircle.getXcoordinate().setText(String.valueOf(center.getX()));
		dialogCircle.getYcoordinate().setText(String.valueOf(center.getY()));
		dialogCircle.getXcoordinate().setEditable(false);
		dialogCircle.getYcoordinate().setEditable(false);
		dialogCircle.setVisible(true);
	}

	public void addCircle(DialogCircle dialogCircle, Point center) {
		Circle circle = new Circle(center, Integer.parseInt(dialogCircle.getRadius().getText()), false, colOuter,
				colInner);
		cmdAdd = new CmdAdd(model, circle);
		executeCommand(cmdAdd);
		clearStack();
		logCommand("Add - " + circle.getClass().getSimpleName() + " " + circle);
	}

	public void btnCircleClicked(MouseEvent click) {
		point = new Point(click.getX(), click.getY(), false, colOuter);
		dialogCircle = new DialogCircle();
		createCircleDialog(point);

		if (dialogCircle.isAccepted())
			addCircle(dialogCircle, point);
	}

	public void createDonutDialog(Point center) {
		dialogDonut.getBtnSetOuterColor().setVisible(false);
		dialogDonut.getBtnSetInnerColor().setVisible(false);
		dialogDonut.getXcoordinate().setText(String.valueOf(center.getX()));
		dialogDonut.getYcoordinate().setText(String.valueOf(center.getY()));
		dialogDonut.getXcoordinate().setEditable(false);
		dialogDonut.getYcoordinate().setEditable(false);
		dialogDonut.setVisible(true);
	}

	public void addDonut(DialogDonut dialogDonut, Point center) {
		Donut donut = new Donut(center, Integer.parseInt(dialogDonut.getRadius().getText()),
				Integer.parseInt(dialogDonut.getSmallRadius().getText()), false, colOuter, colInner);

		cmdAdd = new CmdAdd(model, donut);
		executeCommand(cmdAdd);
		clearStack();
		logCommand("Add - " + donut.getClass().getSimpleName() + " " + donut);
	}

	public void btnDonutClicked(MouseEvent click) {
		point = new Point(click.getX(), click.getY(), false, colOuter);
		dialogDonut = new DialogDonut();
		createDonutDialog(point);

		if (dialogDonut.isAccepted())
			addDonut(dialogDonut, point);
	}

	public void createHexagonDialog(Point center) {
		dialogHexagon.getBtnSetOuterColor().setVisible(false);
		dialogHexagon.getBtnSetInnerColor().setVisible(false);
		dialogHexagon.getXcoordinate().setText(String.valueOf(center.getX()));
		dialogHexagon.getYcoordinate().setText(String.valueOf(center.getY()));
		dialogHexagon.getXcoordinate().setEditable(false);
		dialogHexagon.getYcoordinate().setEditable(false);
		dialogHexagon.setVisible(true);
	}

	public void addHexagon(DialogHexagon dialogHexagon, Point center) {
		HexagonAdapter hexagon = new HexagonAdapter(
				new Hexagon(center.getX(), center.getY(), Integer.parseInt(dialogHexagon.getRadius().getText())),
				colOuter, colInner);
		cmdAdd = new CmdAdd(model, hexagon);
		executeCommand(cmdAdd);
		clearStack();
		logCommand("Add - " + hexagon.getClass().getSimpleName() + " " + hexagon);
	}

	public void btnHexagonClicked(MouseEvent click) {
		point = new Point(click.getX(), click.getY(), false, colOuter);
		dialogHexagon = new DialogHexagon();
		createHexagonDialog(point);

		if (dialogHexagon.isAccepted())
			addHexagon(dialogHexagon, point);
	}

	public void createPointDialog(Shape selectedShape) {
		dialogPoint.getXcoordinate().setText(String.valueOf(((Point) selectedShape).getX()));
		dialogPoint.getYcoordinate().setText(String.valueOf(((Point) selectedShape).getY()));
		dialogPoint.setOuterColor(((Point) selectedShape).getBorderColor());
		dialogPoint.getBtnSetOuterColor().setBackground(dialogPoint.getOuterColor());
		dialogPoint.setVisible(true);
	}

	public void modifyPoint(Shape selectedShape, DialogPoint dialogPoint) {
		Point oldState = (Point) selectedShape;

		Point newState = new Point(Integer.parseInt(dialogPoint.getXcoordinate().getText()),
				Integer.parseInt(dialogPoint.getYcoordinate().getText()), oldState.isSelected(),
				dialogPoint.getOuterColor());

		logCommand("Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
		cmdModifyPoint = new CmdModifyPoint(oldState, (Point) newState);
		executeCommand(cmdModifyPoint);
		clearStack();
	}

	public void createLineDialog(Shape selectedShape) {
		dialogLine.getX1coordinate().setText(String.valueOf(((Line) selectedShape).getStartPoint().getX()));
		dialogLine.getY1coordinate().setText(String.valueOf(((Line) selectedShape).getStartPoint().getY()));
		dialogLine.getX2coordinate().setText(String.valueOf(((Line) selectedShape).getEndPoint().getX()));
		dialogLine.getY2coordinate().setText(String.valueOf(((Line) selectedShape).getEndPoint().getY()));
		dialogLine.setOuterColor(((Line) selectedShape).getBorderColor());
		dialogLine.getBtnSetOuterColor().setBackground(dialogLine.getOuterColor());
		dialogLine.setVisible(true);
	}

	public void modifyLine(Shape selectedShape, DialogLine dialogLine) {
		Line oldState = (Line) selectedShape;

		Line newState = new Line(
				new Point(Integer.parseInt(dialogLine.getX1coordinate().getText()),
						Integer.parseInt(dialogLine.getY1coordinate().getText()), oldState.isSelected(),
						dialogLine.getOuterColor()),
				new Point(Integer.parseInt(dialogLine.getX2coordinate().getText()),
						Integer.parseInt(dialogLine.getY2coordinate().getText()), oldState.isSelected(),
						dialogLine.getOuterColor()),
				oldState.isSelected(), dialogLine.getOuterColor());

		logCommand("Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
		cmdModifyLine = new CmdModifyLine(oldState, (Line) newState);
		executeCommand(cmdModifyLine);
		clearStack();
	}

	public void createRectangleModifyDialog(Shape selectedShape) {
		dialogRectangle.getXcoordinate()
				.setText(String.valueOf(((Rectangle) selectedShape).getUpperLeftPoint().getX()));
		dialogRectangle.getYcoordinate()
				.setText(String.valueOf(((Rectangle) selectedShape).getUpperLeftPoint().getY()));
		dialogRectangle.getheight().setText(String.valueOf(((Rectangle) selectedShape).getHeight()));
		dialogRectangle.getwidth().setText(String.valueOf(((Rectangle) selectedShape).getWidth()));
		dialogRectangle.setOuterColor(((Rectangle) selectedShape).getBorderColor());
		dialogRectangle.setInnerColor(((Rectangle) selectedShape).getFillColor());
		dialogRectangle.getBtnSetBorderColor().setBackground(dialogRectangle.getOuterColor());
		dialogRectangle.getBtnSetFillColor().setBackground(dialogRectangle.getInnerColor());
		dialogRectangle.setVisible(true);
	}

	public void modifyRectangle(Shape selectedShape, DialogRectangle dialogRectangle) {
		Rectangle oldState = (Rectangle) selectedShape;

		Rectangle newState = new Rectangle(
				new Point(Integer.parseInt(dialogRectangle.getXcoordinate().getText()),
						Integer.parseInt(dialogRectangle.getYcoordinate().getText()), oldState.isSelected(),
						dialogRectangle.getOuterColor()),
				Integer.parseInt(dialogRectangle.getheight().getText()),
				Integer.parseInt(dialogRectangle.getwidth().getText()), oldState.isSelected(),
				dialogRectangle.getOuterColor(), dialogRectangle.getInnerColor());

		logCommand("Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
		cmdModifyRectangle = new CmdModifyRectangle(oldState, (Rectangle) newState);
		executeCommand(cmdModifyRectangle);
		clearStack();
	}

	public void createDonutModifyDialog(Shape selectedShape) {
		dialogDonut.getXcoordinate().setText(String.valueOf(((Donut) selectedShape).getCenter().getX()));
		dialogDonut.getYcoordinate().setText(String.valueOf(((Donut) selectedShape).getCenter().getY()));
		dialogDonut.getRadius().setText(String.valueOf(((Donut) selectedShape).getRadius()));
		dialogDonut.getSmallRadius().setText(String.valueOf(((Donut) selectedShape).getInnerRadius()));
		dialogDonut.setOuterColor(((Donut) selectedShape).getBorderColor());
		dialogDonut.setInnerColor(((Donut) selectedShape).getFillColor());
		dialogDonut.getBtnSetOuterColor().setBackground(dialogDonut.getOuterColor());
		dialogDonut.getBtnSetInnerColor().setBackground(dialogDonut.getInnerColor());
		dialogDonut.setVisible(true);
	}

	public void modifyDonut(Shape selectedShape, DialogDonut dialogDonut) {
		Donut oldState = (Donut) selectedShape;

		Donut newState = new Donut(
				new Point(Integer.parseInt(dialogDonut.getXcoordinate().getText()),
						Integer.parseInt(dialogDonut.getYcoordinate().getText()), oldState.isSelected(),
						dialogDonut.getOuterColor()),
				Integer.parseInt(dialogDonut.getRadius().getText()),
				Integer.parseInt(dialogDonut.getSmallRadius().getText()), oldState.isSelected(),
				dialogDonut.getOuterColor(), dialogDonut.getInnerColor());

		logCommand("Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
		cmdModifyDonut = new CmdModifyDonut(oldState, (Donut) newState);
		executeCommand(cmdModifyDonut);
		clearStack();
	}

	public void createCircleModifyDialog(Shape selectedShape) {
		dialogCircle.getXcoordinate().setText(String.valueOf(((Circle) selectedShape).getCenter().getX()));
		dialogCircle.getYcoordinate().setText(String.valueOf(((Circle) selectedShape).getCenter().getY()));
		dialogCircle.getRadius().setText(String.valueOf(((Circle) selectedShape).getRadius()));
		dialogCircle.setOuterColor(((Circle) selectedShape).getBorderColor());
		dialogCircle.setInnerColor(((Circle) selectedShape).getFillColor());
		dialogCircle.getBtnSetOuterColor().setBackground(dialogCircle.getOuterColor());
		dialogCircle.getBtnSetInnerColor().setBackground(dialogCircle.getInnerColor());
		dialogCircle.setVisible(true);
	}

	public void modifyCircle(Shape selectedShape, DialogCircle dialogCircle) {
		Circle oldState = (Circle) selectedShape;

		Circle newState = new Circle(
				new Point(Integer.parseInt(dialogCircle.getXcoordinate().getText()),
						Integer.parseInt(dialogCircle.getYcoordinate().getText()), oldState.isSelected(),
						dialogCircle.getOuterColor()),
				Integer.parseInt(dialogCircle.getRadius().getText()), oldState.isSelected(),
				dialogCircle.getOuterColor(), dialogCircle.getInnerColor());

		logCommand("Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
		cmdModifyCircle = new CmdModifyCircle(oldState, (Circle) newState);
		executeCommand(cmdModifyCircle);
		clearStack();
	}

	public void createHexagonModifyDialog(Shape selectedShape) {
		dialogHexagon.getXcoordinate().setText(String.valueOf(((HexagonAdapter) selectedShape).getX()));
		dialogHexagon.getYcoordinate().setText(String.valueOf(((HexagonAdapter) selectedShape).getY()));
		dialogHexagon.getRadius().setText(String.valueOf(((HexagonAdapter) selectedShape).getR()));
		dialogHexagon.setOuterColor(((HexagonAdapter) selectedShape).getBorderColor());
		dialogHexagon.setInnerColor(((HexagonAdapter) selectedShape).getFillColor());
		dialogHexagon.getBtnSetOuterColor().setBackground(dialogHexagon.getOuterColor());
		dialogHexagon.getBtnSetInnerColor().setBackground(dialogHexagon.getInnerColor());
		dialogHexagon.setVisible(true);
	}

	public void modifyHexagon(Shape selectedShape, DialogHexagon dialogHexagon) {
		HexagonAdapter oldState = (HexagonAdapter) selectedShape;

		HexagonAdapter newState = new HexagonAdapter(Integer.parseInt(dialogHexagon.getXcoordinate().getText()),
				Integer.parseInt(dialogHexagon.getYcoordinate().getText()),
				Integer.parseInt(dialogHexagon.getRadius().getText()), dialogHexagon.getOuterColor(),
				dialogHexagon.getInnerColor(), oldState.isSelected());

		logCommand("Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
		cmdModifyHexagon = new CmdModifyHexagon(oldState, (HexagonAdapter) newState);
		executeCommand(cmdModifyHexagon);
		clearStack();
	}

	public void modify() {
		Shape selectedShape = model.getOneSelectedShape();

		if (model.getSelectedShapes().size() != 1)
			return;

		if (selectedShape instanceof Point) {
			dialogPoint = new DialogPoint();
			createPointDialog(selectedShape);
			if (dialogPoint.isAccepted())
				modifyPoint(selectedShape, dialogPoint);
		} else if (selectedShape instanceof Line) {
			dialogLine = new DialogLine();
			createLineDialog(selectedShape);
			if (dialogLine.isAccepted())
				modifyLine(selectedShape, dialogLine);
		} else if (selectedShape instanceof Rectangle) {
			dialogRectangle = new DialogRectangle();
			createRectangleModifyDialog(selectedShape);
			if (dialogRectangle.isAccepted())
				modifyRectangle(selectedShape, dialogRectangle);
		} else if (selectedShape instanceof Donut) {
			dialogDonut = new DialogDonut();
			createDonutModifyDialog(selectedShape);
			if (dialogDonut.isAccepted())
				modifyDonut(selectedShape, dialogDonut);
		} else if (selectedShape instanceof Circle) {
			dialogCircle = new DialogCircle();
			createCircleModifyDialog(selectedShape);
			if (dialogCircle.isAccepted())
				modifyCircle(selectedShape, dialogCircle);
		} else if (selectedShape instanceof HexagonAdapter) {
			dialogHexagon = new DialogHexagon();
			createHexagonModifyDialog(selectedShape);
			if (dialogHexagon.isAccepted())
				modifyHexagon(selectedShape, dialogHexagon);
		}

		mainFrame.getView().repaint();
		fireUndoRedo();
	}

	public void delete() {
		if (model.getSelectedShapes().size() == 0)
			return;

		if (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete selected shape/shapes?",
				"Delete it?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			deleteForLogs();
			logCommand("Deleted");
			mainFrame.getView().repaint();
			fireEvents();
		}
	}

	public void deleteForLogs() {
		for (int i = 0; i < model.getSelectedShapes().size(); i++) {
			cmdRemove = new CmdRemove(model, model.getSelectedShapes().get(i));
			executeCommand(cmdRemove);
			clearStack();
			// main_frame.getlModel().addElement("Deleted - " +
			// model.getSelectedShapes().get(i).getClass().getSimpleName()
			// + " " + model.getSelectedShapes().get(i).toString());
		}

		model.setSelectedShapes(new ArrayList<Shape>());
	}

	public void undo() {
		if (commandsNormal.isEmpty())
			return;

		undoForLog();
		logCommand("Undo");
		mainFrame.getView().repaint();
		fireUndoRedo();
		fireEvents();
	}

	public void undoForLog() {
		commandsNormal.peek().unexecute();
		commandsReverse.push(commandsNormal.peek());
		commandsNormal.pop();
	}

	public void redo() {
		if (commandsReverse.isEmpty())
			return;

		redoForLog();
		logCommand("Redo");
		mainFrame.getView().repaint();
		fireUndoRedo();
		fireEvents();
	}

	public void redoForLog() {
		commandsReverse.peek().execute();
		commandsNormal.push(commandsReverse.peek());
		commandsReverse.pop();
	}

	public void outerColor() {
		colOuter = JColorChooser.showDialog(null, "Choose a color!", colOuter);

		if (colOuter != null)
			mainFrame.getBtnOuterCol().setBackground(colOuter);
	}

	public void innerColor() {
		colInner = JColorChooser.showDialog(null, "Choose a color!", colInner);

		if (colInner != null)
			mainFrame.getBtnInnerCol().setBackground(colInner);
	}

	public void toBack() {
		if (model.getSelectedShapes().size() == 1) {
			Shape shape = model.getOneSelectedShape();
			cmdToBack = new CmdToBack(model, shape);
			executeCommand(cmdToBack);
			clearStack();
			logCommand("ToBack - " + shape.getClass().getSimpleName() + " " + shape.toString());
			mainFrame.getView().repaint();
		}
	}

	public void toFront() {
		if (model.getSelectedShapes().size() == 1) {
			Shape shape = model.getOneSelectedShape();
			cmdToFront = new CmdToFront(model, shape);
			executeCommand(cmdToFront);
			clearStack();
			logCommand("ToFront - " + shape.getClass().getSimpleName() + " " + shape.toString());
			mainFrame.getView().repaint();
		}
	}

	public void sendToBack() {
		if (model.getSelectedShapes().size() == 1) {
			Shape shape = model.getOneSelectedShape();
			cmdSendToBack = new CmdSendToBack(model, shape);
			executeCommand(cmdSendToBack);
			clearStack();
			logCommand("SendToBack - " + shape.getClass().getSimpleName() + " " + shape.toString());
			mainFrame.getView().repaint();
		}
	}

	public void bringToFront() {
		if (model.getSelectedShapes().size() == 1) {
			Shape shape = model.getOneSelectedShape();
			cmdBringToFront = new CmdBringToFront(model, shape);
			executeCommand(cmdBringToFront);
			clearStack();
			logCommand("BringToFront - " + shape.getClass().getSimpleName() + " " + shape.toString());
			mainFrame.getView().repaint();
		}
	}

	public void fireEvents() {
		observer.setBtnDeleteEnable(model.getSelectedShapes().size() > 0);
		observer.setBtnModifyEnable(model.getSelectedShapes().size() == 1);
		observer.setBtnToBackEnable(model.getSelectedShapes().size() == 1);
		observer.setBtnToFrontEnable(model.getSelectedShapes().size() == 1);
		observer.setBtnSendToBackEnable(model.getSelectedShapes().size() == 1);
		observer.setBtnBringToFrontEnable(model.getSelectedShapes().size() == 1);
	}

	public void fireUndoRedo() {
		observer.setBtnUndoEnable(commandsNormal.size() > 0);
		observer.setBtnRedoEnable(commandsReverse.size() > 0);
	}

	public void saveDrawing() {
		painting.setShapes(model.getShapes());
		strategy.setStrategy(painting);
		strategy.save(
				fileChooser.getSelectedFile().getAbsolutePath() + "\\" + mainFrame.getFileName().getText() + ".bin");
	}

	public void saveLog() {
		log = "";

		for (int i = 0; i < mainFrame.getlModel().getSize(); i++)
			log = log + mainFrame.getlModel().get(i) + "\n";

		saveLog.setLog(log);
		strategy.setStrategy(saveLog);
		strategy.save(
				fileChooser.getSelectedFile().getAbsolutePath() + "\\" + mainFrame.getFileName().getText() + ".txt");
	}

	public String getLog() {
		return log;
	}

	public int showFileChooser(String title, int mode) {
		fileChooser.setDialogTitle(title);
		fileChooser.setFileSelectionMode(mode);
		return fileChooser.showSaveDialog(mainFrame);
	}

	public void save() {
		if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "You can't save because you haven't draw anythig yet!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		fileChooser = new JFileChooser();

		if (showFileChooser("Specify the location where you want to save your drawing",
				JFileChooser.DIRECTORIES_ONLY) == JFileChooser.APPROVE_OPTION) {

			saveLog = new SaveLog();
			strategy = new StrategyManager();
			saveLog();

			painting = new SavePainting();
			strategy = new StrategyManager();
			saveDrawing();
		}
	}

	public void loadLog() {
		mainFrame.getBtnNext().setEnabled(true);
		setLogCommands(new LinkedList<String>());
		fileChooser = new JFileChooser();

		if (showFileChooser("Specify the log you want to open", JFileChooser.FILES_ONLY) == JFileChooser.APPROVE_OPTION)
			addLog();
	}

	public void addLog() {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getAbsolutePath()));
			String text = "";

			while ((text = buffer.readLine()) != null)
				logCommands.add(text);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

	public void addPainting() {
		try {
			FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile().getAbsoluteFile());
			ObjectInputStream ois = new ObjectInputStream(fis);
			model.setShapes((List<Shape>) ois.readObject());
			mainFrame.getView().repaint();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void loadPainting() {
		fileChooser = new JFileChooser();
		showFileChooser("Specify the painting you want to open", JFileChooser.FILES_ONLY);

		if (fileChooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION)
			addPainting();
	}

	public void addPointFromLog(String[] line) {
		Point point = new Point(Integer.parseInt(line[4]), Integer.parseInt(line[7]), false,
				(Integer.parseInt(line[11]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[11]))));

		cmdAdd = new CmdAdd(model, point);
		executeCommand(cmdAdd);
	}

	public void addLineFromLog(String[] line) {
		Point startPoint = new Point(Integer.parseInt(line[6]), Integer.parseInt(line[9]), false,
				(Integer.parseInt(line[13]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[13]))));

		Point endPoint = new Point(Integer.parseInt(line[18]), Integer.parseInt(line[21]), false,
				(Integer.parseInt(line[25]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[25]))));

		Line lineShape = new Line(startPoint, endPoint, false,
				(Integer.parseInt(line[29]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[29]))));

		cmdAdd = new CmdAdd(model, lineShape);
		executeCommand(cmdAdd);
	}

	public void addRectangleFromLog(String[] line) {
		Point point = new Point(Integer.parseInt(line[7]), Integer.parseInt(line[10]), false,
				(Integer.parseInt(line[14]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[14]))));

		Rectangle rectangle = new Rectangle(point, Integer.parseInt(line[17]), Integer.parseInt(line[20]), false,
				(Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[24]))),
				(Integer.parseInt(line[28]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[28]))));

		cmdAdd = new CmdAdd(model, rectangle);
		executeCommand(cmdAdd);
	}

	public void addCircleFromLog(String[] line) {
		Point point = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false,
				(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12]))));

		Circle circle = new Circle(point, Integer.parseInt(line[15]), false,
				(Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[19]))),
				(Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[23]))));

		cmdAdd = new CmdAdd(model, circle);
		executeCommand(cmdAdd);
	}

	public void addDonutFromLog(String[] line) {
		Point point = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false,
				(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12]))));

		Donut donut = new Donut(point, Integer.parseInt(line[15]), Integer.parseInt(line[27]), false,
				(Integer.parseInt(line[31]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[31]))),
				(Integer.parseInt(line[35]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[35]))));

		cmdAdd = new CmdAdd(model, donut);
		executeCommand(cmdAdd);
	}

	public void addHexagonFromLog(String[] line) {
		Point point = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false,
				(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12]))));

		HexagonAdapter hexagon = new HexagonAdapter(point.getX(), point.getY(), Integer.parseInt(line[15]),
				(Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[19]))),
				(Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[23]))),
				false);

		cmdAdd = new CmdAdd(model, hexagon);
		executeCommand(cmdAdd);
	}

	public void selectPoint(String[] line) {
		Point point = new Point(Integer.parseInt(line[4]), Integer.parseInt(line[7]), false,
				(Integer.parseInt(line[11]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[11]))));

		cmdSelect = new CmdSelect(point, model);
		executeCommand(cmdSelect);
	}

	public void selectLine(String[] line) {
		Point startPoint = new Point(Integer.parseInt(line[6]), Integer.parseInt(line[9]), false,
				(Integer.parseInt(line[13]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[13]))));

		Point endPoint = new Point(Integer.parseInt(line[18]), Integer.parseInt(line[21]), false,
				(Integer.parseInt(line[25]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[25]))));

		Line lineShape = new Line(startPoint, endPoint, true,
				(Integer.parseInt(line[29]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[29]))));

		cmdSelect = new CmdSelect(lineShape, model);
		executeCommand(cmdSelect);
	}

	public void selectRectangle(String[] line) {
		Point point = new Point(Integer.parseInt(line[7]), Integer.parseInt(line[10]), false,
				(Integer.parseInt(line[14]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[14]))));

		Rectangle rectangle = new Rectangle(point, Integer.parseInt(line[17]), Integer.parseInt(line[20]), true,
				(Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[24]))),
				(Integer.parseInt(line[28]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[28]))));

		cmdSelect = new CmdSelect(rectangle, model);
		executeCommand(cmdSelect);
	}

	public void selectCircle(String[] line) {
		Point point = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false,
				(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12]))));

		Circle circle = new Circle(point, Integer.parseInt(line[15]), true,
				(Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[19]))),
				(Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[23]))));

		cmdSelect = new CmdSelect(circle, model);
		executeCommand(cmdSelect);
	}

	public void selectDonut(String[] line) {
		Point point = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false,
				(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12]))));

		Donut donut = new Donut(point, Integer.parseInt(line[15]), Integer.parseInt(line[27]), true,
				(Integer.parseInt(line[31]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[31]))),
				(Integer.parseInt(line[35]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[35]))));

		cmdSelect = new CmdSelect(donut, model);
		executeCommand(cmdSelect);
	}

	public void selectHexagon(String[] line) {
		Point point = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false,
				(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12]))));

		HexagonAdapter hexagon = new HexagonAdapter(point.getX(), point.getY(), Integer.parseInt(line[15]),
				(Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[19]))),
				(Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[23]))),
				false);

		cmdSelect = new CmdSelect(hexagon, model);
		executeCommand(cmdSelect);
	}

	public void deselectPoint(String[] line) {
		Point point = new Point(Integer.parseInt(line[4]), Integer.parseInt(line[7]), true,
				(Integer.parseInt(line[11]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[11]))));

		cmdDeselect = new CmdDeselect(point, model);
		executeCommand(cmdDeselect);
	}

	public void deselectLine(String[] line) {
		Point startPoint = new Point(Integer.parseInt(line[6]), Integer.parseInt(line[9]), false,
				(Integer.parseInt(line[13]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[13]))));

		Point endPoint = new Point(Integer.parseInt(line[18]), Integer.parseInt(line[21]), false,
				(Integer.parseInt(line[25]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[25]))));

		Line lineShape = new Line(startPoint, endPoint, true,
				(Integer.parseInt(line[29]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[29]))));

		cmdDeselect = new CmdDeselect(lineShape, model);
		executeCommand(cmdDeselect);
	}

	public void deselectRectangle(String[] line) {
		Point point = new Point(Integer.parseInt(line[7]), Integer.parseInt(line[10]), false,
				(Integer.parseInt(line[14]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[14]))));

		Rectangle rectangle = new Rectangle(point, Integer.parseInt(line[17]), Integer.parseInt(line[20]), true,
				(Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[24]))),
				(Integer.parseInt(line[28]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[28]))));

		cmdDeselect = new CmdDeselect(rectangle, model);
		executeCommand(cmdDeselect);
	}

	public void deselectCircle(String[] line) {
		Point point = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false,
				(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12]))));

		Circle circle = new Circle(point, Integer.parseInt(line[15]), true,
				(Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[19]))),
				(Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[23]))));

		cmdDeselect = new CmdDeselect(circle, model);
		executeCommand(cmdDeselect);
	}

	public void deselectDonut(String[] line) {
		Point point = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false,
				(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12]))));

		Donut donut = new Donut(point, Integer.parseInt(line[15]), Integer.parseInt(line[27]), true,
				(Integer.parseInt(line[31]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[31]))),
				(Integer.parseInt(line[35]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[35]))));

		cmdDeselect = new CmdDeselect(donut, model);
		executeCommand(cmdDeselect);
	}

	public void deselectHexagon(String[] line) {
		Point point = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false,
				(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12]))));

		HexagonAdapter hexagon = new HexagonAdapter(point.getX(), point.getY(), Integer.parseInt(line[15]),
				(Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[19]))),
				(Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[23]))),
				true);

		cmdDeselect = new CmdDeselect(hexagon, model);
		executeCommand(cmdDeselect);
	}

	public void modifyPointFromLog(String[] line, Shape selectedShape) {
		Point oldState = (Point) selectedShape;

		Point newState = new Point(Integer.parseInt(line[17]), Integer.parseInt(line[20]), true,
				(Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[24]))));

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(newState);

		cmdModifyPoint = new CmdModifyPoint(oldState, newState);
		executeCommand(cmdModifyPoint);
	}

	public void modifyLineFromLog(String[] line, Shape selectedShape) {
		Line oldState = (Line) selectedShape;

		Point startPoint = new Point(Integer.parseInt(line[36]), Integer.parseInt(line[39]), false,
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))));

		Point endPoint = new Point(Integer.parseInt(line[48]), Integer.parseInt(line[51]), false,
				(Integer.parseInt(line[55]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[55]))));

		Line newState = new Line(startPoint, endPoint, true,
				(Integer.parseInt(line[59]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[59]))));

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(newState);

		cmdModifyLine = new CmdModifyLine(oldState, newState);
		executeCommand(cmdModifyLine);
	}

	public void modifyRectangleFromLog(String[] line, Shape selectedShape) {
		Rectangle oldState = (Rectangle) selectedShape;

		Point point = new Point(Integer.parseInt(line[36]), Integer.parseInt(line[39]), false,
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))));

		Rectangle newState = new Rectangle(point, Integer.parseInt(line[46]), Integer.parseInt(line[49]), true,
				(Integer.parseInt(line[53]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[53]))),
				(Integer.parseInt(line[57]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[57]))));

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(newState);

		cmdModifyRectangle = new CmdModifyRectangle(oldState, newState);
		executeCommand(cmdModifyRectangle);
	}

	public void modifyCircleFromLog(String[] line, Shape selectedShape) {
		Circle oldState = (Circle) selectedShape;

		Point point = new Point(Integer.parseInt(line[29]), Integer.parseInt(line[32]), false,
				(Integer.parseInt(line[36]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[36]))));

		Circle newState = new Circle(point, Integer.parseInt(line[39]), true,
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))),
				(Integer.parseInt(line[47]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[47]))));

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(newState);

		cmdModifyCircle = new CmdModifyCircle(oldState, newState);
		executeCommand(cmdModifyCircle);
	}

	public void modifyDonutFromLog(String[] line, Shape selectedShape) {
		Donut oldState = (Donut) selectedShape;

		Point point = new Point(Integer.parseInt(line[41]), Integer.parseInt(line[44]), false,
				(Integer.parseInt(line[48]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[48]))));

		Donut newState = new Donut(point, Integer.parseInt(line[51]), Integer.parseInt(line[63]), true,
				(Integer.parseInt(line[67]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[67]))),
				(Integer.parseInt(line[71]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[71]))));

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(newState);

		cmdModifyDonut = new CmdModifyDonut(oldState, newState);
		executeCommand(cmdModifyDonut);
	}

	public void modifyHexagonFromLog(String[] line, Shape selectedShape) {
		HexagonAdapter oldState = (HexagonAdapter) selectedShape;

		Point point = new Point(Integer.parseInt(line[29]), Integer.parseInt(line[32]), false,
				(Integer.parseInt(line[36]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[36]))));

		HexagonAdapter newState = new HexagonAdapter(point.getX(), point.getY(), Integer.parseInt(line[39]),
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))),
				(Integer.parseInt(line[47]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[47]))),
				true);

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(newState);

		cmdModifyHexagon = new CmdModifyHexagon(oldState, newState);
		executeCommand(cmdModifyHexagon);
	}

	public void nextCommand() {
		String[] line = logCommands.peek().split(" ");

		if (line[0].equals("Add")) {
			if (line[2].equals("Point"))
				addPointFromLog(line);
			else if (line[2].equals("Line"))
				addLineFromLog(line);
			else if (line[2].equals("Rectangle"))
				addRectangleFromLog(line);
			else if (line[2].equals("Circle"))
				addCircleFromLog(line);
			else if (line[2].equals("Donut"))
				addDonutFromLog(line);
			else if (line[2].equals("HexagonAdapter"))
				addHexagonFromLog(line);
		} else if (line[0].equals("Select")) {
			if (line[2].equals("Point"))
				selectPoint(line);
			else if (line[2].equals("Line"))
				selectLine(line);
			else if (line[2].equals("Rectangle"))
				selectRectangle(line);
			else if (line[2].equals("Circle"))
				selectCircle(line);
			else if (line[2].equals("Donut"))
				selectDonut(line);
			else if (line[2].equals("HexagonAdapter"))
				selectHexagon(line);
		} else if (line[0].equals("Deselect")) {
			if (line[2].equals("Point"))
				deselectPoint(line);
			else if (line[2].equals("Line"))
				deselectLine(line);
			else if (line[2].equals("Rectangle"))
				deselectRectangle(line);
			else if (line[2].equals("Circle"))
				deselectCircle(line);
			else if (line[2].equals("Donut"))
				deselectDonut(line);
			else if (line[2].equals("HexagonAdapter"))
				deselectHexagon(line);
		} else if (line[0].equals("Modify")) {
			Shape selectedShape = model.getOneSelectedShape();

			if (line[2].equals("Point"))
				modifyPointFromLog(line, selectedShape);
			else if (line[2].equals("Line"))
				modifyLineFromLog(line, selectedShape);
			else if (line[2].equals("Rectangle"))
				modifyRectangleFromLog(line, selectedShape);
			else if (line[2].equals("Circle"))
				modifyCircleFromLog(line, selectedShape);
			else if (line[2].equals("Donut"))
				modifyDonutFromLog(line, selectedShape);
			else if (line[2].equals("HexagonAdapter"))
				modifyHexagonFromLog(line, selectedShape);
		} else if (line[0].equals("Deleted"))
			deleteForLogs();
		else if (line[0].equals("Undo"))
			undoForLog();
		else if (line[0].equals("Redo"))
			redoForLog();
		else if (line[0].equals("ToBack"))
			toBack();
		else if (line[0].equals("ToFront"))
			toFront();
		else if (line[0].equals("BringToFront"))
			bringToFront();
		else if (line[0].equals("SendToBack"))
			sendToBack();

		if (getLogCommands().size() == 1)
			mainFrame.getBtnNext().setEnabled(false);

		fireEvents();
		fireUndoRedo();
		mainFrame.getView().repaint();
		mainFrame.getlModel().addElement(getLogCommands().poll());
	}

	public Queue<String> getLogCommands() {
		return logCommands;
	}

	public void setLogCommands(Queue<String> loggComm) {
		this.logCommands = loggComm;
	}

	public Stack<Command> getCommandsNormal() {
		return commandsNormal;
	}

	public void setCommandsNormal(Stack<Command> commandsNormal) {
		this.commandsNormal = commandsNormal;
	}

	public Stack<Command> getCommandsReverse() {
		return commandsReverse;
	}

	public void setCommandsReverse(Stack<Command> commandsReverse) {
		this.commandsReverse = commandsReverse;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdRemove getCmdRemove() {
		return cmdRemove;
	}

	public void setCmdRemove(CmdRemove cmdRemove) {
		this.cmdRemove = cmdRemove;
	}

	public CmdModifyCircle getCmdModifyCircle() {
		return cmdModifyCircle;
	}

	public void setCmdModifyCircle(CmdModifyCircle cmdModifyCircle) {
		this.cmdModifyCircle = cmdModifyCircle;
	}

	public CmdModifyDonut getCmdModifyDonut() {
		return cmdModifyDonut;
	}

	public void setCmdModifyDonut(CmdModifyDonut cmdModifyDonut) {
		this.cmdModifyDonut = cmdModifyDonut;
	}

	public CmdModifyLine getCmdModifyLine() {
		return cmdModifyLine;
	}

	public void setCmdModifyLine(CmdModifyLine cmdModifyLine) {
		this.cmdModifyLine = cmdModifyLine;
	}

	public CmdModifyHexagon getCmdModifyHexagon() {
		return cmdModifyHexagon;
	}

	public void setCmdModifyHexagon(CmdModifyHexagon cmdModifyHexagon) {
		this.cmdModifyHexagon = cmdModifyHexagon;
	}

	public CmdModifyPoint getCmdModifyPoint() {
		return cmdModifyPoint;
	}

	public void setCmdModifyPoint(CmdModifyPoint cmdModifyPoint) {
		this.cmdModifyPoint = cmdModifyPoint;
	}

	public CmdModifyRectangle getCmdModifyRectangle() {
		return cmdModifyRectangle;
	}

	public void setCmdModifyRectangle(CmdModifyRectangle cmdModifyRectangle) {
		this.cmdModifyRectangle = cmdModifyRectangle;
	}

	public CmdToBack getCmdToBack() {
		return cmdToBack;
	}

	public void setCmdToBack(CmdToBack cmdToBack) {
		this.cmdToBack = cmdToBack;
	}

	public CmdToFront getCmdToFront() {
		return cmdToFront;
	}

	public void setCmdToFront(CmdToFront cmdToFront) {
		this.cmdToFront = cmdToFront;
	}

	public CmdBringToFront getCmdBringToFront() {
		return cmdBringToFront;
	}

	public void setCmdBringToFront(CmdBringToFront cmdBringToFront) {
		this.cmdBringToFront = cmdBringToFront;
	}

	public CmdSendToBack getCmdSendToBack() {
		return cmdSendToBack;
	}

	public void setCmdSendToBack(CmdSendToBack cmdSendToBack) {
		this.cmdSendToBack = cmdSendToBack;
	}

	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	public void setCmdSelect(CmdSelect cmdSelect) {
		this.cmdSelect = cmdSelect;
	}

	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}

	public void setCmdDeselect(CmdDeselect cmdDeselect) {
		this.cmdDeselect = cmdDeselect;
	}

	public Color getInnerColor() {
		return colInner;
	}

	public Color getOuterColor() {
		return colOuter;
	}

	public void setDialogRectangle(DialogRectangle dialogRectangle) {
		this.dialogRectangle = dialogRectangle;
	}

	public DialogRectangle getDialogRectangle() {
		return dialogRectangle;
	}

	public DialogCircle getDialogCircle() {
		return dialogCircle;
	}

	public void setDialogCircle(DialogCircle dialogCircle) {
		this.dialogCircle = dialogCircle;
	}

	public DialogDonut getDialogDonut() {
		return dialogDonut;
	}

	public void setDialogDonut(DialogDonut dialogDonut) {
		this.dialogDonut = dialogDonut;
	}

	public DialogHexagon getDialogHexagon() {
		return dialogHexagon;
	}

	public void setDialogHexagon(DialogHexagon dialogHexagon) {
		this.dialogHexagon = dialogHexagon;
	}

	public DialogPoint getDialogPoint() {
		return dialogPoint;
	}

	public void setDialogPoint(DialogPoint dialogPoint) {
		this.dialogPoint = dialogPoint;
	}

	public DialogLine getDialogLine() {
		return dialogLine;
	}

	public void setDialogLine(DialogLine dialogLine) {
		this.dialogLine = dialogLine;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Point getPoint() {
		return point;
	}

	public StrategyManager getStrategy() {
		return strategy;
	}

	public void setStrategy(StrategyManager strategy) {
		this.strategy = strategy;
	}

	public SaveLog getSaveLog() {
		return saveLog;
	}

	public void setSaveLog(SaveLog saveLog) {
		this.saveLog = saveLog;
	}

	public SavePainting getPainting() {
		return painting;
	}

	public void setPainting(SavePainting painting) {
		this.painting = painting;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}
}