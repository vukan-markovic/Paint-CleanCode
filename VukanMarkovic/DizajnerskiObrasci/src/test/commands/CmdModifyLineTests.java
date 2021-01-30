package commands;

import shapes.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import java.awt.Color;

public class CmdModifyLineTests {
	private Line oldState;
	private Line newState;
	private Line originalState;
	private CmdModifyLine cmdModifyLine;

	@Before
	public void setUp() {
		oldState = new Line(new Point(1, 1), new Point(3, 2), false, Color.BLACK);
		newState = new Line(new Point(1, 2), new Point(4, 1), true, Color.WHITE);
		originalState = new Line(new Point(1, 1), new Point(3, 2), false, Color.BLACK);
		cmdModifyLine = new CmdModifyLine(oldState, newState);
	}

	@Test
	public void testExecute() {
		cmdModifyLine.execute();
		assertEquals(newState, oldState);
	}

	@Test
	public void testUnexecute() {
		cmdModifyLine.unexecute();
		assertEquals(originalState, oldState);
	}
}