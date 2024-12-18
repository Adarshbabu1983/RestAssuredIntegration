package tests;

import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Utils;

import java.sql.ResultSet;

public class DatabaseTest extends BaseTest {
    private ExtentTest testLogger;

    @BeforeClass
    public void setup() throws Exception {
        testLogger = extent.createTest("Database Test", "Validating Database Values");
        testLogger.info("Setting up database connection...");
        Utils.connectToDatabase("config/config.properties");
    }

    @Test
    public void testSpecificRowValue() throws Exception {
        testLogger.info("Executing testSpecificRowValue...");

        String query = "SELECT employee_name FROM Employee WHERE employee_id = 1";
        ResultSet resultSet = Utils.executeQuery(query);

        if (resultSet.next()) {
            String value = resultSet.getString("employee_name");
            Assert.assertEquals(value, "John Doe", "Mismatch in employee name.");
            testLogger.pass("Employee name validated successfully.");
        } else {
            testLogger.fail("No data returned for the query.");
            Assert.fail("No result returned from query.");
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        testLogger.info("Closing database connection....");
        Utils.closeConnection();
    }
}
