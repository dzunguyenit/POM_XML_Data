package com.bankguru.customer;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.Element;

import com.CommonPages.CommonTestcases;
import com.CommonPages.ManageEnviroment.Environment;
import com.bankguru.EditCustomerPage;
import com.bankguru.HomePage;
import com.bankguru.LoginPage;

public class EditCustomer extends CommonTestcases {
	Environment urlEnvironment;
	WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private EditCustomerPage editCustomerPage;
	Element data;
	String userPath = System.getProperty("user.dir");

	@Parameters({ "browser", "environment", "version", "dataXml" })
	@BeforeClass
	public void beforeClass(String browser, String environment, String version, String dataXml) {

		ConfigFactory.setProperty("env", environment);
		urlEnvironment = ConfigFactory.create(Environment.class);

		String pathDataXml = userPath.concat("/data/").concat(dataXml);
		data = readXmlFile(pathDataXml);
		log.info("----------OPEN BROWSER-----------");
		driver = openMultiBrowser(browser, urlEnvironment.url(), version);
		loginPage = PageFactory.initElements(driver, LoginPage.class);

		loginPage.inputEmail(Register.emailLogin);
		loginPage.inputPassword(Register.passwordLogin);
		homePage = loginPage.clickSubmitButton();

	}

	@BeforeMethod
	public void beforeMethod() {
		log.info("Edit Customer_01 Step 01 - Open Edit Customer Page");
		editCustomerPage = homePage.openEditCustomerPage();

	}

	@Test
	public void TC_01_CustomerIdCanotEmpty() {
		log.info("Edit Customer_01 Step 02 - Press Tab CustomerId Field");
		editCustomerPage.pressTabCustomerIdField();
		log.info("Edit Customer_01 Step 03 - Verify Text Error CustomerID");
		verifyEquals(getData(data, "requireIdMsg"), editCustomerPage.getDynamicMsg("Customer ID is required"));

	}

	@Test
	public void TC_02_CustomerIdCannotBeNumberic() {
		log.info("Edit Customer_01 Step 02 - Enter Nummeric CustomerId Field");
		editCustomerPage.enterNummericCustomerIdField(getData(data, "nummericValueIdCustomer"));
		log.info("Edit Customer_01 Step 03 - Verify Text Error CustomerID");
		verifyEquals(getData(data, "mustBeNumbericMsg"), editCustomerPage.getDynamicMsg("Characters are not allowed"));

	}

	@Test
	public void TC_03_CustomerIdCannotHaveSpecialCharacter() {
		log.info("Edit Customer_01 Step 02 - Enter Special Character CustomerId Field");
		editCustomerPage.enterSpecialCharacterCustomerIdField(getData(data, "specialCharacterCustomerId"));
		log.info("Edit Customer_01 Step 03 - Verify Text Error CustomerID");
		verifyEquals(getData(data, "cannotSpecialCharacterMsg"),
				editCustomerPage.getDynamicMsg("Special characters are not allowed"));
	}

