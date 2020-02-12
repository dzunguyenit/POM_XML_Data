package com.CommonPages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import ObjectPageJson.AbstractObJectJson;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;

public class CommonTestcases {
	WebDriver driver;
	protected final Log log;

	protected CommonTestcases() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver openMultiBrowser(String browser, String url, String version) {
		if (browser.equals("ie")) {
			InternetExplorerDriverManager.getInstance().version(version).setup();
			driver = new InternetExplorerDriver();
		} else if (browser.equals("chrome")) {
			ChromeDriverManager.getInstance().version(version).setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extendsions");
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			FirefoxDriverManager.getInstance().setup();
			driver = new FirefoxDriver();
		}
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public String randomUniqueNumber() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Long ExtentEmail = timestamp.getTime();
		return ExtentEmail.toString();
	}

	public AbstractObJectJson getDataJson(String JsonFile) {
		String json = readFile(JsonFile);
		return new Gson().fromJson(json, AbstractObJectJson.class);
	}

	public String readFile(String filename) {
		String result = "";
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String randomEmail() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		String email = dateFormat.format(date);
		return email;
	}

	protected void verifyTrue(boolean condition) {
		Assert.assertTrue(condition);
	}

	protected void verifyFalse(boolean condition) {
		Assert.assertFalse(condition);
	}

	protected void verifyEquals(String actual, String expected) {
		Assert.assertEquals(actual, expected);
	}

	public void closeBrowser() {
		driver.quit();
	}

}