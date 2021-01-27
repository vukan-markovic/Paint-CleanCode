package logger;

import commands.*;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;
import stack.CommandsStack;

public class LineLogReader implements LogReader {
	private CmdAdd cmdAdd;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private DrawingModel model;
	private CmdModifyLine cmdModifyLine;
	private CommandsStack commandsStack;

	public LineLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
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
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
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
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
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
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	@Override
	public void modifyShapeFromLog(String[] line, Shape selectedShape) {
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
		cmdModifyLine.execute();
	}
}