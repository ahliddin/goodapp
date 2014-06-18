package org.shahapps.goodapp.rest.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shahapps.goodapp.domain.Feedback;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml",
		"classpath:/META-INF/spring/applicationContext.xml" })
public class RESTTest {

	@Test
	public void test_save() {
		Feedback feedback = new Feedback();
		ArrayList<Feedback> list = null;
		feedback.setName("Rest Template");
		feedback.setEmail("rest.template@spring.org");
		feedback.setComment("I am the REST tester, call me RestTemplate!");
		feedback.setSpam(1);

		RestTemplate restTemplate = new RestTemplate();

		String JSONInput = JSONObject.fromObject(feedback).toString();
		System.out.println();
		System.out.println(JSONInput);
		System.out.println();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(JSONInput, headers);
		try {
//			restTemplate.postForObject(
//					"http://goodapp-shahapps.rhcloud.com/feedbacks", request,
//					Void.class);
			list = restTemplate.getForObject("http://goodapp-shahapps.rhcloud.com/feedbacks", ArrayList.class);
			System.out.println("\n******\n" + list);
		} catch (Exception e) {
			System.out.println("\n*******error:  " + e.getMessage());
			e.printStackTrace();

		}
	}

}
