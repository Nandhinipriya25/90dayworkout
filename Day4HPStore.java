package vanilascripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HPStore {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--disable-notifications"); 


		//Go to https://store.hp.com/in-en/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver(options);

		driver.get("https://store.hp.com/in-en/");
		/* driver.findElementByClassName("optly-modal-close close-icon").click(); */
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);


		// Mouse over on Laptops menu and click on Pavilion
		Actions builder=new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//span[text()='Laptops']")).perform();
		builder.click(driver.findElementByXPath("(//span[text()='Pavilion'])[1]")).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Accept Cookies']"))); 
		driver.findElement(By.xpath("//button[text()='Accept Cookies']")).click(); 

		// Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		driver.findElementByXPath("//span[text()='Intel Core i7']").click();
		driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();
		Thread.sleep(3000);

		// Hard Drive Capacity -->More than 1TB
		driver.findElementByXPath("//span[text()='More than 1 TB']").click();
		Thread.sleep(2000);

		// Select Sort By: Price: Low to High
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//select[@id='sorter'])[1]")));
		WebElement sortOpt = driver.findElementByXPath("(//select[@id='sorter'])[1]");
		Select selLowToHigh=new Select(sortOpt);
		selLowToHigh.deselectByVisibleText("Price : Low to High");
		Thread.sleep(2000);

		// Print the First resulting Product Name and Price
		System.out.println(driver.findElementByXPath("(//a[@class='product-item-link'])[1]").getText());
		String price = driver.findElementByXPath("(//span[@class='price'])[2]").getText();
		int priceOFfirstItem=Integer.parseInt(price.replaceAll("\\D", ""));
		System.out.println(priceOFfirstItem);

		// Click on Add to Cart
		driver.findElementByXPath("(//span[text()='Add To Cart'])[1]").click();
		Thread.sleep(2000);

		// Click on Shopping Cart icon --> Click on View and Edit Cart
		driver.findElementByClassName("action showcart").click();
		driver.findElementByClassName("action primary viewcart").click();
		Thread.sleep(2000);

		// Check the Shipping Option --> Check availability at Pincode
		driver.findElementByName("pincode").sendKeys("600113");
		driver.findElementByXPath("//button[text()='check']").click();

		// Verify the order Total against the product price
		// Proceed to Checkout if Order Total and Product Price matches
		String grandTot = driver.findElementByXPath("(//span[@class='price'])[7]").getText();
		int totalAmt=Integer.parseInt(grandTot.replaceAll("\\D", ""));
		if(priceOFfirstItem==totalAmt) {
			System.out.println("Price Mathced");
			driver.findElementByXPath("(//span[text()='Proceed to Checkout'])[1]").click();
		}
		else {
			System.out.println("Price Mismatched");
		}
		Thread.sleep(2000);

		// Click on Place Order
		driver.findElementByXPath("(//span[text()='Place Order'])[4]").click();
		Thread.sleep(3000);

		// Capture the Error message and Print
		System.out.println(driver.findElementByXPath("(//span[text()='This is a required field.'])[1]").getText());

		// Close Browser
		driver.close();
	}

}
