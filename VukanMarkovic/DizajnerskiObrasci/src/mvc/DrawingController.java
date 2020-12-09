package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import command.CmdAdd;
import command.CmdBringToFront;
import command.CmdDeselect;
import command.CmdModifyCircle;
import command.CmdModifyDonut;
import command.CmdModifyHexagon;
import command.CmdModifyLine;
import command.CmdModifyPoint;
import command.CmdModifyRectangle;
import command.CmdRemove;
import command.CmdSelect;
import command.CmdSendToBack;
import command.CmdToBack;
import command.CmdToFront;
import command.Command;
import geometry.Shape;
import observer.Observer;
import observer.PropertyManager;
import strategy.SaveLog;
import strategy.SavePainting;
import strategy.StrategyManager;
import dialogs.DialogCircle;
import dialogs.DialogDonut;
import dialogs.DialogHexagon;
import dialogs.DialogLine;
import dialogs.DialogPoint;
import dialogs.DialogRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

public class DrawingController {
	
	DrawingModel model;
	DrawingFrame main_frame;
	private Color colInner = new Color(255, 235, 205);
	private Color colOuter = new Color(250, 128, 114);
	private Point startPoint;
	private Stack<Command> commandsNormal = new Stack<Command>();
	private Stack<Command> commandsReverse = new Stack<Command>();
	Observer observer = new Observer();
	PropertyManager manager;
	private Queue<String> loggComm = new LinkedList<String>();
	
	
	



	public Queue<String> getLoggComm() {
		return loggComm;
	}


	public void setLoggComm(Queue<String> loggComm) {
		this.loggComm = loggComm;
	}


	public Stack<Command> getCommandsNormal() {
		return commandsNormal;
	}


	public void setCommandsNormal(Stack<Command> commandsNormal) {
		this.commandsNormal = commandsNormal;
	}


	public Stack<Command> getCommandsReverse() {
		return commandsReverse;
	}


	public void setCommandsReverse(Stack<Command> commandsReverse) {
		this.commandsReverse = commandsReverse;
	}


	public DrawingController(DrawingModel model, DrawingFrame main_frame) {
		super();
		this.model = model;
		this.main_frame = main_frame;
		manager = new PropertyManager(main_frame);
		observer.addPropertyChangeListener(manager);
	}
	
	public void deselectAll () {
		for( Shape shape : model.getShapes()) {
			Command deselectAll = new CmdDeselect(shape, model);
			deselectAll.execute();
			commandsNormal.push(deselectAll);
		}
	}


