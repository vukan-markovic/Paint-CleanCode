package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.testCommand.TestCmdAdd;
import test.testCommand.TestCmdBringToFront;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestCmdAdd.class, TestCmdBringToFront.class })
public class TestSuite {
}