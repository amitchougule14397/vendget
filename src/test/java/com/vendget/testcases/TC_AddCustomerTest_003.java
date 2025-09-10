package com.vendget.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vendget.pageobjects.AddCustomerPage;
import com.vendget.pageobjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{
    @Test
    public void addNewCustomer() throws InterruptedException, IOException
    {
    	LoginPage lp=new LoginPage(driver);
    	lp.SetUserName(username);
    	logger.info("username is provided");
    	lp.setPassword(password);
    	logger.info("password is provided");
    	lp.clickSubmit();
    	
    	Thread.sleep(3000);
    	
    	AddCustomerPage addcust=new AddCustomerPage(driver);
    	addcust.clickAddNewCustomer();
    	logger.info("providing customer details...");
    	addcust.custName("amar");
    	addcust.custgender("male");
    	addcust.custdob("10", "10", "1985");
    	Thread.sleep(3000);
    	addcust.custaddress("INDIA");
    	addcust.custcity("HYD");
    	addcust.custstate("AP");
    	addcust.custpinno("500074");
    	addcust.custtelephoneno("1234567890");
    	
    	String email = randomeString()+"@gmail.com";
    	addcust.custemailid(email);
    	addcust.custsubmit();
    	
    	Thread.sleep(3000);
    	
    	logger.info("validation started...");
    	
    	boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
    	if(res==true)
    	{
    		Assert.assertTrue(true);
    		logger.info("testcase is passed");
    	}
    	else
    	{
    		logger.info("testcase is failed");
    		captureScreen(driver, "addNewCustomer");
    		Assert.assertTrue(false);
    	}
    }
    
}
