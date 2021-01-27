package logger;

import commands.*;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;
import stack.CommandsStack;

public class CircleLogReader implements LogReader {
	private CmdAdd cmdAdd;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private DrawingModel model;
	private CmdModifyCircle cmdModifyCircle;
	private CommandsStack commandsStack;

	public CircleLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Circle circle = new Circle(point, Integer.parseInt(logLine[15]), false,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdAdd = new CmdAdd(model, circle);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Circle circle = new Circle(point, Integer.parseInt(logLine[15]), true,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdSelect = new CmdSelect(model, circle);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Circle circle = new Circle(point, Integer.parseInt(logLine[15]), true,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdDeselect = new CmdDeselect(model, circle);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	@Override
	public void modifyShapeFromLog(String[] logLine, Shape selectedShape) {
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
		cmdModifyCircle.execute();
		commandsStack.addCommand(cmdModifyCircle);
	}
}