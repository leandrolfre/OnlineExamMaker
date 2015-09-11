package com.techTrial.testMaker.repository;

import com.techTrial.testMaker.domain.Exam;
import com.techTrial.testMaker.domain.User;
import com.techTrial.testMaker.service.ExamRepository;
import com.techTrial.testMaker.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private StandardPasswordEncoder encoder;

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Long createUser(User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        return repository.createUser(user);
    }
}
