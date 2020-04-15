package vanilascripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class day1Myntra {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");

		//disable windownotifications
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");

		//Open URL
		ChromeDriver driver=new ChromeDriver(options);
		driver.get("http://www.Myntra.com"); 	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//Declare a variable for categories total count
		int totalJacketsCoats;

		//Mouse Hover and sel woman-jackets		
		WebElement selWomen = driver.findElementByXPath("(//div[@class='desktop-navLink'])[2]");
		WebElement selCoats = driver.findElementByXPath("//a[@data-reactid='231']");
		Actions builder=new Actions(driver);
		builder.moveToElement(selWomen).perform(); 
		Thread.sleep(2000);
		builder.click(selCoats).perform();

		// Find count of Jackets and coats
		String countOfJacketsCoats = driver.findElementByXPath("//span[@class='title-count']").getText();
		int splitcountOfJacketsCoats=Integer.parseInt(countOfJacketsCoats.replaceAll("\\D", ""));
		System.out.println(splitcountOfJacketsCoats);

		//find Count Jackets
		String countOfJackets = driver.findElementByXPath("(//label[@class='common-customCheckbox vertical-filters-label'])[1]").getText();
		int jacketCount=Integer.parseInt(countOfJackets.replaceAll("\\D", ""));
		System.out.println(jacketCount);

		//Find Count of Coats
		String countOfCoats=driver.findElementByXPath("(//label[@class='common-customCheckbox vertical-filters-label'])[2]").getText();
		int coatscount=Integer.parseInt(countOfCoats.replaceAll("\\D", ""));
		System.out.println(coatscount);

		//Add both counts
		totalJacketsCoats=jacketCount+coatscount;

		//Compare it with count of jackets and coats
		if(splitcountOfJacketsCoats==totalJacketsCoats) {
			System.out.println("Count Matched");
		}
		else {
			System.out.println("cOunt not matched");
		}

		//Check Coats
		driver.findElementByXPath("(//label[@class='common-customCheckbox vertical-filters-label'])[2]").click();

		//Click More
		driver.findElementByXPath("//div[@class='brand-more']").click();

		//Search Mango
		driver.findElementByXPath("//input[@class='FilterDirectory-searchInput']").sendKeys("MANGO");
		driver.findElementByXPath("(//label[text()='MANGO'])[2]").click();
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		Thread.sleep(2000);

		//Find All Products are Mango brand
		List<WebElement> brandNameList = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		for(WebElement eachBrand:brandNameList) {

			if(eachBrand.getText().equalsIgnoreCase("Mango")) {
				System.out.println("Product is from Brand Mango");
			}
			else
			{
				System.out.println("Product is not Mango Brand");
			}
		}

		//Select sort by better discount
		WebElement sortOption = driver.findElementByXPath("//span[text()='Recommended']");
		builder.moveToElement(sortOption).perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//label[text()='Better Discount']").click();
		Thread.sleep(2000);


		//Find the price of first element
		List<WebElement> listOfPrice = driver.findElementsByClassName("product-discountedPrice");
		String priceOfFirstProduct =listOfPrice.get(0).getText();
		System.out.println(priceOfFirstProduct);

		//Mouse Hover on size and click on wishlist
		WebElement sizeOfFirstItem = driver.findElementByClassName("product-price");
		builder.moveToElement(sizeOfFirstItem).perform();
		driver.findElementByXPath("(//span[contains(@class,'product-actionsButton product-wishlist')])[1]").click();
		
		//close browser
		driver.close();
	}

}
