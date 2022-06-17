package com.qa.opencart.Test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends Base {

	@BeforeClass
	public void setupRegistration() {
		registrationPage = loginPage.goToRegisterPage();
	}
	
	public String getRandomEmail() {
		
		Random randomGenerator = new Random(); 
		String email= "opencart"+randomGenerator.nextInt(1000)+"@gamil.com";
		return email;
	}
	
@DataProvider()
public Object[][] getRegisterData()
{
	return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	
}
	@Test(dataProvider="getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String telphone,
			String password, String subscribe) {
		
		Assert.assertTrue(registrationPage.accountRegistration(
				firstName, lastName, getRandomEmail(), telphone,
				 password, subscribe));

	}
	}


