package com.techTrial.testMaker.service;

import com.techTrial.testMaker.domain.Exam;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ExamRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Exam findByName(String name) {
        return (Exam)sessionFactory.getCurrentSession()
                .createQuery("From Exam e where e.name = :name")
                .setParameter("name",name)
                .uniqueResult();
    }

    public Long createExam(Exam exam) {
        return (Long) sessionFactory.getCurrentSession().save(exam);
    }

}
