package commands;

import shapes.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import java.awt.Color;

public class CmdModifyCircleTests {
	private Circle oldState;
	private Circle newState;
	private Circle originalState;
	private CmdModifyCircle cmdModifyCircle;

	@Before
	public void setUp() {
		oldState = new Circle(new Point(1, 1), 2, false, Color.BLACK, Color.WHITE);
		newState = new Circle(new Point(1, 2), 1, true, Color.WHITE, Color.BLACK);
		originalState = new Circle(new Point(1, 1), 2, false, Color.BLACK, Color.WHITE);
		cmdModifyCircle = new CmdModifyCircle(oldState, newState);
	}

	@Test
	public void testExecute() {
		cmdModifyCircle.execute();
		assertEquals(newState, oldState);
	}

	@Test
	public void testUnexecute() {
		cmdModifyCircle.unexecute();
		assertEquals(originalState, oldState);
	}
}