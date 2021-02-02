package controller;

import javax.swing.*;
import commandsExecutors.*;
import dialogs.*;
import shapes.*;
import commandsHandler.CommandsHandler;
import frame.DrawingFrame;
import model.DrawingModel;
import java.awt.event.MouseEvent;

public class DrawingController {
	private OptionsController optionsController;
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
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
		this.optionsController = optionsController;
		model = optionsController.getModel();
		frame = optionsController.getFrame();
		commandsHandler = optionsController.getCommandsHandler();
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
		removeCommandExecutor = new RemoveCommandExecutor(model, frame, commandsHandler);
		selectionCommandsExecutor = new SelectionCommandsExecutor(model, frame, commandsHandler);
	}

	public void selectOrDeselectShape(MouseEvent click) {
		for (int indexOfShape = model.getNumberOfShapes() - 1; indexOfShape >= 0; indexOfShape--) {
			Shape shape = model.getShapeByIndex(indexOfShape);

			if (shape.contains(click.getX(), click.getY()) && shape.isSelected()) {
				deselectShape(shape);
				break;
			} else if (shape.contains(click.getX(), click.getY()) && !shape.isSelected()) {
				selectShape(shape);
				break;
			}
		}
	}

	private void deselectShape(Shape shape) {
		selectionCommandsExecutor.deselectShape(shape);
		optionsController.fireEventsForOptionsButtons();
	}

	private void selectShape(Shape shape) {
		selectionCommandsExecutor.selectShape(shape);
		optionsController.fireEventsForOptionsButtons();
	}

	public void drawPoint(MouseEvent click) {
		pointCommandsExecutor.addShape(click.getX(), click.getY());
		updateStateAfterDrawOrRemove();
	}

	public void drawLine(MouseEvent click) {
		lineCommandsExecutor.addShape(click.getX(), click.getY());
		updateStateAfterDrawOrRemove();
	}

	public void drawRectangleIfAccepted(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getBorderColor());
		dialogRectangle.setCreateDialog(pointClick);

		if (dialogRectangle.isAccepted()) {
			rectangleCommandsExecutor.addShape();
			updateStateAfterDrawOrRemove();
		}
	}

	public void drawCircleIfAccepted(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getBorderColor());
		dialogCircle.setCreateDialog(pointClick);

		if (dialogCircle.isAccepted()) {
			circleCommandsExecutor.addShape();
			updateStateAfterDrawOrRemove();
		}
	}

	public void drawDonutIfAccepted(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getBorderColor());
		dialogDonut.setCreateDialog(pointClick);

		if (dialogDonut.isAccepted()) {
			donutCommandsExecutor.addShape();
			updateStateAfterDrawOrRemove();
		}
	}

	public void drawHexagonIfAccepted(MouseEvent click) {
		pointClick = new Point(click.getX(), click.getY(), false, optionsController.getBorderColor());
		dialogHexagon.setCreateDialog(pointClick);

		if (dialogHexagon.isAccepted()) {
			hexagonCommandsExecutor.addShape();
			updateStateAfterDrawOrRemove();
		}
	}

	public void modifyShapeIfAccepted() {
		Shape selectedShape = model.getFirstSelectedShape();

		if (selectedShape instanceof Point)
			modifyPointIfAccepted(selectedShape);
		else if (selectedShape instanceof Line)
			modifyLineIfAccepted(selectedShape);
		else if (selectedShape instanceof Rectangle)
			modifyRectangleIfAccepted(selectedShape);
		else if (selectedShape instanceof Donut)
			modifyDonutIfAccepted(selectedShape);
		else if (selectedShape instanceof Circle)
			modifyCircleIfAccepted(selectedShape);
		else if (selectedShape instanceof HexagonAdapter)
			modifyHexagonIfAccepted(selectedShape);
	}

	private void modifyHexagonIfAccepted(Shape selectedShape) {
		dialogHexagon.setModifyDialog(selectedShape);

		if (dialogHexagon.isAccepted()) {
			hexagonCommandsExecutor.modifyShape(selectedShape);
			updateStateAfterModify();
		}
	}

	private void modifyCircleIfAccepted(Shape selectedShape) {
		dialogCircle.setModifyDialog(selectedShape);

		if (dialogCircle.isAccepted()) {
			circleCommandsExecutor.modifyShape(selectedShape);
			updateStateAfterModify();
		}
	}

	private void modifyDonutIfAccepted(Shape selectedShape) {
		dialogDonut.setModifyDialog(selectedShape);

		if (dialogDonut.isAccepted()) {
			donutCommandsExecutor.modifyShape(selectedShape);
			updateStateAfterModify();
		}
	}

	private void modifyRectangleIfAccepted(Shape selectedShape) {
		dialogRectangle.setModifyDialog(selectedShape);

		if (dialogRectangle.isAccepted()) {
			rectangleCommandsExecutor.modifyShape(selectedShape);
			updateStateAfterModify();
		}
	}

	private void modifyLineIfAccepted(Shape selectedShape) {
		dialogLine.setModifyDialog(selectedShape);

		if (dialogLine.isAccepted()) {
			lineCommandsExecutor.modifyShape(selectedShape);
			updateStateAfterModify();
		}
	}

	private void modifyPointIfAccepted(Shape selectedShape) {
		dialogPoint.setModifyDialog(selectedShape);

		if (dialogPoint.isAccepted()) {
			pointCommandsExecutor.modifyShape(selectedShape);
			updateStateAfterModify();
		}
	}

	private void updateStateAfterModify() {
		commandsHandler.clearUnexecutedCommands();
		optionsController.fireEventsForUndoAndRedoButtons();
		frame.getView().repaint();
	}

	public void removeShapesIfUserConfirm() {
		if (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete selected shape/shapes?",
				"Delete it?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			removeCommandExecutor.removeShapes();
			updateStateAfterDrawOrRemove();
		}
	}

	private void updateStateAfterDrawOrRemove() {
		optionsController.fireEventsForOptionsButtons();
		commandsHandler.clearUnexecutedCommands();
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