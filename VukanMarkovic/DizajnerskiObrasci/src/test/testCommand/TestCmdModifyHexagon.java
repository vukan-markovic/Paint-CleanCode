package test.testCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import command.CmdModifyHexagon;
import geometry.HexagonAdapter;

public class TestCmdModifyHexagon {
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original;
	private CmdModifyHexagon cmdModifyHexagon;

	@Before
	public void setUp() {
		oldState = original = new HexagonAdapter(3, 2, 3, Color.BLACK, Color.WHITE, false);
		newState = new HexagonAdapter(4, 1, 5, Color.WHITE, Color.BLACK, true);
		cmdModifyHexagon = new CmdModifyHexagon(oldState, newState);
	}

	@Test()
	public void testExecuteOldStateNotEqualsNewState() {
		cmdModifyHexagon = new CmdModifyHexagon(new HexagonAdapter(), new HexagonAdapter());
		cmdModifyHexagon.execute();
		assertNotEquals(newState, oldState);
	}

	@Test
	public void testExecuteOldStateEqualsNewState() {
		cmdModifyHexagon.execute();
		assertEquals(newState, oldState);
	}

	@Test(expected = NullPointerException.class)
	public void testUnexecuteExecuteNotCalledExceptionExpected() {
		cmdModifyHexagon.unexecute();
	}

	@Test
	public void testUnexecuteOldStateEqualsOriginalState() {
		original = new HexagonAdapter(3, 2, 3, Color.BLACK, Color.WHITE, false);
		cmdModifyHexagon.execute();
		cmdModifyHexagon.unexecute();
		assertEquals(original, oldState);
	}
}