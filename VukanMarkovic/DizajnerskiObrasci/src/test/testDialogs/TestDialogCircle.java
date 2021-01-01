package test.testDialogs;

import org.junit.Before;
import org.junit.Test;

import dialogs.DialogCircle;

public class TestDialogCircle {
	private DialogCircle dialogCircle;

	@Before
	public void setUp() {
		dialogCircle = new DialogCircle();
	}

	@Test
	public void test() {
		dialogCircle.getX();
	}
}