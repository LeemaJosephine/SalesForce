package util;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] readExcelSheet(String excelfile) throws IOException {
		
		XSSFWorkbook book=new XSSFWorkbook("./data/"+excelfile+".xlsx");
		XSSFSheet sheet = book.getSheetAt(0);
		int rowcount = sheet.getLastRowNum(); //2
		short columnCount = sheet.getRow(0).getLastCellNum(); //2
		
		String [][] data= new String[rowcount][columnCount]; // data[2][2]
		for (int i = 1; i <= rowcount; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < columnCount; j++) {
				XSSFCell cell = row.getCell(j);
				//System.out.println(cell.getStringCellValue());
				data[i-1][j]=cell.getStringCellValue();   // [0][0] - leema [1][1] abc 
				
			}
		}
		book.close();
		return data;
	}


}
