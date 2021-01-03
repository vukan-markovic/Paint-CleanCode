package test.commandsTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CmdModifyRectangle;
import shapes.Point;
import shapes.Rectangle;

public class CmdModifyRectangleTests {
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle originalState;
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
		assertNotEquals(originalState, oldState);
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		originalState = new Rectangle(new Point(1, 3), 6, 7, false, Color.BLACK, Color.WHITE);
		cmdModifyRectangle.execute();
		cmdModifyRectangle.unexecute();
		assertEquals(originalState, oldState);
	}
}