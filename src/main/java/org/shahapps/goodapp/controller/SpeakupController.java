package org.shahapps.goodapp.controller;



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

import javax.validation.Valid;


@Controller
public class SpeakupController {
	
	@Autowired FeedbackDao feedbackDao;
	
	@RequestMapping (value="/", method=RequestMethod.GET ) 
	public String returnIndex() {
		return "index";
	}
	
    @RequestMapping(value="/feedbacks", method=RequestMethod.PUT, consumes = {"application/json"}) 
    public ResponseEntity<Feedback> addFeedbackPut (@RequestBody @Valid  Feedback newFeedback,
    								BindingResult bindingResult ) {
    	
    	HttpHeaders headers = addAccessControllAllowOrigin();
    	ResponseEntity<Feedback> entity = new ResponseEntity<Feedback>(headers, HttpStatus.OK);
    	feedbackDao.register(newFeedback);
    	return entity;
    	
    }
    
    @RequestMapping(value="/feedbacks", method=RequestMethod.POST, consumes = {"application/json"}) 
    public ResponseEntity<Feedback> addFeedbackPost (@RequestBody @Valid  Feedback newFeedback,
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
