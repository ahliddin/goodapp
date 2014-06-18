package org.shahapps.goodapp.controller;


import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.shahapps.goodapp.dao.FeedbackDao;
import org.shahapps.goodapp.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class SpeakupController {
	
	@Autowired FeedbackDao feedbackDao;
	
	@RequestMapping (value="/", method=RequestMethod.GET ) 
	public String returnIndex() {
		return "index";
	}
	
    @RequestMapping(value="/feedbacks3", method=RequestMethod.POST, consumes = {"application/json"}) 
    public Integer addFeedback3 (@RequestBody @Valid  Feedback newFeedback,
    								BindingResult bindingResult ) {
    	
    	if (bindingResult.hasErrors()) {
    		System.out.println ("\nWrong input!!!\n");
    		return 1;
    	}
    	feedbackDao.register(newFeedback);
    	return 0;
    }

    @RequestMapping(value="/feedbacks2", method=RequestMethod.POST, consumes={"application/json"}) 
    public String addFeedback2 (@RequestBody @Valid Feedback newFeedback,
    							BindingResult bindingResult ) {
    	
    	if (bindingResult.hasErrors()) {
    		System.out.println ("\nWrong input!!!\n");
    		return "bad";
    	}
    	feedbackDao.register(newFeedback);
    	return "good";
    }
    
    @RequestMapping(value="/feedbacks", method=RequestMethod.POST, consumes = {"application/json"}) 
    public ResponseEntity<Feedback> addFeedback (@RequestBody @Valid  Feedback newFeedback,
    								BindingResult bindingResult ) {
    	
    	HttpHeaders headers = addAccessControllAllowOrigin();
    	ResponseEntity<Feedback> entity = new ResponseEntity<Feedback>(headers, HttpStatus.OK);
    	feedbackDao.register(newFeedback);
    	return entity;
    	
    }
    
    private HttpHeaders addAccessControllAllowOrigin() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return headers;
    }
    
}	
