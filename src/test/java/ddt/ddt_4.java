package ddt;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ddt_4 
{
	@Test
	public void test1() throws IOException
    {
      FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"\\testdata\\data3.xlsx");
      XSSFWorkbook workbook = new XSSFWorkbook();
     XSSFSheet sheet = workbook.createSheet("Data");
     
      XSSFRow row = sheet.createRow(2);
      XSSFCell cell = row.createCell(3);
      cell.setCellValue("WELCOME");
      
      
     workbook.write(file); //attach workbook to the file
     workbook.close();
     file.close();   
     System.out.println("file is created...");
    }
}
