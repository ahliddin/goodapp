package org.shahapps.goodapp.dao;

import java.util.List;

import org.shahapps.goodapp.domain.Member;

public interface MemberDao
{
    public Member findById(Long id);

    public Member findByEmail(String email);

    public List<Member> findAllOrderedByName();

    public void register(Member member);
}
