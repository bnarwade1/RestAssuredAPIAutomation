package api.utilities;

import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener, ISuiteListener {

    public static ExtentReports extent;
    public static ExtentTest test;

    @Override
    public void onStart(ISuite suite) {

        String path = System.getProperty("user.dir")
                + "/reports/ExtentReport.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        reporter.config().setDocumentTitle("API Automation Report");
        reporter.config().setReportName("User API Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName());
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test Skipped");

        if (result.getThrowable() != null) {
            test.log(Status.SKIP, result.getThrowable());
        }
    }

    @Override
    public void onFinish(ISuite suite) {

        extent.flush();
    }
}