
package com.example.hassan.outdoor;


import android.util.Log;
import android.widget.TextView;

import junit.framework.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.*;
import java.io.*;



public class TestClass {
    AndroidDriver driver;

    @Before
    public void setUp() throws Exception {

            /* Here we specify the capabilities required by the Appium server.
             * We have already manually specified most of these through the
             * Appium server interface (apk path etc.). */
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Samsung SM-N9005");

            /* We initialize the Appium driver that will connect us to the Android device with
             * the capabilities we have just set. The URL we are providing is telling Appium we
             * are going to run the test locally. */
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }


    @Test
    public void testcaseLogin()throws  Exception
    {
        driver.findElementById("existing_account").click();
        //Assert.assertTrue(driver.findElements(By.id("login_email")).size() > 0);
        driver.findElement(By.id("com.example.hassan.outdoor:id/login_email")).sendKeys("test");
        //driver.findElement(By.xpath("//android.widget.EditText[@text='test']")).click();
        //driver.findElement(By.xpath("//android.widget.EditText[@text='test']")).click();
        driver.findElement(By.id("com.example.hassan.outdoor:id/login_password")).sendKeys("test");
        driver.findElementById("login_button").click();
        boolean bol = driver.findElement(By.id("username")).isDisplayed();
        Assert.assertTrue(bol);
    }

    @After
    public void testCaseTearDown()
    {
        if(driver != null)
            driver.quit();
    }
}
