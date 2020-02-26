package com.CommonPages;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
			FirefoxDriverManager.getInstance().version(version).setup();
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

	public Element readXmlFile(String pathFileXml) {
		Element nodeElement = null;
		try {

			File xmlFile = new File(pathFileXml);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getChildNodes();

			System.out.println("----------------------------");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				System.out.println("\nCurrent Element : " + nNode.getNodeName());
				nodeElement = (Element) nNode;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeElement;
	}

	public String getData(Element nodeElement, String value) {
		return nodeElement.getElementsByTagName(value).item(0).getTextContent().trim();

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