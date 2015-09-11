package com.techTrial.testMaker.controller;


import com.techTrial.testMaker.domain.Choice;
import com.techTrial.testMaker.domain.Exam;
import com.techTrial.testMaker.domain.Question;
import com.techTrial.testMaker.domain.User;
import com.techTrial.testMaker.repository.ExamService;
import com.techTrial.testMaker.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ExamService examService;

    @RequestMapping(value={"/","login"},method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) {
        Exam exam = examService.findByName("Basic Algebra");
        request.getSession().setAttribute("currentExam",exam);
        model.addAttribute("exam", exam);

        return "home/login";
    }

    @RequestMapping(value="grade")
    public String grade() {

        return "home/grade";
    }

    @RequestMapping(value="exam")
    public String exam(HttpServletRequest request, Model model) {
        model.addAttribute("exam",request.getSession().getAttribute("currentExam"));
        return "home/exam";
    }
}
