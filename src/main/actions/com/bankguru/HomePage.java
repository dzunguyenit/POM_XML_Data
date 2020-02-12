package com.bankguru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.CommonPages.CommonFuntions;

public class HomePage extends CommonFuntions {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "uid")
	WebElement EMAIL_TXT;

	@FindBy(name = "password")
	WebElement PASSWORD_TXT;

	@FindBy(partialLinkText = "Edit Custom")
	WebElement EDIT_CUSTOMER_LINK;

	@FindBy(linkText = "New Customer")
	WebElement NEWCUSTOMER_CUSTOMER_LINK;

	public String getLoginPageUrl() {
		return getCurrentUrl();
	}

	public void inputEmail(String username) {
		waitVisible(EMAIL_TXT);
		input(EMAIL_TXT, username);
	}

	public void inputPassword(String password) {
		waitVisible(PASSWORD_TXT);
		input(PASSWORD_TXT, password);
	}

	public EditCustomerPage openEditCustomerPage() {
		waitVisible(EDIT_CUSTOMER_LINK);
		click(EDIT_CUSTOMER_LINK);
		return PageFactory.initElements(driver, EditCustomerPage.class);
	}

	public NewCustomerPage openNewCustomerPage() {
		waitVisible(NEWCUSTOMER_CUSTOMER_LINK);
		click(NEWCUSTOMER_CUSTOMER_LINK);
		return PageFactory.initElements(driver, NewCustomerPage.class);
	}

}
