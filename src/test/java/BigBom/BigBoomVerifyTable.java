package BigBom;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.icon-wrapper")));
		driver.findElement(By.cssSelector("span.icon-wrapper")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-range-key='All time']")));
		driver.findElement(By.cssSelector("li[data-range-key='All time']")).click();

		Thread.sleep(3000);

		List<String> listNumber = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.cssSelector("div[class*=custom-footer] div[class=ant-table-body]")));

				EventFiringWebDriver scrollAction = new EventFiringWebDriver(driver);

				scrollAction.executeScript(
						"document.querySelector('div[class*=custom-footer] div[class=ant-table-body]').scrollTop = 150000");

				Thread.sleep(1500);
			}

			List<WebElement> listTextTmp = driver.findElements(By.xpath("//span[@class='amount-label__spent-text']"));

			for (WebElement element : listTextTmp) {
				String number = element.getText().replaceAll("₫", "");
				String numberFormat = "";
				if (number.contains(",")) {
					numberFormat = number.replaceAll(",", "");
				}
				listNumber.add(numberFormat);
			}

			int sizePage = driver.findElements(By.xpath("//span[@class='amount-label__spent-text']")).size();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[title='Next Page']")));
			WebElement nextPageLbl = driver.findElement(By.cssSelector("li[title='Next Page']"));
			if (sizePage == 50 && nextPageLbl.isEnabled()) {
				nextPageLbl.click();
			}

		}

		int sum = 0;

		for (int i = 0; i < listNumber.size(); i++) {
			sum += Integer.parseInt(listNumber.get(i));
		}
		Assert.assertEquals(listNumber.size(), 148);

		Assert.assertEquals(sum, "3976601621");

	}

	public String getTextRegex(String regex, String text, int group) {
		String textRegex = "";
		Pattern patternRegex = Pattern.compile(regex);
		Matcher m = patternRegex.matcher(text);

		if (m.find()) {
			textRegex = m.group(group);
		}
		return textRegex.replaceAll(",", "");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
