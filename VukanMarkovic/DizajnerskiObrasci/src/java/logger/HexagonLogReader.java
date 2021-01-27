package logger;

import commands.*;
import shapes.*;
import hexagon.Hexagon;
import java.awt.Color;
import model.DrawingModel;
import stack.CommandsStack;

public class HexagonLogReader implements LogReader {
	private CmdAdd cmdAdd;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private DrawingModel model;
	private CmdModifyHexagon cmdModifyHexagon;
	private CommandsStack commandsStack;

	public HexagonLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		HexagonAdapter hexagon = new HexagonAdapter(
				new Hexagon(point.getXcoordinate(), point.getYcoordinate(), Integer.parseInt(logLine[15])), false,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdAdd = new CmdAdd(model, hexagon);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		HexagonAdapter hexagon = new HexagonAdapter(
				new Hexagon(point.getXcoordinate(), point.getYcoordinate(), Integer.parseInt(logLine[15])), false,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdSelect = new CmdSelect(model, hexagon);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
		commandsStack.addCommand(cmdAdd);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		Point point = new Point(Integer.parseInt(logLine[5]), Integer.parseInt(logLine[8]), false,
				(Integer.parseInt(logLine[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[12]))));

		HexagonAdapter hexagon = new HexagonAdapter(
				new Hexagon(point.getXcoordinate(), point.getYcoordinate(), Integer.parseInt(logLine[15])), true,
				(Integer.parseInt(logLine[19]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(logLine[19]))),
				(Integer.parseInt(logLine[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(logLine[23]))));

		cmdDeselect = new CmdDeselect(model, hexagon);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	@Override
	public void modifyShapeFromLog(String[] logLine, Shape selectedShape) {
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
		cmdModifyHexagon.execute();
	}
}