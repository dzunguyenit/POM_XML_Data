package SvgComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class SvgElement {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		ChromeDriverManager.getInstance().version("79.0.3945.36").setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void testcase_01() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.get("https://www.w3schools.com/howto/howto_google_charts.asp");

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//iframe[@src='tryhow_google_pie_chart.htm']")));
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@src='tryhow_google_pie_chart.htm']")));
		String expectedTooltip = "Github";

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][4]/*[name()='text']")));
		WebElement github = driver.findElement(By.xpath("//*[name()='svg']/*[name()='g'][4]/*[name()='text']"));
		github.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][4]/*[name()='text']")));

		WebElement gitHubText = driver.findElement(By.xpath("//*[name()='svg']/*[name()='g'][4]/*[name()='text']"));
		String actualTooltip = gitHubText.getText();

		// Assert the tooltip's value is as expected
		System.out.println("Actual Title of Tool Tip: " + actualTooltip);
		if (actualTooltip.equals(expectedTooltip)) {
			System.out.println("Test Case Passed");
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}