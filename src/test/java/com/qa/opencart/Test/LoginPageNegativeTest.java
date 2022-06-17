package com.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends Base {
	
	@DataProvider
	public Object[][] loginWrongTestData()
	{
		return new Object[][]
				{
			{"test11@gmail.com","test@123"} ,
			{"...", "test@123"},
			{"@#@#@gmail.com", "test@123"},
			{"hi", ""}
				};
	}
	
	@Test(dataProvider="loginWrongTestData")
	public void loginNegativeTest(String username, String passoword)
	{
		
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(username,passoword));
	}

}
