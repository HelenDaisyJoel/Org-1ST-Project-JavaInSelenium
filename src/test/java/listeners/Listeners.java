package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.sql.Driver;
import java.sql.DriverManager;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pageObjects.BasePage;
 // Assuming you have a utility class to manage WebDriver

public class Listeners implements ITestListener {

   

	private ExtentReports extent;
    private ExtentTest test;
    private BasePage basePage; // Instance of BasePage

    @Override
    public void onStart(ITestContext context) {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Video Platform Test Results");
        reporter.config().setDocumentTitle("Automation Test Report");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Helen");
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());

        // Initialize the BasePage with WebDriver
//        WebDriver driver = DriverManager.getDriver(); // Get the WebDriver instance
//        Driver driver = DriverManager.getDriver("chrome");

//        basePage = new BasePage(driver);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
//        test.addScreenCaptureFromPath(getMessage(), getMessage());
        // Capture screenshot using BasePage's getScreenshot method
        String filePath=basePage.getScreenshot(result.getMethod().getMethodName());
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
