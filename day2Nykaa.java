package vanilascripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class day2Nykaa {

	public static void main(String[] args) throws InterruptedException {	
		//open browser
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--diable-notifications");		
		ChromeDriver driver = new ChromeDriver(options);

		//Sent url
		driver.get("https://www.nykaa.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@href='#'])[2]")));
		//Mouseover on Brands and Mouseover on Popular
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("(//a[@href='#'])[2]")).pause(3000).perform();
		Thread.sleep(3000);
		WebElement popular = driver.findElementByXPath("(//a[@class='brandHeadingbox '])[1]");
		builder.moveToElement(popular).perform();
		Thread.sleep(3000);

		//Click L'Oreal Paris
		driver.findElementByXPath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']").click();

		//Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> allWindows = driver.getWindowHandles();
		List<String> windowList=new ArrayList<String>(allWindows);
		driver.switchTo().window(windowList.get(1));
		String title = driver.getTitle();
		if(title.contains("L'Oreal Paris")) {
			System.out.println("Title Matches");
		}
		else {
			System.out.println("Title has not been matched");
		}

		//Click sort By and select customer top rated
		driver.findElementByXPath("(//span[text()='popularity'])[1]").click();
		driver.findElementByXPath("(//div[@class='control__indicator radio'])[4]").click();
		Thread.sleep(3000);

		//Click Category and click Shampoo
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("//span[text()='Shampoo (21)']").click();
		Thread.sleep(3000);

		//check whether the Filter is applied with Shampoo
		String filterApplied = driver.findElementByXPath("//li[text()='Shampoo']").getText();
		if(filterApplied.contains("Shampoo")) {
			System.out.println("Shampoo applied in filter");
		}
		else {
			System.out.println("No filter applied");
		}

		//Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElementByXPath("(//img[@class='listing-img'])[4]").click();

		//GO to the new window and select size as 175ml
		Set<String> allWindows1 = driver.getWindowHandles();
		List<String> windowList1=new ArrayList<String>(allWindows1);
		driver.switchTo().window(windowList1.get(2));
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='175ml']").click();

		//Print the MRP of the product
		String price = driver.findElementByXPath("(//span[text()='150'])[1]").getText();
		int priceOfShampoo=Integer.parseInt(price.replaceAll("\\D", ""));
		System.out.println(priceOfShampoo);

		//Click on ADD to BAG
		driver.findElementByXPath("(//button[text()='ADD TO BAG'])[1]").click();
		Thread.sleep(3000);

		//Go to Shopping Bag 
		driver.findElementByClassName("AddBagIcon").click();

		Thread.sleep(3000);

		//Print the Grand Total amount
		String total = driver.findElementByXPath("//div[@class='value medium-strong']").getText();
		int grandTotal=Integer.parseInt(total.replaceAll("\\D", ""));
		System.out.println(grandTotal);
		
		//Click Proceed
		driver.findElementByXPath("//span[text()='Proceed']").click();

		Thread.sleep(2000);
		//Click on Continue as Guest
		driver.findElementByXPath("(//button[contains(@class,'btn full')])[2]").click();
		Thread.sleep(3000);	

		//Print the warning message (delay in shipment)
		System.out.println(driver.findElementByXPath("//div[@class='message']").getText());

		//Close all windows
		driver.quit();

	}

}
