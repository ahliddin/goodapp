package org.shahapps.goodapp.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shahapps.goodapp.dao.FeedbackDao;
import org.shahapps.goodapp.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-context.xml",
"classpath:/META-INF/spring/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class FeedbackDaoTest {
    @Autowired
    private FeedbackDao feedbackDao;

    @Test
    public void testFindById() {
    	
        Feedback feedback = feedbackDao.findById(0l);

        Assert.assertEquals("John Travolta", feedback.getName());
        Assert.assertEquals("john.travolta@mailinator.com", feedback.getEmail());
        Assert.assertEquals("I dance like a jagger", feedback.getComment());
        Assert.assertEquals(new Integer(0), feedback.getSpam());
        return;
    }

    @Test
    public void testFindByEmail()
    {
        Feedback feedback = feedbackDao.findByEmail("john.travolta@mailinator.com");

        Assert.assertEquals("John Travolta", feedback.getName());
        Assert.assertEquals("john.travolta@mailinator.com", feedback.getEmail());
        Assert.assertEquals("I dance like a jagger", feedback.getComment());
        Assert.assertEquals(new Integer(0), feedback.getSpam());
        return;
    }

    @Test
    public void testRegister() {
    	
        Feedback feedback = new Feedback();
        feedback.setEmail("marylin.monroe@mailinator.com");
        feedback.setName("Marylin Monroe");
        feedback.setComment("I can dance too");
        feedback.setSpam(1);

        feedbackDao.register(feedback);
        Long id = feedback.getId();
        Assert.assertNotNull(id);
        Assert.assertEquals(2, feedbackDao.findAllOrderedByName().size());
        
        Feedback newFeedback = feedbackDao.findById(id);

        Assert.assertEquals("Marylin Monroe", newFeedback.getName());
        Assert.assertEquals("marylin.monroe@mailinator.com", newFeedback.getEmail());
        Assert.assertEquals("I can dance too", newFeedback.getComment());
        Assert.assertEquals(new Integer(1), newFeedback.getSpam());
        return;
    }

    @Test
    public void testFindAllOrderedByName()
    {
    	Feedback feedback = new Feedback();
        feedback.setEmail("marylin.monroe@mailinator.com");
        feedback.setName("Marylin Monroe");
        feedback.setComment("I can dance too");
        feedback.setSpam(1);

        feedbackDao.register(feedback);

        List<Feedback> feedbacks = feedbackDao.findAllOrderedByName();
        
        Assert.assertEquals(2, feedbacks.size());
        Feedback newFeedback = feedbacks.get(1);

        Assert.assertEquals("Marylin Monroe", newFeedback.getName());
        Assert.assertEquals("marylin.monroe@mailinator.com", newFeedback.getEmail());
        Assert.assertEquals("I can dance too", newFeedback.getComment());
        Assert.assertEquals(new Integer(1), newFeedback.getSpam());
        return;
    }
}
