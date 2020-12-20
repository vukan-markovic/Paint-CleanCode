package test.testStrategy;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import strategy.SaveLog;
import strategy.StrategyManager;

public class TestSaveLog {
	private SaveLog saveLog;
	private StrategyManager strategyManager;
	private static BufferedReader buffer;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		saveLog = new SaveLog();
		strategyManager = new StrategyManager();
	}

	@Test(expected = IOException.class)
	public void testSaveLogIOExceptionExpected() throws IOException {
		strategyManager.setStrategy(saveLog);
		String filePath = "";
		strategyManager.save(filePath);
		buffer = new BufferedReader(new FileReader(filePath));
		assertEquals(saveLog.getLog(), buffer.lines().collect(Collectors.joining("\n")));
	}

	@Test
	public void testSaveLog() throws IOException {
		saveLog.setLog("Log\ntest");
		strategyManager.setStrategy(saveLog);
		String filePath = folder.newFile("myfile1.txt").getAbsolutePath();
		strategyManager.save(filePath);
		buffer = new BufferedReader(new FileReader(filePath));
		assertEquals(saveLog.getLog(), buffer.lines().collect(Collectors.joining("\n")));
	}

	@AfterClass
	public static void cleanUp() throws IOException {
		buffer.close();
	}
}