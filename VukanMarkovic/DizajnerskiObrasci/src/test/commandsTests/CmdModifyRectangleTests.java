package test.commandsTests;

import shapes.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.awt.Color;
import commands.CmdModifyRectangle;

public class CmdModifyRectangleTests {
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle originalState;
	private CmdModifyRectangle cmdModifyRectangle;

	@Before
	public void setUp() {
		oldState = new Rectangle(new Point(1, 3), 6, 7, false, Color.BLACK, Color.WHITE);
		newState = new Rectangle(new Point(2, 4), 4, 9, true, Color.WHITE, Color.BLACK);
		originalState = new Rectangle(new Point(1, 3), 6, 7, false, Color.BLACK, Color.WHITE);
		cmdModifyRectangle = new CmdModifyRectangle(oldState, newState);
	}

	@Test
	public void testExecuteOldStateEqualsNewState() {
		cmdModifyRectangle.execute();
		assertEquals(newState, oldState);
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		cmdModifyRectangle.unexecute();
		assertEquals(originalState, oldState);
	}
}