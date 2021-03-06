package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Download_file {

  private RemoteWebDriver driver;
  public String Status = "failed";

  @BeforeMethod
  public void setup(Method m, ITestContext ctx) throws MalformedURLException {
    String username = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" : System.getenv("LT_USERNAME");
    String authkey = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");
    ;
    String hub = "@hub.lambdatest.com/wd/hub";

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("platformName", "windows 10");
        caps.setCapability("browserName", "chrome");

        //caps.setCapability("platformVersion", "14.2");


        caps.setCapability("build", "502_intermittent");
        caps.setCapability("name", "test01");
        caps.setCapability("network",true);
        caps.setCapability("driver_version", "102.0");
        caps.setCapability("console", "true");

        caps.setCapability("extendedDebuging", "true");
        caps.setCapability("selenium_version", "3.141.59");
        caps.setCapability("version", "102.0");
        caps.setCapability("video", "true");
        caps.setCapability("visual", "true");
        caps.setCapability("w3c", "true");

    //caps.setCapability("platform", "Windows 10");
    //caps.setCapability("browserName", "Chrome");
    //caps.setCapability("version", "100.0");
    //caps.setCapability("idleTimeout", "300");


    // Fixed IP
    //caps.setCapability("fixedIP", "10.242.33.74");
    //caps.setCapability("build", "TestNG With Java");
    //caps.setCapability("network",true);
    //caps.setCapability("seCdp",true);
    //caps.setCapability("w3c",true);
    //caps.setCapability("selenium_version","4.1.0");
    caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
//        caps.setCapability("plugin", "git-testng");


    String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
    caps.setCapability("tags", Tags);

    driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
    //System.out.println("https://" + username + ":" + authkey + hub);


  }

  @Test
  public void basicTest() throws InterruptedException {
    String spanText;
    try {

            for(int i=1;i<=5;i++) {
            System.out.println("baseUrl");


            String baseUrl = "https://fitstore.ee/";
            driver.get(baseUrl);

            //using submit method to submit the form. Submit used on password field
            driver.get(baseUrl);
            Thread.sleep(6000);
      System.out.println("Loading Url");
            }

      driver.get("https://chromedriver.storage.googleapis.com/index.html?path=2.0/");

      WebElement element = driver.findElement(By.xpath("/html/body/table/tbody/tr[7]/td[2]/a"));
      element.isDisplayed();
      element.click();
      Thread.sleep(20000);
      Assert.assertEquals(((JavascriptExecutor) driver).executeScript("lambda-file-exists=chromedriver_win32.zip"), true); //file exist check
      System.out.println("??? File exists");

//
//        // Let's also assert that the todo we added is present in the list.
//
//        spanText = driver.findElementByXPath("/html/body/div/div/div/ul/li[9]/span").getText();
//        Assert.assertEquals("Get Taste of Lambda and Stick to It", spanText);
      Status = "passed";
//        Thread.sleep(150);
    }
//
//        System.out.println("TestFinished");
    catch(InterruptedException e)
    {
      e.printStackTrace();

      System.out.println("File not downloaded");

    }


  }

  @AfterMethod
  public void tearDown() {
    driver.executeScript("lambda-status=" + Status);
    //driver.quit();
  }

}
