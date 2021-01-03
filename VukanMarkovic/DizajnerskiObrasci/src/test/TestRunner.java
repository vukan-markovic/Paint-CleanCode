package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String[] args) {
		Result testsResult = JUnitCore.runClasses(TestSuite.class);

		for (Failure testFailure : testsResult.getFailures())
			System.out.println(testFailure.toString());

		System.out.println(testsResult.wasSuccessful());
	}
}