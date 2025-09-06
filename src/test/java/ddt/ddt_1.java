package ddt;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ddt_1 
{
	@Test
	public void test1() throws IOException
    {
      FileInputStream file = new FileInputStream("C:\\Users\\AMIT\\eclipse-workspace\\vendget\\testdata\\data.xlsx");
      XSSFWorkbook workbook = new XSSFWorkbook(file);
     XSSFSheet sheet = workbook.getSheet("Sheet1"); //XSSFSheet Sheet= workbook.getSheetAt(0);
     int totalRows = sheet.getLastRowNum();
     int totalCells = sheet.getRow(0).getLastCellNum();
     System.out.println("number of rows:"+ totalRows);
     System.out.println("number of cells:"+ totalCells);
     for(int r=0; r<=totalRows; r++)
      {
       XSSFRow currentRow=sheet.getRow(r);
       for(int c=0; c<totalCells; c++)
          {
    	XSSFCell cell=currentRow.getCell(c);
    	System.out.println(cell.toString()+"\t");
           }
       System.out.println();
      }
     workbook.close();
     file.close();   
    }
}
