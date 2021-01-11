package test.commandsTests;

import static org.junit.Assert.*;
import org.junit.*;
import java.awt.Color;
import commands.CmdModifyHexagon;
import shapes.HexagonAdapter;

public class CmdModifyHexagonTests {
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter originalState;
	private CmdModifyHexagon cmdModifyHexagon;

	@Before
	public void setUp() {
		oldState = new HexagonAdapter(3, 2, 3, Color.BLACK, Color.WHITE, false);
		newState = new HexagonAdapter(4, 1, 5, Color.WHITE, Color.BLACK, true);
		originalState = new HexagonAdapter(3, 2, 3, Color.BLACK, Color.WHITE, false);
		cmdModifyHexagon = new CmdModifyHexagon(oldState, newState);
	}

	@Test
	public void testExecuteOldStateEqualsNewState() {
		cmdModifyHexagon.execute();
		assertEquals(newState, oldState);
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		cmdModifyHexagon.unexecute();
		assertEquals(originalState, oldState);
	}
}