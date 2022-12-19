package com.maantic.automation.utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    private ExcelUtils() {

    }

    public static List<Map<String, String>> getExcelData(String sheetName) {
        List<Map<String, String>> list = null;
        //copy files
        File sourceExcel = new File(Constants.TEST_DATA_SHEET_PATH);
        File dstExcel = new File(Constants.TEST_OUT_DATA_SHEET_PATH);
        try {
            FileUtils.copyFile(sourceExcel, dstExcel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream fs;

        try {
            // System.out.println("Data File"+Constants.TEST_DATA_SHEET_PATH);
            fs = new FileInputStream(Constants.TEST_DATA_SHEET_PATH);
            XSSFWorkbook wb = new XSSFWorkbook(fs);
            XSSFSheet wSheet = wb.getSheet(sheetName);

            int lastRowNum = wSheet.getLastRowNum();
            int lastColNum = wSheet.getRow(0).getLastCellNum();

            Map<String, String> dataMap = null;
            list = new ArrayList<>();

            for (int i = 1; i <= lastRowNum; i++) {
                dataMap = new HashMap<>();
                String value = "";
                for (int k = 0; k < lastColNum; k++) {
                    String key = wSheet.getRow(0).getCell(k).getStringCellValue();
                    if (wSheet.getRow(i).getCell(k) == null) //getCellType() == CellType.BLANK || wSheet.getRow(i).getCell(k).getStringCellValue().trim().isEmpty())
                        value = "NA";
                    else value = wSheet.getRow(i).getCell(k).getStringCellValue();
                    dataMap.put(key, value);
                }
                list.add(dataMap);
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeExcelData(String writeOutput, String ruleType, int colNum) {
        XSSFWorkbook workbook = null;
        try {
            FileInputStream file = new FileInputStream(new File(Constants.TEST_DATA_SHEET_PATH));
            workbook = new XSSFWorkbook(file);
            XSSFSheet wSheet = workbook.getSheet(Constants.EXCEL_SHEET_NAME);

            int lastRowNum = wSheet.getLastRowNum();
            //int lastColNum = wSheet.getRow(0).getLastCellNum();

            for (int i = 1; i <= lastRowNum; i++) {
                //check if current row's ruletype is same & pass/fail is NULL
                if (wSheet.getRow(i).getCell(0).toString().equals(ruleType) && wSheet.getRow(i).getCell(18) == null){
                    XSSFCell cell = wSheet.getRow(i).createCell(colNum);
                    //XSSFCell cell = wSheet.getRow(i).getCell(colNum);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(writeOutput);
                    file.close();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream out = new FileOutputStream(new
                    File(Constants.TEST_DATA_SHEET_PATH));
            workbook.write(out);
            workbook.close();
            out.close();
            System.out.println("Output generated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
