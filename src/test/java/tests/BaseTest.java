package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;

public class BaseTest {
    protected static ExtentReports extent;  // Shared ExtentReports instance

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Executing @BeforeSuite - Setup for Extent Reports");

        // Set up Extent Reports
        String reportPath = System.getProperty("user.dir") + "/target/ExtentReports/SuiteReport.html";
        File reportDir = new File(System.getProperty("user.dir") + "/target/ExtentReports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Your Name");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Executing @AfterSuite - Teardown Extent Reports");

        if (extent != null) {
            extent.flush(); // Write the report to the file
            System.out.println("Extent Reports flushed successfully.");
        }
    }
}
