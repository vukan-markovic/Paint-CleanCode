package files;

import java.awt.Color;
import java.io.*;
import java.util.*;
import org.junit.*;
import shapes.*;
import static org.junit.Assert.assertEquals;
import org.junit.rules.TemporaryFolder;

import model.DrawingModel;

public class FilePaintingTests {
	private FilePainting filePainting;
	private FileManager strategy;
	private static ObjectInputStream inputStream;
	private DrawingModel model;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		model = new DrawingModel();
		filePainting = new FilePainting(model);
	}

	@Test(expected = IOException.class)
	public void testSavePaintingIOExceptionExpected() throws IOException, ClassNotFoundException {
		strategy = new FileManager(filePainting);
		String filePath = "";
		strategy.save(filePath);
		inputStream = new ObjectInputStream(new FileInputStream(filePath));
		assertEquals(filePainting.getModel().getShapes(), inputStream.readObject());
	}

	@Test
	public void testSavePainting() throws IOException, ClassNotFoundException {
		model.addShapes(new ArrayList<Shape>(Arrays.asList(new Point(1, 2, false, Color.BLACK), new Line(
				new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false, Color.BLACK))));

		strategy = new FileManager(filePainting);
		String filePath = folder.newFile("myfile1.txt").getAbsolutePath();
		strategy.save(filePath);
		inputStream = new ObjectInputStream(new FileInputStream(filePath));
		assertEquals(filePainting.getModel().getShapes(), inputStream.readObject());
	}

	@AfterClass
	public static void tearDown() throws IOException {
		inputStream.close();
	}
}