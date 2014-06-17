package org.shahapps.goodapp.ui.test;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


@RunWith(Arquillian.class)
public class UITest {
    
    @Drone
    WebDriver driver;


	@Test
	public void test() throws Exception {
		driver.get("http://goodapp-shahapps.rhcloud.com/");

	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys("John Travolta");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("john.travolta5@email.com");
	    driver.findElement(By.id("comment")).clear();
	    driver.findElement(By.id("comment")).sendKeys("I dance like Jagger!");
	    driver.findElement(By.cssSelector("input.ng-pristine.ng-valid")).click();
	    driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	    // Warning: verifyTextPresent may require manual changes
      	Assert.assertTrue("Message \"Thank you...\" is missing", driver.findElement(By.id("thx")).getText().matches("Thank you for your feedback!"));

	    Thread.sleep(2000);
	}

}
