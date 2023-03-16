package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.MortgagePaymentCalculator;
import resources.TestData;

public class MortageCalculatorTest extends Base {

	MortgagePaymentCalculator mortageHomePage;

	public MortageCalculatorTest() {
		super();
	}

	@BeforeClass()
	public void SetUP() {
		initialization();


	}

	@Test(dataProvider = "mortageCustomerData", dataProviderClass = TestData.class)
	public void ValidateMortageCalculator(String amount, String year, String rate) {
		mortageHomePage = new MortgagePaymentCalculator(driver);
		mortageHomePage.closePopup();
		mortageHomePage.fillMortgageAmountCalculatorDetails(amount, year, rate);
		mortageHomePage.ClickCalculateBtn();
		mortageHomePage.validateResultPage();

	}

	@AfterTest()
	public void CleanUP() {

		if (driver != null) {
			driver.quit();
		}
	}

}
