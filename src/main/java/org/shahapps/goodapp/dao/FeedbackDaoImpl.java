package org.shahapps.goodapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.shahapps.goodapp.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class FeedbackDaoImpl implements FeedbackDao {
    
	@Autowired
    private EntityManager em;

    public Feedback findById(Long id) {
    	
        return em.find(Feedback.class, id);
    }

    public Feedback findByEmail(String email)  {
    	
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Feedback> criteria = builder.createQuery(Feedback.class);
        Root<Feedback> feedback = criteria.from(Feedback.class);

        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
         */

        criteria.select(feedback).where(builder.equal(feedback.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Feedback> findAllOrderedByName() {
    	
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Feedback> criteria = cb.createQuery(Feedback.class);
        Root<Feedback> feedback = criteria.from(Feedback.class);
        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
         */

        criteria.select(feedback).orderBy(cb.asc(feedback.get("name")));
        return em.createQuery(criteria).getResultList();
    }

    public void register(Feedback feedback) {
        em.persist(feedback);
    }
    
    public void deleteAllFeedbacks () {
    	em.createQuery("delete from Feedback").executeUpdate();

    }
}
