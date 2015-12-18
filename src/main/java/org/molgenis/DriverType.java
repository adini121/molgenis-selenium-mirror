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
//			return DesiredCapabilities.firefox();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("firefox");
			cap.setJavascriptEnabled(true);
			return cap;
		}

		@Override
		public RemoteWebDriver getWebDriverInstance(DesiredCapabilities cap) throws MalformedURLException {
			RemoteWebDriver driver = new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), cap);
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