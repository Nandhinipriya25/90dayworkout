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

Day6 : Bigbasket.com

1) Go to https://www.bigbasket.com/
2) mouse over on  Shop by Category 
3)Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS 
4) Click on Boiled & Steam Rice
5) Choose the Brand as bb Royal
6) Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
7) print the price of Rice
8) Click Add button
9) Verify the success message displayed 
10) Type Dal in Search field and enter
12) Go to Toor/Arhar Dal and select 2kg & set Qty 2 
13) Print the price of Dal
14) Click Add button
15) Mouse hover on My Basket 
16) Validate the Sub Total displayed for the selected items
17) Reduce the Quantity of Dal as 1 
18) Validate the Sub Total for the current items
19) Close the Browser

Day 7 : Honda
22/04/2020
=============
1) Go to https://www.honda2wheelersindia.com/
2) Click on scooters and click dio
3) Click on Specifications and mouseover on ENGINE
4) Get Displacement value
5) Go to Scooters and click Activa 125
6) Click on Specifications and mouseover on ENGINE
7) Get Displacement value
8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
9) Click FAQ from Menu 
10) Click Activa 125 BS-VI under Browse By Product
11) Click  Vehicle Price 
12) Make sure Activa 125 BS-VI selected and click submit
13) click the price link
14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
15) Click Search
16) Print all the 3 models and their prices
17) Close the Browser

Day 8: Pepperfry

23/04/2020
============
1) Go to https://www.pepperfry.com/
2) Mouseover on Furniture and click Office Chairs under Chairs
3) click Executive Chairs
4) Change the minimum Height as 50 in under Dimensions
5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
6) Mouseover on Homeware and Click Pressure Cookers under Cookware
7) Select Prestige as Brand
8) Select Capacity as 1-3 Ltr
9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
10) Verify the number of items in Wishlist
11) Navigate to Wishlist
12) Move Pressure Cooker only to Cart from Wishlist
13) Check for the availability for Pincode 600128
14) Click Proceed to Pay Securely
15 Click Proceed to Pay
16) Capture the screenshot of the item under Order Item
17) Close the browser

Day 9 : 24/04/2020
============
1) Go to https://demo.1crmcloud.com/
2) Give username as admin and password as admin
3) Choose theme as Claro Theme
4) Click on Sales and Marketting 
5) Click Create contact
6) Select Title and type First name, Last Name, Email and Phone Numbers
7) Select Lead Source as "Public Relations"
8) Select Business Roles as "Sales"
9) Fill the Primary Address, City, State, Country and Postal Code and click Save
10) Mouse over on Today's Activities and click Meetings
11) Click Create 
12) Type Subject as "Project Status" , Status as "Planned" 
13) Start Date & Time as tomorrow 3 pm and Duration as 1hr
14) Click Add paricipants, add your created Contact name and click Save
15) Go to Sales and Marketting-->Contacts
16) search the lead Name and click the name from the result
17) Check weather the Meeting is assigned to the contact under Activities Section.

Day 10 : 27/04/2020
============
1) https://www.justdial.com/
2) Set the Location as Chennai
3) Click Auto Care in the left menu
4) Click Car Repair
5) Click Car Brand as Hyundai
6) Click Make as Hyundai Xcent
7) Click on Location and Enter Porur
8) Select Porur from the dropdown list
9) Select Distance starting from 1 km
10) Identify all the Service Center having Ratings >=4.5 and Votes >=50
11) Save all the Service Center name and Phone number matching the above condition in excel 
12) Close the browser

Day 11 : 28/04/2020
============
1) Go to https://www.snapdeal.com/
‎2) Mouse over on Toys, Kids' Fashion & more and click on Toys
3) Click Educational Toys in Toys & Games
‎4) Click the Customer Rating 4 star and Up 
5) Click the offer as 40-50
6) Check the availability for the pincode
7) Click the Quick View of the first product 
8) Click on View Details
9) Capture the Price of the Product and Delivery Charge
10) Validate the You Pay amount matches the sum of (price+deliver charge)
11) Search for Sanitizer
12) Click on Product "BioAyurveda Neem Power Hand Sanitizer"
13) Capture the Price and Delivery Charge
14) Click on Add to Cart
15) Click on Cart 
16) Validate the Proceed to Pay matches the total amount of both the products
17) Close all the windows

Day 12 : 29/04/2020
==========
1) Go to https://www.carwale.com/
2) Click on Used
3) Select the City as Chennai
4) Select budget min (8L) and max(12L) and Click Search
5) Select Cars with Photos under Only Show Cars With
6) Select Manufacturer as "Hyundai" --> Creta
7) Select Fuel Type as Petrol
8) Select Best Match as "KM: Low to High"
9) Validate the Cars are listed with KMs Low to High
10) Add the least KM ran car to Wishlist
11) Go to Wishlist and Click on More Details
12) Print all the details under Overview 
13) Close the browser.

Day 13 : 1 Go to https://studyabroad.shiksha.com/
 
4 2 Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges
 
5 3 Select GRE under Exam Accepted and Score 300 & Below 
 
6 4 Max 10 Lakhs under 1st year Total fees, USA under countries
 
7 5 Select Sort By: Low to high 1st year total fees
 
8 6 Click Add to compare of the College having least fees with Public University, Scholarship and Accomadation facilities
 
9 7 Select the first college under Compare with similar colleges 
 
10 8 Click on Compare College>
 
11 9 Select When to Study as 2021
 
12 10 Select Preferred Countries as USA
 
13 11 Select Level of Study as Masters
 
14 12 Select Preferred Course as MS
 
15 13 Select Specialization as "Computer Science & Engineering"
 
16 14 Click on Sign Up
 
17 15 Print all the warning messages displayed on the screen for missed mandatory fields 

