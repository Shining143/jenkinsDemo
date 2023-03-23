package glueCode;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefs {
	
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public Scenario s;
	public SoftAssert sa;
	public ChromeOptions co;
	
	@Before
	public void method(Scenario s) 
	{
		this.s = s;
		sa = new SoftAssert();
	} 
	
	
	@Given("open chrome browser")
	public void open_chrome_browser() 
	{
		
		WebDriverManager.chromedriver().setup();
		co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		wait = new FluentWait<RemoteWebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(20));
		wait.pollingEvery(Duration.ofMillis(1000));
	}
	
	@When("launch site {string}")
	public void launch_site(String url) 
	{
		driver.get(url);
	    
	}

	@Then("title should be {string}")
	public void title_should_be(String ext) 
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
		String act = driver.getTitle();
		if(act.contains(ext)) 
		{
			s.log("Test passed");
			sa.assertTrue(true);
		}
		else 
		{
			s.log("Expected title is "+ext);
			s.log("Actual title is "+act);
			s.log("Test failed");
			sa.assertTrue(false,"Title test");
		}
	   
	}

	@When("close site")
	public void close_site() 
	{
		driver.quit();
	    
	}
	@After
	public void method1() 
	{
		sa.assertAll();
	}

}
