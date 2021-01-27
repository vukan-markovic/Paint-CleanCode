package logger;

import commands.*;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;
import stack.CommandsStack;

public class RectangleLogReader implements LogReader {
	private CmdAdd cmdAdd;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private DrawingModel model;
	private CmdModifyRectangle cmdModifyRectangle;
	private CommandsStack commandsStack;

	public RectangleLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[7]), Integer.parseInt(logLine[10]), false,
				(Integer.parseInt(logLine[14]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[14]))));

		Rectangle rectangle = new Rectangle(point, Integer.parseInt(logLine[17]), Integer.parseInt(logLine[20]), false,
				(Integer.parseInt(logLine[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[24]))),
				(Integer.parseInt(logLine[28]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[28]))));

		cmdAdd = new CmdAdd(model, rectangle);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[7]), Integer.parseInt(logLine[10]), false,
				(Integer.parseInt(logLine[14]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[14]))));

		Rectangle rectangle = new Rectangle(point, Integer.parseInt(logLine[17]), Integer.parseInt(logLine[20]), true,
				(Integer.parseInt(logLine[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[24]))),
				(Integer.parseInt(logLine[28]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[28]))));

		cmdSelect = new CmdSelect(model, rectangle);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[7]), Integer.parseInt(logLine[10]), false,
				(Integer.parseInt(logLine[14]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[14]))));

		Rectangle rectangle = new Rectangle(point, Integer.parseInt(logLine[17]), Integer.parseInt(logLine[20]), true,
				(Integer.parseInt(logLine[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[24]))),
				(Integer.parseInt(logLine[28]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[28]))));

		cmdDeselect = new CmdDeselect(model, rectangle);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	@Override
	public void modifyShapeFromLog(String[] line, Shape selectedShape) {
		Rectangle oldState = (Rectangle) selectedShape;

		Point point = new Point(Integer.parseInt(line[36]), Integer.parseInt(line[39]), false,
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))));

		Rectangle newState = new Rectangle(point, Integer.parseInt(line[46]), Integer.parseInt(line[49]), true,
				(Integer.parseInt(line[53]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[53]))),
				(Integer.parseInt(line[57]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[57]))));

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(newState);
		cmdModifyRectangle = new CmdModifyRectangle(oldState, newState);
		cmdModifyRectangle.execute();
		commandsStack.addCommand(cmdModifyRectangle);
	}
}