package com.vendget.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver local_driver;
    public LoginPage(WebDriver remote_driver)
    {
    	local_driver=remote_driver;
    	PageFactory.initElements(remote_driver, this);
    }
    // identify the web elements in login page
    @FindBy(name="uid")
    @CacheLookup
    WebElement txtUserName;
    
    @FindBy(name="password")
    @CacheLookup
    WebElement txtPassword;
    
    @FindBy(name="btnLogin")
    @CacheLookup
    WebElement btnLogin;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[1]/ul[1]/li[10]/a[1]")
    @CacheLookup
    WebElement lnkLogout;
    
    // write action methods for web elements
    	public void SetUserName(String uname)
    	{
    		txtUserName.sendKeys(uname);
    	}
    	public void setPassword(String pwd)
    	{
    		txtPassword.sendKeys(pwd);
    	}
    	public void clickSubmit()
    	{
    		btnLogin.click();
    	}
    	
    	public void clickLogout()
    	{
    		try {
    	        // Wait until logout link is clickable
    	        WebDriverWait wait = new WebDriverWait(local_driver, java.time.Duration.ofSeconds(10));
    	        wait.until(ExpectedConditions.elementToBeClickable(lnkLogout));

    	        lnkLogout.click(); // normal click
    	    } catch (Exception e) {
    	        // fallback: force click using JavaScript
    	        ((JavascriptExecutor) local_driver).executeScript("arguments[0].click();", lnkLogout);
    	    }
    	}
}
