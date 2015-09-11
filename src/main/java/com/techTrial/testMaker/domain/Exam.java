package com.techTrial.testMaker.domain;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.exam", cascade = CascadeType.ALL)
    private List<UserExam> userExamList = new ArrayList<UserExam>();

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="exam_id")
    @Sort(type = SortType.NATURAL)
    private SortedSet<Question> questionSet;

    @Column(nullable = false)
    private String name;

    @Column
    private int durationInMinute = 1;

    @Column
    private String description;

    public int getDurationInMinute() {
        return durationInMinute;
    }

    public void setDurationInMinute(int durationInMinute) {
        this.durationInMinute = durationInMinute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SortedSet<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(SortedSet<Question> questionSet) {
        this.questionSet = questionSet;
    }

    public Long getId() {
        return id;
    }

    public List<UserExam> getUserExamList() {
        return userExamList;
    }

    public void setUserExamList(List<UserExam> userExamList) {
        this.userExamList = userExamList;
    }

    public void addQuestion(Question question) {

        if(questionSet == null) {
            questionSet = new TreeSet<Question>();
        }

        questionSet.add(question);
    }

}
