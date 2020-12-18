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

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	TestCmdAdd.class, TestCmdBringToFront.class, TestCmdDeselect.class, 
	TestCmdModifyCircle.class, TestCmdModifyDonut.class, TestCmdModifyHexagon.class, 
	TestCmdModifyLine.class, TestCmdModifyPoint.class, TestCmdModifyRectangle.class, 
	TestCmdRemove.class, TestCmdSelect.class, TestCmdSendToBack.class, 
	TestCmdToBack.class, TestCmdToFront.class
})
public class TestSuite {
}