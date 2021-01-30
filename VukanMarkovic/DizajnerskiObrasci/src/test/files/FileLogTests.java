package files;

import java.io.*;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.assertEquals;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import org.junit.rules.TemporaryFolder;

public class FileLogTests {
	private DefaultListModel<String> log;
	private Queue<String> logCommandsFromFile;
	private FileLog fileLog;
	private FileManager strategy;
	private static BufferedReader reader;
	private BufferedWriter writer;
	private String filePath;
	private File file;

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Before
	public void setUp() {
		log = new DefaultListModel<>();
		logCommandsFromFile = new LinkedList<>();
		fileLog = new FileLog(log, logCommandsFromFile);
	}

	@Test(expected = IOException.class)
	public void testSaveLogInvalidPath() throws IOException {
		strategy = new FileManager(fileLog);
		filePath = "";
		strategy.save(filePath);
		reader = new BufferedReader(new FileReader(filePath));
		assertEquals(fileLog.getExecutedLogCommands(), reader.lines().collect(Collectors.joining("\n")));
	}

	@Test
	public void testSaveLog() throws IOException {
		log.addElement("Log\ntest");
		strategy = new FileManager(fileLog);
		filePath = tempFolder.newFile("myfile1.txt").getAbsolutePath();
		strategy.save(filePath);
		reader = new BufferedReader(new FileReader(filePath));
		assertEquals(fileLog.getExecutedLogCommands().get(0), reader.lines().collect(Collectors.joining("\n")));
	}

	@Test(expected = IOException.class)
	public void testOpenLogInvalidPath() throws IOException {
		file = tempFolder.newFile("");
		openFile();

		assertEquals(logCommandsFromFile.stream().collect(Collectors.joining(",")),
				reader.lines().collect(Collectors.joining(",")));
	}

	@Test
	public void testOpenLog() throws IOException {
		file = tempFolder.newFile("log.txt");
		openFile();

		assertEquals(logCommandsFromFile.stream().collect(Collectors.joining(",")),
				reader.lines().collect(Collectors.joining(",")));
	}

	private void openFile() throws IOException {
		filePath = file.getAbsolutePath();
		writer = new BufferedWriter(new FileWriter(filePath));
		writer.write("Log\ntest");
		writer.close();
		strategy = new FileManager(fileLog);
		strategy.open(filePath);
		reader = new BufferedReader(new FileReader(filePath));
	}

	@AfterClass
	public static void tearDown() throws IOException {
		reader.close();
	}
}