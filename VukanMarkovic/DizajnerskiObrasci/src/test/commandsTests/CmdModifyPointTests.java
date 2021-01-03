package test.commandsTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CmdModifyPoint;
import shapes.Point;

public class CmdModifyPointTests {
	private Point oldState;
	private Point newState;
	private Point originalState;
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
		assertNotEquals(originalState, oldState);
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		originalState = new Point(1, 3, false, Color.BLACK);
		cmdModifyPoint.execute();
		cmdModifyPoint.unexecute();
		assertEquals(originalState, oldState);
	}
}