package files;

import java.io.*;
import java.util.*;
import org.junit.*;
import shapes.*;
import static org.junit.Assert.assertEquals;
import org.junit.rules.TemporaryFolder;
import frame.DrawingFrame;
import model.DrawingModel;

public class FileDrawingTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private FileDrawing fileDrawing;
	private FileManager strategy;
	private static ObjectInputStream inputStream;

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		fileDrawing = new FileDrawing(model, frame);
	}

	@Test(expected = IOException.class)
	public void testSavePaintingInvalidPath() throws IOException, ClassNotFoundException {
		strategy = new FileManager(fileDrawing);
		String filePath = "";
		strategy.save(filePath);
		inputStream = new ObjectInputStream(new FileInputStream(filePath));
		assertEquals(fileDrawing.getModel().getShapes(), inputStream.readObject());
	}

	@Test
	public void testSavePainting() throws IOException, ClassNotFoundException {
		model.addShapes(
				new ArrayList<Shape>(Arrays.asList(new Point(1, 2), new Line(new Point(1, 2), new Point(3, 4)))));

		strategy = new FileManager(fileDrawing);
		String filePath = tempFolder.newFile("myfile1.txt").getAbsolutePath();
		strategy.save(filePath);
		inputStream = new ObjectInputStream(new FileInputStream(filePath));
		assertEquals(fileDrawing.getModel().getShapes(), inputStream.readObject());
	}

	@AfterClass
	public static void tearDown() throws IOException {
		inputStream.close();
	}
}