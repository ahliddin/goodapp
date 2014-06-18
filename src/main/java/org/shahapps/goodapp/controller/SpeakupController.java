package org.shahapps.goodapp.controller;


import org.shahapps.goodapp.dao.FeedbackDao;
import org.shahapps.goodapp.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
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
	
    @RequestMapping(value="/feedbacks", method=RequestMethod.POST) //, consumes="application/json"
    public Integer addFeedback (@RequestBody @Valid 
    										Feedback newFeedBack,
    										BindingResult bindingResult) {
    	
    	if (bindingResult.hasErrors()) {
    		System.out.println ("\nWrong input!!!\n");
    		return 1;
    	}
    	feedbackDao.register(newFeedBack);
    	return 0;
    }
    
    @RequestMapping(value="/feedbacks2", method=RequestMethod.POST) //, consumes="application/json"
    public int addFeedback2 (@RequestBody @Valid 
    										Feedback newFeedBack,
    										BindingResult bindingResult) {
    	
    	if (bindingResult.hasErrors()) {
    		System.out.println ("\nWrong input!!!\n");
    		return 1;
    	}
    	feedbackDao.register(newFeedBack);
    	return 0;
    }
    
    @RequestMapping(value="/feedbacks3", method=RequestMethod.POST) //, consumes="application/json"
    public void addFeedback3 (@RequestBody @Valid 
    										Feedback newFeedBack,
    										BindingResult bindingResult) {
    	
    	if (bindingResult.hasErrors()) {
    		System.out.println ("\nWrong input!!!\n");
    	}
    	feedbackDao.register(newFeedBack);
    }
}	
