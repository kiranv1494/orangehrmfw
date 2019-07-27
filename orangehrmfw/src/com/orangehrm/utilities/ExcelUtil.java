package com.orangehrm.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	static Sheet sheet;
	public static void initializeExcel() throws IOException {
		
		Workbook wbook = null;
		String sheetName=ReadPropertyFile.readProperty("sheetName");

		String fileName = ReadPropertyFile.readProperty("excelPath");
		FileInputStream fis = null;

		File file = new File(fileName);
		try{
			fis = new FileInputStream(file);
		}
        catch(FileNotFoundException fn){
        	Log.error("Unable to find the excel file.Looks like given wrong excel path." +fn.getMessage());
        }
		

		String fileExtension = FilenameUtils.getExtension(fileName);

		if (fileExtension.equals("xlsx")) {
			wbook = new XSSFWorkbook(fis);
		} else if (fileExtension.equals("xls")) {
			wbook = new HSSFWorkbook(fis);
		} else {
			System.out.println("Invalid file format");
		}

		sheet = wbook.getSheet(sheetName);

		
	}
	
	public static String readData(int rowNum,int colNum){
		return sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
	}

}
