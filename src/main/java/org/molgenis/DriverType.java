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
		public DesiredCapabilities getDesiredCapabilities()
		{
//			getDesiredCapabilities().setBrowserName("firefox");
//			getDesiredCapabilities().setJavascriptEnabled(true);
//			return DesiredCapabilities.firefox();
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setBrowserName("firefox");
			return new DesiredCapabilities();
		}

		@Override
		public RemoteWebDriver getWebDriverInstance(DesiredCapabilities capabilities) throws MalformedURLException {
//			FirefoxDriver driver = new FirefoxDriver(capabilities);
//			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//			return driver;

			RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL("http://134.96.235.134:4444/wd/hub"), capabilities);
			remoteWebDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return remoteWebDriver;
		}
	};


	public static final DriverType DEFAULT_DRIVERTYPE = FIREFOX;

	public RemoteWebDriver getDriver() throws MalformedURLException {
		RemoteWebDriver driver = getWebDriverInstance(getDesiredCapabilities());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
}