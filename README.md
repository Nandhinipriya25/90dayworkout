# 90dayworkout - 

Day1 : Mintra

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


Day2 : Nykaa
1) Go to https://www.nykaa.com/
2) Mouseover on Brands and Mouseover on Popular
3) Click L'Oreal Paris
4) Go to the newly opened window and check the title contains L'Oreal Paris
5) Click sort By and select customer top rated
6) Click Category and click Shampoo
7) check whether the Filter is applied with Shampoo
8) Click on L'Oreal Paris Colour Protect Shampoo
9) GO to the new window and select size as 175ml
10) Print the MRP of the product
11) Click on ADD to BAG
12) Go to Shopping Bag 
13) Print the Grand Total amount
14) Click Proceed
15) Click on Continue as Guest
16) Print the warning message (delay in shipment)
17) Close all windows
