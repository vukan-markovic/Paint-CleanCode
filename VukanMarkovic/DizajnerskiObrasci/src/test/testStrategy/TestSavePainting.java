package test.testStrategy;

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

import geometry.Line;
import geometry.Point;
import geometry.Shape;
import strategy.SavePainting;
import strategy.StrategyManager;

public class TestSavePainting {
	private SavePainting savePainting;
	private StrategyManager strategyManager;
	private static ObjectInputStream objectInputStream;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		savePainting = new SavePainting();
		strategyManager = new StrategyManager();
	}

	@Test(expected = IOException.class)
	public void testSavePaintingIOExceptionExpected() throws IOException, ClassNotFoundException {
		strategyManager.setStrategy(savePainting);
		String filePath = "";
		strategyManager.save(filePath);
		objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
		assertEquals(savePainting.getShapes(), objectInputStream.readObject());
	}

	@Test
	public void testSavePainting() throws IOException, ClassNotFoundException {
		savePainting.setShapes(
				new ArrayList<Shape>(Arrays.asList(new Point(1, 2), new Line(new Point(1, 2), new Point(3, 4)))));

		strategyManager.setStrategy(savePainting);
		String filePath = folder.newFile("myfile1.txt").getAbsolutePath();
		strategyManager.save(filePath);
		objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
		assertEquals(savePainting.getShapes(), objectInputStream.readObject());
	}

	@AfterClass
	public static void cleanUp() throws IOException {
		objectInputStream.close();
	}
}