package restapi.automation.reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


/**
 * Class responsible for starting and flusing the Test
 * 
 * @author Jeeshan
 *
 */
public class ExtentReportTestManager {
	private static ExtentReports extent = ExtentReportManager.getExtentReporter();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public static synchronized ExtentTest createTest(final String name, final String description) {
		ExtentTest extentTest = extent.createTest(name, description);
		test.set(extentTest);
		return getTest();
	}

	public static synchronized ExtentTest getTest() {
		return test.get();
	}
	
	public static synchronized void endTest() {
		extent.flush();
	}
}
