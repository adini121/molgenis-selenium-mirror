package org.molgenis;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public enum DriverType implements DriverSetup
{

	FIREFOX
	{
		@Override
		public DesiredCapabilities getDesiredCapabilities() {
//			return DesiredCapabilities.firefox();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browser", "PHANTOMJS_198_MACOS_10.11_64");
			capabilities.setCapability("apikey", "c717c5b3-a307-461e-84ea-1232d44cde89");
			capabilities.setCapability("email", "test@testfabrik.com");
			capabilities.setCapability("record", false);
			capabilities.setCapability("extract", false);
			return capabilities;

		}

		@Override
		public RemoteWebDriver getWebDriverInstance(DesiredCapabilities capabilities) throws MalformedURLException {
			RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return driver;
		}
	};

	public static final DriverType DEFAULT_DRIVERTYPE = FIREFOX;

	public WebDriver getDriver() throws MalformedURLException {
		WebDriver driver = getWebDriverInstance(getDesiredCapabilities());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
}