package various_concepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNG {

	WebDriver driver;
	String browser;
	String url;
	
	@BeforeClass
	public void readfig() {
		//BufferReader//InputStream // FileReader // Scanner
		
		Properties prop = new Properties();
		
		try {
			//InputStream //BufferReader //FileReader //Scanner
			InputStream input = new FileInputStream ("src\\main\\java\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");
		
		}catch(IOException e) {
	
			e.printStackTrace();
	
		}
	
	}
	
	@BeforeMethod
	public void init() {
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
		
		}else if(browser.equalsIgnoreCase("Firefox"));
		  System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		  // Creating web driver instance
		  driver = new FirefoxDriver();
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
		// Element Library
				By USERNAME_ELEMENT = By.xpath("//input[@id='username']");
				By PASSWORD_ELEMENT = By.xpath("//input[@id='password']");
				By SIGNIN_ELEMENT = By.xpath("//button[@type='submit']");
				By DASHBOARD_FIELD_LOCATOR = By.xpath("//h2[contains(text(), 'Dashboard')]");
				By CUSTOMER_ELEMENT = By.xpath("//*[@id=\"side-menu\"]/li[3]/a/span[1]");
				By ADD_CUSTOMER_ELEMENT = By.xpath("//*@id/=\"side-menu\"]/li[3]/ul/li[1]/a");
				By Full__NAME_ELEMENT = By.xpath("//*[@id*\"account\"]");
				By COMPANY_DROPDOWN_ELEMENT = By.xpath("//select[@ids'cid']");
				By EMAIL_ELEMENT = By.xpath("[@id=\"email\"]");
				By COUNTRY_DROPDOWN_ELEMENT = By.xpath("//*@id*\"country\"]");
				
	   // login data
				String userName = "demo@techfios.com";
				String password = "abc123";
				
		//Test Data or Mock data
				String fullName= "Test Feb";
				String companyName = "Techfios";
				String email = "@gmail.com";
				String phoneNumber = "2316564564";
				String countryName = "Albania";

	
	@Test(priority = 2)
	public void loginTest() {

				driver.findElement(USERNAME_ELEMENT).sendKeys(userName);
				driver.findElement(PASSWORD_ELEMENT).sendKeys(password);
				driver.findElement(SIGNIN_ELEMENT).click();

				Assert.assertEquals( driver.findElement(DASHBOARD_FIELD_LOCATOR).getText(), "Dashboard",
						"Dashboard page not found!!!"); 
		}
	
	@Test(priority = 1)
	public void negativeloginTest() {

		driver.findElement(USERNAME_ELEMENT).sendKeys(userName);
		driver.findElement(PASSWORD_ELEMENT).sendKeys(password);
		driver.findElement(SIGNIN_ELEMENT).click();

	}
	
	@Test
	public void addCustomer() {

		driver.findElement(USERNAME_ELEMENT).sendKeys(userName);
		driver.findElement(PASSWORD_ELEMENT).sendKeys(password);
		driver.findElement(SIGNIN_ELEMENT).click();

		Assert.assertEquals( driver.findElement(DASHBOARD_FIELD_LOCATOR).getText(), "Dashboard",
				"Dashboard page not found!!!"); 
		
		driver.findElement(CUSTOMER_ELEMENT).click();
		driver.findElement(ADD_CUSTOMER_ELEMENT).click();
		
		driver.findElement(Full__NAME_ELEMENT).sendKeys(fullName);
		
		
		
		selectFromDropdown(driver.findElement(COMPANY_DROPDOWN_ELEMENT), companyName);
		
		
		randomGenerator(999);
		
		//String email = "hello";
		driver.findElement(Full__NAME_ELEMENT).sendKeys(fullName + randomGenerator(999));
		driver.findElement(EMAIL_ELEMENT).sendKeys(randomGenerator(999) + email);
		selectFromDropdown(driver.findElement(COMPANY_DROPDOWN_ELEMENT), companyName);
		
		selectFromDropdown(driver.findElement(COUNTRY_DROPDOWN_ELEMENT), countryName);
		
		
		}
	
	    public int randomGenerator(int boundaryNumber) {

			Random rnd = new Random();
			int randomNumber = rnd.nextInt(boundaryNumber);
			return randomNumber;
		
	}

		private void selectFromDropdown(WebElement element, String visibleTest) {
		
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleTest);
	}
		
	//waitForElement();

	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();

	}

	@AfterClass
	public static void afterClass() {
		System.out.println("After Class");
		
	}
}
