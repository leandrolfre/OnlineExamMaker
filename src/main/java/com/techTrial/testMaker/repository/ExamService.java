package com.techTrial.testMaker.repository;

import com.techTrial.testMaker.domain.Choice;
import com.techTrial.testMaker.domain.Exam;
import com.techTrial.testMaker.domain.UserExam;
import com.techTrial.testMaker.domain.UserExamID;
import com.techTrial.testMaker.service.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamService {

    @Autowired
    private ExamRepository repository;

    public Exam findByName(String name) {
        return repository.findByName(name);
    }

    public Long createExam(Exam exam) {
        return repository.createExam(exam);
    }

    public List<Choice> findCorrectChoicesByExamId(Long id) {
        return repository.findCorrectChoicesByExamId(id);
    }

    public UserExamID saveGrade(UserExam userExam) {
        return repository.saveGrade(userExam);
    }
}
