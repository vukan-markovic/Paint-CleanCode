package logger;

import commands.*;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;
import stack.CommandsStack;

public class PointLogReader implements LogReader {
	private CmdAdd cmdAdd;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private DrawingModel model;
	private CmdModifyPoint cmdModifyPoint;
	private CommandsStack commandsStack;

	public PointLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] line) {
		Point point = new Point(Integer.parseInt(line[4]), Integer.parseInt(line[7]), false,
				(Integer.parseInt(line[11]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[11]))));

		cmdAdd = new CmdAdd(model, point);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[4]), Integer.parseInt(logLine[7]), false,
				(Integer.parseInt(logLine[11]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[11]))));

		cmdSelect = new CmdSelect(model, point);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[4]), Integer.parseInt(logLine[7]), true,
				(Integer.parseInt(logLine[11]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[11]))));

		cmdDeselect = new CmdDeselect(model, point);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	@Override
	public void modifyShapeFromLog(String[] line, Shape selectedShape) {
		Point oldState = (Point) selectedShape;

		Point newState = new Point(Integer.parseInt(line[17]), Integer.parseInt(line[20]), true,
				(Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[24]))));

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(newState);

		cmdModifyPoint = new CmdModifyPoint(oldState, newState);
		cmdModifyPoint.execute();
	}
}