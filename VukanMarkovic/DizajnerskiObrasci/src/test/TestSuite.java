package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.testCommand.TestCmdAdd;
import test.testCommand.TestCmdBringToFront;
import test.testCommand.TestCmdDeselect;
import test.testCommand.TestCmdModifyCircle;
import test.testCommand.TestCmdModifyDonut;
import test.testCommand.TestCmdModifyHexagon;
import test.testCommand.TestCmdModifyLine;
import test.testCommand.TestCmdModifyPoint;
import test.testCommand.TestCmdModifyRectangle;
import test.testCommand.TestCmdRemove;
import test.testCommand.TestCmdSelect;
import test.testCommand.TestCmdSendToBack;
import test.testCommand.TestCmdToBack;
import test.testCommand.TestCmdToFront;
import test.testDialogs.TestDialogCircle;
import test.testDialogs.TestDialogDonut;
import test.testDialogs.TestDialogHexagon;
import test.testDialogs.TestDialogLine;
import test.testDialogs.TestDialogPoint;
import test.testDialogs.TestDialogRectangle;
import test.testGeometry.TestCircle;
import test.testGeometry.TestDonut;
import test.testGeometry.TestHexagonAdapter;
import test.testGeometry.TestLine;
import test.testGeometry.TestPoint;
import test.testGeometry.TestRectangle;
import test.testMVC.TestDrawingController;
import test.testMVC.TestDrawingFrame;
import test.testMVC.TestDrawingModel;
import test.testMVC.TestDrawingView;
import test.testMVC.TestMainApp;
import test.testObserver.TestObserver;
import test.testStrategy.TestSaveLog;
import test.testStrategy.TestSavePainting;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestCmdAdd.class, TestCmdBringToFront.class, TestCmdDeselect.class, TestCmdModifyCircle.class,
		TestCmdModifyDonut.class, TestCmdModifyHexagon.class, TestCmdModifyLine.class, TestCmdModifyPoint.class,
		TestCmdModifyRectangle.class, TestCmdRemove.class, TestCmdSelect.class, TestCmdSendToBack.class,
		TestCmdToBack.class, TestCmdToFront.class, TestDialogCircle.class, TestDialogDonut.class,
		TestDialogHexagon.class, TestDialogLine.class, TestDialogPoint.class, TestDialogRectangle.class,
		TestCircle.class, TestDonut.class, TestHexagonAdapter.class, TestLine.class, TestPoint.class,
		TestRectangle.class, TestDrawingController.class, TestDrawingFrame.class, TestDrawingModel.class,
		TestDrawingView.class, TestMainApp.class, TestObserver.class, TestSaveLog.class, TestSavePainting.class })
public class TestSuite {
}