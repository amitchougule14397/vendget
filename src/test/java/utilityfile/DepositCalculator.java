package utilityfile;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DepositCalculator
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get("https://www.cit.com/cit-bank/resources/calculators/certificate-of-deposit-calculator");
	    driver.manage().window().maximize();
	    
	    String filePath=System.getProperty("user.dir")+"\\testdata\\caldata1.xlsx";
	    
	    int rows = excelutils.getRowCount(filePath,"Sheet1");
	    
	    for(int i=1; i<=rows; i++)
	    {
	    	// read data from excel 
	    	String amount = excelutils.getCellData(filePath, "Sheet1", i, 0);
	    	String cd = excelutils.getCellData(filePath, "Sheet1", i, 1);
	    	String interest = excelutils.getCellData(filePath, "Sheet1", i, 2);
	    	String compound = excelutils.getCellData(filePath, "Sheet1", i, 3);
	    	String exp_total = excelutils.getCellData(filePath, "Sheet1", i, 4);
	    	
	    	//clear the data
	    	driver.findElement(By.xpath("//input[@ id='mat-input-0']")).clear();
	    	driver.findElement(By.xpath("//input[@ id='mat-input-1']")).clear();
	    	driver.findElement(By.xpath("//input[@ id='mat-input-2']")).clear();
	    	Thread.sleep(3000);
	    	
	    	// pass above data into application
	    	driver.findElement(By.xpath("//input[@ id='mat-input-0']")).sendKeys(amount);
	    	driver.findElement(By.xpath("//input[@ id='mat-input-1']")).sendKeys(cd);
	    	driver.findElement(By.xpath("//input[@ id='mat-input-2']")).sendKeys(interest);
	    	Select perdrp = new Select(driver.findElement(By.xpath("//div[@id='mat-select-value-1']")));
	    	perdrp.selectByVisibleText(compound);
	    	
	    	driver.findElement(By.xpath("//button[@ id='CIT-chart-submit']")).click();
	    	
	    	// validation
	    	String act_total=driver.findElement(By.xpath("//span[@id='displayTotalValue']")).getText();
	    	
	    	if(Double.parseDouble(exp_total)==Double.parseDouble(act_total))
            {
	           System.out.println("Test passed");
	           excelutils.setCellData(filePath, "Sheet1", i, 6, "passed");
	           excelutils.fillGreenColor(filePath, "Sheet1", i, 6);   
            }
            else
            {
 	           System.out.println("Test failed");
 	           excelutils.setCellData(filePath, "Sheet1", i, 6, "failed");
 	           excelutils.fillRedColor(filePath, "Sheet1", i, 6);
             }
	    	Thread.sleep(3000);
	    	driver.quit();
	    }   // ending of for loop
	} // ending of main method
}//ending of class
