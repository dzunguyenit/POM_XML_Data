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
import com.bankguru.HomePage;
import com.bankguru.LoginPage;
import com.bankguru.NewCustomerPage;
import com.bankguru.builder.NewCustomerBuilder;
import com.bankguru.builder.NewCustomerBuilder.Customer.CustomerAccount;

public class NewCustomer extends CommonTestcases {
	Environment urlEnvironment;
	WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private NewCustomerPage newCustomerPage;
	String email;
	Element data;
	static String customerId;
	String userPath = System.getProperty("user.dir");

	@Parameters({ "browser", "environment", "version", "dataXml" })
	@BeforeClass
	public void beforeClass(String browser, String environment, String version, String dataXml) {
		email = "vu" + randomEmail() + "@gmail.com";
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
		log.info("New Customer_01 Step 01 - Open New Customer Page");
		newCustomerPage = homePage.openNewCustomerPage();
	}

	@Test
	public void TC_100_NameCannotEmpty() {
		log.info("TC_100_NameCannotEmpty");
		newCustomerPage = homePage.openNewCustomerPage();
	}

	@Test
	public void TC_101_NameCannotEmpty() {
		log.info("TC_101_NameCannotEmpty");
		newCustomerPage = homePage.openNewCustomerPage();
	}

	@Test
	public void TC_102_NameCannotEmpty() {
		log.info("TC_102_NameCannotEmpty");
		newCustomerPage = homePage.openNewCustomerPage();
	}

	@Test
	public void TC_01_NameCannotEmpty() throws InterruptedException {
		log.info("New Customer_01 Step 02 - Press Button Tab Name Field");
		newCustomerPage.pressTabNameField();
		log.info("New Customer_01 Step 03 - Verify Text Username Error");
		verifyEquals(getData(data, "nameCannotEmptyMsg"),
				newCustomerPage.getDynamicMsg("Customer name must not be blank"));

		TC_100_NameCannotEmpty();

	}

	@Test
	public void TC_02_NameCannotBeNumberic() throws InterruptedException {
		log.info("New Customer_01 Step 02 - Enter Nummeric Name Field");
		newCustomerPage.enterNummericNameField(getData(data, "nummericValueName"));
		log.info("New Customer_01 Step 03 - Verify Text Username Error");
		verifyEquals(getData(data, "cannotBeNumbericMsg"), newCustomerPage.getDynamicMsg("Numbers are not allowed"));

	}

	@Test
	public void TC_03_NameCannotHaveSpecialCharacter() throws InterruptedException {
		log.info("New Customer_01 Step 02 - Enter Special Character Name Field");
		newCustomerPage.enterSpecialCharacterNameField(getData(data, "specialCharacterName"));
		log.info("New Customer_01 Step 03 - Verify Text Username Error");
		verifyEquals(getData(data, "cannotSpecialCharacterMsg"),
				newCustomerPage.getDynamicMsg("Special characters are not allowed"));

		TC_101_NameCannotEmpty();
	}

	@Test
	public void TC_04_NameCannotFirstCharacterBlankSpace() throws Exception {
		log.info("New Customer_01 Step 02 - Press Space Character Name Field");
		newCustomerPage.pressSpaceNameField();
		log.info("New Customer_01 Step 03 - Verify Text Username Error");
		verifyEquals(getData(data, "firstCharacterBlankSpaceMsg"),
				newCustomerPage.getDynamicMsg("First character can not have space"));

		TC_102_NameCannotEmpty();
	}

	@Test
	public void TC_05_AddressCannotEmpty() throws InterruptedException {
		log.info("New Customer_01 Step 02 - Press Button Tab Addres sField");
		newCustomerPage.pressTabAddressField();
		log.info("New Customer_01 Step 03 - Verify Text Address Error");
		verifyEquals(getData(data, "addressCannotEmptyMsg"),
				newCustomerPage.getDynamicMsg("Address Field must not be blank"));

		TC_102_NameCannotEmpty();

	}

	@Test
	public void TC_06_AddressCannotFirstCharacterBlankSpace() {
		log.info("New Customer_01 Step 02 - Press Space Address Field");
		newCustomerPage.pressSpaceAddressField();
		log.info("New Customer_01 Step 03 - Verify Text Address Error");
		verifyEquals(getData(data, "cannotFirstCharacterBlankSpaceMsg"),
				newCustomerPage.getDynamicMsg("First character can not have space"));

	}

	@Test
	public void TC_07_CityCannotEmpty() {
		log.info("New Customer_01 Step 02 - Press Button Tab City Field");
		newCustomerPage.pressTabCityField();
		log.info("New Customer_01 Step 03 - Verify Text City Error");
		verifyEquals(getData(data, "cityCannotEmptyMsg"),
				newCustomerPage.getDynamicMsg("City Field must not be blank"));

	}