	@Test
	public void TC_04_ValidCustomerId() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Verify Navigate Edit Customer");
		verifyEquals(getData(data, "urlEditCustomer"), editCustomerPage.getUrlEditCustomer());
	}

	// Testcas 05, 06, 07: Skip

	// @Test
	// public void TC_05_NameCannotEmpty() {
	// editCustomerPage = newCustomerPage.openEditCustomerPage(driver);
	// editCustomerPage.inputIdCustomer(NewCustomer.customerId);
	// editCustomerPage.clickSubmitToEditInfor();
	// editCustomerPage.pressTabNameField();
	// verifyEquals("Customer name must not be blank",
	// editCustomerPage.getTextUsernameErr());
	//
	// }
	//
	// @Parameters({ "nummericValueNameUpdate" })
	// @Test
	// public void TC_06_NameCannotBeNumberic(String nummericValueNameUpdate) {
	// editCustomerPage = newCustomerPage.openEditCustomerPage(driver);
	// editCustomerPage.inputIdCustomer(NewCustomer.customerId);
	// editCustomerPage.clickSubmitToEditInfor();
	// editCustomerPage.enterNummericNameField(nummericValueNameUpdate);
	// verifyEquals("Numbers are not allowed",
	// editCustomerPage.getTextUsernameErr());
	//
	// }
	//
	// @Parameters({ "specialCharacterNameUpdate" })
	// @Test
	// public void TC_07_NameCannotHaveSpecialCharacter(String
	// specialCharacterNameUpdate) {
	// editCustomerPage = newCustomerPage.openEditCustomerPage(driver);
	// editCustomerPage.inputIdCustomer(NewCustomer.customerId);
	// editCustomerPage.clickSubmitToEditInfor();
	// editCustomerPage.enterSpecialCharacterNameField(specialCharacterNameUpdate);
	// verifyEquals(cannotSpecialCharacterMsg,
	// editCustomerPage.getTextUsernameErr());
	//
	// }

	@Test
	public void TC_08_AddressCannotEmpty() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Press Tab Addres sField");
		editCustomerPage.pressTabAddressField();
		log.info("Edit Customer_01 Step 05 - Verify Text Address Error");
		verifyEquals(getData(data, "addressCannotEmptyMsg"),
				editCustomerPage.getDynamicMsg("Address Field must not be blank"));

	}

	@Test
	public void TC_09_CityCannotEmpty() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Press Tab City Field");
		editCustomerPage.pressTabCityField();
		log.info("Edit Customer_01 Step 05 - Verify Text City Error");
		verifyEquals(getData(data, "cityCannotEmptyMsg"),
				editCustomerPage.getDynamicMsg("City Field must not be blank"));
	}

	@Test
	public void TC_10_CityCannotBeNumberic() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Enter Nummeric City Field");
		editCustomerPage.enterNummericCityField(getData(data, "nummericCityField"));
		log.info("Edit Customer_01 Step 05 - Verify Text City Error");
		verifyEquals(getData(data, "cannotBeNumbericMsg"), editCustomerPage.getDynamicMsg("Numbers are not allowed"));
	}

	@Test
	public void TC_11_CityCannotHaveSpecialCharacter() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Enter Special Character City Field");
		editCustomerPage.enterSpecialCharacterCityField(getData(data, "specialCharacterCityUpdate"));
		log.info("Edit Customer_01 Step 05 - Verify Text City Error");
		verifyEquals(getData(data, "cannotSpecialCharacterMsg"),
				editCustomerPage.getDynamicMsg("Special characters are not allowed"));
	}

	@Test
	public void TC_12_StateCannotEmpty() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Press Tab State Field");
		editCustomerPage.pressTabStateField();
		log.info("Edit Customer_01 Step 05 - Verify Text State Error");
		verifyEquals(getData(data, "stateCannotEmptyMsg"), editCustomerPage.getDynamicMsg("State must not be blank"));
	}

	@Test
	public void TC_13_StateCannotBeNumberic() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Enter Nummeric State Field");
		editCustomerPage.enterNummericStateField(getData(data, "nummericStateField"));
		log.info("Edit Customer_01 Step 05 - Verify Text State Error");
		verifyEquals(getData(data, "cannotBeNumbericMsg"), editCustomerPage.getDynamicMsg("Numbers are not allowed"));
	}

	@Test
	public void TC_14_StateCannotHaveSpecialCharacter() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Enter Special Character State Field");
		editCustomerPage.enterSpecialCharacterStateField(getData(data, "specialCharacterStateUpdate"));
		log.info("Edit Customer_01 Step 05 - Verify Text State Error");
		verifyEquals(getData(data, "cannotSpecialCharacterMsg"),
				editCustomerPage.getDynamicMsg("Special characters are not allowed"));
	}

	@Test
	public void TC_15_PinMustBeNumeric() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Enter Char Pin Field");
		editCustomerPage.enterCharPinField(getData(data, "pinNumberic"));
		log.info("Edit Customer_01 Step 05 - Verify Text Pin Error");
		verifyEquals(getData(data, "mustBeNumbericMsg"), editCustomerPage.getDynamicMsg("Characters are not allowed"));
	}

	@Test
	public void TC_16_PinCannotEmpty() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Press Tab Pin Field");
		editCustomerPage.pressTabPinField();
		log.info("Edit Customer_01 Step 05 - Verify Text Pin Error");
		verifyEquals(getData(data, "pinCannotEmptyMsg"), editCustomerPage.getDynamicMsg("PIN Code must not be blank"));
	}

	@Test
	public void TC_17_PinMustHave6Digits() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Enter Digit");
		editCustomerPage.enterDigit(getData(data, "digitUpdate"));
		log.info("Edit Customer_01 Step 05 - Verify Text Pin Error");
		verifyEquals(getData(data, "pinMustHave6DigitsMsg"),
				editCustomerPage.getDynamicMsg("PIN Code must have 6 Digits"));
	}

	@Test
	public void TC_18_PinCannotHaveSpecialCharacter() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Enter Special Character Pin Field");
		editCustomerPage.enterSpecialCharacterPinField(getData(data, "specialCharacterPinUpdate"));
		log.info("Edit Customer_01 Step 05 - Verify Text Pin Error");
		verifyEquals(getData(data, "cannotSpecialCharacterMsg"),
				editCustomerPage.getDynamicMsg("Special characters are not allowed"));
	}

	@Test
	public void TC_19_TelephoneCannotEmpty() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Press Tab Telephone Field");
		editCustomerPage.pressTabTelephoneField();
		log.info("Edit Customer_01 Step 05 - Verify Text Telephone Error");
		verifyEquals(getData(data, "telephoneCannotEmptyMsg"),
				editCustomerPage.getDynamicMsg("Mobile no must not be blank"));
	}

	@Test
	public void TC_20_TelephoneCannotHaveSpecialCharacter() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Enter Special Character Telephone Field");
		editCustomerPage.enterSpecialCharacterTelephoneField(getData(data, "specialCharacterTelephoneUpdate"));
		log.info("Edit Customer_01 Step 05 - Verify Text Telephone Error");
		verifyEquals(getData(data, "cannotSpecialCharacterMsg"),
				editCustomerPage.getDynamicMsg("Special characters are not allowed"));
	}

	@Test
	public void TC_21_EmailCannotEmpty() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Press Tab Email Field");
		editCustomerPage.pressTabEmailField();
		log.info("Edit Customer_01 Step 05 - Verify Text Email Error");
		verifyEquals(getData(data, "emailCannotEmptyMsg"), editCustomerPage.getDynamicMsg("Email-ID must not be blank"));
	}

	@Test
	public void TC_22_EmailIncorrectFormat() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Enter Invalid Email");
		editCustomerPage.enterInvalidEmail(getData(data, "invalidEmail"));
		log.info("Edit Customer_01 Step 05 - Verify Text Email Error");
		verifyEquals(getData(data, "emailIncorrectFormatMsg"), editCustomerPage.getDynamicMsg("Email-ID is not valid"));
	}

	@Test
	public void TC_23_EmailCannotHaveBlankSpace() {
		log.info("Edit Customer_01 Step 02 - Input IdCustomer");
		editCustomerPage.inputIdCustomer(NewCustomer.customerId);
		log.info("Edit Customer_01 Step 03 - Click Submit To EditInfor");
		editCustomerPage.clickSubmitToEditInfor();
		log.info("Edit Customer_01 Step 04 - Press Space Email Field");
		editCustomerPage.pressSpaceEmailField();
		log.info("Edit Customer_01 Step 05 - Verify Text Email Error");
		verifyEquals(getData(data, "cannotFirstCharacterBlankSpaceMsg"),
				editCustomerPage.getDynamicMsg("First character can not have space"));
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
		log.info("----------CLOSE BROWSER-----------");
	}

}
