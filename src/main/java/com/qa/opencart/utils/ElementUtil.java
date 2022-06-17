package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement ele= driver.findElement(locator);
		if(DriverFactory.highlight.equals("true"))
		{
			jsUtil.flash(ele);
		}
		return ele;
	}

	public String doGetTitle() {
		return driver.getTitle();
	}

	public String doGetTitle(int timeout, String value) {
		if (waitForTitleToBe(timeout, value)) {
			return driver.getTitle();
		}
		return null;
	}
	
	public boolean doIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	
	public boolean doIsEnabled(By locator)
	{
		return getElement(locator).isEnabled();
	}
	
	public void doClear(By locator)
	{
		getElement(locator).clear();
	}
	public void doSendKeys(By locator ,String value)
	{
		doClear(locator);
		getElement(locator).sendKeys(value);
	}
	
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	public String doGetAttribute(By locator ,String value)
	{
		return getElement(locator).getAttribute(value);
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	/**************** wait util WebElements  *****************/
	public List<WebElement> waitForElementsToBeVisible(int timeout,By locator)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**************** wait util for Non-WebElements *****************/
	public Boolean waitForTitleToBe(int timeout, String value) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return w.until(ExpectedConditions.titleIs(value));

	}

	public Boolean waitForURLToContain(int timeout, String value) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return w.until(ExpectedConditions.urlContains(value));
	}

}
