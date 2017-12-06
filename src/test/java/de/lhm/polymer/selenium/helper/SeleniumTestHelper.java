package de.lhm.polymer.selenium.helper;

import org.openqa.selenium.WebElement;

public class SeleniumTestHelper {

	static public boolean checkTagname(WebElement element, String name) {
		return element != null && name.equals(element.getTagName());
		
	}
}
