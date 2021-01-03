package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.commandsTests.CmdAddTests;
import test.commandsTests.CmdBringToFrontTests;
import test.commandsTests.CmdDeselectTests;
import test.commandsTests.CmdModifyCircleTests;
import test.commandsTests.CmdModifyDonutTests;
import test.commandsTests.CmdModifyHexagonTests;
import test.commandsTests.CmdModifyLineTests;
import test.commandsTests.CmdModifyPointTests;
import test.commandsTests.CmdModifyRectangleTests;
import test.commandsTests.CmdRemoveTests;
import test.commandsTests.CmdSelectTests;
import test.commandsTests.CmdSendToBackTests;
import test.commandsTests.CmdToBackTests;
import test.commandsTests.CmdToFrontTests;
import test.dialogsTests.DialogCircleTests;
import test.dialogsTests.DialogDonutTests;
import test.dialogsTests.DialogHexagonTests;
import test.dialogsTests.DialogLineTests;
import test.dialogsTests.DialogPointTests;
import test.dialogsTests.DialogRectangleTests;
import test.mvcTests.DrawingControllerTests;
import test.mvcTests.DrawingFrameTests;
import test.mvcTests.DrawingModelTests;
import test.mvcTests.DrawingViewTests;
import test.mvcTests.MainAppTests;
import test.observerTests.DrawingObserverTests;
import test.shapesTests.CircleTests;
import test.shapesTests.DonutTests;
import test.shapesTests.HexagonAdapterTests;
import test.shapesTests.LineTests;
import test.shapesTests.PointTests;
import test.shapesTests.RectangleTests;
import test.strategyTests.SaveLogTests;
import test.strategyTests.SavePaintingTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CmdAddTests.class, CmdBringToFrontTests.class, CmdDeselectTests.class, CmdModifyCircleTests.class,
		CmdModifyDonutTests.class, CmdModifyHexagonTests.class, CmdModifyLineTests.class, CmdModifyPointTests.class,
		CmdModifyRectangleTests.class, CmdRemoveTests.class, CmdSelectTests.class, CmdSendToBackTests.class,
		CmdToBackTests.class, CmdToFrontTests.class, DialogCircleTests.class, DialogDonutTests.class,
		DialogHexagonTests.class, DialogLineTests.class, DialogPointTests.class, DialogRectangleTests.class,
		CircleTests.class, DonutTests.class, HexagonAdapterTests.class, LineTests.class, PointTests.class,
		RectangleTests.class, DrawingControllerTests.class, DrawingFrameTests.class, DrawingModelTests.class,
		DrawingViewTests.class, MainAppTests.class, DrawingObserverTests.class, SaveLogTests.class,
		SavePaintingTests.class })
public class TestSuite {
}