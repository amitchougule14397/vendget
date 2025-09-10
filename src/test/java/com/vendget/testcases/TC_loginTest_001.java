package com.vendget.testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.vendget.pageobjects.LoginPage;

public class TC_loginTest_001 extends BaseClass {
    @Test
    public void loginTest() throws IOException {
        logger.info("URL is opened");

        LoginPage lp = new LoginPage(driver);
        lp.SetUserName(username);
        logger.info("Entered username");

        lp.setPassword(password);
        logger.info("Entered password");

        lp.clickSubmit();
        logger.info("Clicked submit button");

        if (driver.getTitle().equals("GTPL Bank Manager HomePage")) {
            logger.info("✅ Login test passed");
            Assert.assertTrue(true);
        } else {
            captureScreen(driver, "loginTest");
            logger.error("❌ Login test failed");
            Assert.fail("Page title mismatch");
        }
    }
}
