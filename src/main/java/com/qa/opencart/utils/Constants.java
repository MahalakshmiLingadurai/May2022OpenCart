package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";

	public static final int DEFAULT_TIME_OUT = 5;
	public static final int IMAC_IMAGE_COUNT = 3;
	public static final int MACBOOKPRO_IMAGE_COUNT = 4;
	public static final int MACBOOKAIR_IMAGE_COUNT = 4;

	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String ACCOUNTS_PAGE_HEADER = "https://naveenautomationlabs.com/opencart/index.php?route=common/home";
	public static final String LOGIN_ERROR_MESSG = "No match for E-Mail Address and/or Password";
	public static final String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "registartionData";

	public static List<String> getExpectedAccSecList() {
		List<String> a = new ArrayList<String>();
		a.add("My Account");
		a.add("My Orders");
		a.add("My Affiliate Account");
		a.add("Newsletter");
		return a;

	}

}
