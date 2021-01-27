package logger;

import commands.*;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;
import stack.CommandsStack;

public class DonutLogReader implements LogReader {
	private CmdAdd cmdAdd;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private DrawingModel model;
	private CmdModifyDonut cmdModifyDonut;
	private CommandsStack commandsStack;

	public DonutLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Donut donut = new Donut(point, Integer.parseInt(logLine[15]), Integer.parseInt(logLine[27]), false,
				(Integer.parseInt(logLine[31]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[31]))),
				(Integer.parseInt(logLine[35]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[35]))));

		cmdAdd = new CmdAdd(model, donut);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Donut donut = new Donut(point, Integer.parseInt(logLine[15]), Integer.parseInt(logLine[27]), true,
				(Integer.parseInt(logLine[31]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[31]))),
				(Integer.parseInt(logLine[35]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[35]))));

		cmdSelect = new CmdSelect(model, donut);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		Donut donut = new Donut(point, Integer.parseInt(logLine[15]), Integer.parseInt(logLine[27]), true,
				(Integer.parseInt(logLine[31]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[31]))),
				(Integer.parseInt(logLine[35]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[35]))));

		cmdDeselect = new CmdDeselect(model, donut);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	@Override
	public void modifyShapeFromLog(String[] logLine, Shape selectedShape) {
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
		cmdModifyDonut.execute();
		commandsStack.addCommand(cmdModifyDonut);
	}
}
