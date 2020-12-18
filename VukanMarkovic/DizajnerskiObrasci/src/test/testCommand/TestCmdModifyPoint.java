package test.testCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import command.CmdModifyPoint;
import geometry.Point;

public class TestCmdModifyPoint {
	private Point oldState;
	private Point newState;
	private Point original;
	private CmdModifyPoint cmdModifyPoint;

	@Before
	public void setUp() {
		oldState = new Point(1, 3, false, Color.BLACK);
		newState = new Point(2, 4, true, Color.WHITE);
		cmdModifyPoint = new CmdModifyPoint(oldState, newState);
	}

	@Test()
	public void testExecuteOldStateNotEqualsNewState() {
		cmdModifyPoint = new CmdModifyPoint(new Point(), new Point());
		cmdModifyPoint.execute();
		assertNotEquals(newState, oldState);
	}

	@Test
	public void testExecuteOldStateEqualsNewState() {
		cmdModifyPoint.execute();
		assertEquals(newState, oldState);
	}

	@Test()
	public void testUnexecuteExecuteNotCalledOldStateNotEqualsOriginalState() {
		cmdModifyPoint.unexecute();
		assertNotEquals(original, oldState);
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		original = new Point(1, 3, false, Color.BLACK);
		cmdModifyPoint.execute();
		cmdModifyPoint.unexecute();
		assertEquals(original, oldState);
	}
}