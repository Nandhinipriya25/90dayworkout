package vanilascripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class day3MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver1.exe");
		ChromeDriver driver=new ChromeDriver();

		//Go to https://www.makemytrip.com/
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//Click Hotels
		driver.findElementByXPath("(//span[contains(@class,'chNavIcon appendBottom2')])[2]").click();


		//Enter city as Goa, and choose Goa, India
		driver.findElementByXPath("//input[@class='hsw_inputField font30 lineHeight36 latoBlack']")
		.sendKeys("Goa",Keys.TAB);


		//Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		driver.findElementByXPath("//div[@aria-label='Fri May 15 2020']").click();
		driver.findElementByXPath("//div[@aria-label='Wed May 20 2020']").click();
		Thread.sleep(2000);



		// Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		driver.findElementByXPath("(//span[@class='font30 lineHeight36 latoBlack'])[3]").click();
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		WebElement child = driver.findElementByClassName("ageSelectBox");
		Select selChild=new Select(child);
		selChild.selectByVisibleText("12");
		driver.findElementByXPath("//button[@class='primaryBtn btnApply']").click(); 


		//Click Search button
		driver.findElementById("hsw_search_button").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();


		//Select locality as Baga
		driver.findElementByXPath("//label[@for='mmLocality_checkbox_35']").click();
		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='5 Star']")));

		//Select 5 start in Star Category under Select Filters
		driver.findElementByXPath("//label[text()='5 Star']").click();


		//Click on the first resulting hotel and go to the new window
		driver.findElementByXPath("//span[text()='The Park Baga River Goa']").click();

		// Print the Hotel Name 
		Set<String> allWindows = driver.getWindowHandles();
		List<String> windowList=new ArrayList<String>(allWindows);
		driver.switchTo().window(windowList.get(1));
		System.out.println(driver.findElementByXPath("(//ul[contains(@class,'_BreadCrumbs breadCrumbs')]//li)[3]").getText());


		// Click MORE OPTIONS link and Select 3Months plan and close

		driver.findElementByXPath("(//span[text()='MORE OPTIONS'])[1]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//span[@class='latoBold font12 pointer makeFlex hrtlCenter right blueText'])[1]").click();
		driver.findElementByXPath("//span[@class='close']").click();

		// Click on BOOK THIS NOW
		driver.findElementById("detpg_headerright_book_now").click();

		// Print the Total Payable amount
		String totAmt = driver.findElementById("revpg_total_payable_amt").getText();
		int totalPayable=Integer.parseInt(totAmt.replaceAll("\\D", ""));
		System.out.println(totalPayable);
		driver.findElementByXPath("//span[@class='close']").click();

		// Close the browser
		driver.quit();

	}

}
