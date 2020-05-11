package vanilascripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class proj1Ajio {

	public static void main(String[] args) throws InterruptedException {

		// Go to https://www.ajio.com/ 
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications"); 
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver(options);
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver, 30);

		//Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElementByXPath("//INPUT[@placeholder='Search AJIO']").sendKeys("Bags",Keys.ENTER);
		driver.findElementByXPath("//label[@for='Women']").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='five-grid']")));
		driver.findElementByXPath("//div[@class='five-grid']").click();

		//Click on five grid and Select SORT BY as "What's New"  
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='filter-dropdown']/select")));
		WebElement selSortBy = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		Select selDD=new Select(selSortBy);
		selDD.selectByVisibleText("What's New");

		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='price']")));

		//Enter Price Range Min as 2000 and Max as 5000
		driver.findElementByXPath("//span[text()='price']/parent::div").click();
		driver.findElementById("minPrice").sendKeys("2000",Keys.TAB);
		driver.findElementById("maxPrice").sendKeys("5000",Keys.ENTER);

		//Click on the product "Puma Ferrari LS Shoulder Bag" 
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//img[@alt='Puma Red Shoulder Ferrari LS Shoulder Bag']")));
		driver.findElementByXPath("//img[@alt='Puma Red Shoulder Ferrari LS Shoulder Bag']").click();


		//Verify the Coupon code for the price above 2690 is applicable for your product, if applicable the get the Coupon Code and Calculate the discount price for the coupon
		Set<String> allWindows = driver.getWindowHandles(); 
		List<String> listOfWindow = new ArrayList(allWindows); 
		driver.switchTo().window(listOfWindow.get(1)); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByClassName("prod-sp")));
		String totPrice = driver.findElementByClassName("prod-sp").getText();
		int totalAmt=Integer.parseInt(totPrice.replaceAll("\\D", ""));
		if(totalAmt>=2690) {
			System.out.println("Coupon is Applicable");
			String couponCode = driver.findElementByXPath("//div[text()='EPIC']").getText();
			System.out.println(couponCode);
		}
		else {
			System.out.println("Coupon is not Applicable");
		}


		String discountedPrice = driver.findElementByXPath("//div[text()='Get it for ']/span").getText(); 
		int discPrice = Integer.parseInt(discountedPrice.replaceAll("\\D", "")); 
		System.out.println("Discounted price: "+discPrice); 
		int couponAmount=totalAmt-discPrice; 
		System.out.println("Coupon Amount: "+couponAmount); 

		//Check the availability of the product for pincode 560043, print the expected delivery date if it is available 
		Thread.sleep(1000); 
		driver.findElementByXPath("//span[contains(text(),'Enter pin-code')]").click(); 
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[@name='pincode']"))); 
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("682001"); 
		Thread.sleep(500); 
		driver.findElementByXPath("//button[text()='CONFIRM PINCODE']").click(); 
		Thread.sleep(2000); 
		String pincodeMsg = driver.findElementByXPath("//ul[@class='edd-message-success-details']/li").getText(); 
		if(pincodeMsg.contains("Expected Delivery")) 
		{ 
			System.out.println("Product available for pincode"); 
		} 
		else System.err.println("Product not available for pincode"); 


		//Click on Other Informations under Product Details and Print the Customer Care address, phone and email 
		Actions builder=new Actions(driver);				
		builder.moveToElement(driver.findElementByXPath("//div[text()='Other information']")).click().build().perform(); 
		Thread.sleep(500); 
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Customer Care Address']"))); 
		String customerCareDetails = driver.findElementByXPath("//span[text()='Customer Care Address']/parent::li/span[@class='other-info']").getText(); 
		System.out.println("Customer care address, phone and email: "+customerCareDetails); 


		//Click on ADD TO BAG and then GO TO BAG 
		driver.findElementByXPath("//span[text()='ADD TO BAG']/ancestor::div[1]").click(); 
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='GO TO BAG']/ancestor::div[1]"))); 
		executor.executeScript("arguments[0].click();",driver.findElementByXPath("//span[text()='GO TO BAG']/ancestor::div[1]")); 
		Thread.sleep(1000); 
		if(driver.getTitle().contains("Shopping Bag")) 
		{ 
			System.out.println("Navigated to Shopping bag"); 
		} 


		//Check the Order Total before apply coupon 
		Thread.sleep(2000); 
		String strOrderTotal = driver.findElementByXPath("//span[text()='Order Total']/following-sibling::span").getText(); 
		String strOrderTot = strOrderTotal.replaceAll("\\D", ""); 
		int total = Integer.parseInt(strOrderTot.substring(0, strOrderTot.length()-2)); 
		System.out.println("Order total before applying coupon code: "+total); 

		// Enter Coupon Code and Click Apply 
		driver.findElementById("couponCodeInput").sendKeys("EPIC"); 
		driver.findElementByXPath("//button[text()='Apply']").click(); 
		System.out.println("Coupon applied"); 

		// Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details 
		Thread.sleep(1000); 
		String rawPriceAfterCoupon = driver.findElementByXPath("//span[@class='applied-coupon-section']/p").getText(); 
		String rawPriceAfter = rawPriceAfterCoupon.replaceAll("[:,a-zA-Z ]", ""); 
		String substring = rawPriceAfter.substring(1); 
		double dpriceAfterCoupon = Double.parseDouble(substring); 
		int priceAfterCoupon=(int)Math.round(dpriceAfterCoupon); 
		if(priceAfterCoupon==couponAmount) 
		{ 
			System.out.println("Price after couple applied matches with coupon amount: "+priceAfterCoupon); 
		} 

		// Click on Delete and Delete the item from Bag 
		driver.findElementByXPath("//div[@class='product-delete']/div").click(); 
		Thread.sleep(1000); 
		driver.findElementByXPath("//div[@class='card-delete-button']//div[text()='DELETE']").click(); 

		//Close browser 
		driver.quit(); 


	}

}