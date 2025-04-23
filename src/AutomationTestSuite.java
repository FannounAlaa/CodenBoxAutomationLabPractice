import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class AutomationTestSuite {
	WebDriver driver = new EdgeDriver();
	WebDriverWait wait;
	
	@BeforeTest
	public void Setup() {
		String URL = "https://codenboxautomationlab.com/practice/";
		driver.manage().window().maximize();
		driver.get(URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@Test (priority = 1, enabled = false)
	public void TestRadioBtnSelection() {
		List<WebElement> RadioBtns = driver.findElements(By.className("radioButton"));
		
		for (int i = 0; i < RadioBtns.size(); i++) {
			RadioBtns.get(i).click();
		}
		
//		for (WebElement radio : RadioBtns) {
//			radio.click();
//		}
		
		
	}
	
	@Test (priority = 2, enabled = false)
	public void TestDynamicDDL() {
		WebElement DynamicDDL = driver.findElement(By.id("autocomplete"));
		DynamicDDL.sendKeys("Jor");
		
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("li.ui-menu-item")));
		
		List<WebElement> Suggestions = driver.findElements(By.cssSelector("li.ui-menu-item"));
		
		
		for (WebElement suggestion : Suggestions) {
			String text = suggestion.getText();
            System.out.println("Suggestion: " + text);
			
			if (text.equalsIgnoreCase("Jordan")) {
				suggestion.click();
				break;
			}
		}
	}
	
	@Test (priority = 3, enabled = false)
	public void TestStaticDDL() {
		WebElement StaticDDL = driver.findElement(By.id("dropdown-class-example"));
		//StaticDDL.click();
		
		Select select = new Select(StaticDDL);
		
		// loop to select all list items
//	    List<WebElement> options = select.getOptions();
//	    for (int i = 0; i < options.size(); i++ ) {
//	    	select.selectByIndex(i);
//	    	
//	    	select.getFirstSelectedOption().getText();
//	    } 
	    
	    //select by item
        //select.selectByVisibleText("Selenium"); // Select  by visible text
        select.selectByValue("option2"); // Select  by value
        //select.selectByIndex(3); // Select "API" by index        
        select.getFirstSelectedOption().getText();
        
	}
	
	@Test (priority = 4)
	public void TestCheckbox() {
		// Get all checkboxes inside the checkbox example section
		List<WebElement> CheckBoxes = driver.findElements(By.cssSelector("#checkbox-example input[type='checkbox']"));
		for (WebElement checkBox : CheckBoxes) {
			if (!checkBox.isSelected()) {
				checkBox.click();
			}
		}
		
		//uncheck option2
		WebElement Option2 = driver.findElement(By.id("checkBoxOption2"));
		Option2.click();
		
	}	
	
	@Test (priority = 5, enabled = false)
	public void TestSwitchWindow() {
		String MainWindowHandle = driver.getWindowHandle();
		
		driver.findElement(By.id("openwindow")).click();
		
		Set <String> AllWindowsHandle = driver.getWindowHandles();
		
		for (String handle : AllWindowsHandle) {
			if (!handle.equals(MainWindowHandle)) {
				
				driver.switchTo().window(handle);
				
				System.out.println(driver.getCurrentUrl());
				System.out.println(driver.getTitle());
				
				driver.close();
				
				driver.switchTo().window(MainWindowHandle);
				break;
				
			}
		}
	}
	@Test (priority = 6)
	public void TestSwitchTab() {
		String originalTab = driver.getWindowHandle();
		
		driver.findElement(By.id("opentab")).click();
		
		Set <String> AllTabs = driver.getWindowHandles();
		
		for (String tab : AllTabs) {
			if (!tab.equals(originalTab)) {
				
				driver.switchTo().window(tab);
				
				System.out.println(driver.getCurrentUrl());
				System.out.println(driver.getTitle());
				
				driver.close();
				
				driver.switchTo().window(originalTab);
				break;
				
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@AfterTest
	public void DoWork() {
		
	}

}
