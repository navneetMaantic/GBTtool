package com.maantic.automation.tests;

import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class TestRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TestListenerAdapter tla = new TestListenerAdapter();
//    	TestNG testng = new TestNG();
//    	testng.setTestClasses(new Class[] { GBTDecisionTableTest.class });
//    	testng.addListener(tla);
//    	testng.run();
    	
//    	TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add("C:\\Users\\User\\IdeaProjects\\pega-automation-framework\\testng.xml");
//        suites.add("c:/tests/testng2.xml");
        testng.setTestSuites(suites);
        testng.run();

	}

}
