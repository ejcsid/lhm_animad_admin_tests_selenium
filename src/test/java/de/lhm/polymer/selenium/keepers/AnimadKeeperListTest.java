package de.lhm.polymer.selenium.keepers;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.lhm.polymer.selenium.helper.SeleniumTestHelper;

public class AnimadKeeperListTest extends TestCase {

	private WebDriver driver;
	private WebDriverWait wait;
	private String mainUrl = "http://127.0.0.1:8081/";
	
	// Animad specific settings
	private String appTitle = "animad"; // GENERATOR pattern: [DOMAINNAME]
	private String appElementTag = "animad-app"; // GENERATOR pattern: [DOMAINNAME]-app
	private String viewElementTag = "animad-keepers-view"; // GENERATOR pattern: [DOMAINNAME]-[ENTITYNAME]-view
	private String listElementTag = "animad-keepers-list"; // GENERATOR pattern: [DOMAINNAME]-[ENTITYNAME]-list
	private String gridElementTag = "animadKeepersList"; // GENERATOR pattern (camelCase): [DOMAINNAME][ENTITYNAME]List
	
	private String listName = "animad-keepers-list"; // GENERATOR pattern: [DOMAINNAME]-[ENTITYNAME]-list 
	private String urlpath = "keepers-view"; // GENERATOR pattern: [ENTITYNAME]-view
	private int nrColumnsExpected = 6; // GENERATOR: count columns: checkboxes, delete-buttons, index + number of table fields (keepers = 3)
	
	
	@Override
	protected void setUp() throws Exception {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
	}
	
	@Test
	public void test() {
        String url = mainUrl+urlpath;
        
        // Navigate to view
        driver.get(url);
        
        // Check title of application
        assertTrue(wait.until(ExpectedConditions.titleIs(appTitle)));

        // Check for all expected elements on page
        WebElement appElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(appElementTag)));
        assertTrue(appElement != null);
        
        WebElement viewElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(appElement, By.tagName(viewElementTag)));
        assertTrue(viewElement != null);
        
        WebElement listElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(viewElement, By.tagName(listElementTag)));
        assertTrue(listElement != null);
        
        // not view specific
        WebElement listPanelElement1 = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(listElement, By.id("list-panel-1")));
        assertTrue(SeleniumTestHelper.checkTagname(listPanelElement1, "paper-item"));
        
        // not view specific
        WebElement listPanelElement2 = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(listElement, By.id("list-panel-2")));
        assertTrue(SeleniumTestHelper.checkTagname(listPanelElement2, "paper-item"));
        
        // not view specific
        WebElement dialogDeleteSelectedElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(listElement, By.id("dialogDeleteSelected")));
        assertTrue(SeleniumTestHelper.checkTagname(dialogDeleteSelectedElement, "paper-dialog"));

        // not view specific
        WebElement dialogDeleteItemElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(listElement, By.id("dialogDeleteItem")));
        assertTrue(SeleniumTestHelper.checkTagname(dialogDeleteItemElement, "paper-dialog"));

        WebElement gridElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(listElement, By.id(gridElementTag)));
        assertTrue(SeleniumTestHelper.checkTagname(gridElement, "vaadin-grid"));
        
        // Check if table contains all columns as expected
        // not view specific
        WebElement gridHeaderElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(gridElement, By.id("header")));
        assertTrue(SeleniumTestHelper.checkTagname(gridHeaderElement, "thead"));
        
        List<WebElement> gridColumns = gridHeaderElement.findElements(By.xpath(".//vaadin-grid-cell-content[contains(@class,'"+listName+"')]"));
        assertTrue(gridColumns != null && gridColumns.size() == nrColumnsExpected);
        
        
        
        // Check if table contains all rows as expected
        
        // Check sorting - all rows except for checkboxes and deletion columns
        
        // Check filtering - search bar input field
        
        // Check filtering - select filter
        
        // Check deleting - delete button within row
        
        // Check selecting and deleting - delete button in table header panel
        
        // Check editing
        
        // Check creating
        
        // close browser
	}
	
	@Override
	protected void tearDown() {
        if (driver != null) driver.close();
	}

}
