package ddt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ddt_3 
{
	@Test
	public void test1() throws IOException
    {
      FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"\\testdata\\data2.xlsx");
      XSSFWorkbook workbook = new XSSFWorkbook();
     XSSFSheet sheet = workbook.createSheet("DynamicData");
     Scanner sc = new Scanner(System.in);
     
      System.out.println("enter how many rows?");
      int noOfrows = sc.nextInt();
      
      System.out.println("enter how many cells?");
      int noOfcells = sc.nextInt();
      
      for(int r=0; r<=noOfrows; r++)
      {
    	  XSSFRow currentRow=sheet.createRow(r);
         for(int c=0; c<noOfcells; c++)
         {
        	 XSSFCell cell = currentRow.createCell(c);
        	 cell.setCellValue(sc.next());
         }
      }
     workbook.write(file); //attach workbook to the file
     workbook.close();
     file.close();   
     System.out.println("file is created...");
    }
}
