package org.molgenis.selenium.test;

import org.molgenis.DriverType;
import org.molgenis.JenkinsConfig;
import org.molgenis.selenium.model.SignInAppModel;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@ContextConfiguration(classes = JenkinsConfig.class)
public class SignInAppTest extends AbstractTestNGSpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(AnnotatorTest.class);
	private RemoteWebDriver driver;
	private SignInAppModel model;

	@Value("${test.baseurl}")
	private String baseURL;

	@Value("${test.uid}")
	private String uid;

	@Value("${test.pwd}")
	private String pwd;

	@BeforeClass
	public void beforeSuite() throws InterruptedException, MalformedURLException {
		driver = DriverType.FIREFOX.getDriver();
		model = new SignInAppModel(driver);
	}

	@Test
	public void test1() throws InterruptedException
	{
		driver.get(baseURL);

		// open the signin
		model.open();
		// should result in a popup where we type username and password

		model.signIn(uid, "blaat");

		// should show error messages
		Assert.assertTrue(model.shows("The username or password you entered is incorrect"));

		model.signIn(uid, pwd);

		// should show sign out button
		Assert.assertTrue(model.shows("Sign out"));

		model.signOut();

		// should show sign in button again
		Assert.assertTrue(model.shows("Sign in"));
	}

	@AfterClass
	public void afterClass() throws InterruptedException
	{
		// Clear cookies
		this.driver.manage().deleteAllCookies();

		// Close driver
		this.driver.close();
	}
}
