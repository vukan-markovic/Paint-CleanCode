package test.strategyTests;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import shapes.Line;
import shapes.Point;
import shapes.Shape;
import strategy.SavePainting;
import strategy.StrategyManager;

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