package org.shahapps.goodapp.ui.test;

import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@RunWith(Arquillian.class)
public class SpeakupTest {
	@Drone
	WebDriver driver;

	/* 
	 * Deleting all feedbacks - setting the page to initial state.
	 */
	@Before 
	public void prepareGoodApp() {
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		driver.get("http://goodapp-shahapps.rhcloud.com/feedbackspage");
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	}
	
	/*
	 * Assert, whether properly filled up form is submitted.
	 */
	@Test
	public void validFormSubmit() throws Exception {
		
		submitForm("John Travolta", "john.travolta5@email.com", "I dance like Jagger!", true);

		WebElement element = driver.findElement(By.id("thx"));
		Thread.sleep(1000);
		
		Assert.assertTrue("Message \"Thank you...\" is missing", element
				.getText().matches("Thank you for your feedback!"));

		Thread.sleep(2000);
	}
	
	/*
	 * Form with empty NAME field cannot be submitted.
	 */
	@Test
	public void nameEmpty() throws Exception {
		
		submitForm("", "john.travolta5@email.com", "I dance like Jagger!", true);

		WebElement element = driver.findElement(By.id("thx"));
		Thread.sleep(1000);

		Assert.assertFalse("Message \"Thank you\" shouldn't be here!", 
				element.getText().matches("Thank you for your feedback!"));

		Thread.sleep(2000);
	}
	
	/*
	 * Form with empty EMAIL field cannot be submitted.
	 */
	@Test
	public void emailEmpty() throws Exception {
		submitForm("John Travolta", "", "I dance like Jagger!", true);

		WebElement element = driver.findElement(By.id("thx"));
		Thread.sleep(1000);
		
		Assert.assertFalse("Message \"Thank you\" shouldn't be here!", element
				.getText().matches("Thank you for your feedback!"));

		Thread.sleep(2000);
	}
	
	/*
	 * Form with empty COMMENT field cannot be submitted.
	 */
	@Test
	public void commentEmpty() throws Exception {
		
		submitForm("John Travolta", "john.travolta5@email.com", "", true);
		
		WebElement element = driver.findElement(By.id("thx"));
		Thread.sleep(1000);
		
		Assert.assertFalse("Message \"Thank you\" shouldn't be here!", 
				element.getText().matches("Thank you for your feedback!"));

		Thread.sleep(2000);
	}
	
	public void submitForm (String name, String email, String comment, boolean spam) {
		driver.get("http://goodapp-shahapps.rhcloud.com/");
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("comment")).clear();
		driver.findElement(By.id("comment")).sendKeys(comment);
		if (spam) {
			driver.findElement(By.cssSelector("input.ng-pristine.ng-valid")).click();
		}
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		
	}

}
