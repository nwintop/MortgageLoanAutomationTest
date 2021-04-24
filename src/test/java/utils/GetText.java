package utils;

import org.openqa.selenium.WebElement;

public class GetText {

	public static String getStringValue(WebElement element) {
		return element.getText().trim();
	}
}
