package com.bankguru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.CommonPages.CommonFuntions;

public class RegisterPage extends CommonFuntions {

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "btnLogin")
	WebElement SUBMIT_BTN;

	@FindBy(name = "emailid")
	WebElement EMAIL_ID_TXT;

	@FindBy(xpath = "//td[contains(text(),'User ID :')]/following-sibling::td")
	WebElement USER_ID_TEXT;

	@FindBy(xpath = "//td[contains(text(),'Password :')]/following-sibling::td")
	WebElement PASSWORD_ID_TEXT;

	public void inputEmail(String emailValue) {
		waitVisible(EMAIL_ID_TXT);
		input(EMAIL_ID_TXT, emailValue);
	}

	public void clickSubmitButton() {
		waitVisible(SUBMIT_BTN);
		click(SUBMIT_BTN);
	}

	public String getUserIDInfo() {
		waitVisible(USER_ID_TEXT);
		highlightElement(USER_ID_TEXT);
		return getText(USER_ID_TEXT);
	}

	public String getPasswordIDInfo() {
		waitVisible(PASSWORD_ID_TEXT);
		highlightElement(PASSWORD_ID_TEXT);
		return getText(PASSWORD_ID_TEXT);
	}

	public LoginPage openLoginPage(String url) {
		openUrl(url);
		return PageFactory.initElements(driver, LoginPage.class);
	}
}
