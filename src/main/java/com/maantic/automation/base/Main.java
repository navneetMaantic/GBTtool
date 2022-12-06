package com.maantic.automation.base;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TestNG runner=new TestNG();
        List<String> suitefiles=new ArrayList<String>();
//        suitefiles.add(System.getProperty("user.dir")+"\\testng.xml");
        suitefiles.add(System.getProperty("C:\\Users\\User\\IdeaProjects\\pega-automation-framework\\testng.xml"));
        runner.setTestSuites(suitefiles);
        runner.run();

//        org.testng.TestNG.main(args);
    }
}
