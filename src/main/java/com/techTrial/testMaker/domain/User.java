package com.techTrial.testMaker.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade = CascadeType.ALL)
    private List<UserExam> userExamList = new ArrayList<UserExam>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserExam> getUserExamList() {
        return userExamList;
    }

    public void setUserExamList(List<UserExam> userExamList) {
        this.userExamList = userExamList;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof User) {
            return this.id.equals(((User)that).getId());
        }
        return false;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

}