	@Test
	public void TC_08_CityCannotBeNumberic() {
		log.info("New Customer_01 Step 02 - Enter Nummeric City Field");
		newCustomerPage.enterNummericCityField(getData(data, "nummericValueCity"));
		log.info("New Customer_01 Step 03 - Verify Text City Error");
		verifyEquals(getData(data, "cannotBeNumbericMsg"), newCustomerPage.getDynamicMsg("Numbers are not allowed"));

	}

	@Test
	public void TC_09_NameCannotHaveSpecialCharacter() {
		log.info("New Customer_01 Step 02 - Enter Special Character City Field");
		newCustomerPage.enterSpecialCharacterCityField(getData(data, "specialCharacterCityUpdate"));
		log.info("New Customer_01 Step 03 - Verify Text City Error");
		verifyEquals(getData(data, "cannotSpecialCharacterMsg"),
				newCustomerPage.getDynamicMsg("Special characters are not allowed"));

	}

	@Test
	public void TC_10_CityCannotFirstCharacterBlankSpace() {
		log.info("New Customer_01 Step 02 - Press Space Address Field");
		newCustomerPage.pressSpaceAddressField();
		log.info("New Customer_01 Step 03 - Verify Text Address Error");
		verifyEquals(getData(data, "cannotFirstCharacterBlankSpaceMsg"),
				newCustomerPage.getDynamicMsg("First character can not have space"));

	}

	@Test
	public void TC_11_StateCannotEmpty() {
		log.info("New Customer_01 Step 02 - Press Tab State Field");
		newCustomerPage.pressTabStateField();
		log.info("New Customer_01 Step 03 - Verify Text State Error");
		verifyEquals(getData(data, "stateCannotEmptyMsg"), newCustomerPage.getDynamicMsg("State must not be blank"));

	}

	@Test
	public void TC_12_StateCannotBeNumberic() {
		log.info("New Customer_01 Step 02 - Enter Nummeric State Field");
		newCustomerPage.enterNummericStateField(getData(data, "nummericValueState"));
		log.info("New Customer_01 Step 03 - Verify Text State Error");
		verifyEquals(getData(data, "cannotBeNumbericMsg"), newCustomerPage.getDynamicMsg("Numbers are not allowed"));

	}

	@Test
	public void TC_13_StateCannotHaveSpecialCharacter() {
		log.info("New Customer_01 Step 02 - Enter Special Character State Field");
		newCustomerPage.enterSpecialCharacterStateField(getData(data, "specialCharacterState"));
		log.info("New Customer_01 Step 03 - Verify Text State Error");
		verifyEquals(getData(data, "cannotSpecialCharacterMsg"),
				newCustomerPage.getDynamicMsg("Special characters are not allowed"));

	}

	@Test
	public void TC_14_StateCannotFirstCharacterBlankSpace() {
		log.info("New Customer_01 Step 02 - Press Space State Field");
		newCustomerPage.pressSpaceStateField();
		log.info("New Customer_01 Step 03 - Verify Text State Error");
		verifyEquals(getData(data, "firstCharacterBlankSpaceMsg"),
				newCustomerPage.getDynamicMsg("First character can not have space"));

	}

	@Test
	public void TC_15_PinMustBeNumeric() {
		log.info("New Customer_01 Step 02 - Enter Char Pin Field");
		newCustomerPage.enterCharPinField(getData(data, "CharValueState"));
		log.info("New Customer_01 Step 03 - Verify Text Pin Error");
		verifyEquals(getData(data, "mustBeNumbericMsg"), newCustomerPage.getDynamicMsg("Characters are not allowed"));

	}

	@Test
	public void TC_16_PinCannotEmpty() {
		log.info("New Customer_01 Step 02 - Press Tab Pin Field");
		newCustomerPage.pressTabPinField();
		log.info("New Customer_01 Step 03 - Verify Text Pin Error");
		verifyEquals(getData(data, "pinCannotEmptyMsg"), newCustomerPage.getDynamicMsg("PIN Code must not be blank"));

	}

	@Test
	public void TC_17_PinMustHave6Digits() {
		log.info("New Customer_01 Step 02 - Enter Digit");
		newCustomerPage.enterDigit(getData(data, "digit"));
		log.info("New Customer_01 Step 03 - Verify Text Pin Error");
		verifyEquals(getData(data, "pinMustHave6DigitsMsg"),
				newCustomerPage.getDynamicMsg("PIN Code must have 6 Digits"));

	}

	@Test
	public void TC_18_PinCannotHaveSpecialCharacter() {
		log.info("New Customer_01 Step 02 - Enter Special Character Pin Field");
		newCustomerPage.enterSpecialCharacterPinField(getData(data, "specialCharacterPin"));
		log.info("New Customer_01 Step 03 - Verify Text Pin Error");
		verifyEquals(getData(data, "cannotSpecialCharacterMsg"),
				newCustomerPage.getDynamicMsg("Special characters are not allowed"));

	}

