package com.maantic.automation.utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelDataProvider {

    @DataProvider(name = "ExcelTestData",parallel = false)
    public static Object[] getLoginTestData(Method method){
        String testCase = method.getName();

        List<Map<String,String>> list = ExcelUtils.getExcelData("Contact");
        List<Map<String,String>> smallList = new ArrayList<>();

        for(int i=0;i<list.size();i++){
            System.out.println("Test Case Name: "+list.get(i).get("TCName"));
            if(list.get(i).get("TCName").trim().equalsIgnoreCase(testCase)){
                smallList.add(list.get(i));
            }
        }
        return smallList.toArray();
    }

    @DataProvider(name = "ExcelTestDataGBT",parallel = false)
    public static Object[] getLoginTestDataGBT(Method method){
        String testCase = method.getName();

        List<Map<String,String>> list = ExcelUtils.getExcelData(Constants.EXCEL_SHEET_NAME);
        List<Map<String,String>> smallList = new ArrayList<>();

        for(int i=0;i<list.size();i++){
            System.out.println("Test Case Name: "+list.get(i).get("RuleType"));
            if(list.get(i).get("RuleType").trim().equalsIgnoreCase(testCase)){
                smallList.add(list.get(i));
                break;
            }
        }
        return smallList.toArray();
    }
}
