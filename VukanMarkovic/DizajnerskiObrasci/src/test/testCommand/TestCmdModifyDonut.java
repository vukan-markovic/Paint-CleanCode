package test.testCommand;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import command.CmdModifyDonut;
import geometry.Donut;
import geometry.Point;

public class TestCmdModifyDonut {
	private Donut oldState;
	private Donut newState;
	private Donut original;
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
		original = new Donut(new Point(1, 1), 3, 2, false, Color.BLACK, Color.WHITE);
		cmdModifyDonut.execute();
		cmdModifyDonut.unexecute();
		assertEquals(original, oldState);
	}
}