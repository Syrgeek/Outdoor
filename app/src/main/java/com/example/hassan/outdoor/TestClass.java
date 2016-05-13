
package com.example.hassan.outdoor;


import android.util.Log;

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
        capabilities.setCapability("deviceName","Sony C2305");

            /* We initialize the Appium driver that will connect us to the Android device with
             * the capabilities we have just set. The URL we are providing is telling Appium we
             * are going to run the test locally. */
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }


    @Test
    public void testcaseLogin()throws  Exception
    {
        driver.findElementById("login_button").click();
        Assert.assertTrue(driver.findElementById("profile").isDisplayed());
    }

    @After
    public void testCaseTearDown()
    {
        if(driver != null)
            driver.quit();
    }
}
