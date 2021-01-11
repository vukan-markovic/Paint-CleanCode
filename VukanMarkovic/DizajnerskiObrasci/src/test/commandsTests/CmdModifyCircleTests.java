package test.commandsTests;

import shapes.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import java.awt.Color;
import commands.CmdModifyCircle;

public class CmdModifyCircleTests {
	private Circle oldState;
	private Circle newState;
	private Circle originalState;
	private CmdModifyCircle cmdModifyCircle;

	@Before
	public void setUp() {
		oldState = new Circle(new Point(1, 1, false, Color.BLACK), 2, false, Color.BLACK, Color.WHITE);
		newState = new Circle(new Point(1, 2, false, Color.BLACK), 1, true, Color.WHITE, Color.BLACK);
		originalState = new Circle(new Point(1, 1, false, Color.BLACK), 2, false, Color.BLACK, Color.WHITE);
		cmdModifyCircle = new CmdModifyCircle(oldState, newState);
	}

	@Test
	public void testExecuteOldStateEqualsNewState() {
		cmdModifyCircle.execute();
		assertEquals(newState, oldState);
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		cmdModifyCircle.unexecute();
		assertEquals(originalState, oldState);
	}
}