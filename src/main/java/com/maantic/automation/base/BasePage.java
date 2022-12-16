package com.maantic.automation.base;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class BasePage {

     static String appUrl;
    protected  static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeSuite
    @Parameters({"browser"})
    public void getEnvironment(@Optional String browser){
        System.out.println("Testing started");

        try {
//            InputStream input = BasePage.class.getClassLoader().getResourceAsStream("common.properties");
//            if (input == null) {
//                System.out.println("Sorry, unable to find common.properties");
//                return;
//            }
//            Properties prop = new Properties();
//            // load a properties file from class path, inside static method
//            prop.load(input);
//            // get the property value and print it out
//            System.out.println(prop.getProperty("app"));
//            if(prop.getProperty("app")!=null) {
//                appUrl = prop.getProperty("app");
//            }else{
//                appUrl = prop.getProperty("defaulturl");
//            }
//            // System.out.println(prop.getProperty("additional"));
//            System.out.println(prop.getProperty("message"));
        	appUrl = "https://bfs.maanticpegaservices.com/prweb";

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if(browser==null || browser==" "){
            System.out.println("************  No profile is selected for browser from Maven.****************");
            System.out.println("************  Test is running on default browser Chrome.****************");
            browser="chrome";
        }
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Operating System", "Windows")
                        .put("Browser", browser)
                        .put("Application", appUrl)
                        .build());
    }


    @BeforeMethod
    @Parameters({"browser"})
    public void initializeDriver(@Optional String browser){
        if(browser==null || browser==" "){
            browser="chrome";
        }
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            System.setProperty("webdriver.chrome.silentOutput", "true");
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);

            options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
           // options.addArguments("--incognito");
            driver.set(new ChromeDriver(options));
            System.out.println("Chrome browser is opening....");
        }
        if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"null");
            //driver = new FirefoxDriver();
            driver.set(new FirefoxDriver());
            System.out.println("Firefox browser is opening....");
        }
        getDriver().manage().window().maximize();
        getDriver().get(appUrl);
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        System.out.println("About to zoom out");
        for (int i = 0; i < 3; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
    }

    public static synchronized WebDriver getDriver() {
        return driver.get();
    }

    //@AfterMethod
    public void closeDriver(){
        getDriver().quit();
    }
}
