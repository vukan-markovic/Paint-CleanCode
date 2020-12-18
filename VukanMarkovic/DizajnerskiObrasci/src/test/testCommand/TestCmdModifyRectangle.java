package test.testCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import command.CmdModifyRectangle;
import geometry.Point;
import geometry.Rectangle;

public class TestCmdModifyRectangle {
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original;
	private CmdModifyRectangle cmdModifyRectangle;

	@Before
	public void setUp() {
		oldState = new Rectangle(new Point(1, 3), 6, 7, false, Color.BLACK, Color.WHITE);
		newState = new Rectangle(new Point(2, 4), 4, 9, true, Color.WHITE, Color.BLACK);
		cmdModifyRectangle = new CmdModifyRectangle(oldState, newState);
	}

	@Test(expected = NullPointerException.class)
	public void testExecuteExceptionExpected() {
		cmdModifyRectangle = new CmdModifyRectangle(new Rectangle(), new Rectangle());
		cmdModifyRectangle.execute();
		assertNotEquals(newState, oldState);
	}

	@Test
	public void testExecuteOldStateEqualsNewState() {
		cmdModifyRectangle.execute();
		assertEquals(newState, oldState);
	}

	@Test()
	public void testUnexecuteExecuteNotCalledOldStateNotEqualsOriginalState() {
		cmdModifyRectangle.unexecute();
		assertNotEquals(original, oldState);
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		original = new Rectangle(new Point(1, 3), 6, 7, false, Color.BLACK, Color.WHITE);
		cmdModifyRectangle.execute();
		cmdModifyRectangle.unexecute();
		assertEquals(original, oldState);
	}
}