package test.strategyTests;

import java.io.*;
import org.junit.*;
import strategy.*;
import static org.junit.Assert.assertEquals;
import java.util.stream.Collectors;
import org.junit.rules.TemporaryFolder;

public class SaveLogTests {
	private SaveLog saveLog;
	private StrategyManager strategy;
	private static BufferedReader reader;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		saveLog = new SaveLog();
		strategy = new StrategyManager();
	}

	@Test(expected = IOException.class)
	public void testSaveLogIOExceptionExpected() throws IOException {
		strategy.setStrategy(saveLog);
		String filePath = "";
		strategy.save(filePath);
		reader = new BufferedReader(new FileReader(filePath));
		assertEquals(saveLog.getCommandsLog(), reader.lines().collect(Collectors.joining("\n")));
	}

	@Test
	public void testSaveLog() throws IOException {
		saveLog.setCommandsLog("Log\ntest");
		strategy.setStrategy(saveLog);
		String filePath = folder.newFile("myfile1.txt").getAbsolutePath();
		strategy.save(filePath);
		reader = new BufferedReader(new FileReader(filePath));
		assertEquals(saveLog.getCommandsLog(), reader.lines().collect(Collectors.joining("\n")));
	}

	@AfterClass
	public static void tearDown() throws IOException {
		reader.close();
	}
}