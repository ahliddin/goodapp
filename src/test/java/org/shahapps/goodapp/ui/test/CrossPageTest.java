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

import static org.junit.Assert.*;

/*
 * Here we test the reflexivity of two pages, that is the exact same values
 * inserted in form should appear in feedbacks page.
 * 
 */
@RunWith(Arquillian.class)
public class CrossPageTest {

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
	 * Valid form submit and checking if the same values are in feedbacks table
	 * 
	 */
	@Test
	public void firstValidTest() throws Exception {
		WebElement element;
		driver.get("http://goodapp-shahapps.rhcloud.com/feedbackspage");
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		
		submitForm ("John Travolta", "john.travolta5@email.com", "I dance like Jagger!", true);
		Thread.sleep(1000);

		/* Check feedbacks page. */
		driver.get("http://goodapp-shahapps.rhcloud.com/feedbackspage");
		Thread.sleep(2000);
		
		element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[1]"));
		assertEquals(element.getText(), "John Travolta");
		
		element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]"));
		assertEquals(element.getText(), "john.travolta5@email.com");
		
		element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[3]"));
		assertEquals(element.getText(), "I dance like Jagger!");
		
		element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[4]"));
		assertEquals(element.getText(), "true");
		
		Thread.sleep(2000);
	}
	
	/*
	 * Valid form submit and checking if the same values are in feedbacks table
	 * 
	 */
	@Test
	public void secondValidTest() throws Exception {
		WebElement element;

		submitForm ("John Travolta", "john.travolta5@email.com", "I dance like Jagger!", true);
		Thread.sleep(1000);

		submitForm ("Marylin Monroe", "marylin.monroe5@email.com", "I dance too!", false);
		Thread.sleep(1000);

		/* Check the feedbackpage */
		driver.get("http://goodapp-shahapps.rhcloud.com/feedbackspage");
		Thread.sleep(2000);
		
		element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[1]"));
		assertEquals(element.getText(), "Marylin Monroe");
		
		element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[2]"));
		assertEquals(element.getText(), "marylin.monroe5@email.com");
		
		element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[3]"));
		assertEquals(element.getText(), "I dance too!");
		
		element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[5]"));
		assertEquals(element.getText(), "false");
		
		Thread.sleep(2000);
	}
	
	/* 
	 * Assert, whether button "Clear all" endeed deletes all table entries.
	 */
	@Test
	public void clearFeedbacks() throws InterruptedException {
		driver.get("http://goodapp-shahapps.rhcloud.com/feedbackspage");
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		WebElement element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody"));
		
		Thread.sleep(1000);
		assertTrue("tbody should be empty", element.getText().isEmpty());

		Thread.sleep(2000);
	}
	
	@Test
	public void invalidForm() throws Exception {
		driver.get("http://goodapp-shahapps.rhcloud.com/");
		
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("john.travolta5@email.com");
		driver.findElement(By.id("comment")).clear();
		driver.findElement(By.id("comment")).sendKeys("I dance like Jagger!");
		driver.findElement(By.cssSelector("input.ng-pristine.ng-valid")).click();
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		
		WebElement element = driver.findElement(By.id("thx"));
		Thread.sleep(1000);

		// Now let's check feedbacks page.
		driver.get("http://goodapp-shahapps.rhcloud.com/feedbackspage");
		element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody"));
		
		Thread.sleep(1000);
		Assert.assertTrue("tbody should be empty", element.getText().isEmpty());
		
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
