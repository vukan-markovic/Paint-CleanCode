package controller;

import java.io.*;
import java.util.*;
import javax.swing.*;
import commands.*;
import dialogs.*;
import files.*;
import frame.DrawingFrame;
import hexagon.Hexagon;
import model.DrawingModel;
import observer.*;
import shapes.*;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private DrawingObserver observer;
	private PropertyManager manager;
	private Queue<String> commandsLog;
	private Stack<Command> executedCommands;
	private Stack<Command> unexecutedCommands;
	private StrategyManager strategy;
	private SaveLog saveLog;
	private SavePainting savePainting;
	private JFileChooser fileChooser;
	private String log;
	private Color innerColor;
	private Color outerColor;
	private Point startPoint;
	private Point point;
	private DialogRectangle dialogRectangle;
	private DialogCircle dialogCircle;
	private DialogDonut dialogDonut;
	private DialogHexagon dialogHexagon;
	private DialogPoint dialogPoint;
	private DialogLine dialogLine;
	private CmdAdd cmdAdd;
	private CmdRemove cmdRemove;
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

	public DrawingController() {
	}

	public void setController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		innerColor = Color.WHITE;
		outerColor = Color.BLACK;
		executedCommands = new Stack<Command>();
		unexecutedCommands = new Stack<Command>();
		observer = new DrawingObserver();
		commandsLog = new LinkedList<String>();
		manager = new PropertyManager(frame);
		observer.addPropertyChangeListener(manager);
	}

	public void executeCommand(Command command) {
		command.execute();
		executedCommands.push(command);
	}

	public void logCommand(String command) {
		frame.getCommandsListModel().addElement(command);
	}

	public void btnSelectClicked(MouseEvent click) {
		if (model.getShapes().size() != 0) {
			for (int indexOfShape = model.getShapes().size() - 1; indexOfShape >= 0; indexOfShape--) {
				Shape shape = model.getShapeByIndex(indexOfShape);

				if (shape.contains(click.getX(), click.getY()) && shape.isSelected()) {
					executeCommand(new CmdDeselect(model, shape));
					logCommand("Deselect - " + model.getShapeByIndex(indexOfShape).getClass().getSimpleName() + " "
							+ model.getShapeByIndex(indexOfShape).toString());
					fireEvents();
					break;
				} else if (shape.contains(click.getX(), click.getY()) && !shape.isSelected()) {
					executeCommand(new CmdSelect(model, shape));
					logCommand("Select - " + model.getShapeByIndex(indexOfShape).getClass().getSimpleName() + " "
							+ model.getShapeByIndex(indexOfShape).toString());
					fireEvents();
					break;
				} else if (indexOfShape == 0) {
					for (int shapeIndex = 0; model.getSelectedShapes().size() > 0;) {
						Shape deselected = model.getSelectedShapes().get(shapeIndex);
						executeCommand(new CmdDeselect(model, deselected));
						logCommand("Deselect - " + deselected.getClass().getSimpleName() + " " + deselected.toString());
						fireEvents();
					}
				}
			}
		}
	}

	public void btnPointClicked(MouseEvent click) {
		addPoint(click);
		clearUnexecutedCommands();
	}

	public void addPoint(MouseEvent click) {
		Point point = new Point(click.getX(), click.getY(), false, outerColor);
		cmdAdd = new CmdAdd(model, point);
		executeCommand(cmdAdd);
		logCommand("Add - " + point.getClass().getSimpleName() + " " + point);
	}

	public void clearUnexecutedCommands() {
		unexecutedCommands.clear();
	}

	public void addLine(MouseEvent click) {
		Point endPoint = new Point(click.getX(), click.getY(), false, outerColor);
		Line line = new Line(startPoint, endPoint, false, outerColor);
		cmdAdd = new CmdAdd(model, line);
		executeCommand(cmdAdd);
		logCommand("Add - " + line.getClass().getSimpleName() + " " + line);
	}

	public void btnLineClicked(MouseEvent click) {
		if (startPoint == null)
			startPoint = new Point(click.getX(), click.getY(), false, outerColor);
		else {
			addLine(click);
			clearUnexecutedCommands();
			startPoint = null;
		}
	}

	public void addRectangle(DialogRectangle dialogRectangle, Point point) {
		Rectangle rectangle = new Rectangle(point, Integer.parseInt(dialogRectangle.getheight().getText()),
				Integer.parseInt(dialogRectangle.getwidth().getText()), false, outerColor, innerColor);
		cmdAdd = new CmdAdd(model, rectangle);
		executeCommand(cmdAdd);
		clearUnexecutedCommands();
		logCommand("Add - " + rectangle.getClass().getSimpleName() + " " + rectangle);
	}

	public void createRectangleDialog(Point point) {
		dialogRectangle.getBtnOuterColor().setVisible(false);
		dialogRectangle.getBtnInnerColor().setVisible(false);
		dialogRectangle.getXcoordinate().setText(String.valueOf(point.getXcoordinate()));
		dialogRectangle.getYcoordinate().setText(String.valueOf(point.getYcoordinate()));
		dialogRectangle.getXcoordinate().setEditable(false);
		dialogRectangle.getYcoordinate().setEditable(false);
		dialogRectangle.setVisible(true);
	}

	public void btnRectangleClicked(MouseEvent click) {
		point = new Point(click.getX(), click.getY(), false, outerColor);
		dialogRectangle = new DialogRectangle();
		createRectangleDialog(point);

		if (dialogRectangle.isAccepted())
			addRectangle(dialogRectangle, point);
	}

	public void createCircleDialog(Point center) {
		dialogCircle.getBtnOuterColor().setVisible(false);
		dialogCircle.getBtnInnerColor().setVisible(false);
		dialogCircle.getXcoordinate().setText(String.valueOf(center.getXcoordinate()));
		dialogCircle.getYcoordinate().setText(String.valueOf(center.getYcoordinate()));
		dialogCircle.getXcoordinate().setEditable(false);
		dialogCircle.getYcoordinate().setEditable(false);
		dialogCircle.setVisible(true);
	}

	public void addCircle(DialogCircle dialogCircle, Point center) {
		Circle circle = new Circle(center, Integer.parseInt(dialogCircle.getRadius().getText()), false, outerColor,
				innerColor);
		cmdAdd = new CmdAdd(model, circle);
		executeCommand(cmdAdd);
		clearUnexecutedCommands();
		logCommand("Add - " + circle.getClass().getSimpleName() + " " + circle);
	}

	public void btnCircleClicked(MouseEvent click) {
		point = new Point(click.getX(), click.getY(), false, outerColor);
		dialogCircle = new DialogCircle();
		createCircleDialog(point);

		if (dialogCircle.isAccepted())
			addCircle(dialogCircle, point);
	}

	public void createDonutDialog(Point center) {
		dialogDonut.getBtnOuterColor().setVisible(false);
		dialogDonut.getBtnInnerColor().setVisible(false);
		dialogDonut.getXcoordinate().setText(String.valueOf(center.getXcoordinate()));
		dialogDonut.getYcoordinate().setText(String.valueOf(center.getYcoordinate()));
		dialogDonut.getXcoordinate().setEditable(false);
		dialogDonut.getYcoordinate().setEditable(false);
		dialogDonut.setVisible(true);
	}

	public void addDonut(DialogDonut dialogDonut, Point center) {
		Donut donut = new Donut(center, Integer.parseInt(dialogDonut.getRadius().getText()),
				Integer.parseInt(dialogDonut.getInnerRadius().getText()), false, outerColor, innerColor);

		cmdAdd = new CmdAdd(model, donut);
		executeCommand(cmdAdd);
		clearUnexecutedCommands();
		logCommand("Add - " + donut.getClass().getSimpleName() + " " + donut);
	}

	public void btnDonutClicked(MouseEvent click) {
		point = new Point(click.getX(), click.getY(), false, outerColor);
		dialogDonut = new DialogDonut();
		createDonutDialog(point);

		if (dialogDonut.isAccepted())
			addDonut(dialogDonut, point);
	}

	public void createHexagonDialog(Point center) {
		dialogHexagon.getBtnOuterColor().setVisible(false);
		dialogHexagon.getBtnInnerColor().setVisible(false);
		dialogHexagon.getXcoordinate().setText(String.valueOf(center.getXcoordinate()));
		dialogHexagon.getYcoordinate().setText(String.valueOf(center.getYcoordinate()));
		dialogHexagon.getXcoordinate().setEditable(false);
		dialogHexagon.getYcoordinate().setEditable(false);
		dialogHexagon.setVisible(true);
	}

	public void addHexagon(DialogHexagon dialogHexagon, Point center) {
		HexagonAdapter hexagon = new HexagonAdapter(new Hexagon(center.getXcoordinate(), center.getYcoordinate(),
				Integer.parseInt(dialogHexagon.getRadius().getText())), false, innerColor, outerColor);

		cmdAdd = new CmdAdd(model, hexagon);
		executeCommand(cmdAdd);
		clearUnexecutedCommands();
		logCommand("Add - " + hexagon.getClass().getSimpleName() + " " + hexagon);
	}

	public void btnHexagonClicked(MouseEvent click) {
		point = new Point(click.getX(), click.getY(), false, outerColor);
		dialogHexagon = new DialogHexagon();
		createHexagonDialog(point);

		if (dialogHexagon.isAccepted())
			addHexagon(dialogHexagon, point);
	}

	public void createPointDialog(Shape selectedShape) {
		dialogPoint.getXcoordinate().setText(String.valueOf(((Point) selectedShape).getXcoordinate()));
		dialogPoint.getYcoordinate().setText(String.valueOf(((Point) selectedShape).getYcoordinate()));
		dialogPoint.setOuterColor(((Point) selectedShape).getOuterColor());
		dialogPoint.getBtnOuterColor().setBackground(dialogPoint.getOuterColor());
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
		clearUnexecutedCommands();
	}

	public void createLineDialog(Shape selectedShape) {
		dialogLine.getXcoordinate().setText(String.valueOf(((Line) selectedShape).getStartPoint().getXcoordinate()));
		dialogLine.getYcoordinate().setText(String.valueOf(((Line) selectedShape).getStartPoint().getYcoordinate()));
		dialogLine.getXCoordinateOfEndPoint()
				.setText(String.valueOf(((Line) selectedShape).getEndPoint().getXcoordinate()));
		dialogLine.getYCoordinateOfEndPoint()
				.setText(String.valueOf(((Line) selectedShape).getEndPoint().getYcoordinate()));
		dialogLine.setOuterColor(((Line) selectedShape).getOuterColor());
		dialogLine.getBtnOuterColor().setBackground(dialogLine.getOuterColor());
		dialogLine.setVisible(true);
	}

	public void modifyLine(Shape selectedShape, DialogLine dialogLine) {
		Line oldState = (Line) selectedShape;

		Line newState = new Line(
				new Point(Integer.parseInt(dialogLine.getXcoordinate().getText()),
						Integer.parseInt(dialogLine.getYcoordinate().getText()), oldState.isSelected(),
						dialogLine.getOuterColor()),
				new Point(Integer.parseInt(dialogLine.getXCoordinateOfEndPoint().getText()),
						Integer.parseInt(dialogLine.getYCoordinateOfEndPoint().getText()), oldState.isSelected(),
						dialogLine.getOuterColor()),
				oldState.isSelected(), dialogLine.getOuterColor());

		logCommand("Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
		cmdModifyLine = new CmdModifyLine(oldState, (Line) newState);
		executeCommand(cmdModifyLine);
		clearUnexecutedCommands();
	}

	public void createRectangleModifyDialog(Shape selectedShape) {
		dialogRectangle.getXcoordinate()
				.setText(String.valueOf(((Rectangle) selectedShape).getUpperLeftPoint().getXcoordinate()));

		dialogRectangle.getYcoordinate()
				.setText(String.valueOf(((Rectangle) selectedShape).getUpperLeftPoint().getYcoordinate()));

		dialogRectangle.getheight().setText(String.valueOf(((Rectangle) selectedShape).getHeight()));
		dialogRectangle.getwidth().setText(String.valueOf(((Rectangle) selectedShape).getWidth()));
		dialogRectangle.setOuterColor(((Rectangle) selectedShape).getOuterColor());
		dialogRectangle.setInnerColor(((Rectangle) selectedShape).getInnerColor());
		dialogRectangle.getBtnOuterColor().setBackground(dialogRectangle.getOuterColor());
		dialogRectangle.getBtnInnerColor().setBackground(dialogRectangle.getInnerColor());
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
		clearUnexecutedCommands();
	}

	public void createDonutModifyDialog(Shape selectedShape) {
		dialogDonut.getXcoordinate().setText(String.valueOf(((Donut) selectedShape).getCenter().getXcoordinate()));
		dialogDonut.getYcoordinate().setText(String.valueOf(((Donut) selectedShape).getCenter().getYcoordinate()));
		dialogDonut.getRadius().setText(String.valueOf(((Donut) selectedShape).getRadius()));
		dialogDonut.getInnerRadius().setText(String.valueOf(((Donut) selectedShape).getInnerRadius()));
		dialogDonut.setOuterColor(((Donut) selectedShape).getOuterColor());
		dialogDonut.setInnerColor(((Donut) selectedShape).getInnerColor());
		dialogDonut.getBtnOuterColor().setBackground(dialogDonut.getOuterColor());
		dialogDonut.getBtnInnerColor().setBackground(dialogDonut.getInnerColor());
		dialogDonut.setVisible(true);
	}

	public void modifyDonut(Shape selectedShape, DialogDonut dialogDonut) {
		Donut oldState = (Donut) selectedShape;

		Donut newState = new Donut(
				new Point(Integer.parseInt(dialogDonut.getXcoordinate().getText()),
						Integer.parseInt(dialogDonut.getYcoordinate().getText()), oldState.isSelected(),
						dialogDonut.getOuterColor()),
				Integer.parseInt(dialogDonut.getRadius().getText()),
				Integer.parseInt(dialogDonut.getInnerRadius().getText()), oldState.isSelected(),
				dialogDonut.getOuterColor(), dialogDonut.getInnerColor());

		logCommand("Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
		cmdModifyDonut = new CmdModifyDonut(oldState, (Donut) newState);
		executeCommand(cmdModifyDonut);
		clearUnexecutedCommands();
	}

	public void createCircleModifyDialog(Shape selectedShape) {
		dialogCircle.getXcoordinate().setText(String.valueOf(((Circle) selectedShape).getCenter().getXcoordinate()));
		dialogCircle.getYcoordinate().setText(String.valueOf(((Circle) selectedShape).getCenter().getYcoordinate()));
		dialogCircle.getRadius().setText(String.valueOf(((Circle) selectedShape).getRadius()));
		dialogCircle.setOuterColor(((Circle) selectedShape).getOuterColor());
		dialogCircle.setInnerColor(((Circle) selectedShape).getInnerColor());
		dialogCircle.getBtnOuterColor().setBackground(dialogCircle.getOuterColor());
		dialogCircle.getBtnInnerColor().setBackground(dialogCircle.getInnerColor());
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
		cmdModifyCircle = new CmdModifyCircle(oldState, newState);
		executeCommand(cmdModifyCircle);
		clearUnexecutedCommands();
	}

	public void createHexagonModifyDialog(Shape selectedShape) {
		dialogHexagon.getXcoordinate().setText(String.valueOf(((HexagonAdapter) selectedShape).getXcoordinate()));
		dialogHexagon.getYcoordinate().setText(String.valueOf(((HexagonAdapter) selectedShape).getYcoordinate()));
		dialogHexagon.getRadius().setText(String.valueOf(((HexagonAdapter) selectedShape).getRadius()));
		dialogHexagon.setOuterColor(((HexagonAdapter) selectedShape).getInnerColor());
		dialogHexagon.setInnerColor(((HexagonAdapter) selectedShape).getInnerColor());
		dialogHexagon.getBtnOuterColor().setBackground(dialogHexagon.getOuterColor());
		dialogHexagon.getBtnInnerColor().setBackground(dialogHexagon.getInnerColor());
		dialogHexagon.setVisible(true);
	}

	public void modifyHexagon(Shape selectedShape, DialogHexagon dialogHexagon) {
		HexagonAdapter oldState = (HexagonAdapter) selectedShape;

		HexagonAdapter newState = new HexagonAdapter(
				new Hexagon(Integer.parseInt(dialogHexagon.getXcoordinate().getText()),
						Integer.parseInt(dialogHexagon.getYcoordinate().getText()),
						Integer.parseInt(dialogHexagon.getRadius().getText())),
				oldState.isSelected(), dialogHexagon.getInnerColor(), dialogHexagon.getOuterColor());

		logCommand("Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
		cmdModifyHexagon = new CmdModifyHexagon(oldState, (HexagonAdapter) newState);
		executeCommand(cmdModifyHexagon);
		clearUnexecutedCommands();
	}

	public void btnModifyClicked() {
		Shape selectedShape = model.getFirstSelectedShape();

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

		frame.getView().repaint();
		fireEventsForUndoAndRedoButtons();
	}

	public void btnRemoveClicked() {
		if (model.getSelectedShapes().size() == 0)
			return;

		if (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete selected shape/shapes?",
				"Delete it?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			removeShapes();
			logCommand("Deleted");
			frame.getView().repaint();
			fireEvents();
		}
	}

	public void removeShapes() {
		for (int indexOfShape = 0; indexOfShape < model.getSelectedShapes().size(); indexOfShape++) {
			cmdRemove = new CmdRemove(model, model.getSelectedShapes().get(indexOfShape));
			executeCommand(cmdRemove);
			clearUnexecutedCommands();
		}

		model.getSelectedShapes().clear();
		;
	}

	public void btnUndoClicked() {
		if (executedCommands.isEmpty())
			return;

		undoCommand();
		logCommand("Undo");
		frame.getView().repaint();
		fireEventsForUndoAndRedoButtons();
		fireEvents();
	}

	public void undoCommand() {
		executedCommands.peek().unexecute();
		unexecutedCommands.push(executedCommands.peek());
		executedCommands.pop();
	}

	public void btnRedoClicked() {
		if (unexecutedCommands.isEmpty())
			return;

		redoCommand();
		logCommand("Redo");
		frame.getView().repaint();
		fireEventsForUndoAndRedoButtons();
		fireEvents();
	}

	public void redoCommand() {
		unexecutedCommands.peek().execute();
		executedCommands.push(unexecutedCommands.peek());
		unexecutedCommands.pop();
	}

	public void chooseOuterColor() {
		outerColor = JColorChooser.showDialog(frame, "Choose a color!", outerColor);

		if (outerColor != null)
			frame.getRightToolbar().getBtnOuterColor().setBackground(outerColor);
	}

	public void chooseInnerColor() {
		innerColor = JColorChooser.showDialog(frame, "Choose a color!", innerColor);

		if (innerColor != null)
			frame.getRightToolbar().getBtnInnerColor().setBackground(innerColor);
	}

	public void btnToBackClicked() {
		if (model.getSelectedShapes().size() == 1) {
			Shape shape = model.getFirstSelectedShape();
			cmdToBack = new CmdToBack(model, shape);
			executeCommand(cmdToBack);
			clearUnexecutedCommands();
			logCommand("ToBack - " + shape.getClass().getSimpleName() + " " + shape.toString());
			frame.getView().repaint();
		}
	}

	public void btnToFrontClicked() {
		if (model.getSelectedShapes().size() == 1) {
			Shape shape = model.getFirstSelectedShape();
			cmdToFront = new CmdToFront(model, shape);
			executeCommand(cmdToFront);
			clearUnexecutedCommands();
			logCommand("ToFront - " + shape.getClass().getSimpleName() + " " + shape.toString());
			frame.getView().repaint();
		}
	}

	public void btnSendToBackClicked() {
		if (model.getSelectedShapes().size() == 1) {
			Shape shape = model.getFirstSelectedShape();
			cmdSendToBack = new CmdSendToBack(model, shape);
			executeCommand(cmdSendToBack);
			clearUnexecutedCommands();
			logCommand("SendToBack - " + shape.getClass().getSimpleName() + " " + shape.toString());
			frame.getView().repaint();
		}
	}

	public void btnBringToFrontClicked() {
		if (model.getSelectedShapes().size() == 1) {
			Shape shape = model.getFirstSelectedShape();
			cmdBringToFront = new CmdBringToFront(model, shape);
			executeCommand(cmdBringToFront);
			clearUnexecutedCommands();
			logCommand("BringToFront - " + shape.getClass().getSimpleName() + " " + shape.toString());
			frame.getView().repaint();
		}
	}

	public void fireEvents() {
		observer.setBtnDeleteEnabled(model.getSelectedShapes().size() > 0);
		observer.setBtnModifyEnabled(model.getSelectedShapes().size() == 1);
		observer.setBtnToBackEnabled(model.getSelectedShapes().size() == 1);
		observer.setBtnToFrontEnabled(model.getSelectedShapes().size() == 1);
		observer.setBtnSendToBackEnabled(model.getSelectedShapes().size() == 1);
		observer.setBtnBringToFrontEnabled(model.getSelectedShapes().size() == 1);
	}

	public void fireEventsForUndoAndRedoButtons() {
		observer.setBtnUndoEnabled(executedCommands.size() > 0);
		observer.setBtnRedoEnabled(unexecutedCommands.size() > 0);
	}

	public void saveDrawing() {
		savePainting.setShapes(model.getShapes());
		strategy.setStrategy(savePainting);
		strategy.save(fileChooser.getSelectedFile().getAbsolutePath() + "\\"
				+ frame.getRightToolbar().getFileName().getText() + ".bin");
	}

	public void saveLog() {
		log = "";

		for (int i = 0; i < frame.getCommandsListModel().getSize(); i++)
			log = log + frame.getCommandsListModel().get(i) + "\n";

		saveLog.setCommandsLog(log);
		strategy.setStrategy(saveLog);
		strategy.save(fileChooser.getSelectedFile().getAbsolutePath() + "\\"
				+ frame.getRightToolbar().getFileName().getText() + ".txt");
	}

	public String getLog() {
		return log;
	}

	public int showFileChooser(String title, int mode) {
		fileChooser.setDialogTitle(title);
		fileChooser.setFileSelectionMode(mode);
		return fileChooser.showSaveDialog(frame);
	}

	public void save() {
		if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(frame, "You can't save because you haven't draw anythig yet!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		fileChooser = new JFileChooser();

		if (showFileChooser("Specify the location where you want to save your drawing",
				JFileChooser.DIRECTORIES_ONLY) == JFileChooser.APPROVE_OPTION) {

			saveLog = new SaveLog();
			strategy = new StrategyManager();
			saveLog();

			savePainting = new SavePainting();
			strategy = new StrategyManager();
			saveDrawing();
		}
	}

	public void loadLog() {
		frame.getRightToolbar().getBtnNext().setEnabled(true);
		commandsLog.clear();
		fileChooser = new JFileChooser();

		if (showFileChooser("Specify the log you want to open", JFileChooser.FILES_ONLY) == JFileChooser.APPROVE_OPTION)
			addLog();
	}

	public void addLog() {
		try (BufferedReader reader = new BufferedReader(
				new FileReader(fileChooser.getSelectedFile().getAbsolutePath()))) {
			String logLine = "";

			while ((logLine = reader.readLine()) != null)
				commandsLog.add(logLine);
		} catch (IOException exception) {
			commandsLog.clear();
			System.out.println(exception.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void addPainting() {
		try (ObjectInputStream inputStream = new ObjectInputStream(
				new FileInputStream(fileChooser.getSelectedFile().getAbsoluteFile()))) {
			model.setShapes((ArrayList<Shape>) inputStream.readObject());
			frame.getView().repaint();
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		} catch (ClassNotFoundException exception) {
			model.getShapes().clear();
			System.out.println(exception.getMessage());
		}
	}

	public void loadPainting() {
		fileChooser = new JFileChooser();
		showFileChooser("Specify the painting you want to open", JFileChooser.FILES_ONLY);

		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
			addPainting();
	}

	public void addPointFromLog(String[] line) {
		Point point = new Point(Integer.parseInt(line[4]), Integer.parseInt(line[7]), false,
				(Integer.parseInt(line[11]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[11]))));

		cmdAdd = new CmdAdd(model, point);
		executeCommand(cmdAdd);
	}

	public void addLineFromLog(String[] logLine) {
		Point startPoint = new Point(Integer.parseInt(logLine[6]), Integer.parseInt(logLine[9]), false,
				(Integer.parseInt(logLine[13]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[13]))));

		Point endPoint = new Point(Integer.parseInt(logLine[18]), Integer.parseInt(logLine[21]), false,
				(Integer.parseInt(logLine[25]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[25]))));

		Line lineShape = new Line(startPoint, endPoint, false,
				(Integer.parseInt(logLine[29]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[29]))));

		cmdAdd = new CmdAdd(model, lineShape);
		executeCommand(cmdAdd);
	}

	public void addRectangleFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[7]), Integer.parseInt(logLine[10]), false,
				(Integer.parseInt(logLine[14]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[14]))));

		Rectangle rectangle = new Rectangle(point, Integer.parseInt(logLine[17]), Integer.parseInt(logLine[20]), false,
				(Integer.parseInt(logLine[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[24]))),
				(Integer.parseInt(logLine[28]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[28]))));

		cmdAdd = new CmdAdd(model, rectangle);
		executeCommand(cmdAdd);
	}

	public void addCircleFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Circle circle = new Circle(point, Integer.parseInt(logLine[15]), false,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdAdd = new CmdAdd(model, circle);
		executeCommand(cmdAdd);
	}

	public void addDonutFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Donut donut = new Donut(point, Integer.parseInt(logLine[15]), Integer.parseInt(logLine[27]), false,
				(Integer.parseInt(logLine[31]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[31]))),
				(Integer.parseInt(logLine[35]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[35]))));

		cmdAdd = new CmdAdd(model, donut);
		executeCommand(cmdAdd);
	}

	public void addHexagonFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		HexagonAdapter hexagon = new HexagonAdapter(
				new Hexagon(point.getXcoordinate(), point.getYcoordinate(), Integer.parseInt(logLine[15])), false,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdAdd = new CmdAdd(model, hexagon);
		executeCommand(cmdAdd);
	}

	public void selectPointFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[4]), Integer.parseInt(logLine[7]), false,
				(Integer.parseInt(logLine[11]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[11]))));

		cmdSelect = new CmdSelect(model, point);
		executeCommand(cmdSelect);
	}

	public void selectLineFromLog(String[] logLine) {
		Point startPoint = new Point(Integer.parseInt(logLine[6]), Integer.parseInt(logLine[9]), false,
				(Integer.parseInt(logLine[13]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[13]))));

		Point endPoint = new Point(Integer.parseInt(logLine[18]), Integer.parseInt(logLine[21]), false,
				(Integer.parseInt(logLine[25]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[25]))));

		Line lineShape = new Line(startPoint, endPoint, true,
				(Integer.parseInt(logLine[29]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[29]))));

		cmdSelect = new CmdSelect(model, lineShape);
		executeCommand(cmdSelect);
	}

	public void selectRectangleFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[7]), Integer.parseInt(logLine[10]), false,
				(Integer.parseInt(logLine[14]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[14]))));

		Rectangle rectangle = new Rectangle(point, Integer.parseInt(logLine[17]), Integer.parseInt(logLine[20]), true,
				(Integer.parseInt(logLine[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[24]))),
				(Integer.parseInt(logLine[28]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[28]))));

		cmdSelect = new CmdSelect(model, rectangle);
		executeCommand(cmdSelect);
	}

	public void selectCircleFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Circle circle = new Circle(point, Integer.parseInt(logLine[15]), true,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdSelect = new CmdSelect(model, circle);
		executeCommand(cmdSelect);
	}

	public void selectDonutFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Donut donut = new Donut(point, Integer.parseInt(logLine[15]), Integer.parseInt(logLine[27]), true,
				(Integer.parseInt(logLine[31]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[31]))),
				(Integer.parseInt(logLine[35]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[35]))));

		cmdSelect = new CmdSelect(model, donut);
		executeCommand(cmdSelect);
	}

	public void selectHexagonFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		HexagonAdapter hexagon = new HexagonAdapter(
				new Hexagon(point.getXcoordinate(), point.getYcoordinate(), Integer.parseInt(logLine[15])), false,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdSelect = new CmdSelect(model, hexagon);
		executeCommand(cmdSelect);
	}

	public void deselectPointFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[4]), Integer.parseInt(logLine[7]), true,
				(Integer.parseInt(logLine[11]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[11]))));

		cmdDeselect = new CmdDeselect(model, point);
		executeCommand(cmdDeselect);
	}

	public void deselectLineFromLog(String[] logLine) {
		Point startPoint = new Point(Integer.parseInt(logLine[6]), Integer.parseInt(logLine[9]), false,
				(Integer.parseInt(logLine[13]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[13]))));

		Point endPoint = new Point(Integer.parseInt(logLine[18]), Integer.parseInt(logLine[21]), false,
				(Integer.parseInt(logLine[25]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[25]))));

		Line lineShape = new Line(startPoint, endPoint, true,
				(Integer.parseInt(logLine[29]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[29]))));

		cmdDeselect = new CmdDeselect(model, lineShape);
		executeCommand(cmdDeselect);
	}

	public void deselectRectangleFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[7]), Integer.parseInt(logLine[10]), false,
				(Integer.parseInt(logLine[14]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[14]))));

		Rectangle rectangle = new Rectangle(point, Integer.parseInt(logLine[17]), Integer.parseInt(logLine[20]), true,
				(Integer.parseInt(logLine[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[24]))),
				(Integer.parseInt(logLine[28]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[28]))));

		cmdDeselect = new CmdDeselect(model, rectangle);
		executeCommand(cmdDeselect);
	}

	public void deselectCircleFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Circle circle = new Circle(point, Integer.parseInt(logLine[15]), true,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdDeselect = new CmdDeselect(model, circle);
		executeCommand(cmdDeselect);
	}

	public void deselectDonutFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Donut donut = new Donut(point, Integer.parseInt(logLine[15]), Integer.parseInt(logLine[27]), true,
				(Integer.parseInt(logLine[31]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[31]))),
				(Integer.parseInt(logLine[35]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[35]))));

		cmdDeselect = new CmdDeselect(model, donut);
		executeCommand(cmdDeselect);
	}

	public void deselectHexagonFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		HexagonAdapter hexagon = new HexagonAdapter(
				new Hexagon(point.getXcoordinate(), point.getYcoordinate(), Integer.parseInt(logLine[15])), true,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdDeselect = new CmdDeselect(model, hexagon);
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

	public void modifyCircleFromLog(String[] logLine, Shape selectedShape) {
		Circle oldState = (Circle) selectedShape;

		Point point = new Point(Integer.parseInt(logLine[29]), Integer.parseInt(logLine[32]), false,
				(Integer.parseInt(logLine[36]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[36]))));

		Circle newState = new Circle(point, Integer.parseInt(logLine[39]), true,
				(Integer.parseInt(logLine[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[43]))),
				(Integer.parseInt(logLine[47]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[47]))));

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(newState);

		cmdModifyCircle = new CmdModifyCircle(oldState, newState);
		executeCommand(cmdModifyCircle);
	}

	public void modifyDonutFromLog(String[] logLine, Shape selectedShape) {
		Donut oldState = (Donut) selectedShape;

		Point point = new Point(Integer.parseInt(logLine[41]), Integer.parseInt(logLine[44]), false,
				(Integer.parseInt(logLine[48]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[48]))));

		Donut newState = new Donut(point, Integer.parseInt(logLine[51]), Integer.parseInt(logLine[63]), true,
				(Integer.parseInt(logLine[67]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[67]))),
				(Integer.parseInt(logLine[71]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[71]))));

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(newState);

		cmdModifyDonut = new CmdModifyDonut(oldState, newState);
		executeCommand(cmdModifyDonut);
	}

	public void modifyHexagonFromLog(String[] logLine, Shape selectedShape) {
		HexagonAdapter oldState = (HexagonAdapter) selectedShape;

		Point point = new Point(Integer.parseInt(logLine[29]), Integer.parseInt(logLine[32]), false,
				(Integer.parseInt(logLine[36]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[36]))));

		HexagonAdapter newState = new HexagonAdapter(
				new Hexagon(point.getXcoordinate(), point.getYcoordinate(), Integer.parseInt(logLine[39])), true,
				(Integer.parseInt(logLine[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[43]))),
				(Integer.parseInt(logLine[47]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[47]))));

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(newState);

		cmdModifyHexagon = new CmdModifyHexagon(oldState, newState);
		executeCommand(cmdModifyHexagon);
	}

	public void executeCommandFromLog() {
		String[] logLine = commandsLog.peek().split(" ");

		if (logLine[0].equals("Add")) {
			if (logLine[2].equals("Point"))
				addPointFromLog(logLine);
			else if (logLine[2].equals("Line"))
				addLineFromLog(logLine);
			else if (logLine[2].equals("Rectangle"))
				addRectangleFromLog(logLine);
			else if (logLine[2].equals("Circle"))
				addCircleFromLog(logLine);
			else if (logLine[2].equals("Donut"))
				addDonutFromLog(logLine);
			else if (logLine[2].equals("HexagonAdapter"))
				addHexagonFromLog(logLine);
		} else if (logLine[0].equals("Select")) {
			if (logLine[2].equals("Point"))
				selectPointFromLog(logLine);
			else if (logLine[2].equals("Line"))
				selectLineFromLog(logLine);
			else if (logLine[2].equals("Rectangle"))
				selectRectangleFromLog(logLine);
			else if (logLine[2].equals("Circle"))
				selectCircleFromLog(logLine);
			else if (logLine[2].equals("Donut"))
				selectDonutFromLog(logLine);
			else if (logLine[2].equals("HexagonAdapter"))
				selectHexagonFromLog(logLine);
		} else if (logLine[0].equals("Deselect")) {
			if (logLine[2].equals("Point"))
				deselectPointFromLog(logLine);
			else if (logLine[2].equals("Line"))
				deselectLineFromLog(logLine);
			else if (logLine[2].equals("Rectangle"))
				deselectRectangleFromLog(logLine);
			else if (logLine[2].equals("Circle"))
				deselectCircleFromLog(logLine);
			else if (logLine[2].equals("Donut"))
				deselectDonutFromLog(logLine);
			else if (logLine[2].equals("HexagonAdapter"))
				deselectHexagonFromLog(logLine);
		} else if (logLine[0].equals("Modify")) {
			Shape selectedShape = model.getFirstSelectedShape();

			if (logLine[2].equals("Point"))
				modifyPointFromLog(logLine, selectedShape);
			else if (logLine[2].equals("Line"))
				modifyLineFromLog(logLine, selectedShape);
			else if (logLine[2].equals("Rectangle"))
				modifyRectangleFromLog(logLine, selectedShape);
			else if (logLine[2].equals("Circle"))
				modifyCircleFromLog(logLine, selectedShape);
			else if (logLine[2].equals("Donut"))
				modifyDonutFromLog(logLine, selectedShape);
			else if (logLine[2].equals("HexagonAdapter"))
				modifyHexagonFromLog(logLine, selectedShape);
		} else if (logLine[0].equals("Deleted"))
			removeShapes();
		else if (logLine[0].equals("Undo"))
			undoCommand();
		else if (logLine[0].equals("Redo"))
			redoCommand();
		else if (logLine[0].equals("ToBack"))
			btnToBackClicked();
		else if (logLine[0].equals("ToFront"))
			btnToFrontClicked();
		else if (logLine[0].equals("BringToFront"))
			btnBringToFrontClicked();
		else if (logLine[0].equals("SendToBack"))
			btnSendToBackClicked();

		if (getCommandsLog().size() == 1)
			frame.getRightToolbar().getBtnNext().setEnabled(false);

		fireEvents();
		fireEventsForUndoAndRedoButtons();
		frame.getView().repaint();
		frame.getCommandsListModel().addElement(getCommandsLog().poll());
	}

	public Queue<String> getCommandsLog() {
		return commandsLog;
	}

	public void setCommandsLog(Queue<String> commandsLog) {
		this.commandsLog = commandsLog;
	}

	public Stack<Command> getExecutedCommands() {
		return executedCommands;
	}

	public void setExecutedCommands(Stack<Command> executedCommands) {
		this.executedCommands = executedCommands;
	}

	public Stack<Command> getUnexecutedCommands() {
		return unexecutedCommands;
	}

	public void setUnexecutedCommands(Stack<Command> unexecutedCommands) {
		this.unexecutedCommands = unexecutedCommands;
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
		return innerColor;
	}

	public Color getOuterColor() {
		return outerColor;
	}

	public DialogRectangle getDialogRectangle() {
		return dialogRectangle;
	}

	public void setDialogRectangle(DialogRectangle dialogRectangle) {
		this.dialogRectangle = dialogRectangle;
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

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
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

	public SavePainting getSavePainting() {
		return savePainting;
	}

	public void setSavePainting(SavePainting savePainting) {
		this.savePainting = savePainting;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}
}