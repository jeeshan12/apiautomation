package restapi.automation.reports;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang3.SystemUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Singleton class for Creating the Extent Reports
 * 
 * @author Jeeshan
 *
 */
public class ExtentReportManager {

	private static ExtentReports extent;

	private ExtentReportManager() {

	}

	public synchronized static ExtentReports getExtentReporter() {

		if (extent == null)
			createExtentReportInstance();
		return extent;

	}

	private static void createExtentReportInstance() {
		Date d = new Date();
		String dateTimeStamp = d.toString().replace(" ", "_").replace(":", "_");

		String fileName = SystemUtils.getUserDir() + File.separator + "target" + File.separator + "ExtentReports"
				+ File.separator + dateTimeStamp + ".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTimeStampFormat("yyyy.MM.dd.HH.mm.ss");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Test Automation  Result");

		extent = new ExtentReports();
		extent.setSystemInfo("Application Name", "Rest API");
		extent.setSystemInfo("User Name", "Jeeshan");
		extent.setSystemInfo("Envirnoment", "QA");
		extent.attachReporter(htmlReporter);

	}
}
