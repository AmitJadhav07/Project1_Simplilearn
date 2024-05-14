package com.Assignment1_Simlilearn;


import java.util.ArrayList;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;





public class Amazon_Assignment1_Simplilearn {

	public static void main(String[] args) {
		
		
		Scanner inputFromUser = new Scanner(System.in);
		System.out.println("Please enter your credentials");
		System.out.println();
		System.out.print("Enter UserId or MobileNo. :- ");
		String userIDInput = inputFromUser.next();
		System.out.print("Enter Password :- ");
		String passwordInput = inputFromUser.next();
		inputFromUser.close();
		
		String productName = "Arduino UNO";  //Type product name here, which you want to search for
		String orderQty = "3";               //Type product order quantity here.
		
		
		
		
		
		System.setProperty("webdriver.chrome.driver", "./BrowserUtils/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		WebElement login1 = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
		login1.click();
		
		WebElement userID = driver.findElement(By.xpath("//input[@type='email']"));
		userID.sendKeys(userIDInput);
		WebElement login2 = driver.findElement(By.xpath("//*[@id='continue']"));
		login2.click();
		
		WebElement pass = driver.findElement(By.xpath("//*[@id='ap_password']"));
		pass.sendKeys(passwordInput);
		WebElement login3 = driver.findElement(By.xpath("//*[@id='signInSubmit']"));
		login3.click();
		
		WebElement searchBar = driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
		searchBar.sendKeys(productName);
		
		WebElement search = driver.findElement(By.xpath("//*[@id='nav-search-submit-button']"));
		search.click();
		
		WebElement product1 = driver.findElement(By.partialLinkText(productName));
		product1.click();
		
		ArrayList<String> chromeTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window((String)chromeTabs.get(1));
		
		WebElement selectedProduct = driver.findElement(By.xpath("//*[@id=\"productTitle\"]"));
		String selectedProductName = selectedProduct.getText();
		
		
		try {
			WebElement productQty = driver.findElement(By.xpath("//*[@id=\'quantity\']"));
			Select selectProductQty = new Select(productQty);
			selectProductQty.selectByVisibleText(orderQty);
		}
		
		catch (Exception a) {
			//System.out.println(a);
			System.out.println();
			System.out.println();
			System.out.println("sorry! Item is out of stock");
		}
		
		
		
		WebElement addToCart = driver.findElement(By.xpath("//*[@id=\'add-to-cart-button\']"));
		addToCart.click();
		
		WebElement goToCart = driver.findElement(By.xpath("//*[@id='sw-gtc']/span/a"));
		goToCart.click();
		
		WebElement eleSelectedQty = driver.findElement(By.xpath("//span[@id=\"a-autoid-0-announce\"]/span[2]"));
		String selectedQty = eleSelectedQty.getText();
		

		System.out.println("Ordered Quantity is :- " +orderQty);
		System.out.println("Selected Quantity is :- " +selectedQty);
		
		boolean verifyProductName = selectedProductName.contains(productName);
		System.out.println("Product Name Verifyed :- " + verifyProductName);
		boolean verifyOrderQty = selectedQty==orderQty;
		System.out.println("Ordered Qty Verified :- " + verifyOrderQty);
		
		
		
		SoftAssert asrt1 = new SoftAssert();
		asrt1.assertEquals(orderQty, selectedQty);

	}
}
