package vanilascripts;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class proj2Microsoft {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications"); 
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>(); 
		//  chromePrefs.put("profile.default_content_settings.popups", 0); 
		chromePrefs.put("plugins.always_open_pdf_externally", true); 
		//   chromePrefs.put("safebrowsing.enabled", "false"); 
		options.setExperimentalOption("prefs", chromePrefs); 
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver(options);

		//Go to https://azure.microsoft.com/en-in/
		driver.get("https://www.azure.microsoft.com/en-in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver, 30);

		//Click on Pricing
		driver.findElementById("navigation-pricing").click();

		//Click on Pricing Calculator
		driver.findElementByXPath("(//a[@href='/en-us/pricing/calculator/'])[1]").click();

		//Click on Containers
		driver.findElementByXPath("//button[@data-event-property='containers']").click();

		//Select Container Instances
		driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();

		//Click on Container Instance Added View
		driver.findElementById("new-module-loc").click();

		//Select Region as "South India"
		WebElement region = driver.findElementByXPath("//select[@aria-label='Region']");
		Select selRegion=new Select(region);
		selRegion.selectByVisibleText("South India");

		//Set the Duration as 180000 seconds
		WebElement duration = driver.findElementByXPath("(//input[@aria-label='Seconds'])[1]");
		duration.clear();
		duration.sendKeys(Keys.BACK_SPACE,"180000");

		//Select the Memory as 4GB
		WebElement memory = driver.findElementByXPath("(//select[@aria-label='Memory'])[1]");
		Select selMemory=new Select(memory);
		selMemory.selectByVisibleText("4 GB");

		//Enable SHOW DEV/TEST PRICING
		driver.findElementByName("devTestSelected").click();

		//Select Indian Rupee  as currency
		WebElement currency = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select selCurrency=new Select(currency);
		selCurrency.selectByVisibleText("Indian Rupee (₹)");

		//Print the Estimated monthly price
		String monthlyPrice = driver.findElementByXPath("//span[text()='₹288.77']").getText();
		monthlyPrice=monthlyPrice.replaceAll("[^0-9.]","");
		System.out.println("EstimatedMonthlyPrice is" +"" +monthlyPrice);

		//Click on Export to download the estimate as excel
		driver.findElementByXPath("(//button[contains(@class,'calculator-button button-transparent')])[4]").click();

		//Verify the downloaded file in downloads folder
		File file = new File("C:\\Users\\Priya\\Downloads"); 
		if(file.exists()) 
		{ 
			System.out.println("File has been Downloaded successfully"); 
		} 
		else 
		{ 
			System.out.println("File has not been downloaded"); 
		} 

		//Navigate to Example Scenarios and Select CI/CD for Containers
		Actions builder = new Actions(driver); 
		builder.moveToElement(driver.findElementByXPath("//a[text()='Example Scenarios']")).perform(); 

		Thread.sleep(1000); 
		driver.findElementByXPath("//a[text()='Example Scenarios']").click(); 
		Thread.sleep(2000); 
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[@title='CI/CD for Containers']"))); 
		driver.findElementByXPath("//button[@title='CI/CD for Containers']").click(); 

		//Click Add to Estimate
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[text()='Add to estimate']")));
		builder.moveToElement(driver.findElementByXPath("//button[text()='Add to estimate']")).click().perform();
		Thread.sleep(2000);

		//Change the Currency as Indian Rupee
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//select[@class='select currency-dropdown']")));
		WebElement currency1 = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select selCurrency1=new Select(currency1);
		selCurrency1.selectByVisibleText("Indian Rupee (₹)");

		//Enable SHOW DEV/TEST PRICING
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByName("devTestSelected")));

		if(driver.findElementByName("devTestSelected").isEnabled()) 
		{ 
			System.out.println("Enabled");
		}
		else
		{
			driver.findElementByName("devTestSelected").click();
		}

		//Export the Estimate
		driver.findElementByXPath("(//button[contains(@class,'calculator-button button-transparent')])[4]").click();

		//close the browser
		driver.quit();
	}
}
