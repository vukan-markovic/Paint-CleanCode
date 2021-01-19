package files;

import java.io.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;

import org.junit.rules.TemporaryFolder;

public class FileLogTests {
	private FileLog fileLog;
	private FileManager strategy;
	private static BufferedReader reader;
	private DefaultListModel<String> log;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		log = new DefaultListModel<>();
		fileLog = new FileLog(log);

	}

	@Test(expected = IOException.class)
	public void testSaveLogIOExceptionExpected() throws IOException {
		strategy = new FileManager(fileLog);
		String filePath = "";
		strategy.save(filePath);
		reader = new BufferedReader(new FileReader(filePath));
		assertEquals(fileLog.getLog(), reader.lines().collect(Collectors.joining("\n")));
	}

	@Test
	public void testSaveLog() throws IOException {
		log.addElement("Log\ntest");
		strategy = new FileManager(fileLog);
		String filePath = folder.newFile("myfile1.txt").getAbsolutePath();
		strategy.save(filePath);
		reader = new BufferedReader(new FileReader(filePath));
		assertEquals(fileLog.getLog().get(0), reader.lines().collect(Collectors.joining("\n")));
	}

	@AfterClass
	public static void tearDown() throws IOException {
		reader.close();
	}
}