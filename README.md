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


Day3: MakeMy Trip

1) Go to https://www.makemytrip.com/
2) Click Hotels
3) Enter city as Goa, and choose Goa, India
4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5
5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
6) Click Search button
7) Select locality as Baga
8) Select 5 start in Star Category under Select Filters
9) Click on the first resulting hotel and go to the new window
10) Print the Hotel Name 
11) Click MORE OPTIONS link and Select 3Months plan and close
12) Click on BOOK THIS NOW
13) Print the Total Payable amount
14) Close the browser

Day4: HP Store

1) Go to https://store.hp.com/in-en/
2) Mouse over on Laptops menu and click on Pavilion
3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
4) Hard Drive Capacity -->More than 1TB
5) Select Sort By: Price: Low to High
6) Print the First resulting Product Name and Price
7) Click on Add to Cart
8) Click on Shopping Cart icon --> Click on View and Edit Cart
9) Check the Shipping Option --> Check availability at Pincode
10) Verify the order Total against the product price
11) Proceed to Checkout if Order Total and Product Price matches
12) Click on Place Order
13) Capture the Error message and Print
14) Close Browser

