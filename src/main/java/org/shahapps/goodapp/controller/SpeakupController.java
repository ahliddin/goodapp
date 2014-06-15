package org.shahapps.goodapp.controller;


import org.shahapps.goodapp.dao.FeedbackDao;
import org.shahapps.goodapp.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody void addFeedback (@RequestBody @Valid 
    										Feedback newFeedBack,
    										BindingResult bindingResult) {
    	
    	if (bindingResult.hasErrors()) {
    		System.out.println ("\nWrong input!!!\n");
    		//System.out.println (bindingResult.getErrors())
    	}
    	System.out.println (newFeedBack);
    	feedbackDao.register(newFeedBack);
    }
}	
