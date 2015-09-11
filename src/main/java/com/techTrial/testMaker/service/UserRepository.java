package com.techTrial.testMaker.service;

import com.techTrial.testMaker.domain.Exam;
import com.techTrial.testMaker.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    SessionFactory sessionFactory;

    public User findByUsername(String username) {
        return (User)sessionFactory.getCurrentSession()
                .createQuery("From User u where u.username = :username")
                .setParameter("username",username)
                .uniqueResult();
    }

    public Long createUser(User user) {
        return (Long) sessionFactory.getCurrentSession().save(user);
    }

}
