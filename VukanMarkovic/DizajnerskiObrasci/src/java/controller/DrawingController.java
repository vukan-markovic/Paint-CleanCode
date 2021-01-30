package controller;

import javax.swing.*;
import commandsExecutors.*;
import dialogs.*;
import observer.*;
import shapes.*;
import frame.DrawingFrame;
import model.DrawingModel;
import stack.CommandsStack;
import java.awt.event.MouseEvent;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private OptionsController optionsController;
	private DrawingObserver observer;
	private PropertyManager manager;
	private DialogPoint dialogPoint;
	private DialogLine dialogLine;
	private DialogRectangle dialogRectangle;
	private DialogHexagon dialogHexagon;
	private DialogDonut dialogDonut;
	private DialogCircle dialogCircle;
	private PointCommandsExecutor pointCommandsExecutor;
	private RectangleCommandsExecutor rectangleCommandsExecutor;
	private HexagonCommandsExecutor hexagonCommandsExecutor;
	private DonutCommandsExecutor donutCommandsExecutor;
	private LineCommandsExecutor lineCommandsExecutor;
	private CircleCommandsExecutor circleCommandsExecutor;
	private RemoveCommandExecutor removeCommandExecutor;
	private SelectionCommandsExecutor selectionCommandsExecutor;
	private Point pointClick;

	public DrawingController(OptionsController optionsController) {
		this.model = optionsController.getModel();
		frame = optionsController.getFrame();
		this.commandsStack = optionsController.getCommandsStack();
		this.optionsController = optionsController;
		observer = new DrawingObserver();
		manager = new PropertyManager(frame);
		observer.addPropertyChangeListener(manager);
		dialogPoint = new DialogPoint();
		dialogLine = new DialogLine();
		dialogRectangle = new DialogRectangle();
		dialogHexagon = new DialogHexagon();
		dialogDonut = new DialogDonut();
		dialogCircle = new DialogCircle();
		pointCommandsExecutor = new PointCommandsExecutor(dialogPoint, optionsController);
		lineCommandsExecutor = new LineCommandsExecutor(dialogLine, optionsController);
		rectangleCommandsExecutor = new RectangleCommandsExecutor(dialogRectangle, optionsController);
		hexagonCommandsExecutor = new HexagonCommandsExecutor(dialogHexagon, optionsController);
		donutCommandsExecutor = new DonutCommandsExecutor(dialogDonut, optionsController);
		circleCommandsExecutor = new CircleCommandsExecutor(dialogCircle, optionsController);
		removeCommandExecutor = new RemoveCommandExecutor(model, frame, commandsStack);
		selectionCommandsExecutor = new SelectionCommandsExecutor(model, frame, commandsStack);
	}

	public void selectOrDeselectShapes(MouseEvent click) {
		for (int indexOfShape = model.getNumberOfShapes() - 1; indexOfShape >= 0; indexOfShape--) {
			Shape shape = model.getShapeByIndex(indexOfShape);

			if (shape.contains(click.getX(), click.getY()) && shape.isSelected()) {
				deselectShape(shape);
				break;
			} else if (shape.contains(click.getX(), click.getY()) && !shape.isSelected()) {
				selectionCommandsExecutor.selectShape(shape);
				optionsController.fireEventsForOptionsButtons();
				break;
			} else if (indexOfShape == 0)
				deselectShapes();
		}
	}

	private void deselectShapes() {
		for (int shapeIndex = 0; model.getSelectedShapes().size() > 0;) {
			Shape shape = model.getSelectedShapeByIndex(shapeIndex);
			deselectShape(shape);
		}
	}

	private void deselectShape(Shape shape) {
		selectionCommandsExecutor.deselectShape(shape);
		optionsController.fireEventsForOptionsButtons();
	}

	public void drawPoint(MouseEvent click) {
		pointCommandsExecutor.addShape(click.getX(), click.getY());
		optionsController.fireEventsForOptionsButtons();
		commandsStack.clearUnexecutedCommands();
	}

	public void drawLine(MouseEvent click) {
		lineCommandsExecutor.addShape(click.getX(), click.getY());
		optionsController.fireEventsForOptionsButtons();
		commandsStack.clearUnexecutedCommands();
	}

	public void drawRectangle(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getBorderColor());
		dialogRectangle.setCreateDialog(pointClick);

		if (dialogRectangle.isAccepted()) {
			rectangleCommandsExecutor.addShape();
			optionsController.fireEventsForOptionsButtons();
		}
		
		commandsStack.clearUnexecutedCommands();
	}

	public void drawCircle(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getBorderColor());
		dialogCircle.setCreateDialog(pointClick);

		if (dialogCircle.isAccepted()) {
			circleCommandsExecutor.addShape();
			optionsController.fireEventsForOptionsButtons();
		}
		
		commandsStack.clearUnexecutedCommands();
	}

	public void drawDonut(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getBorderColor());
		dialogDonut.setCreateDialog(pointClick);

		if (dialogDonut.isAccepted()) {
			donutCommandsExecutor.addShape();
			optionsController.fireEventsForOptionsButtons();
		}
		
		commandsStack.clearUnexecutedCommands();
	}

	public void drawHexagon(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getBorderColor());
		dialogHexagon.setCreateDialog(pointClick);

		if (dialogHexagon.isAccepted()) { 
			hexagonCommandsExecutor.addShape();
			optionsController.fireEventsForOptionsButtons();
		}
		
		commandsStack.clearUnexecutedCommands();
	}

	public void modifyShape() {
		Shape selectedShape = model.getFirstSelectedShape();

		if (selectedShape instanceof Point) {
			dialogPoint.setModifyDialog(selectedShape);

			if (dialogPoint.isAccepted())
				pointCommandsExecutor.modifyShape(selectedShape);
		} else if (selectedShape instanceof Line) {
			dialogLine.setModifyDialog(selectedShape);

			if (dialogLine.isAccepted())
				lineCommandsExecutor.modifyShape(selectedShape);
		} else if (selectedShape instanceof Rectangle) {
			dialogRectangle.setModifyDialog(selectedShape);

			if (dialogRectangle.isAccepted())
				rectangleCommandsExecutor.modifyShape(selectedShape);
		} else if (selectedShape instanceof Donut) {
			dialogDonut.setModifyDialog(selectedShape);

			if (dialogDonut.isAccepted())
				donutCommandsExecutor.modifyShape(selectedShape);
		} else if (selectedShape instanceof Circle) {
			dialogCircle.setModifyDialog(selectedShape);

			if (dialogCircle.isAccepted())
				circleCommandsExecutor.modifyShape(selectedShape);
		} else if (selectedShape instanceof HexagonAdapter) {
			dialogHexagon.setModifyDialog(selectedShape);

			if (dialogHexagon.isAccepted())
				hexagonCommandsExecutor.modifyShape(selectedShape);
		}

		commandsStack.clearUnexecutedCommands();
		frame.getView().repaint();
		optionsController.fireEventsForUndoAndRedoButtons();
	}

	public void removeShapesIfUserConfirm() {
		if (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete selected shape/shapes?",
				"Delete it?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			removeCommandExecutor.removeShapes();
			commandsStack.clearUnexecutedCommands();
			optionsController.fireEventsForOptionsButtons();
		}
	}

	public OptionsController getOptionsController() {
		return optionsController;
	}

	public DialogRectangle getDialogRectangle() {
		return dialogRectangle;
	}

	public DialogHexagon getDialogHexagon() {
		return dialogHexagon;
	}

	public DialogDonut getDialogDonut() {
		return dialogDonut;
	}

	public DialogCircle getDialogCircle() {
		return dialogCircle;
	}

	public PointCommandsExecutor getPointCommandsExecutor() {
		return pointCommandsExecutor;
	}

	public RectangleCommandsExecutor getRectangleCommandsExecutor() {
		return rectangleCommandsExecutor;
	}

	public HexagonCommandsExecutor getHexagonCommandsExecutor() {
		return hexagonCommandsExecutor;
	}

	public DonutCommandsExecutor getDonutCommandsExecutor() {
		return donutCommandsExecutor;
	}

	public LineCommandsExecutor getLineCommandsExecutor() {
		return lineCommandsExecutor;
	}

	public CircleCommandsExecutor getCircleCommandsExecutor() {
		return circleCommandsExecutor;
	}
}