package ddt;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ddt_2 
{
	@Test
	public void test1() throws IOException
    {
      FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"\\testdata\\data1.xlsx");
      XSSFWorkbook workbook = new XSSFWorkbook();
     XSSFSheet sheet = workbook.createSheet("data");
     
      XSSFRow row1 = sheet.createRow(0);
      row1.createCell(0).setCellValue("java");
      row1.createCell(1).setCellValue("19");
      row1.createCell(2).setCellValue("Automation");
      
      XSSFRow row2 = sheet.createRow(1);
      row2.createCell(0).setCellValue("python");
      row2.createCell(1).setCellValue("3");
      row2.createCell(2).setCellValue("Automation");
      
      XSSFRow row3 = sheet.createRow(2);
      row3.createCell(0).setCellValue("c#");
      row3.createCell(1).setCellValue("5");
      row3.createCell(2).setCellValue("Automation");
     
     workbook.write(file);
     workbook.close();
     file.close();   
    }
}
