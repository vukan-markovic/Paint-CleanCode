package commands;

import org.junit.*;
import shapes.*;
import static org.junit.Assert.assertEquals;
import java.awt.Color;

public class CmdModifyDonutTests {
	private Donut oldState;
	private Donut newState;
	private Donut originalState;
	private CmdModifyDonut cmdModifyDonut;

	@Before
	public void setUp() {
		oldState = new Donut(new Point(1, 1), 3, 2, false, Color.BLACK, Color.WHITE);
		newState = new Donut(new Point(1, 2), 4, 1, true, Color.WHITE, Color.BLACK);
		originalState = new Donut(new Point(1, 1), 3, 2, false, Color.BLACK, Color.WHITE);
		cmdModifyDonut = new CmdModifyDonut(oldState, newState);
	}

	@Test
	public void testExecute() {
		cmdModifyDonut.execute();
		assertEquals(newState, oldState);
	}

	@Test
	public void testUnexecute() {
		cmdModifyDonut.unexecute();
		assertEquals(originalState, oldState);
	}
}