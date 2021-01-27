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
	private SelectCommandExecutor selectCommandExecutor;
	private Point pointClick;

	public DrawingController(DrawingModel model, DrawingFrame frame, CommandsStack commandsStack,
			OptionsController optionsController) {
		this.model = model;
		this.frame = frame;
		this.commandsStack = commandsStack;
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

		pointCommandsExecutor = new PointCommandsExecutor(model, frame, commandsStack, dialogPoint, optionsController);
		lineCommandsExecutor = new LineCommandsExecutor(model, frame, commandsStack, dialogLine, optionsController);

		rectangleCommandsExecutor = new RectangleCommandsExecutor(model, frame, commandsStack, dialogRectangle,
				optionsController);

		hexagonCommandsExecutor = new HexagonCommandsExecutor(model, frame, commandsStack, dialogHexagon,
				optionsController);

		donutCommandsExecutor = new DonutCommandsExecutor(model, frame, commandsStack, dialogDonut, optionsController);

		circleCommandsExecutor = new CircleCommandsExecutor(model, frame, commandsStack, dialogCircle,
				optionsController);

		removeCommandExecutor = new RemoveCommandExecutor(model, frame, commandsStack);
		selectCommandExecutor = new SelectCommandExecutor(model, frame, commandsStack);
	}

	public void selectShape(MouseEvent click) {
		if (model.getNumberOfShapes() != 0) {
			for (int indexOfShape = model.getNumberOfShapes() - 1; indexOfShape >= 0; indexOfShape--) {
				Shape shape = model.getShapeByIndex(indexOfShape);

				if (shape.contains(click.getX(), click.getY()) && shape.isSelected()) {
					deselectShape(shape);
					break;
				} else if (shape.contains(click.getX(), click.getY()) && !shape.isSelected()) {
					selectCommandExecutor.selectShape(shape);
					fireEvents();
					break;
				} else if (indexOfShape == 0)
					deselectShapes();
			}
		}
	}

	private void deselectShapes() {
		for (int shapeIndex = 0; model.getSelectedShapes().size() > 0;) {
			Shape shape = model.getSelectedShapeByIndex(shapeIndex);
			deselectShape(shape);
		}
	}

	private void deselectShape(Shape shape) {
		selectCommandExecutor.deselectShape(shape);
		fireEvents();
	}

	public void drawPoint(MouseEvent click) {
		pointCommandsExecutor.addPoint(click);
		commandsStack.clearUnexecutedCommands();
	}

	public void drawLine(MouseEvent click) {
		lineCommandsExecutor.addLineOrCreateStartPoint(click);
		commandsStack.clearUnexecutedCommands();
	}

	public void drawRectangle(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getOuterColor());
		dialogRectangle.setCreateDialog(pointClick);

		if (dialogRectangle.isAccepted())
			rectangleCommandsExecutor.addShape();

		commandsStack.clearUnexecutedCommands();
	}

	public void drawCircle(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getOuterColor());
		dialogCircle.setCreateDialog(pointClick);

		if (dialogCircle.isAccepted())
			circleCommandsExecutor.addShape();

		commandsStack.clearUnexecutedCommands();
	}

	public void drawDonut(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getOuterColor());
		dialogDonut.setCreateDialog(pointClick);

		if (dialogDonut.isAccepted())
			donutCommandsExecutor.addShape();

		commandsStack.clearUnexecutedCommands();
	}

	public void drawHexagon(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getOuterColor());
		dialogHexagon.setCreateDialog(pointClick);

		if (dialogHexagon.isAccepted())
			hexagonCommandsExecutor.addShape();

		commandsStack.clearUnexecutedCommands();
	}

	public void modifyShape() {
		Shape selectedShape = model.getFirstSelectedShape();

		if (model.getSelectedShapes().size() != 1)
			return;

		if (selectedShape instanceof Point) {
			dialogPoint.setModifyDialog(selectedShape);

			if (dialogPoint.isAccepted())
				pointCommandsExecutor.modifyPoint(selectedShape);
		} else if (selectedShape instanceof Line) {
			dialogLine.setModifyDialog(selectedShape);

			if (dialogLine.isAccepted())
				lineCommandsExecutor.modifyLine(selectedShape);
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
		fireEventsForUndoAndRedoButtons();
	}

	public void removeShapes() {
		if (model.getSelectedShapes().size() == 0)
			return;

		if (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete selected shape/shapes?",
				"Delete it?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			removeCommandExecutor.removeShapes();
			commandsStack.clearUnexecutedCommands();
			fireEvents();
		}
	}

	public void fireEventsForUndoAndRedoButtons() {
		optionsController.fireEventsForUndoAndRedoButtons();
	}

	public void fireEvents() {
		optionsController.fireEventsForButtons();
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

	public Point getPoint() {
		return pointClick;
	}

	public void setPoint(Point point) {
		this.pointClick = point;
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

	public DialogHexagon getDialogHexagon() {
		return dialogHexagon;
	}

	public void setDialogHexagon(DialogHexagon dialogHexagon) {
		this.dialogHexagon = dialogHexagon;
	}
}