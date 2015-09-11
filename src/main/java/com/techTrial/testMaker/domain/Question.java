package com.techTrial.testMaker.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="question")
public class Question implements Comparable<Question> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String description;

    @Column
    private double cost;

    @Column
    private String label;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="question_id")
    private Set<Choice> choiceSet;

    @ManyToOne(cascade=CascadeType.ALL)
    private Exam exam;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void addChoice(Choice choice) {

        if(choiceSet == null) {
            choiceSet = new HashSet<Choice>();
        }

        choiceSet.add(choice);
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Set<Choice> getChoiceSet() {
        return choiceSet;
    }

    public void setChoiceSet(Set<Choice> choiceSet) {
        this.choiceSet = choiceSet;
    }

    public Long getId() {
        return id;
    }

    public int compareTo(Question o) {
        return this.label.compareTo(o.getLabel());
    }
}
