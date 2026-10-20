package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public CellStyle style;
	String path;
	
	public XLUtility(String path) {
		this.path=path;
	}
	public int getRowCount(String sheetName) throws IOException{
		
		fi= new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet= workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
		
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet= workbook.getSheet(sheetName);
		row= sheet.getRow(rowNum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
		
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {

	    fi = new FileInputStream(path);
	    workbook = new XSSFWorkbook(fi);
	    sheet = workbook.getSheet(sheetName);
	    row = sheet.getRow(rowNum);

	    String data = "";

	    if (row != null && row.getCell(colNum) != null) {
	        data = row.getCell(colNum).toString();
	    }

	    workbook.close();
	    fi.close();

	    return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {

	    fi = new FileInputStream(path);
	    workbook = new XSSFWorkbook(fi);
	    sheet = workbook.getSheet(sheetName);

	    row = sheet.getRow(rowNum);

	    if (row == null) {
	        row = sheet.createRow(rowNum);
	    }

	    if (row.getCell(colNum) == null) {
	        row.createCell(colNum);
	    }

	    row.getCell(colNum).setCellValue(data);

	    fo = new FileOutputStream(path);
	    workbook.write(fo);

	    workbook.close();
	    fi.close();
	    fo.close();
	}
	
	
	public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException {

	    fi = new FileInputStream(path);
	    workbook = new XSSFWorkbook(fi);
	    sheet = workbook.getSheet(sheetName);
	    row = sheet.getRow(rowNum);

	    if (row == null) {
	        row = sheet.createRow(rowNum);
	    }

	    if (row.getCell(colNum) == null) {
	        row.createCell(colNum);
	    }

	    style = workbook.createCellStyle();
	    style.setFillForegroundColor(
	        org.apache.poi.ss.usermodel.IndexedColors.GREEN.getIndex()
	    );
	    style.setFillPattern(
	        org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND
	    );

	    row.getCell(colNum).setCellStyle(style);

	    fo = new FileOutputStream(path);
	    workbook.write(fo);

	    workbook.close();
	    fi.close();
	    fo.close();
	}
	
	public void fillRedColor(String sheetName, int rowNum, int colNum) throws IOException {

	    fi = new FileInputStream(path);
	    workbook = new XSSFWorkbook(fi);
	    sheet = workbook.getSheet(sheetName);
	    row = sheet.getRow(rowNum);

	    if (row == null) {
	        row = sheet.createRow(rowNum);
	    }

	    if (row.getCell(colNum) == null) {
	        row.createCell(colNum);
	    }

	    style = workbook.createCellStyle();
	    style.setFillForegroundColor(
	        org.apache.poi.ss.usermodel.IndexedColors.RED.getIndex()
	    );
	    style.setFillPattern(
	        org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND
	    );

	    row.getCell(colNum).setCellStyle(style);

	    fo = new FileOutputStream(path);
	    workbook.write(fo);

	    workbook.close();
	    fi.close();
	    fo.close();
	}
	
}
