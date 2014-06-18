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


/*
 * Here we test the functionality of feedbackspage.html
 * 
 */
@RunWith(Arquillian.class)
public class FeedbacksTest {

	@Drone
	WebDriver driver;

	
	/* 
	 * Inserting some feedback - making sure the table is not empty.
	 */
	@Before 
	public void prepareGoodApp() {
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.get("http://goodapp-shahapps.rhcloud.com/");
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("john.travolta5@email.com");
		driver.findElement(By.id("comment")).clear();
		driver.findElement(By.id("comment")).sendKeys("I dance like Jagger!");
		driver.findElement(By.cssSelector("input.ng-pristine.ng-valid")).click();
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	}
	
	/* 
	 * Assert, whether button "Clear all" endeed deletes all table entries.
	 */
	@Test
	public void clearFeedbacks() throws InterruptedException {
		driver.get("http://goodapp-shahapps.rhcloud.com/feedbackspage");
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		WebElement element = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody"));
		
		Thread.sleep(1000);
		Assert.assertTrue("tbody should be empty", element.getText().isEmpty());

		Thread.sleep(2000);
	}
	
	
	

}
