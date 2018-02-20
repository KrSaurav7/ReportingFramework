package com.wipro.atm.webdriver.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestDataReader {

	FileInputStream file;
	Logger logger = LogManager.getRootLogger();
	public String path;
	public static FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	public HSSFSheet sheet;
	private static HSSFWorkbook workbook = null;
	// private HSSFRow row = null;
	// private HSSFCell cell = null;
	public static LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();

	public TestDataReader() {

		try {

			fis = new FileInputStream(Constants.TESTDATAPATH);
			workbook = new HSSFWorkbook(fis);
			// fis.close();
		} catch (Exception e) {
			logger.info("Please check the excel file and excel sheet"
					+ e.getMessage());
		}
	}

	/*
	 * Function Name : getEntityData Returns : Scenarios specified data in the
	 * form of ArrayList of ArrayList Strings Arguments : SheetName(Sheet Data
	 * to read),ScenarioID(The scenario against which data need to be fetched)
	 */

	public LinkedHashMap<String, String> getEntityData(String senarioName,
			String sheetName) throws Exception {

		int firstRowNumber, lastRowNumber, firstCellNumber, lastCellNumber;
		HSSFRow headerRow = null;
		HSSFRow tempRow = null;
		HSSFRow firstRow = null;
		ArrayList<String> arrayListData = null;

		ArrayList<String> arrayListScenario = null;
		ArrayList<ArrayList<String>> arrayListScenarios = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> arrayListsData = new ArrayList<ArrayList<String>>();
		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheet(sheetName);
		firstRowNumber = sheet.getFirstRowNum();
		lastRowNumber = sheet.getLastRowNum();

		firstRow = sheet.getRow(lastRowNumber);

		firstCellNumber = firstRow.getFirstCellNum();
		lastCellNumber = firstRow.getLastCellNum();

		for (int row = (firstRowNumber + 1); row <= lastRowNumber; row++) {
			tempRow = sheet.getRow(row);
			arrayListScenario = new ArrayList<String>();
			for (int col = (firstCellNumber); col < lastCellNumber; col++) {
				arrayListScenario.add(tempRow.getCell(col).toString());
			}
			arrayListScenarios.add(arrayListScenario);
		}
		for (ArrayList<String> individualScenarios : arrayListScenarios) {

			if (individualScenarios.get(0).equalsIgnoreCase(senarioName)) {
				arrayListData = new ArrayList<String>();
				for (int i = 1; i < individualScenarios.size(); i++) {
					headerRow = sheet.getRow(0);
					dataMap.put(headerRow.getCell(i).toString(),
							individualScenarios.get(i));
				}
				arrayListsData.add(arrayListData);
			}
		}
		return dataMap;
	}
}