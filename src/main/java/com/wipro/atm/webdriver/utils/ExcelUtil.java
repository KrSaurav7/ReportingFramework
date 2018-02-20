package com.wipro.atm.webdriver.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static Logger logger = LogManager.getRootLogger();
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static FileInputStream ExcelFile;
	
	/**
	 * This method is to set the File path and open the excel file.
	 * @param path - path of the ExcelFIle
	 * @param sheetName - SheetName
	 */
	public static void setExcelFile(String path,String sheetName){
		try{
			ExcelFile = new FileInputStream(path);//open Excel file
			ExcelWBook = new XSSFWorkbook(ExcelFile);//Access the required test
			ExcelWSheet = ExcelWBook.getSheet(sheetName);// data sheet
		}catch(IOException exec){
			logger.error(exec.getMessage());
		}
	}
	/**
	 * this method is to get the data
	 * @param filePath - Excel sheet path
	 * @param sheetName - Sheet name
	 * @param iTestCaseRow - Test case row
	 * @param columnStart - start column of the test case
	 * @param columnEnd - end column of the test case
	 * @return
	 */
	public static Object[][] getTableArray(String filePath, String sheetName, int iTestCaseRow,int columnStart, int columnEnd){
		String[][] tabArray = null;
		try{
			FileInputStream ExcelFile = new FileInputStream(filePath);//open Excel file
			ExcelWBook = new XSSFWorkbook(ExcelFile);//Access the required test
			ExcelWSheet = ExcelWBook.getSheet(sheetName);// data sheet
			int ci = 0, cj = 0;
			int totalRows = 1;
			int totalColumn = columnEnd - columnStart;
			tabArray = new String[totalRows][totalColumn + 1];
			for (int j = columnStart; j <= columnEnd; j++, cj++) {
				tabArray[ci][cj] = getCellData(iTestCaseRow, j);
				logger.info(tabArray[ci][cj]);
			}
		}catch(IOException exec){
			logger.error("Could not read the excel sheet");
			logger.error(exec.getMessage());
		}
		return (tabArray);
	}
	/**
	 * This method is to read the test data from excel cell
	 * @param rowNum - row number
	 * @param colNum - column number
	 * @return - cell value
	 */
	public static String getCellData(int rowNum, int colNum){
		try{
			Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);
			String cellData = Cell.getStringCellValue();
			return cellData;
		}catch(Exception exc){
			return "logger.error(exc.getMessage())";
		}	
	}
	/**
	 * This method is to get the test case name
	 * @param sTestCase
	 * @return
	 */
	public static String getTestCaseName(String sTestCase){
		String value = sTestCase;
		int posi = value.indexOf("@");
		value = value.substring(0, posi);
		posi = value.lastIndexOf(".");
		value = value.substring(posi + 1);
		return value;	
	}
	/**
	 * This method is to get the row contains
	 * @param sTestCaseName - test case name
	 * @param colNum - column number
	 * @return
	 */
	public static int getRowContains(String sTestCaseName, int colNum){
		int i;
		int rowCount = ExcelUtil.getRowUsed();
		for (i = 0; i <= rowCount; i++) {
			if(ExcelUtil.getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)){
				break;
			}
		}
		return i;
	}
	/**
	 * This method is to get the column contains
	 * @param rowNum - row number
	 * @param colName - column name
	 * @return
	 */
	public static int getColContains(int rowNum, String colName){
		int i;
		int columnCount = ExcelWSheet.getRow(0).getLastCellNum();
		for(i = 0; i <= columnCount; i++){
			if(ExcelUtil.getCellData(rowNum, i).equalsIgnoreCase(colName)){
				break;
			}
		}
		return i;
	}
	/**
	 * This method is to get the excel sheet rows
	 * @return
	 */
	public static int getRowUsed(){
		int rowCount = ExcelWSheet.getLastRowNum();
		return rowCount;
	}
	/**
	 * 
	 * @param excelPath
	 * @param sheetName
	 * @param columnName
	 * @param filterColumnName
	 * @param filterColumnValue
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws FileNotFoundException
	 * @throws IOException
	
	public static String getCellDataBasedOnRowValueAndColumnName(String excelPath, String sheetName, String columnName,String filterColumnName, String filterColumnValue) throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException{
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File(excelPath)));
		Sheet sheet = wb.getSheet(sheetName);
		Iterator<Row> rows = sheet.rowIterator();
		Map<String, Integer> headers = new HashMap<String, Integer>();
		if(rows.hasNext()){
			Row row = rows.next();
			for(Cell cell : row){
				headers.put(cell.getStringCellValue(), cell.getColumnIndex());
			}
		}
		return filterColumnValue;
		
	}
	 */
	/**
	 * This method is to close the excel file
	 */
	public static void closeExcelFile(){
		try {
			ExcelFile.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
}
	
