package com.techTrial.testMaker.domain;

import javax.persistence.*;

@Entity
@Table(name="user_exam")
@AssociationOverrides({
        @AssociationOverride(name="pk.exam", joinColumns = @JoinColumn(name="exam_id")),
        @AssociationOverride(name="pk.user", joinColumns = @JoinColumn(name="user_id"))
})
public class UserExam {

    private UserExamID pk = new UserExamID();

    @Column(name="grade")
    private double grade;

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @EmbeddedId
    public UserExamID getPk() {
        return pk;
    }

    public void setPk(UserExamID pk) {
        this.pk = pk;
    }

}
