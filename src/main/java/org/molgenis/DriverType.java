package org.molgenis;

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
		public DesiredCapabilities getDesiredCapabilities() throws MalformedURLException {
//			return DesiredCapabilities.firefox();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");

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

	public RemoteWebDriver getDriver() throws MalformedURLException {
		RemoteWebDriver driver = getWebDriverInstance(getDesiredCapabilities());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;
	}
}