package com.lawencon.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author lawencon05
 */
public class ExcelUtil {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFCell cell;
	private ByteArrayOutputStream bos;
	private XSSFCellStyle boldStyle;

	public void initRead(String sheetName, InputStream file) throws Exception {
		if (workbook == null)
			workbook = new XSSFWorkbook(file);

		sheet = workbook.getSheet(sheetName);
	}

	public void initWrite(String... sheetNames) throws Exception {
		workbook = new XSSFWorkbook();
		for (String sheetName : sheetNames) {
			workbook.createSheet(sheetName);
		}
		bos = new ByteArrayOutputStream();
	}

	public String getCellData(int rowNumber, int cellNumber) {
		String result = null;
		try {
			cell = sheet.getRow(rowNumber).getCell(cellNumber);
			if (cell != null) {
				CellType type = cell.getCellType();
				switch (type) {
				case STRING:
					result = cell.getRichStringCellValue().toString();
					break;
				case NUMERIC:
					result = String.valueOf(cell.getNumericCellValue());
					break;
				case BOOLEAN:
					result = String.valueOf(cell.getBooleanCellValue());
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getRowCountInSheet() {
		return sheet.getLastRowNum() + 1;
	}

	public int getColCountInRow(int row) {
		int col = sheet.getRow(row).getLastCellNum();
		return col;
	}

	public void setCellValue(int rowNumber, List<String> cellValues, boolean isBold) throws Exception {
		Row row = sheet.createRow(rowNumber);
		for (int i = 0; i < cellValues.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(cellValues.get(i));

			if (isBold)
				cell.setCellStyle(boldStyle());
		}
	}

	public void setCellValue(String sheetName, int rowNumber, List<String> cellValues, boolean isBold)
			throws Exception {
		sheet = workbook.getSheet(sheetName);
		setCellValue(rowNumber, cellValues, isBold);
	}

	public byte[] getByteArrayFile() throws Exception {
		workbook.write(bos);
		bos.close();
		return bos.toByteArray();
	}

	private XSSFCellStyle boldStyle() {
		if (boldStyle == null) {
			boldStyle = workbook.createCellStyle();
			XSSFFont fontBold = workbook.createFont();
			fontBold.setBold(true);
			boldStyle.setFont(fontBold);
		}
		return boldStyle;
	}
}