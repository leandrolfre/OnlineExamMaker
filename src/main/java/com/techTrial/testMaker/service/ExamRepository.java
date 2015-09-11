package com.techTrial.testMaker.service;

import com.techTrial.testMaker.domain.Choice;
import com.techTrial.testMaker.domain.Exam;
import com.techTrial.testMaker.domain.UserExam;
import com.techTrial.testMaker.domain.UserExamID;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Choice> findCorrectChoicesByExamId(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("select c from Exam e join e.questionSet q join q.choiceSet c where c.correctAnswer = true and e.id = :id")
                .setParameter("id",id)
                .list();

    }

    public Long createExam(Exam exam) {
        return (Long) sessionFactory.getCurrentSession().save(exam);
    }

    public UserExamID saveGrade(UserExam userExam) {
        return (UserExamID) sessionFactory.getCurrentSession().save(userExam);
    }

}
