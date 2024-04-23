package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseClass.BaseClass;

public class ExtentReportManager extends BaseClass implements ITestListener {

	private ExtentSparkReporter extentSpark;
	private ExtentReports extent;
	private ExtentTest test;
	private String reportName;

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);

		String timeStamp = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss").format(new Date());
		reportName = "Test-Report-" + timeStamp + ".html";
		extentSpark = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + reportName);
		extentSpark.config().setDocumentTitle("Flight Book Automation Report");
		extentSpark.config().setReportName("Flight Booking Functional Test");
		extentSpark.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(extentSpark);

		extent.setSystemInfo("Application", "Flight Book");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		List<String> groups = context.getCurrentXmlTest().getIncludedGroups();

		if (!groups.isEmpty()) {

			extent.setSystemInfo("Groups", groups.toString());
		}

	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);

		test = extent.createTest(result.getTestClass().getName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);

		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName().toUpperCase() + " got successfully executed.!!");

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);

		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName().toUpperCase() + " got failed.!!");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String path = captureScreenShot(result.getName());
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName().toUpperCase() + " got skipped.!!");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);

		extent.flush();

		// complete all the test and report will be automatically open in browser

		String pathName = System.getProperty("user.dir") + "\\reports\\" + reportName;
		File report = new File(pathName);

		try {
			Desktop.getDesktop().browse(report.toURI());

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
