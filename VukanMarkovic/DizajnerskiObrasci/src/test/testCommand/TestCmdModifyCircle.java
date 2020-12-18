package test.testCommand;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import command.CmdModifyCircle;
import geometry.Circle;
import geometry.Point;

public class TestCmdModifyCircle {
	private Circle oldState;
	private Circle newState;
	private Circle original;
	private CmdModifyCircle cmdModifyCircle;

	@Before
	public void setUp() {
		oldState = new Circle(new Point(1, 1), 2, false, Color.BLACK, Color.WHITE);
		newState = new Circle(new Point(1, 2), 1, true, Color.WHITE, Color.BLACK);
		cmdModifyCircle = new CmdModifyCircle(oldState, newState);
	}

	@Test(expected = NullPointerException.class)
	public void testExecuteExceptionExpected() {
		cmdModifyCircle = new CmdModifyCircle(new Circle(), new Circle());
		cmdModifyCircle.execute();
	}

	@Test
	public void testExecuteOldStateEqualsNewState() {
		cmdModifyCircle.execute();
		assertEquals(newState, oldState);
	}

	@Test(expected = NullPointerException.class)
	public void testUnexecuteExecuteNotCalledExceptionExpected() {
		cmdModifyCircle.unexecute();
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		original = new Circle(new Point(1, 1), 2, false, Color.BLACK, Color.WHITE);
		cmdModifyCircle.execute();
		cmdModifyCircle.unexecute();
		assertEquals(original, oldState);
	}
}