import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
	
	@Test (priority = 1)
	public void TestRadioBtnSelection() {
		List<WebElement> RadioBtns = driver.findElements(By.className("radioButton"));
		
		for (int i = 0; i < RadioBtns.size(); i++) {
			RadioBtns.get(i).click();
		}
		
//		for (WebElement radio : RadioBtns) {
//			radio.click();
//		}
		
		
	}
	
	@Test (priority = 2)
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
	
	
	@AfterTest
	public void DoWork() {
		
	}

}
