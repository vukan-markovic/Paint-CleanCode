package test.commandsTests;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CmdModifyDonut;
import shapes.Donut;
import shapes.Point;

public class CmdModifyDonutTests {
	private Donut oldState;
	private Donut newState;
	private Donut originalState;
	private CmdModifyDonut cmdModifyDonut;

	@Before
	public void setUp() {
		oldState = new Donut(new Point(1, 1), 3, 2, false, Color.BLACK, Color.WHITE);
		newState = new Donut(new Point(1, 2), 4, 1, true, Color.WHITE, Color.BLACK);
		cmdModifyDonut = new CmdModifyDonut(oldState, newState);
	}

	@Test(expected = NullPointerException.class)
	public void testExecuteExceptionExpected() {
		cmdModifyDonut = new CmdModifyDonut(new Donut(), new Donut());
		cmdModifyDonut.execute();
	}

	@Test
	public void testExecuteOldStateEqualsNewState() {
		cmdModifyDonut.execute();
		assertEquals(newState, oldState);
	}

	@Test(expected = NullPointerException.class)
	public void testUnexecuteExecuteNotCalledExceptionExpected() {
		cmdModifyDonut.unexecute();
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		originalState = new Donut(new Point(1, 1), 3, 2, false, Color.BLACK, Color.WHITE);
		cmdModifyDonut.execute();
		cmdModifyDonut.unexecute();
		assertEquals(originalState, oldState);
	}
}