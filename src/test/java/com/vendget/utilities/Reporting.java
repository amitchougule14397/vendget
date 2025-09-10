package com.vendget.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    private static ThreadLocal<ExtentTest> logger = new ThreadLocal<ExtentTest>();

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); 
        String repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);
        sparkReporter.config().setDocumentTitle("Automation Test Report"); 
        sparkReporter.config().setReportName("Functional Test Report"); 
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Host Name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User ", "Amit");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        logger.set(extent.createTest(tr.getName()));
        logger.get().log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        logger.set(extent.createTest(tr.getName()));
        logger.get().log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + tr.getName() + ".png";
        File f = new File(screenshotPath);

        if (f.exists()) {
            // No try-catch needed here because addScreenCaptureFromPath does not throw IOException
            logger.get().fail("Screenshot is below:" + logger.get().addScreenCaptureFromPath(screenshotPath));
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        logger.set(extent.createTest(tr.getName()));
        logger.get().log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}
