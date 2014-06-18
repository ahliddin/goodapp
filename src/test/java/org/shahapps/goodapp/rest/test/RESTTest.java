package org.shahapps.goodapp.rest.test;

import java.util.ArrayList;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shahapps.goodapp.domain.Feedback;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
		list = restTemplate.getForObject(
				"http://goodapp-shahapps.rhcloud.com/feedbacks",
				ArrayList.class);
		assertEquals(0, list.size());

	}

	@Test
	public void postAndGet() {
		Feedback feedback = new Feedback();
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Feedback> list = null;
		HttpHeaders headers = new HttpHeaders();
		String name = "Rest Template";
		String email = "rest.template@spring.org";
		String comment = "I am the REST tester, call me RestTemplate!";
		Integer spam = 1;

		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setComment(comment);
		feedback.setSpam(spam);

		String JSONInput = JSONObject.fromObject(feedback).toString();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<String> request = new HttpEntity<String>(JSONInput, headers);

		try {
			restTemplate
					.put("http://goodapp-shahapps.rhcloud.com/feedbacks",
							request);
			list = restTemplate.getForObject(
					"http://goodapp-shahapps.rhcloud.com/feedbacks",
					ArrayList.class);

			assertEquals(1, list.size());
			assertEquals(name, list.get(0).getName());
			assertEquals(email, list.get(0).getEmail());
			assertEquals(comment, list.get(0).getComment());
			assertEquals(spam, list.get(0).getSpam());
		} catch (Exception e) {
			System.out.println("\n***error:  " + e.getMessage());
		}
	}

	@Test
	public void wrongPost() {
		/*
		 * Sending a form with empty name
		 */
		Feedback feedback = new Feedback();
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Feedback> list = null;
		HttpHeaders headers = new HttpHeaders();
		String name = "";
		String email = "rest.template@spring.org";
		String comment = "I am the REST tester, call me RestTemplate!";
		Integer spam = 1;

		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setComment(comment);
		feedback.setSpam(spam);

		String JSONInput = JSONObject.fromObject(feedback).toString();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<String> request = new HttpEntity<String>(JSONInput, headers);

		try {
			restTemplate.put("http://goodapp-shahapps.rhcloud.com/feedbacks",
					request);
			list = restTemplate.getForObject(
					"http://goodapp-shahapps.rhcloud.com/feedbacks",
					ArrayList.class);

			assertEquals(0, list.size());

		} catch (Exception e) {
			System.out.println("\n***error:  " + e.getMessage());
		}
	}

	@Test
	public void tooLongName() {
		/*
		 * Sending a form with very long name (max 30)
		 */
		Feedback feedback = new Feedback();
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Feedback> list = null;
		HttpHeaders headers = new HttpHeaders();
		String name = "veryveryveryveryveryLONGlongNamebrrr"; // limit is 30
		String email = "rest.template@spring.org";
		String comment = "I am the REST tester, call me RestTemplate!";
		Integer spam = 1;

		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setComment(comment);
		feedback.setSpam(spam);

		String JSONInput = JSONObject.fromObject(feedback).toString();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<String> request = new HttpEntity<String>(JSONInput, headers);

		try {
			restTemplate.put("http://goodapp-shahapps.rhcloud.com/feedbacks",
					request);
			list = restTemplate.getForObject(
					"http://goodapp-shahapps.rhcloud.com/feedbacks",
					ArrayList.class);

			assertEquals(0, list.size());

		} catch (Exception e) {
			System.out.println("\n***error:  " + e.getMessage());
		}
	}

	@Test
	public void invalidName() {
		/*
		 * Sending a form with invalid name (only letters and spaces allowed)
		 */
		Feedback feedback = new Feedback();
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Feedback> list = null;
		HttpHeaders headers = new HttpHeaders();
		String name = "Some Bad name invalid symbol $"; // limit is 30
		String email = "rest.template@spring.org";
		String comment = "I am the REST tester, call me RestTemplate!";
		Integer spam = 1;

		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setComment(comment);
		feedback.setSpam(spam);

		String JSONInput = JSONObject.fromObject(feedback).toString();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<String> request = new HttpEntity<String>(JSONInput, headers);

		try {
			restTemplate.put("http://goodapp-shahapps.rhcloud.com/feedbacks",
					request);
			list = restTemplate.getForObject(
					"http://goodapp-shahapps.rhcloud.com/feedbacks",
					ArrayList.class);

			assertEquals(0, list.size());

		} catch (Exception e) {
			System.out.println("\n***error:  " + e.getMessage());
		}
	}

	@Test
	public void emptyEmail() {
		/*
		 * Testing empty email field
		 */
		Feedback feedback = new Feedback();
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Feedback> list = null;
		HttpHeaders headers = new HttpHeaders();
		String name = "Rest Template"; // limit is 30
		String email = ""; // empty email
		String comment = "I am the REST tester, call me RestTemplate!";
		Integer spam = 1;

		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setComment(comment);
		feedback.setSpam(spam);

		String JSONInput = JSONObject.fromObject(feedback).toString();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<String> request = new HttpEntity<String>(JSONInput, headers);

		try {
			restTemplate.put("http://goodapp-shahapps.rhcloud.com/feedbacks",
					request);
			list = restTemplate.getForObject(
					"http://goodapp-shahapps.rhcloud.com/feedbacks",
					ArrayList.class);

			assertEquals(0, list.size());

		} catch (Exception e) {
			System.out.println("\n***error:  " + e.getMessage());
		}
	}

	@Test
	public void invalidEmail() {
		/*
		 * Testing invalid email field
		 */
		Feedback feedback = new Feedback();
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Feedback> list = null;
		HttpHeaders headers = new HttpHeaders();
		String name = "Rest Template"; // limit is 30
		String email = "rest.templatespring.org"; // missing @
		String comment = "I am the REST tester, call me RestTemplate!";
		Integer spam = 1;

		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setComment(comment);
		feedback.setSpam(spam);

		String JSONInput = JSONObject.fromObject(feedback).toString();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<String> request = new HttpEntity<String>(JSONInput, headers);

		try {
			restTemplate.put("http://goodapp-shahapps.rhcloud.com/feedbacks",
					request);
			list = restTemplate.getForObject(
					"http://goodapp-shahapps.rhcloud.com/feedbacks",
					ArrayList.class);

			assertEquals(0, list.size());

		} catch (Exception e) {
			System.out.println("\n***error:  " + e.getMessage());
		}
	}

	@Test
	public void veryLongEmail() {
		/*
		 * Testing too long name for email (max 50)
		 */
		Feedback feedback = new Feedback();
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Feedback> list = null;
		HttpHeaders headers = new HttpHeaders();
		String name = "Rest Template"; // limit is 30
		String email = "rest.templaterest.templaterest.templateresttemplaterest";
		email += "rest.templaterest.templaterest.templaterest.templaterest@spring.org";// limit
																						// 50
		String comment = "I am the REST tester, call me RestTemplate!";
		Integer spam = 1;

		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setComment(comment);
		feedback.setSpam(spam);

		String JSONInput = JSONObject.fromObject(feedback).toString();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<String> request = new HttpEntity<String>(JSONInput, headers);

		try {
			restTemplate.put("http://goodapp-shahapps.rhcloud.com/feedbacks",
					request);
			list = restTemplate.getForObject(
					"http://goodapp-shahapps.rhcloud.com/feedbacks",
					ArrayList.class);

			assertEquals(0, list.size());

		} catch (Exception e) {
			System.out.println("\n***error:  " + e.getMessage());
		}
	}

	@Test
	public void emptyComment() {
		/*
		 * Testing empty comment field
		 */
		Feedback feedback = new Feedback();
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Feedback> list = null;
		HttpHeaders headers = new HttpHeaders();
		String name = "Rest Template"; // limit is 30
		String email = "rest.templatespring.org"; // missing @
		String comment = "";
		Integer spam = 1;

		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setComment(comment);
		feedback.setSpam(spam);

		String JSONInput = JSONObject.fromObject(feedback).toString();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<String> request = new HttpEntity<String>(JSONInput, headers);

		try {
			restTemplate.put("http://goodapp-shahapps.rhcloud.com/feedbacks",
					request);
			list = restTemplate.getForObject(
					"http://goodapp-shahapps.rhcloud.com/feedbacks",
					ArrayList.class);

			assertEquals(0, list.size());

		} catch (Exception e) {
			System.out.println("\n***error:  " + e.getMessage());
		}
	}

	@Test 
	public void veryLongComment() {
		/*
		 * Testing  very long comment (max 250)
		 */
		Feedback feedback = new Feedback();
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Feedback> list = null;
		HttpHeaders headers = new HttpHeaders();
		String name = "Rest Template"; //limit is 30
		String email = "rest.templatespring.org"; //missing @
		String comment = "In publishing and graphic design, lorem ipsum is common placeholder text used"
						+ " to demonstrate the graphic elements of a document or visual presentation, "
						+ "such as web pages, typography, and graphical layout. It is a form of"
						+ "Even though using lorem ipsum often arouses curiosity due to its resemblance"
						+ " to classical Latin, it is not intended to have meaning. Where text is visible"
						+ " in a document, people tend to focus on the textual content rather than upon"
						+ " overall presentation, so publishers use lorem ipsum when displaying a typeface"
						+ " or design in order to direct the focus to presentation. also approximates a typical"
						+ " distribution of letters in English.";
		Integer spam = 1;
		
		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setComment(comment);
		feedback.setSpam(spam);


		String JSONInput = JSONObject.fromObject(feedback).toString();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<String> request = new HttpEntity<String>(JSONInput, headers);
		
		try {
			restTemplate.put("http://goodapp-shahapps.rhcloud.com/feedbacks", request);
			list = restTemplate.getForObject("http://goodapp-shahapps.rhcloud.com/feedbacks", ArrayList.class);

			assertEquals(0, list.size());
			
		}
		catch (Exception e) {
			System.out.println("\n***error:  " + e.getMessage());
		}
	}
}
