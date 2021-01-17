package commandsTests;

import static org.junit.Assert.*;
import org.junit.*;
import java.awt.Color;
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
		originalState = new Point(1, 3, false, Color.BLACK);
		cmdModifyPoint = new CmdModifyPoint(oldState, newState);
	}

	@Test()
	public void testExecuteOldStateNotEqualsNewState() {
		cmdModifyPoint = new CmdModifyPoint(new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK));
		cmdModifyPoint.execute();
		assertNotEquals(newState, oldState);
	}

	@Test
	public void testExecuteOldStateEqualsNewState() {
		cmdModifyPoint.execute();
		assertEquals(newState, oldState);
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		cmdModifyPoint.unexecute();
		assertEquals(originalState, oldState);
	}
}