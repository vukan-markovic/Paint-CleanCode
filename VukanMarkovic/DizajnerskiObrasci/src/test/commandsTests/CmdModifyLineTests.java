package test.commandsTests;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CmdModifyLine;
import shapes.Line;
import shapes.Point;

public class CmdModifyLineTests {
	private Line oldState;
	private Line newState;
	private Line originalState;
	private CmdModifyLine cmdModifyLine;

	@Before
	public void setUp() {
		oldState = new Line(new Point(1, 1), new Point(3, 2), false, Color.BLACK);
		newState = new Line(new Point(1, 2), new Point(4, 1), true, Color.WHITE);
		cmdModifyLine = new CmdModifyLine(oldState, newState);
	}

	@Test(expected = NullPointerException.class)
	public void testExecuteExceptionExpected() {
		cmdModifyLine = new CmdModifyLine(new Line(), new Line());
		cmdModifyLine.execute();
	}

	@Test
	public void testExecuteOldStateEqualsNewState() {
		cmdModifyLine.execute();
		assertEquals(newState, oldState);
	}

	@Test(expected = NullPointerException.class)
	public void testUnexecuteExecuteNotCalledExceptionExpected() {
		cmdModifyLine.unexecute();
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		originalState = new Line(new Point(1, 1), new Point(3, 2), false, Color.BLACK);
		cmdModifyLine.execute();
		cmdModifyLine.unexecute();
		assertEquals(originalState, oldState);
	}
}