	@Test
	public void TC_19_PinCannotFirstCharacterBlankSpace() {
		log.info("New Customer_01 Step 02 - Press Space Pin Field");
		newCustomerPage.pressSpacePinField();
		log.info("New Customer_01 Step 03 - Verify Text Pin Error");
		verifyEquals(getData(data, "firstCharacterBlankSpaceMsg"),
				newCustomerPage.getDynamicMsg("First character can not have space"));

	}

	@Test
	public void TC_20_PinCannotHaveBlankSpace() {
		log.info("New Customer_01 Step 02 - Enter Blank Space Pin Field");
		newCustomerPage.enterBlankSpacePinField(getData(data, "pinBlankSpace"));
		log.info("New Customer_01 Step 03 - Verify Text Pin Error");
		verifyEquals(getData(data, "mustBeNumbericMsg"), newCustomerPage.getDynamicMsg("Characters are not allowed"));

	}

	@Test
	public void TC_21_TelephoneCannotEmpty() {
		log.info("New Customer_01 Step 02 - Press Tab Telephone Field");
		newCustomerPage.pressTabTelephoneField();
		log.info("New Customer_01 Step 03 - Verify Text Telephone Error");
		verifyEquals(getData(data, "telephoneCannotEmptyMsg"),
				newCustomerPage.getDynamicMsg("Mobile no must not be blank"));

	}

	@Test
	public void TC_22_TelephoneCannotFirstCharacterBlankSpace() {
		log.info("New Customer_01 Step 02 - Press Space TelePhone Field");
		newCustomerPage.pressSpaceTelePhoneField();
		log.info("New Customer_01 Step 03 - Verify Text Telephone Error");
		verifyEquals(getData(data, "firstCharacterBlankSpaceMsg"),
				newCustomerPage.getDynamicMsg("First character can not have space"));

	}

	@Test
	public void TC_23_TelephoneCannotHaveBlankSpace() {
		log.info("New Customer_01 Step 02 - Enter Blank Space Telephone Field");
		newCustomerPage.enterBlankSpaceTelephoneField(getData(data, "telephoneBlankSpace"));
		log.info("New Customer_01 Step 03 - Verify Text Telephone Error");
		verifyEquals(getData(data, "mustBeNumbericMsg"), newCustomerPage.getDynamicMsg("Characters are not allowed"));

	}

	@Test
	public void TC_24_TelephoneCannotHaveSpecialCharacter() {
		log.info("New Customer_01 Step 02 - Enter Special Character TelePhone Field");
		newCustomerPage.enterSpecialCharacterTelePhoneField(getData(data, "specialCharacterTelephone"));
		log.info("New Customer_01 Step 03 - Verify Text Telephone Error");
		verifyEquals(getData(data, "cannotSpecialCharacterMsg"),
				newCustomerPage.getDynamicMsg("Special characters are not allowed"));

	}

	@Test
	public void TC_25_EmailCannotEmpty() {
		log.info("New Customer_01 Step 02 - Press Emaile Field");
		newCustomerPage.pressEmaileField();
		log.info("New Customer_01 Step 03 - Verify Text Email Error");
		verifyEquals(getData(data, "emailCannotEmptyMsg"), newCustomerPage.getDynamicMsg("Email-ID must not be blank"));

	}

	@Test
	public void TC_26_EmailIncorrectFormat() {
		log.info("New Customer_01 Step 02 - Enter Incorrect Email");
		newCustomerPage.enterIncorrectEmail(getData(data, "incorrectEmail"));
		log.info("New Customer_01 Step 03 - Verify Text Email Error");
		verifyEquals(getData(data, "emailIncorrectFormatMsg"), newCustomerPage.getDynamicMsg("Email-ID is not valid"));

	}

	@Test
	public void TC_27_EmailCannotHaveBlankSpace() {
		log.info("New Customer_01 Step 02 - Press Space Email Field");
		newCustomerPage.pressSpaceEmailField();
		log.info("New Customer_01 Step 03 - Verify Text Email Error");
		verifyEquals(getData(data, "firstCharacterBlankSpaceMsg"),
				newCustomerPage.getDynamicMsg("First character can not have space"));

	}

	@Test
	public void TC_28_CreateCustomerSuccessfully() {
		CustomerAccount customerAccount = new NewCustomerBuilder.Customer()
				.withCustomerName(getData(data, "customerName")).withDateOfBirth(getData(data, "dateOfBirth"))
				.withAddress(getData(data, "address")).withCity(getData(data, "city")).withState(getData(data, "state"))
				.withPin(getData(data, "PIN")).withMobileNumber(getData(data, "mobileNumber")).withEmail(email)
				.withPassword(getData(data, "passwordCustomer")).build();

		newCustomerPage.inputAllFields(customerAccount);
		newCustomerPage.clickSubmit();

		verifyEquals(getData(data, "registerSuccessfullyMsg"), newCustomerPage.getTextMessageCreateCustomerSucces());
		customerId = newCustomerPage.getTextUserID();

	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
		log.info("----------CLOSE BROWSER-----------");
	}

}
