package vanilascripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class Day6BigBasket {

	public static void main(String[] args) throws InterruptedException {
		// Go to https://www.bigbasket.com/

		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--disable-notifications");   		 
		DesiredCapabilities capabilities = new DesiredCapabilities();  
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS); 
		options.merge(capabilities);  


		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver(options);
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);

		//mouse over on  Shop by Category 
		Actions builder=new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//a[@class='dropdown-toggle meganav-shop']")).perform();;

		//Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS 
		builder.moveToElement(driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]")).perform();;
		Thread.sleep(2000);
		builder.moveToElement(driver.findElementByXPath("(//a[text()='Rice & Rice Products'])[2]")).perform();;
		Thread.sleep(2000);

		//Click on Boiled & Steam Rice
		driver.findElementByXPath("(//a[text()='Boiled & Steam Rice'])[2]").click();
		Thread.sleep(2000);

		// Choose the Brand as bb Royal
		driver.findElementByXPath("(//span[text()='bb Royal'])[1]").click();
		Thread.sleep(2000);

		// Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
		/* driver.findElementByLinkText("Ponni Boiled Rice - Super Premium").click(); */
		driver.findElementByXPath("(//a[contains(text(),'Ponni Boiled Rice - Super')]/following::button[@type='button'])[1]").click();  
		driver.findElementByXPath("(//ul[@class='dropdown-menu drop-select']//span[text()='5 kg'])[3]").click(); 


		// print the price of Rice
		System.out.println(driver.findElementByXPath("//span[text()='5 kg  - Rs 336.00 ']").getText());



		// Click Add button
		driver.findElementByXPath("(//button[@class='btn btn-add col-xs-9'])[4]").click();

		try { 
			driver.findElementByLinkText("Continue").click(); 
		} catch (Exception e) { 
			System.out.println("Location popup not displayed."); 
			e.printStackTrace(); 
		} 

		// Verify the success message displayed 
		/*
		 * if (driver.findElementByClassName("toast-title").getText().
		 * contains("Successfully added Ponni Boiled Rice -")) {
		 * System.out.println("verified");} else {
		 * System.out.println("Rice has not beenn aedded."); }
		 */
		//Type Dal in Search field and enter
		driver.findElementByXPath("(//input[contains(@class,'form-control ng-pristine')])[1]").sendKeys("Dal",Keys.ENTER);
		Thread.sleep(2000);

		// Go to Toor/Arhar Dal and select 2kg & set Qty 2 
		/*
		 * WebElement selProd =
		 * driver.findElementByXPath("(//span[text()='111.00'])[1]"); Select selDD=new
		 * Select(selProd); selDD.selectByIndex(4);
		 */
		/*
		 * driver.findElementByXPath("(//span[text()='111.00'])[1]").click();
		 * 
		 * 
		 * WebElement selQuantity =
		 * driver.findElementByXPath("(//input[@ng-model='vm.startQuantity'])[3]");
		 * selQuantity.clear(); selQuantity.sendKeys("2");
		 */

		driver.findElementByXPath("(//a[contains(text(),'Thuvaram Paruppu')])[1]/following::span[2]").click(); 
		driver.findElementByXPath("(//a[contains(text(),'Thuvaram Paruppu')])[1]/following::span[2]/following::span[text()='2 kg'][1]").click(); 
		driver.findElementByXPath("(//a[contains(text(),'Thuvaram Paruppu')])[1]/following::input[@type='text'][1]").clear();  
		driver.findElementByXPath("(//a[contains(text(),'Thuvaram Paruppu')])[1]/following::input[@type='text'][1]").sendKeys("2");  

		// Print the price of Dal
		System.out.println(driver.findElementByXPath("(//button[@data-toggle='dropdown'])[3]").getText());

		// Click Add button
		driver.findElementByXPath("(//button[@qa='add'])[3]").click();

		// Mouse hover on My Basket 
		builder.moveToElement(driver.findElementByXPath("//a[@qa='myBasket']")).perform();

		// Validate the Sub Total displayed for the selected items
		String subTot = driver.findElementByXPath("//span[@qa='subTotalMB']").getText();
		int totAmt=Integer.parseInt(subTot.replaceAll("\\D", ""));
		if(totAmt==774) {
			System.out.println("Amount Matches");
		}
		else {
			System.out.println("Amount Mismatched");

		}		

		// Reduce the Quantity of Dal as 1 
		driver.findElementByXPath("(//button[text()='−'])[2]").click();


		// Validate the Sub Total for the current items
		String subTot1 = driver.findElementByXPath("//span[@qa='subTotalMB']").getText();
		int totAmt1=Integer.parseInt(subTot1.replaceAll("\\D", ""));
		if(totAmt1==555) {
			System.out.println("Amount Matches");
		}
		else {
			System.out.println("Amount Mismatched");

		}		
		// Close the Browser
		driver.close();

	}

}
