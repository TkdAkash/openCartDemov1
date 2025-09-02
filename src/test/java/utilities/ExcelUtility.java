package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public String path;
	public FileInputStream fi;
	public FileOutputStream fo;
	public Workbook wb;
	public Sheet sh;
	public Row row;
	public Cell cell;
	public CellStyle style;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		row = sh.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		wb.close();
		return cellCount;
	}

	public String getCellData(String sheetName, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		row = sh.getRow(rownum);
		cell = row.getCell(cellnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			// TODO: handle exception
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public void setCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {

		File xFile = new File(path);
		if (!xFile.exists()) { // If file not exists then create new file
			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);

		// If sheet not exists then create new file
		if (wb.getSheetIndex(sheetName) == -1) {
			wb.createSheet(sheetName);
			sh = wb.getSheet(sheetName);
		}

		// If row not exists then create new file
		if (sh.getRow(rownum) == null) {
			sh.createRow(rownum);
			row = sh.getRow(rownum);
		}

		cell = row.createCell(cellnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);

		row = sh.getRow(rownum);
		cell = row.getCell(colnum);

		style = wb.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		row = sh.getRow(rownum);
		cell = row.getCell(colnum);

		style = wb.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
