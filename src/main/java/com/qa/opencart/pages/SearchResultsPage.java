package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By productResults = By.xpath("//div[@class='caption']//a");

	// Get the count of the products
	public int getProductsListCount() {
		int count= eleUtil.waitForElementsToBeVisible(10, productResults).size();
		System.out.println("the search product count: " + count);
		return count;
	}

	// [or]
	/*
	 * public int getProductsListCount() { int resultCount =
	 * eleUtil.waitForElementsToBeVisible(productResults, 10, 2000).size();
	 * System.out.println("the search product count: " + resultCount); return
	 * resultCount;
	 */

	// select any one of the product and click

	public ProductInfoPage selectProduct(String mainProductName) {
		List<WebElement> searchList = eleUtil.waitForElementsToBeVisible(10, productResults);
		for (int i = 0; i < searchList.size(); i++) {
			String text = searchList.get(i).getText();
			if (text.equals(mainProductName)) {
				searchList.get(i).click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