	public void mouseClicked(MouseEvent e) {
		
		
		if(main_frame.getTglbtnSelect().isSelected() == true)
		{
			
			if (model.getShapes().size() != 0) {

				for (int i = model.getShapes().size() - 1; i >= 0; i--) {
					Shape s = model.get(i);

					if (s.contains(e.getX(), e.getY()) && s.isSelected()) {
						
						Command deselect = new CmdDeselect(model.get(i), model);
						deselect.execute();
						commandsNormal.push(deselect);
						fireEvents();
						System.out.println(model.getSelectedShapes().size());
						main_frame.getlModel().addElement("Deselect - " + model.get(i).getClass().getSimpleName() + " " + model.get(i).toString());
						break;
						
					} else if (s.contains(e.getX(), e.getY()) && !s.isSelected()) {
						
						Command select = new CmdSelect(model.get(i), model);
						select.execute();
						commandsNormal.push(select);
						fireEvents();
						System.out.println(model.getSelectedShapes().size());
						main_frame.getlModel().addElement("Select - " + model.get(i).getClass().getSimpleName() + " " + model.get(i).toString());
						//System.out.println(model.getSelectedShapes().size());
						break;
						
					} else if (i == 0) {
						
						for (int j = 0; model.getSelectedShapes().size() > 0;) {
							
							Shape deselected = model.getSelectedShapes().get(j);
							Command deselect = new CmdDeselect(deselected, model);
							deselect.execute();
							commandsNormal.push(deselect);
							main_frame.getlModel().addElement("Deselect - " + deselected.getClass().getSimpleName() + " " + deselected.toString() );
							
							
							fireEvents();
							
						}
						
						//main_frame.getlModel().addElement("DeselectAll");

					}

				}

			}
			
		}
		
		else if(main_frame.getTglbtnPoint().isSelected() == true) {
			
			Point p = new Point(e.getX(),e.getY(), false, colOuter);	
			
				CmdAdd addPointComm = new CmdAdd(model, p);
				addPointComm.execute();
				commandsReverse = new Stack<Command>();
				commandsNormal.push(addPointComm);
				main_frame.getlModel().addElement("Add - " + p.getClass().getSimpleName() + " " + p);
				
		}
		
		else if (main_frame.getTglbtnLine().isSelected() == true) {
			
			if(startPoint == null)
			{
				startPoint = new Point(e.getX(),e.getY(), false, colOuter);
			}
			else
			{
				Point endPoint = new Point(e.getX(),e.getY(), false, colOuter);
				Line l = new Line(startPoint, endPoint, false, colOuter);
				
					CmdAdd addLineComm = new CmdAdd(model, l);
					addLineComm.execute();
					commandsReverse = new Stack<Command>();
					commandsNormal.push(addLineComm);
					main_frame.getlModel().addElement("Add - " + l.getClass().getSimpleName() + " " + l);
				
					startPoint = null;
					
			}
			
		}
		
		else if (main_frame.getTglbtnRectangle().isSelected() == true) {
			
			try {
				Point p = new Point(e.getX(),e.getY(), false, colOuter);
				DialogRectangle dialogR = new DialogRectangle();
				dialogR.getBtnSetBorderColor().setVisible(false);
				dialogR.getBtnSetFillColor().setVisible(false);
				dialogR.getxCoord().setText(String.valueOf(p.getX()));
				dialogR.getyCoord().setText(String.valueOf(p.getY()));
				dialogR.getxCoord().setEditable(false);
				dialogR.getyCoord().setEditable(false);
				dialogR.setVisible(true);
				
				if(dialogR.isAccepted())
				{
					int height = Integer.parseInt(dialogR.getheight().getText());
					int width = Integer.parseInt(dialogR.getwidth().getText());
					
					Rectangle rect = new Rectangle(p, height, width, false, colOuter, colInner);
				
					CmdAdd addRectComm = new CmdAdd(model, rect);
					addRectComm.execute();
					commandsReverse = new Stack<Command>();
					commandsNormal.push(addRectComm);
					main_frame.getlModel().addElement("Add - " + rect.getClass().getSimpleName() + " " + rect);
					
				}
			
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Fill all the fields!" , "Error", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
		else if (main_frame.getTglbtnCircle().isSelected() == true) {
			try {
				Point center = new Point(e.getX(), e.getY(), false, colOuter);
				DialogCircle dialogC = new DialogCircle();
				dialogC.getBtnSetOuterColor().setVisible(false);
				dialogC.getBtnSetInnerColor().setVisible(false);
				dialogC.getxCoord().setText(String.valueOf(center.getX()));
				dialogC.getyCoord().setText(String.valueOf(center.getY()));
				dialogC.getxCoord().setEditable(false);
				dialogC.getyCoord().setEditable(false);
				dialogC.setVisible(true);
				
				if(dialogC.isAccepted())
				{
					int radius = Integer.parseInt(dialogC.getRadius().getText());
					Circle circle = new Circle(center, radius, false, colOuter, colInner);

					CmdAdd addCircleComm = new CmdAdd(model, circle);
					addCircleComm.execute();
					commandsReverse = new Stack<Command>();
					commandsNormal.push(addCircleComm);
					main_frame.getlModel().addElement("Add - " + circle.getClass().getSimpleName() + " " + circle);
					
				}
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Fill all the fields!" , "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		else if (main_frame.getTglbtnDonut().isSelected() == true) {
			
			try {
				Point center = new Point(e.getX(), e.getY(), false, colOuter);
				DialogDonut dialogD = new DialogDonut();
				dialogD.getBtnSetOuterColor().setVisible(false);
				dialogD.getBtnSetInnerColor().setVisible(false);
				dialogD.getxCoord().setText(String.valueOf(center.getX()));
				dialogD.getyCoord().setText(String.valueOf(center.getY()));
				dialogD.getxCoord().setEditable(false);
				dialogD.getyCoord().setEditable(false);
				dialogD.setVisible(true);
				
				if(dialogD.isAccepted())
				{
					int radius = Integer.parseInt(dialogD.getRadius().getText());
					int smallRadius = Integer.parseInt(dialogD.getSmallRadius().getText());
					Donut donut = new Donut(center, radius, smallRadius, false, colOuter, colInner);
					
					CmdAdd addDonutComm = new CmdAdd(model, donut);
					addDonutComm.execute();
					commandsReverse = new Stack<Command>();
					commandsNormal.push(addDonutComm);
					main_frame.getlModel().addElement("Add - " + donut.getClass().getSimpleName() + " " + donut);
				}
			
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Fill all the fields!" , "Error", JOptionPane.WARNING_MESSAGE);
			}
			
		} 
		
		else if (main_frame.getTglbtnHexagon().isSelected() == true) {
			
			try {
			
				Point center = new Point(e.getX(), e.getY(), false, colOuter);
				DialogHexagon dialogH = new DialogHexagon();
				dialogH.getBtnSetOuterColor().setVisible(false);
				dialogH.getBtnSetInnerColor().setVisible(false);
				dialogH.getxCoord().setText(String.valueOf(center.getX()));
				dialogH.getyCoord().setText(String.valueOf(center.getY()));
				dialogH.getxCoord().setEditable(false);
				dialogH.getyCoord().setEditable(false);
				dialogH.setVisible(true);
				
				if(dialogH.isAccepted())
				{
					int radius = Integer.parseInt(dialogH.getRadius().getText());
					HexagonAdapter hexagon = new HexagonAdapter(center.getX(), center.getY(), radius, colOuter, colInner);
					
					CmdAdd addHexagonComm = new CmdAdd(model, hexagon);
					addHexagonComm.execute();
					commandsReverse = new Stack<Command>();
					commandsNormal.push(addHexagonComm);
					main_frame.getlModel().addElement("Add - " + hexagon.getClass().getSimpleName() + " " + hexagon);
				}
			
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Fill all the fields!" , "Error", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
		main_frame.getView().repaint();
		fireUndoRedo();
		
	}
	
	public void Modify() {
		Shape selected = model.getOneSelectedShape();
		Shape newState;
		
		if(model.getSelectedShapes().size() != 1)
			return;
		
		if (selected instanceof Point)
		{
			DialogPoint dialogP = new DialogPoint();
			dialogP.getxCoord().setText(String.valueOf(((Point) selected).getX()));
			dialogP.getyCoord().setText(String.valueOf(((Point) selected).getY()));
			dialogP.setOuterColor(((Point)selected).getBorder_Color());
			dialogP.getBtnSetOuterColor().setBackground(dialogP.getOuterColor());
			dialogP.setVisible(true);
			
			if(dialogP.isAccepted())
			{
				
				Point oldState = (Point) selected;
				newState = new Point(Integer.parseInt(dialogP.getxCoord().getText()), 
						Integer.parseInt(dialogP.getyCoord().getText()), oldState.isSelected(), dialogP.getOuterColor());
				
				
				main_frame.getlModel().addElement("Modify - " + newState.getClass().getSimpleName() 
						+ " from " + oldState + " to "  + " " + newState);
				CmdModifyPoint pointM = new CmdModifyPoint(oldState, (Point)newState);
				pointM.execute();
				commandsReverse = new Stack<Command>();
				commandsNormal.push(pointM);
				
				
			}
			
			main_frame.getView().repaint();
		}
		else if(selected instanceof Line)
		{
			DialogLine dialogL = new DialogLine();
			dialogL.getX1Coord().setText(String.valueOf(((Line) selected).getStartPoint().getX()));
			dialogL.getY1Coord().setText(String.valueOf(((Line) selected).getStartPoint().getY()));
			dialogL.getX2Coord().setText(String.valueOf(((Line) selected).getEndPoint().getX()));
			dialogL.getY2Coord().setText(String.valueOf(((Line) selected).getEndPoint().getY()));
			dialogL.setOuterColor(((Line) selected).getBorder_Color());
			dialogL.getBtnSetOuterColor().setBackground(dialogL.getOuterColor());
			dialogL.setVisible(true);
			
			if(dialogL.isAccepted())
			{
				Line oldState = (Line) selected;
				Point newStartPoint = new Point(Integer.parseInt(dialogL.getX1Coord().getText()), Integer.parseInt(dialogL.getY1Coord().getText()), oldState.isSelected(), dialogL.getOuterColor());
				Point newEndPoint = new Point(Integer.parseInt(dialogL.getX2Coord().getText()), Integer.parseInt(dialogL.getY2Coord().getText()), oldState.isSelected(), dialogL.getOuterColor());
				newState = new Line(newStartPoint, newEndPoint, oldState.isSelected(), dialogL.getOuterColor());
				
				main_frame.getlModel().addElement("Modify - " + newState.getClass().getSimpleName() 
						+ " from " + oldState + " to "  + " " + newState);
				
				CmdModifyLine lineM = new CmdModifyLine(oldState, (Line)newState);
				lineM.execute();
				commandsReverse = new Stack<Command>();
				commandsNormal.push(lineM);
				
			}
			
			main_frame.getView().repaint();
			
			
			
		}
		else if (selected instanceof Rectangle)
		{
			try {
				DialogRectangle dialogR = new DialogRectangle();
				dialogR.getxCoord().setText(String.valueOf(((Rectangle) selected).getUpperLeftPoint().getX()));
				dialogR.getyCoord().setText(String.valueOf(((Rectangle) selected).getUpperLeftPoint().getY()));
				dialogR.getheight().setText(String.valueOf(((Rectangle) selected).getHeight()));
				dialogR.getwidth().setText(String.valueOf(((Rectangle) selected).getWidth()));
				dialogR.setOuterColor(((Rectangle) selected).getBorder_Color());
				dialogR.setInnerColor(((Rectangle) selected).getFill_Color());
				dialogR.getBtnSetBorderColor().setBackground(dialogR.getOuterColor());
				dialogR.getBtnSetFillColor().setBackground(dialogR.getInnerColor());
				dialogR.setVisible(true);
				
				if(dialogR.isAccepted())
				{
					Rectangle oldState = (Rectangle) selected;
					Point newUpperLeftPoint = new Point(Integer.parseInt(dialogR.getxCoord().getText()), Integer.parseInt(dialogR.getyCoord().getText()), oldState.isSelected(), dialogR.getOuterColor());
					int newHeight = Integer.parseInt(dialogR.getheight().getText());
					int newWidth = Integer.parseInt(dialogR.getwidth().getText());
					newState = new Rectangle(newUpperLeftPoint, newHeight, newWidth, oldState.isSelected(), dialogR.getOuterColor(), dialogR.getInnerColor());
					
					main_frame.getlModel().addElement("Modify - " + newState.getClass().getSimpleName() 
							+ " from " + oldState + " to "  + " " + newState);
					
					CmdModifyRectangle rectangleM = new CmdModifyRectangle(oldState, (Rectangle)newState);
					rectangleM.execute();
					commandsReverse = new Stack<Command>();
					commandsNormal.push(rectangleM);
					
				}
				
				main_frame.getView().repaint();
			
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog(new JFrame(), "Fill all the fields!" , "Error", JOptionPane.WARNING_MESSAGE);
		}
			
		}
		
		else if (selected instanceof Donut)
		{
			try {
				DialogDonut dialogD = new DialogDonut();
				dialogD.getxCoord().setText(String.valueOf(((Donut) selected).getCenter().getX()));
				dialogD.getyCoord().setText(String.valueOf(((Donut) selected).getCenter().getY()));
				dialogD.getRadius().setText(String.valueOf(((Donut) selected).getRadius()));
				dialogD.getSmallRadius().setText(String.valueOf(((Donut) selected).getInnerRadius()));
				dialogD.setOuterColor(((Donut)selected).getBorder_Color());
				dialogD.setInnerColor(((Donut)selected).getFill_Color());
				dialogD.getBtnSetOuterColor().setBackground(dialogD.getOuterColor());
				dialogD.getBtnSetInnerColor().setBackground(dialogD.getInnerColor());
				dialogD.setVisible(true);
				
				if(dialogD.isAccepted())
				{
					Donut oldState = (Donut) selected;
					Point newCenter = new Point(Integer.parseInt(dialogD.getxCoord().getText()),Integer.parseInt(dialogD.getyCoord().getText()), oldState.isSelected(), dialogD.getOuterColor());
					int newRadius = Integer.parseInt(dialogD.getRadius().getText());
					int newSmallRadius = Integer.parseInt(dialogD.getSmallRadius().getText());
					newState = new Donut(newCenter, newRadius, newSmallRadius, oldState.isSelected(), dialogD.getOuterColor(), dialogD.getInnerColor());
					
					main_frame.getlModel().addElement("Modify - " + newState.getClass().getSimpleName() 
							+ " from " + oldState + " to "  + " " + newState);
					
					CmdModifyDonut donutM = new CmdModifyDonut(oldState, (Donut)newState);
					donutM.execute();
					commandsReverse = new Stack<Command>();
					commandsNormal.push(donutM);

					
				}
				main_frame.getView().repaint();
			
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Fill all the fields!" , "Error", JOptionPane.WARNING_MESSAGE);
			}
		
		}
		
		else if (selected instanceof Circle)
		{
			try {
				DialogCircle dialogC = new DialogCircle();
				dialogC.getxCoord().setText(String.valueOf(((Circle) selected).getCenter().getX()));
				dialogC.getyCoord().setText(String.valueOf(((Circle) selected).getCenter().getY()));
				dialogC.getRadius().setText(String.valueOf(((Circle) selected).getRadius()));
				dialogC.setOuterColor(((Circle)selected).getBorder_Color());
				dialogC.setInnerColor(((Circle)selected).getFill_Color());
				dialogC.getBtnSetOuterColor().setBackground(dialogC.getOuterColor());
				dialogC.getBtnSetInnerColor().setBackground(dialogC.getInnerColor());
				dialogC.setVisible(true);
				
				
				if(dialogC.isAccepted())
				{
					Circle oldState = (Circle) selected;
					Point newCenter = new Point(Integer.parseInt(dialogC.getxCoord().getText()),Integer.parseInt(dialogC.getyCoord().getText()), oldState.isSelected(), dialogC.getOuterColor());
					int newRadius = Integer.parseInt(dialogC.getRadius().getText());
					newState = new Circle(newCenter, newRadius, oldState.isSelected(), dialogC.getOuterColor(), dialogC.getInnerColor());
					
					main_frame.getlModel().addElement("Modify - " + newState.getClass().getSimpleName() 
							+ " from " + oldState + " to "  + " " + newState);
					
					CmdModifyCircle circleM = new CmdModifyCircle(oldState, (Circle)newState);
					circleM.execute();
					commandsReverse = new Stack<Command>();
					commandsNormal.push(circleM);

					
				}
				main_frame.getView().repaint();
				
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Fill all the fields!" , "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		else if(selected instanceof HexagonAdapter)
		{
			try {
				
				DialogHexagon dialogH = new DialogHexagon();
				dialogH.getxCoord().setText(String.valueOf(((HexagonAdapter) selected).getX()));
				dialogH.getyCoord().setText(String.valueOf(((HexagonAdapter) selected).getY()));
				dialogH.getRadius().setText(String.valueOf(((HexagonAdapter) selected).getR()));
				dialogH.setOuterColor(((HexagonAdapter)selected).getBorder_Color());
				dialogH.setInnerColor(((HexagonAdapter)selected).getInnerColor());
				dialogH.getBtnSetOuterColor().setBackground(dialogH.getOuterColor());
				dialogH.getBtnSetInnerColor().setBackground(dialogH.getInnerColor());
				dialogH.setVisible(true);
				
				if(dialogH.isAccepted())
				{
					HexagonAdapter oldState = (HexagonAdapter) selected;
					
					newState = new HexagonAdapter(Integer.parseInt(dialogH.getxCoord().getText()),
							Integer.parseInt(dialogH.getyCoord().getText()),
							Integer.parseInt(dialogH.getRadius().getText()), dialogH.getOuterColor(), dialogH.getInnerColor(),
							oldState.isSelected());
					
					main_frame.getlModel().addElement("Modify - " + newState.getClass().getSimpleName() 
							+ " from " + oldState + " to "  + " " + newState);
					
					CmdModifyHexagon hexagonM = new CmdModifyHexagon(oldState, (HexagonAdapter)newState);
					hexagonM.execute();
					commandsReverse = new Stack<Command>();
					commandsNormal.push(hexagonM);

				}
				
				main_frame.getView().repaint();
				
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Fill all the fields!" , "Error", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
		fireUndoRedo();
		
		
		
		
	}
	
	
	
	public void Delete() {
		
		if(model.getSelectedShapes().size() == 0)
			return;
		
		int questionDialog = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete selected shape/shapes?", "Delete it?", JOptionPane.YES_NO_OPTION);
		
		if(questionDialog == JOptionPane.YES_OPTION)
		{
			
			deleteForLogs();
			main_frame.getlModel().addElement("Deleted");
			main_frame.getView().repaint();
			fireEvents();
		}
		
		
		
	}
	
	public void deleteForLogs() {
		
		for (int i = 0; i < model.getSelectedShapes().size(); i++) {
			
			CmdRemove cmd = new CmdRemove(model, model.getSelectedShapes().get(i));
			cmd.execute();
			commandsReverse = new Stack<Command>();
			commandsNormal.push(cmd);
			//main_frame.getlModel().addElement("Deleted - " + model.getSelectedShapes().get(i).getClass().getSimpleName() 
			//		+ " " + model.getSelectedShapes().get(i).toString());
			
		}
		model.setSelectedShapes(new ArrayList<Shape>());
		
	}
	
	public void undo() {
		
		if(commandsNormal.isEmpty())
			return;
		undoForLog();
		main_frame.getlModel().addElement("Undo");
		main_frame.getView().repaint();
		fireUndoRedo();
		fireEvents();
		
	}
	
	public void undoForLog() {
		
		commandsNormal.peek().unexecute();
		commandsReverse.push(commandsNormal.peek());
		commandsNormal.pop();
	}
	
	public void redo() {
		
		if(commandsReverse.isEmpty())
			return;
		redoForLog();
		main_frame.getlModel().addElement("Redo");
		main_frame.getView().repaint();
		fireUndoRedo();
		fireEvents();
	}
	
	public void redoForLog() {
		
		System.out.println(commandsReverse.peek());
		commandsReverse.peek().execute();
		commandsNormal.push(commandsReverse.peek());
		commandsReverse.pop();
		
	}
	
	public void outerColor() {
		
		colOuter = JColorChooser.showDialog(null, "Choose a color!", colOuter);
		
		if(colOuter != null) {
			main_frame.getBtnOuterCol().setBackground(colOuter);
		}
	}
	
	public void innerColor() {
		
		colInner = JColorChooser.showDialog(null, "Choose a color!", colInner);
				
		if(colInner != null) {
			main_frame.getBtnInnerCol().setBackground(colInner);
		}
	}
	
	public void toBack() {
		
		if(model.getSelectedShapes().size() == 1) {
			
			Shape shape = model.getOneSelectedShape();
			CmdToBack comm = new CmdToBack(model, shape);
			comm.execute();
			commandsReverse = new Stack<Command>();
			commandsNormal.push(comm);
			main_frame.getlModel().addElement("ToBack - " + shape.getClass().getSimpleName() + " " + shape.toString());
			main_frame.getView().repaint();
			
		}
		
	}
	
	public void toFront() {
		
		if(model.getSelectedShapes().size() == 1) {
			
			Shape shape = model.getOneSelectedShape();
			CmdToFront comm = new CmdToFront(model, shape);
			comm.execute();
			commandsReverse = new Stack<Command>();
			commandsNormal.push(comm);
			main_frame.getlModel().addElement("ToFront - " + shape.getClass().getSimpleName() + " " + shape.toString());
			main_frame.getView().repaint();
			
		}
		
	}
	
	public void sendToBack() {
		
		if(model.getSelectedShapes().size() == 1) {
			
			Shape shape = model.getOneSelectedShape();
			CmdSendToBack comm = new CmdSendToBack(model, shape);
			comm.execute();
			commandsReverse = new Stack<Command>();
			commandsNormal.push(comm);
			main_frame.getlModel().addElement("SendToBack - " + shape.getClass().getSimpleName() + " " + shape.toString());
			main_frame.getView().repaint();
			
		}
		
	}
	
	public void bringToFront() {
		
		if(model.getSelectedShapes().size() == 1) {
			
			Shape shape = model.getOneSelectedShape();
			CmdBringToFront comm = new CmdBringToFront(model, shape);
			comm.execute();
			commandsReverse = new Stack<Command>();
			commandsNormal.push(comm);
			main_frame.getlModel().addElement("BringToFront - " + shape.getClass().getSimpleName() + " " + shape.toString());
			main_frame.getView().repaint();
			
		}

	}
	
	public void fireEvents() {
		
		observer.setBtnDeleteEnable(model.getSelectedShapes().size() > 0);
		observer.setBtnModifyEnable(model.getSelectedShapes().size() == 1);
		observer.setBtnToBackEnable(model.getSelectedShapes().size() == 1);
		observer.setBtnToFrontEnable(model.getSelectedShapes().size() == 1);
		observer.setBtnSendToBackEnable(model.getSelectedShapes().size() == 1);
		observer.setBtnBringToFrontEnable(model.getSelectedShapes().size() == 1);
		
	}
	
	public void fireUndoRedo() {
		
		observer.setBtnUndoEnable(commandsNormal.size() > 0);
		observer.setBtnRedoEnable(commandsReverse.size() > 0);
		
	}
	
	
	public void save() {
		
		if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "You can't save because you haven't draw anythig yet!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JFileChooser fileCh = new JFileChooser();
		fileCh.setDialogTitle("Specify the location where you want to save your drawing");
		fileCh.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		//System.out.println("Nije ispisao");
		
		if(fileCh.showSaveDialog(main_frame) == JFileChooser.APPROVE_OPTION) {
			
			StrategyManager strategy = new StrategyManager();
			SaveLog saveLog = new SaveLog();
			SavePainting painting = new SavePainting();
			
			
			String log = "";
			
			for (int i = 0; i < main_frame.getlModel().getSize(); i++) {
				
				log = log + main_frame.getlModel().get(i) + "\n";
				
			}
			
			saveLog.setLog(log);
			strategy.setStrategy(saveLog);
			strategy.save(fileCh.getSelectedFile().getAbsolutePath() + "\\" + main_frame.getFileName().getText() + ".txt");
			
			painting.setShapes(model.getShapes());
			strategy.setStrategy(painting);
			strategy.save(fileCh.getSelectedFile().getAbsolutePath() + "\\" + main_frame.getFileName().getText() + ".bin");
			System.out.println("Ispisao");
			
		}
		
		
		
	}
	
	public void loadLog() {
		
		main_frame.getBtnNext().setEnabled(true);
		setLoggComm(new LinkedList<String>());
		JFileChooser fileCh = new JFileChooser();
		fileCh.setDialogTitle("Specify the log you want to open");
		fileCh.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(fileCh.showOpenDialog(main_frame) == JFileChooser.APPROVE_OPTION) {
			
			try {
				
				BufferedReader buffer = new BufferedReader(new FileReader(fileCh.getSelectedFile().getAbsolutePath()));
				String text = "";
				
				while((text = buffer.readLine()) != null) {
					getLoggComm().add(text);
				}
				
			} catch (Exception ex) {
				
				System.out.println(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadPainting() {
		
		JFileChooser fileCh = new JFileChooser();
		fileCh.setDialogTitle("Specify the painting you want to open");
		fileCh.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(fileCh.showOpenDialog(main_frame) == JFileChooser.APPROVE_OPTION) {
			
			try {
				
				FileInputStream fis = new FileInputStream(fileCh.getSelectedFile().getAbsoluteFile());
				ObjectInputStream ois = new ObjectInputStream(fis);
				model.setShapes((List<Shape>)ois.readObject());
				main_frame.getView().repaint();
				
			} catch (Exception ex) {
				
				System.out.println(ex.getMessage());
			}
		}
		
	}
	
	public void nextComm() {
		
		String[] line = getLoggComm().peek().split(" ");
		
		if(line[0].equals("Add")) {
			
			if(line[2].equals("Point")) {
				
				Point p = new Point(Integer.parseInt(line[4]), Integer.parseInt(line[7]), false, (Integer.parseInt(line[11]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[11]))));
				Command addPoint = new CmdAdd(model, p);
				addPoint.execute();
				commandsNormal.add(addPoint);
				
			}
			else if (line[2].equals("Line")) {
				
				Point p1 = new Point(Integer.parseInt(line[6]), Integer.parseInt(line[9]), false, (Integer.parseInt(line[13]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[13]))));
				Point p2 = new Point(Integer.parseInt(line[18]), Integer.parseInt(line[21]), false, (Integer.parseInt(line[25]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[25]))));
				
				Line l = new Line(p1, p2, false, (Integer.parseInt(line[29]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[29]))));
				
				Command addLine = new CmdAdd(model, l);
				addLine.execute();
				commandsNormal.add(addLine);
				
			}
			else if (line[2].equals("Rectangle")) {
				
				Point p = new Point(Integer.parseInt(line[7]), Integer.parseInt(line[10]), false, (Integer.parseInt(line[14]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[14]))));
				int height = Integer.parseInt(line[17]);
				int width = Integer.parseInt(line[20]);
				Rectangle r = new Rectangle(p, height, width, false, (Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[24]))), (Integer.parseInt(line[28]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[28]))));
				
				Command addRect = new CmdAdd(model, r);
				addRect.execute();
				commandsNormal.add(addRect);
				
			}
			else if (line[2].equals("Circle")) {
				
				Point p = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false, (Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[12]))));
				int radius = Integer.parseInt(line[15]);
				Circle c = new Circle(p, radius, false, (Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[19]))), (Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[23]))));
				
				Command addCircle = new CmdAdd(model, c);
				addCircle.execute();
				commandsNormal.add(addCircle);
				
			}
			else if (line[2].equals("Donut")) {
				
				Point p = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false, (Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[12]))));
				int radius = Integer.parseInt(line[15]);
				int innerRadius = Integer.parseInt(line[27]);
				
				Donut d = new Donut(p, radius, innerRadius, false, (Integer.parseInt(line[31]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[31]))), (Integer.parseInt(line[35]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[35]))));
				
				Command addDonut = new CmdAdd(model, d);
				addDonut.execute();
				commandsNormal.add(addDonut);
				
			}
			else if (line[2].equals("HexagonAdapter")) {
				
				Point p = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false, (Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[12]))));
				int radius = Integer.parseInt(line[15]);
				HexagonAdapter h = new HexagonAdapter(p.getX(), p.getY(), radius, (Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[19]))), (Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[23]))), false);
				
				Command addHex = new CmdAdd(model, h);
				addHex.execute();
				commandsNormal.add(addHex);
				
			}
			
			
		}
		else if(line[0].equals("Select")) {
			
			if(line[2].equals("Point")) {
				
				Point p = new Point(Integer.parseInt(line[4]), Integer.parseInt(line[7]), false, (Integer.parseInt(line[11]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[11]))));
				Command selectPoint = new CmdSelect(p,model);
				selectPoint.execute();
				commandsNormal.add(selectPoint);
				
			}
			else if (line[2].equals("Line")) {
				
				Point p1 = new Point(Integer.parseInt(line[6]), Integer.parseInt(line[9]), false, (Integer.parseInt(line[13]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[13]))));
				Point p2 = new Point(Integer.parseInt(line[18]), Integer.parseInt(line[21]), false, (Integer.parseInt(line[25]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[25]))));
				
				Line l = new Line(p1, p2, true, (Integer.parseInt(line[29]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[29]))));
				
				Command selectLine = new CmdSelect(l,model);
				selectLine.execute();
				commandsNormal.add(selectLine);
				
			}
			else if (line[2].equals("Rectangle")) {
				
				Point p = new Point(Integer.parseInt(line[7]), Integer.parseInt(line[10]), false, (Integer.parseInt(line[14]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[14]))));
				int height = Integer.parseInt(line[17]);
				int width = Integer.parseInt(line[20]);
				Rectangle r = new Rectangle(p, height, width, true, (Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[24]))), (Integer.parseInt(line[28]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[28]))));
				
				Command selectRect = new CmdSelect(r,model);
				selectRect.execute();
				commandsNormal.add(selectRect);
				
			}
			else if (line[2].equals("Circle")) {
				
				Point p = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false, (Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[12]))));
				int radius = Integer.parseInt(line[15]);
				Circle c = new Circle(p, radius, true, (Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[19]))), (Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[23]))));
				
				Command selectCircle = new CmdSelect(c,model);
				selectCircle.execute();
				commandsNormal.add(selectCircle);
				
			}
			else if (line[2].equals("Donut")) {
				
				Point p = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false, (Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[12]))));
				int radius = Integer.parseInt(line[15]);
				int innerRadius = Integer.parseInt(line[27]);
				
				Donut d = new Donut(p, radius, innerRadius, true, (Integer.parseInt(line[31]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[31]))), (Integer.parseInt(line[35]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[35]))));
				
				Command selectDonut = new CmdSelect(d,model);
				selectDonut.execute();
				commandsNormal.add(selectDonut);
				
			}
			else if (line[2].equals("HexagonAdapter")) {
				
				Point p = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false, (Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[12]))));
				int radius = Integer.parseInt(line[15]);
				HexagonAdapter h = new HexagonAdapter(p.getX(), p.getY(), radius, (Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[19]))), (Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[23]))), false);
				
				Command selectHex = new CmdSelect(h,model);
				selectHex.execute();
				commandsNormal.add(selectHex);
				
			}
			
			
		
		}
		else if(line[0].equals("Deselect")) {

			if(line[2].equals("Point")) {
				
				Point p = new Point(Integer.parseInt(line[4]), Integer.parseInt(line[7]), true, (Integer.parseInt(line[11]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[11]))));
				Command deselect = new CmdDeselect(p,model);
				deselect.execute();
				commandsNormal.add(deselect);
				
			}
			else if (line[2].equals("Line")) {
				
				Point p1 = new Point(Integer.parseInt(line[6]), Integer.parseInt(line[9]), false, (Integer.parseInt(line[13]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[13]))));
				Point p2 = new Point(Integer.parseInt(line[18]), Integer.parseInt(line[21]), false, (Integer.parseInt(line[25]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[25]))));
				
				Line l = new Line(p1, p2, true, (Integer.parseInt(line[29]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[29]))));
				
				Command deselect = new CmdDeselect(l,model);
				deselect.execute();
				commandsNormal.add(deselect);
				
			}
			else if (line[2].equals("Rectangle")) {
				
				Point p = new Point(Integer.parseInt(line[7]), Integer.parseInt(line[10]), false, (Integer.parseInt(line[14]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[14]))));
				int height = Integer.parseInt(line[17]);
				int width = Integer.parseInt(line[20]);
				Rectangle r = new Rectangle(p, height, width, true, (Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[24]))), (Integer.parseInt(line[28]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[28]))));
				
				Command deselect = new CmdDeselect(r,model);
				deselect.execute();
				commandsNormal.add(deselect);
				
			}
			else if (line[2].equals("Circle")) {
				
				Point p = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false, (Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[12]))));
				int radius = Integer.parseInt(line[15]);
				Circle c = new Circle(p, radius, true, (Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[19]))), (Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[23]))));
				
				Command deselect = new CmdDeselect(c,model);
				deselect.execute();
				commandsNormal.add(deselect);
				
			}
			else if (line[2].equals("Donut")) {
				
				Point p = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false, (Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[12]))));
				int radius = Integer.parseInt(line[15]);
				int innerRadius = Integer.parseInt(line[27]);
				
				Donut d = new Donut(p, radius, innerRadius, true, (Integer.parseInt(line[31]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[31]))), (Integer.parseInt(line[35]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[35]))));
				
				Command deselect = new CmdDeselect(d,model);
				deselect.execute();
				commandsNormal.add(deselect);
				
			}
			else if (line[2].equals("HexagonAdapter")) {
				
				Point p = new Point(Integer.parseInt(line[5]), Integer.parseInt(line[8]), false, (Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[12]))));
				int radius = Integer.parseInt(line[15]);
				HexagonAdapter h = new HexagonAdapter(p.getX(), p.getY(), radius, (Integer.parseInt(line[19]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[19]))), (Integer.parseInt(line[23]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[23]))), true);
				
				Command deselect = new CmdDeselect(h,model);
				deselect.execute();
				commandsNormal.add(deselect);
				
			}
			
		}
		else if(line[0].equals("Modify")) {

			Shape selected = model.getOneSelectedShape();
			
			if(line[2].equals("Point")) {
				
				Point oldState = (Point) selected;
				Point newState = new Point(Integer.parseInt(line[17]), Integer.parseInt(line[20]), true, (Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[24]))));
				
				model.getSelectedShapes().remove(oldState);
				model.getSelectedShapes().add(newState);
				Command modify = new CmdModifyPoint(oldState, newState);
				modify.execute();
				commandsNormal.add(modify);
				
			}
			else if (line[2].equals("Line")) {
				
				Line oldState = (Line)selected;
				
				Point p1 = new Point(Integer.parseInt(line[36]), Integer.parseInt(line[39]), false, (Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[43]))));
				Point p2 = new Point(Integer.parseInt(line[48]), Integer.parseInt(line[51]), false, (Integer.parseInt(line[55]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[55]))));
				
				Line newState = new Line(p1, p2, true, (Integer.parseInt(line[59]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[59]))));
				
				model.getSelectedShapes().remove(oldState);
				model.getSelectedShapes().add(newState);
				Command modify = new CmdModifyLine(oldState, newState);
				modify.execute();
				commandsNormal.add(modify);
				
			}
			else if (line[2].equals("Rectangle")) {
				
				Rectangle oldState = (Rectangle)selected;
				Point p = new Point(Integer.parseInt(line[36]), Integer.parseInt(line[39]), false, (Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[43]))));
				int height = Integer.parseInt(line[46]);
				int width = Integer.parseInt(line[49]);
				Rectangle newState = new Rectangle(p, height, width, true, (Integer.parseInt(line[53]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[53]))), (Integer.parseInt(line[57]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[57]))));
				
				model.getSelectedShapes().remove(oldState);
				model.getSelectedShapes().add(newState);
				Command modify = new CmdModifyRectangle(oldState, newState);
				modify.execute();
				commandsNormal.add(modify);
				
			}
			else if (line[2].equals("Circle")) {
				
				Circle oldState = (Circle)selected;
				Point p = new Point(Integer.parseInt(line[29]), Integer.parseInt(line[32]), false, (Integer.parseInt(line[36]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[36]))));
				int radius = Integer.parseInt(line[39]);
				Circle newState = new Circle(p, radius, true, (Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[43]))), (Integer.parseInt(line[47]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[47]))));
				
				model.getSelectedShapes().remove(oldState);
				model.getSelectedShapes().add(newState);
				Command modify = new CmdModifyCircle(oldState, newState);
				modify.execute();
				commandsNormal.add(modify);
				
			}
			else if (line[2].equals("Donut")) {
				
				Donut oldState = (Donut)selected;
				Point p = new Point(Integer.parseInt(line[41]), Integer.parseInt(line[44]), false, (Integer.parseInt(line[48]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[48]))));
				int radius = Integer.parseInt(line[51]);
				int innerRadius = Integer.parseInt(line[63]);
				
				Donut newState = new Donut(p, radius, innerRadius, true, (Integer.parseInt(line[67]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[67]))), (Integer.parseInt(line[71]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[71]))));
				
				model.getSelectedShapes().remove(oldState);
				model.getSelectedShapes().add(newState);
				Command modify = new CmdModifyDonut(oldState, newState);
				modify.execute();
				commandsNormal.add(modify);
				
			}
			else if (line[2].equals("HexagonAdapter")) {
				
				HexagonAdapter oldState = (HexagonAdapter)selected;
				Point p = new Point(Integer.parseInt(line[29]), Integer.parseInt(line[32]), false, (Integer.parseInt(line[36]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[36]))));
				int radius = Integer.parseInt(line[39]);
				HexagonAdapter newState = new HexagonAdapter(p.getX(), p.getY(), radius, (Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[43]))), (Integer.parseInt(line[47]) == 0 ? new Color(0, 0, 0, 0)
						: new Color(Integer.parseInt(line[47]))), true);
				
				model.getSelectedShapes().remove(oldState);
				model.getSelectedShapes().add(newState);
				Command modify = new CmdModifyHexagon(oldState, newState);
				modify.execute();
				commandsNormal.add(modify);
				
			}
			
			
		}
		else if(line[0].equals("Deleted")) {

			deleteForLogs();
			
		}
		else if(line[0].equals("Undo")) {

			undoForLog();
			
		}
		else if(line[0].equals("Redo")) {

			redoForLog();
			
		}
		else if(line[0].equals("ToBack")) {

			toBack();
			
		}
		else if(line[0].equals("ToFront")) {

			toFront();
			
		}
		else if(line[0].equals("BringToFront")) {

			bringToFront();
			
		}
		else if(line[0].equals("SendToBack")) {

			sendToBack();
			
		}
		
		if(getLoggComm().size() == 1) {
		
			main_frame.getBtnNext().setEnabled(false);
		}
		
		//System.out.println(getLoggComm().size());
		
		fireEvents();
		fireUndoRedo();
		main_frame.getView().repaint();
		main_frame.getlModel().addElement(getLoggComm().poll());
	}
	
	
	
	



	
	
}
