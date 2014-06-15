package org.shahapps.goodapp.dao;

import java.util.List;

import org.shahapps.goodapp.domain.Feedback;

public interface FeedbackDao
{
    public Feedback findById(Long id);

    public Feedback findByEmail(String email);

    public List<Feedback> findAllOrderedByName();

    public void register(Feedback feedback);
}
