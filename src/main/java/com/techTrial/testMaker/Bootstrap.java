package com.techTrial.testMaker;

import com.techTrial.testMaker.domain.Choice;
import com.techTrial.testMaker.domain.Exam;
import com.techTrial.testMaker.domain.Question;
import com.techTrial.testMaker.domain.User;
import com.techTrial.testMaker.repository.ExamService;
import com.techTrial.testMaker.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createUser();
        createExam();
    }

    private void createExam() {
        Exam exam = new Exam();
        exam.setName("Basic Algebra");
        String labels[] = {"a","b","c","d"};
        for(int i=0; i < 4; i++) {
            Question question = new Question();
            question.setDescription("0+"+(i+1)+"?");
            question.setLabel(labels[i]);

            for(int j=0; j < 4; j++) {
                Choice choice = new Choice();
                if((j+1)%4==0) {
                    choice.setDescription(new Integer(i+1).toString());
                    choice.setCorrectAnswer(true);
                } else {
                    choice.setDescription(new Integer((j+1)*5).toString());
                    choice.setCorrectAnswer(false);
                }

                question.addChoice(choice);

            }
            exam.addQuestion(question);
        }

        examService.createExam(exam);
    }

    private void createUser() {
        User user = new User();
        user.setUsername("crossover");
        user.setPassword("crossover");

        userService.createUser(user);
    }

}
