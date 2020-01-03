package restapi.automation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import restapi.automation.reports.ExtentReportTestManager;

public class TestListener implements ITestListener {


	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReportTestManager.getTest().pass("Test passed");
		ExtentReportTestManager.getTest().pass(MarkupHelper.createLabel(result.getMethod().getMethodName()+" passed !",ExtentColor.GREEN));

	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		ExtentReportTestManager.getTest().fail(result.getThrowable());
		ExtentReportTestManager.getTest().fail(MarkupHelper.createLabel(result.getMethod().getMethodName()+" failed !",ExtentColor.RED));

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReportTestManager.getTest().skip(result.getThrowable());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentReportTestManager.endTest();

	}

}
