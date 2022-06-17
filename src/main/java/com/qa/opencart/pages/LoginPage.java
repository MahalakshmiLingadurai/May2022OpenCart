package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginErrorMesg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

//getting login page title value
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);
	}

//getting login page URL

	public Boolean getLoginPageUrl() {
		return eleUtil.waitForURLToContain(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_URL_FRACTION);
	}

//checking forgot pwd link exist or not
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}

//checking register link exist or not
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
//do login with Username and  password

	public AccountsPage doLogin(String userName, String password) {
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	public boolean doLoginWithWrongCredentials(String userName, String password) {
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doClick(loginBtn);
		String errorMesg = eleUtil.doGetText(loginErrorMesg);
		System.out.println(errorMesg);
		if (errorMesg.contains(Constants.LOGIN_ERROR_MESSG)) {
			System.out.println("login is not done....");
			return false;
		}
		return true;
	}
	
	public RegistrationPage goToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
