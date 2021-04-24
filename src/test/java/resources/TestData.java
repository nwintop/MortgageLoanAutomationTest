package resources;

import org.testng.annotations.DataProvider;

public class TestData {
	@DataProvider
	public Object[][] mortageCustomerData() {
		return new Object[][] { { "200000", "30", "5" } };
	}
}
