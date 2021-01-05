package test;

import test.commandsTests.*;
import test.dialogsTests.*;
import test.mvcTests.*;
import test.shapesTests.*;
import test.strategyTests.*;
import test.observerTests.DrawingObserverTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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