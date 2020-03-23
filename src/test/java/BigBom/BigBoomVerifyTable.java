package BigBom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class BigBoomVerifyTable {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		ChromeDriverManager.getInstance().version("80.0.3987.106").setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);
		driver.get("https://beta-app.bigbom.net/");
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_LoginWithEmptyInformation() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=email]")));
		driver.findElement(By.cssSelector("input[name=email]")).sendKeys("qcbigbom@gmail.com");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=password]")));
		driver.findElement(By.cssSelector("input[name=password]")).sendKeys("123456");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.sign-in")));
		driver.findElement(By.cssSelector("button.sign-in")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='ant-modal-body']//*[name()='svg']")).click();

		int size = 50 / 10;
		for (int i = 0; i < size; i++) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("div[class*=custom-footer] div[class=ant-table-body]")));

			EventFiringWebDriver scrollAction = new EventFiringWebDriver(driver);

			scrollAction.executeScript(
					"document.querySelector('div[class*=custom-footer] div[class=ant-table-body]').scrollTop = 150000");

			Thread.sleep(1500);
		}

		List<WebElement> listItems = driver.findElements(By.cssSelector("span[class='amount-label__spent-text']"));
		int sizeListItems = listItems.size();

		Assert.assertEquals(sizeListItems, 50);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
