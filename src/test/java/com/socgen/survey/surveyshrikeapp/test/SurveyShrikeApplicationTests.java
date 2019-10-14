package com.socgen.survey.surveyshrikeapp.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyShrikeApplicationTests {

	@LocalServerPort
	int randomServerPort;

	@Test
	public void testGetAllSurveys() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort
				+ "/survey/getAllSurveys";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());

	}
	

	@Test
	public void testGetSurveyById() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort
				+ "/survey/getAllSurveys";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());

	}
	
	
	
	@Test
	public void testActivateSurvey() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	    final String baseUrl = "http://localhost:"+randomServerPort+"/survey/activateSurvey?surveyId=12";
	    URI uri = new URI(baseUrl);
	   Long id=(long) 1;
	     
	    HttpHeaders headers = new HttpHeaders();
	 
	    HttpEntity<Long> request = new HttpEntity<>(id, headers);
	     
	    try
	    {
	    	ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
	        Assert.assertEquals(200, result.getStatusCodeValue());
	    }
	    catch(HttpClientErrorException ex)
	    {
	        //Verify bad request and missing header
	        Assert.assertEquals(400, ex.getRawStatusCode());
	        Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
	    }

	}
	
	@Test
	public void testDeactivateSurvey() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	    final String baseUrl = "http://localhost:"+randomServerPort+"/survey/deactivateSurvey?surveyId=12";
	    URI uri = new URI(baseUrl);
	   Long id=(long) 1;
	     
	    HttpHeaders headers = new HttpHeaders();
	 
	    HttpEntity<Long> request = new HttpEntity<>(id, headers);
	     
	    try
	    {
	    	ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
	        Assert.assertEquals(200, result.getStatusCodeValue());
	    }
	    catch(HttpClientErrorException ex)
	    {
	        //Verify bad request and missing header
	        Assert.assertEquals(400, ex.getRawStatusCode());
	        Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
	    }

	}
	
	

	
	
	
	
	@Test
	public void testCreateSurvey() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort
				+ "/survey/getAllSurveys";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());

	}

}
