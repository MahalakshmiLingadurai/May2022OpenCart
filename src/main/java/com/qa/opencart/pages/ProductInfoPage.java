package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private LinkedHashMap<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productImages = By.xpath("//ul[@class='thumbnails']//img");
	private By productMetaData = By.xpath("(//div[@id='content'] //ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content'] //ul[@class='list-unstyled'])[2]/li");
	private By qty = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");

	public String getProductHeader() {
		String productHeaderText = eleUtil.doGetText(productHeader);
		System.out.println("product header is: " + productHeaderText);
		return productHeaderText;
	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(10, productImages).size();
	}

	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("ProductHeader", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;

	}

	private void getProductMetaData() {

		List<WebElement> ele = eleUtil.waitForElementsToBeVisible(10, productMetaData);
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: Out Of Stock

		for (int i = 0; i < ele.size(); i++) {
			String text = ele.get(i).getText();
			String str[] = text.split(":");
			String metaKey = str[0];
			String metaValue = str[1];
			productInfoMap.put(metaKey, metaValue);

		}
	}

	private void getProductPriceData() {
		List<WebElement> ele = eleUtil.waitForElementsToBeVisible(10, productPriceData);
//		$2,000.00
//		Ex Tax: $2,000.00
		String price = ele.get(0).getText();
		String exPrice = ele.get(1).getText();
		productInfoMap.put("price", price);
		productInfoMap.put("ExTaxPrice", exPrice);
		

	}
}
