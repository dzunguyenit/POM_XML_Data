package BigBom;

import java.util.ArrayList;
import java.util.Arrays;
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
		// Log In vào hệ thống
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=email]")));
		driver.findElement(By.cssSelector("input[name=email]")).sendKeys("qcbigbom@gmail.com");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=password]")));
		driver.findElement(By.cssSelector("input[name=password]")).sendKeys("123456");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.sign-in")));
		driver.findElement(By.cssSelector("button.sign-in")).click();

		Thread.sleep(4000);

		// Click bỏ popup
		driver.findElement(By.xpath("//div[@class='ant-modal-body']//*[name()='svg']")).click();

		// Click chọn all dữ liệu
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.icon-wrapper")));
		driver.findElement(By.cssSelector("span.icon-wrapper")).click();
		// Click chọn all dữ liệu
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-range-key='All time']")));
		driver.findElement(By.cssSelector("li[data-range-key='All time']")).click();

		Thread.sleep(3000);

		// Khai báo 1 cái ArrayList để lưu trữ tất cả các dòng dữ liệu trên page
		// Giả sử có 100 hàng cần tính thì nó lưu lại 100 phần tử đó: 1-50 of 100

		ArrayList<String> listNumber = new ArrayList<>();

		// Lấy ra chữ 100 trong chuỗi: 1-50 of 100 ( tức là tổng số phần tử)
		String sizePageText = driver.findElement(By.xpath("//li[@class='ant-pagination-total-text']/strong")).getText();
		// Sử dụng patten regex: (.*?) of (\\d+) để lấy ra được số 100

		int sizePage = getTextRegex("(.*?) of (\\d+)", sizePageText, 2);

		// PageIndex là chỉ số trang: trang 1, trang 2, trang 3....
		// Lấy % của 50: Giả sử có 125 phần tử thì 125%50 thì ra 2 thì khi đó ta phải
		// cộng thêm 1
		// Ngược lại: 150%50 = 3 thì ta lấy lun số 3

		int pageIndex = (sizePage % 50 != 0) ? (sizePage / 50 + 1) : (sizePage / 50);
		// Ý tưởng chạy từng trang: trang 1, trang 2, trang 3...
		for (int i = 0; i < pageIndex; i++) {

			// For j = 5 tức là luôn luôn scroll 5 lần để lấy được 50 phần tử trên 1 trang
			// trước khi thao tác
			for (int j = 0; j < 5; j++) {
				// div[class*=custom-footer] div[class=ant-table-body]: locator của thanh scroll
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.cssSelector("div[class*=custom-footer] div[class=ant-table-body]")));
				// Khởi tạo actions scroll
				EventFiringWebDriver scrollAction = new EventFiringWebDriver(driver);
				// Scroll xuống
				scrollAction.executeScript(
						"document.querySelector('div[class*=custom-footer] div[class=ant-table-body]').scrollTop = 150000");

				Thread.sleep(1500);
			}
			// List tất cả các phần tử trên 1 trang
			List<WebElement> listTextTmp = driver.findElements(By.xpath("//span[@class='amount-label__spent-text']"));

			// Duyệt từng phần tử và thay thế chuỗi
			// ví dụ: ₫6,675,310
			for (WebElement element : listTextTmp) {
				// ví dụ: ₫6,675,310 thì xóa đi chữ ₫
				String number = element.getText().replaceAll("₫", "");
				// Khi đó chuỗi còn tồn tại: 6,675,310 thì ta phải thay thế dấu phẩy để thành 1
				// số kiểu String
				if (number.contains(",")) {
					number = number.replaceAll(",", "");
				}
				// Đưa vào list phần tử chỉ chứa số nhưng kiểu vẫn là String
				listNumber.add(number);
			}
			// Kiểm tra: Nếu tổng các phần tử là 50 và button mũi tên sang phải enabel thì
			// click quang trang mới ngoặc lại thì ngắt( break)
			int pageSize = driver.findElements(By.xpath("//span[@class='amount-label__spent-text']")).size();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[title='Next Page']")));
			WebElement nextPageLbl = driver.findElement(By.cssSelector("li[title='Next Page']"));
			if (pageSize == 50 && nextPageLbl.isEnabled()) {
				nextPageLbl.click();
			} else {
				break;
			}

		}
// Nguyên đoạn này để chuyển nguyên mảng số kiểu String sang kiểu Int để thực hiện tính tổng
		int[] intTong = new int[listNumber.size()];
		for (int i = 0; i < listNumber.size(); i++) {
			intTong[i] = Integer.parseInt(listNumber.get(i));
			Arrays.toString(intTong);
		}

		long sum = 0;
// TÍnh tổng tất cả các phần tử trong mảng		
		for (int i = 0; i < intTong.length; i++) {
			if (intTong[i] != 0) {
				System.out.println(intTong[i]);
			}

			sum += intTong[i];
		}
		// Kiểm tra số lượng dòng
		Assert.assertEquals(listNumber.size(), 100);
		// So sánh tổng các số Total spent
		long expectRessult = 397660162L;
		Assert.assertEquals(sum, expectRessult);

	}
	// Hàm lấy ra tổng số dòng
	// 1-50 of 100: Muốn lấy ra số 100 ở sau thì sử dụng hàm này

	public static int getTextRegex(String regex, String text, int group) {
		String textRegex = "";
		Pattern patternRegex = Pattern.compile(regex);
		Matcher m = patternRegex.matcher(text);

		if (m.find()) {
			textRegex = m.group(group);
		}
		return Integer.parseInt(textRegex);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
