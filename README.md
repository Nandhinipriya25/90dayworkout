# 90dayworkout - Mintra

1) Open https://www.myntra.com/
2) Mouse over on WOMEN (Actions -> moveToElement
3) Click Jackets & Coats
4) Find the total count of item (top) -> getText -> String

	 String str = driver.findElementByClassName("title-count").getText();
	 split, 
	 String text = str.replaceAll("\\D","") -> String
	 Integer.parseInt(text) -> int

5) Validate the sum of categories count matches
6) Check Coats
7) Click + More option under BRAND	
8) Type MANGO and click checkbox
9) Close the pop-up x
10) Confirm all the Coats are of brand MANGO
    findElements (brand) -> List<WebElement> 
    foreach -> getText of each brand 
    compare > if(condition)
11) Sort by Better Discount
12) Find the price of first displayed item
     findElements (price) -> List<WebElement> 
     get(0) -> WebElement -> getText -> String -> int
13) Mouse over on size of the first item
14) Click on WishList Now
15) Close Browser

