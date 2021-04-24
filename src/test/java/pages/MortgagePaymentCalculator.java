package pages;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Base.Base;
import utils.GetText;
import utils.SelectDropdown;

public class MortgagePaymentCalculator extends Base {

	public MortgagePaymentCalculator(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'×')]")
	WebElement adpopup;

	@FindBy(how = How.ID, using = "KJE-LOAN_AMOUNT")
	WebElement mortgageAmount;

	@FindBy(how = How.ID, using = "KJE-TERM")
	WebElement termInYears;

	@FindBy(how = How.ID, using = "KJE-INTEREST_RATE")
	WebElement interestRate;

	@FindBy(how = How.ID, using = "KJE-MONTHLY_PAYMENT")
	WebElement monthlyPayment;

	@FindBy(how = How.ID, using = "KJE-BY_YEAR1")
	WebElement annually;

	@FindBy(how = How.ID, using = "KJECalculate")
	WebElement calculate;

	@FindBy(how = How.XPATH, using = "//h2[starts-with(text(),'Total Payments')]")
	WebElement totalPayments;

	@FindBy(how = How.XPATH, using = "//h2/div[starts-with(text(),'Total Interest')]")
	WebElement totalInterest;

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public void closePopup() {
		new FluentWait(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(adpopup));

		adpopup.click();
	}

	public void fillMortgageAmountCalculatorDetails(String loanamount, String terminyears, String interestrate) {

		// Enter Mortage Amount
		wait.until(ExpectedConditions.visibilityOf(mortgageAmount));
		mortgageAmount.clear();
		mortgageAmount.sendKeys(loanamount);

		// selecting dropdown
		wait.until(ExpectedConditions.visibilityOf(termInYears));
		SelectDropdown.selectByValue(termInYears, terminyears);

		// Enter Interest
		wait.until(ExpectedConditions.visibilityOf(interestRate));
		interestRate.clear();
		interestRate.sendKeys(interestrate);

		// Select radio btn if not selected
		wait.until(ExpectedConditions.elementToBeClickable(annually));
		if (!annually.isSelected())
			annually.click();

	}

	public void ClickCalculateBtn() {
		// click on btn
		wait.until(ExpectedConditions.visibilityOf(calculate));
		calculate.click();
	}

	public void validateResultPage() {
		// validating all three
		wait.until(ExpectedConditions.visibilityOf(monthlyPayment));
		Assert.assertTrue(GetText.getStringValue(monthlyPayment).contains(prop.getProperty("MonthlyPayment")));

		wait.until(ExpectedConditions.visibilityOf(totalPayments));
		Assert.assertTrue(GetText.getStringValue(totalPayments).contains(prop.getProperty("TotalPayments")));

		wait.until(ExpectedConditions.visibilityOf(totalInterest));
		Assert.assertTrue(GetText.getStringValue(totalInterest).contains(prop.getProperty("TotalInterest")));

	}

}
