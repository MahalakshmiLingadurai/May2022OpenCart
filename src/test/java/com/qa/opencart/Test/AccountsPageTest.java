package com.qa.opencart.Test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends Base {

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String actTitle = accountsPage.getAccountPageTitle();
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);

	}

	@Test(enabled = false)
	public void accPageHeaderTest() {
		String header = accountsPage.getAccountsPageHeader("href");
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}

	@Test(priority = 4)
	public void accPageSectionsTest() {
		List<String> ele = accountsPage.getAccountSecList();
		Assert.assertEquals(ele, Constants.getExpectedAccSecList());
	}

	@DataProvider()
	public Object[][] productData() {
		return new Object[][] { { "MacBook" }, { "Apple" }, { "Samsung" }, };
	}

	@Test(priority = 5, dataProvider = "productData")
	public void SearchTest(String productName) {
		searchResultPage=accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultPage.getProductsListCount()>0);
		
	}
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] { 
			{ "MacBook" , "MacBook Pro"}, 
			{ "iMac", "iMac" }, 
			{ "Samsung" , "Samsung SyncMaster 941BW"},
			{"Apple", "Apple Cinema 30\""}
			};
	}
	
	@Test(priority = 6,dataProvider="productSelectData")
	public void ProductInfoPage(String productName, String mainProductName)
	{
		searchResultPage=accountsPage.doSearch(productName);
		searchResultPage.getProductsListCount();
		searchResultPage.selectProduct(mainProductName);
		
	}
	

	
	
	
}
