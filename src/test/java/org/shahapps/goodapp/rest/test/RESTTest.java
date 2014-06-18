package org.shahapps.goodapp.rest.test;

import java.util.ArrayList;

import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shahapps.goodapp.domain.Feedback;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml",
		"classpath:/META-INF/spring/applicationContext.xml" })
public class RESTTest {

	@Before
	public void removeAllFeedbacks() {
		/*
		 * Sending HTTP DELETE request to remove all feedbacks
		 */
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://goodapp-shahapps.rhcloud.com/feedbacks");
	}
	
	@Test
	public void getTest() {
		/*
		 * Testing the REST GET
		 */
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Feedback> list = null;
		list = restTemplate.getForObject("http://goodapp-shahapps.rhcloud.com/feedbacks", ArrayList.class);
		assertEquals(0, list.size());
		
	}
	
	@Test 
	public void postAndGet() {
		Feedback feedback = new Feedback();
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Feedback> list = null;
		HttpHeaders headers = new HttpHeaders();
		
		feedback.setName("Rest Template");
		feedback.setEmail("rest.template@spring.org");
		feedback.setComment("I am the REST tester, call me RestTemplate!");
		feedback.setSpam(1);


		String JSONInput = JSONObject.fromObject(feedback).toString();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<String> request = new HttpEntity<String>(JSONInput, headers);
		
		try {
			ResponseEntity<Feedback> r = restTemplate.postForEntity ("http://goodapp-shahapps.rhcloud.com/feedbacks", request, Feedback.class);
			
			list = restTemplate.getForObject("http://goodapp-shahapps.rhcloud.com/feedbacks", ArrayList.class);
			System.out.println("\n******\n" + list);
		}
		catch (Exception e) {
			System.out.println("\n*******error:  " + e.getMessage());
			e.printStackTrace();
		}
	}

}
