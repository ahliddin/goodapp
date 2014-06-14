package org.shahapps.goodapp.controller;

import java.util.List;

import org.shahapps.goodapp.dao.FeedbackDao;
import org.shahapps.goodapp.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/members")
public class FeedbackController
{
    @Autowired
    private FeedbackDao feedbackDao;

    @RequestMapping(method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Feedback> listAllFeedbacks()
    {
        return feedbackDao.findAllOrderedByName();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Feedback lookupMemberById(@PathVariable("id") Long id)
    {
        return feedbackDao.findById(id);
    }
}
