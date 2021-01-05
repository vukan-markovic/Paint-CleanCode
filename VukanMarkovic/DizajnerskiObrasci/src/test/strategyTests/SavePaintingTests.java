package test.strategyTests;

import java.io.*;
import java.util.*;
import org.junit.*;
import shapes.*;
import strategy.*;
import static org.junit.Assert.assertEquals;
import org.junit.rules.TemporaryFolder;

public class SavePaintingTests {
	private SavePainting savePainting;
	private StrategyManager strategy;
	private static ObjectInputStream inputStream;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		savePainting = new SavePainting();
		strategy = new StrategyManager();
	}

	@Test(expected = IOException.class)
	public void testSavePaintingIOExceptionExpected() throws IOException, ClassNotFoundException {
		strategy.setStrategy(savePainting);
		String filePath = "";
		strategy.save(filePath);
		inputStream = new ObjectInputStream(new FileInputStream(filePath));
		assertEquals(savePainting.getShapes(), inputStream.readObject());
	}

	@Test
	public void testSavePainting() throws IOException, ClassNotFoundException {
		savePainting.setShapes(
				new ArrayList<Shape>(Arrays.asList(new Point(1, 2), new Line(new Point(1, 2), new Point(3, 4)))));

		strategy.setStrategy(savePainting);
		String filePath = folder.newFile("myfile1.txt").getAbsolutePath();
		strategy.save(filePath);
		inputStream = new ObjectInputStream(new FileInputStream(filePath));
		assertEquals(savePainting.getShapes(), inputStream.readObject());
	}

	@AfterClass
	public static void tearDown() throws IOException {
		inputStream.close();
	}
}