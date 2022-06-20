package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public Properties prop;
	public WebDriver driver;
	public static String highlight;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(Properties prop) {
		String browsername = prop.getProperty("browser");
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		if (browsername.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browsername.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}

		else if (browsername.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		} else {
			System.out.println("please pass the right browser: " + browsername);
		}
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		return driver;

	}

	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}
	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("./src/test/resources/config/config.properties");
			try {
				prop.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return prop;

	}

}
