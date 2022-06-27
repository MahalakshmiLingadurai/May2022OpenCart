package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browsername.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
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
		return getDriver();

	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");// qa/dev/stage/uat

		if (envName == null) {
			System.out.println("Running on PROD env: ");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on environment: " + envName);
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;

				default:
					System.out.println("please pass the right environment.....");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
