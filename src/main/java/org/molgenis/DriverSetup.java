package org.molgenis;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public interface DriverSetup
{
	RemoteWebDriver getWebDriverInstance(DesiredCapabilities desiredCapabilities) throws MalformedURLException;
	DesiredCapabilities getDesiredCapabilities();
}