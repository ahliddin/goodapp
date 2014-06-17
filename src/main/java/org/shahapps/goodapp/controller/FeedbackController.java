package org.shahapps.goodapp.controller;

import java.util.List;

import org.shahapps.goodapp.dao.FeedbackDao;
import org.shahapps.goodapp.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FeedbackController {

	@Autowired
    private FeedbackDao feedbackDao;

	@RequestMapping(value="/feedbackspage", method=RequestMethod.GET)
	public String returnFeedbacksPage () {
		return "feedbackspage";
	}

	@RequestMapping(value="/feedbacks", method=RequestMethod.GET, produces="application/json") 
    public @ResponseBody List<Feedback> listAllFeedbacks() {
    	List<Feedback> list = feedbackDao.findAllOrderedByName();
    	System.out.println (list);
        return list;
    }
	
	@RequestMapping(value="/feedbacks", method=RequestMethod.DELETE) 
    public @ResponseBody void deleteAllFeedbacks() {
		feedbackDao.deleteAllFeedbacks();
	
	}
    
}
