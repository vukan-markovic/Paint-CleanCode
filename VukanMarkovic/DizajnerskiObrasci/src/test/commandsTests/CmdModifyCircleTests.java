package test.commandsTests;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CmdModifyCircle;
import shapes.Circle;
import shapes.Point;

public class CmdModifyCircleTests {
	private Circle oldState;
	private Circle newState;
	private Circle originalState;
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
		originalState = new Circle(new Point(1, 1), 2, false, Color.BLACK, Color.WHITE);
		cmdModifyCircle.execute();
		cmdModifyCircle.unexecute();
		assertEquals(originalState, oldState);
	}
}