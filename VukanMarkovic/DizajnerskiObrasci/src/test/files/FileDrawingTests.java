package files;

import java.awt.Color;
import java.io.*;
import java.util.*;
import org.junit.*;
import shapes.*;
import static org.junit.Assert.assertEquals;
import org.junit.rules.TemporaryFolder;

import frame.DrawingFrame;
import model.DrawingModel;

public class FileDrawingTests {
	private FileDrawing fileDrawing;
	private FileManager strategy;
	private static ObjectInputStream inputStream;
	private DrawingModel model;
	private DrawingFrame frame;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		fileDrawing = new FileDrawing(model, frame);
	}

	@Test(expected = IOException.class)
	public void testSavePaintingIOExceptionExpected() throws IOException, ClassNotFoundException {
		strategy = new FileManager(fileDrawing);
		String filePath = "";
		strategy.save(filePath);
		inputStream = new ObjectInputStream(new FileInputStream(filePath));
		assertEquals(fileDrawing.getModel().getShapes(), inputStream.readObject());
	}

	@Test
	public void testSavePainting() throws IOException, ClassNotFoundException {
		model.addShapes(new ArrayList<Shape>(Arrays.asList(new Point(1, 2, false, Color.BLACK), new Line(
				new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false, Color.BLACK))));

		strategy = new FileManager(fileDrawing);
		String filePath = folder.newFile("myfile1.txt").getAbsolutePath();
		strategy.save(filePath);
		inputStream = new ObjectInputStream(new FileInputStream(filePath));
		assertEquals(fileDrawing.getModel().getShapes(), inputStream.readObject());
	}

	@AfterClass
	public static void tearDown() throws IOException {
		inputStream.close();
	}
}