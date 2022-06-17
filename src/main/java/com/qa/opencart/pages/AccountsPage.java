package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By header = By.xpath("//div[@id='logo']/a");
	private By accountsPageSections = By.xpath("//div[@id='content']/h2");
	private By searchField = By.name("search");
	private By searchButton = By.xpath("//div[@id='search']//button");
	private By logoutLink = By.linkText("Logout");

	// get the AccountPageTitle

	public String getAccountPageTitle() {
		return eleUtil.doGetTitle(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNTS_PAGE_TITLE);
	}

	// get the Accounts Page Header Title

	public String getAccountsPageHeader(String value) {
		return eleUtil.doGetAttribute(accountsPageSections,value);
	}

	// Verify Logout Link Exist

	public boolean isLogoutLinkExist() {
		return eleUtil.doIsDisplayed(logoutLink);
	}

	// get the Account section List
	public List<String> getAccountSecList() {
		List<WebElement> ele = eleUtil.waitForElementsToBeVisible(10, accountsPageSections);
		List<String> accSecValList = new ArrayList<String>();
		for (int i = 0; i < ele.size(); i++) {
			String Text = ele.get(i).getText();
			accSecValList.add(Text);
		}
		return accSecValList;
	}
	// Verify the search text is enabled

	public boolean isSearchExist() {
		return eleUtil.doIsEnabled(searchField);
	}
	// Type the product in search and click search button

	public SearchResultsPage doSearch(String productName) {
		System.out.println("searching the product: " + productName);
		eleUtil.doSendKeys(searchField, productName);
		eleUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}

}
