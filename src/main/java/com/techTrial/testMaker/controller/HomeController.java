package com.techTrial.testMaker.controller;


import com.techTrial.testMaker.domain.*;
import com.techTrial.testMaker.repository.ExamService;
import com.techTrial.testMaker.repository.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;

    @RequestMapping(value={"/","login"},method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) {
        Exam exam = examService.findByName("Basic Algebra");
        request.getSession().setAttribute("currentExam",exam);
        request.getSession().setAttribute("startTime",new Date());
        model.addAttribute("exam", exam);

        return "home/login";
    }

    @RequestMapping(value="/grade")
    public String grade(HttpServletRequest request, Model model, @RequestParam Map<String, String> params) {

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Exam exam = (Exam) request.getSession().getAttribute("currentExam");

        com.techTrial.testMaker.domain.User u = userService.findByUsername(user.getUsername());

        List<Choice> correctChoices = examService.findCorrectChoicesByExamId(exam.getId());
        double grade = 0.0;

        for (Choice choice : correctChoices){
            Question question = choice.getQuestion();
            String key = "answer["+question.getId()+"]";
            if (params.containsKey(key)) {
                grade+=(Long.parseLong(params.get(key)) == choice.getId() ? question.getCost() : 0.0);
            }
        }
        UserExamID pk = new UserExamID();
        pk.setUser(u);
        pk.setExam(exam);

        UserExam userExam = new UserExam();
        userExam.setGrade(grade);
        userExam.setPk(pk);

        examService.saveGrade(userExam);

        model.addAttribute("grade", grade);

        return "home/grade";
    }

    @RequestMapping(value="exam")
    public String exam(HttpServletRequest request, Model model) {
        model.addAttribute("exam",request.getSession().getAttribute("currentExam"));
        return "home/exam";
    }

    @RequestMapping(value = "/checkTime", method = RequestMethod.GET)
    public @ResponseBody
    String checkTime(HttpServletRequest request) {
        Exam exam = (Exam) request.getSession().getAttribute("currentExam");
        Date startTime = (Date)request.getSession().getAttribute("startTime");
        Date now = new Date();
        Long timeInMilli = exam.getDurationInMinute()*60*1000L;
        logger.info("elapsedTime:" + (now.getTime() - startTime.getTime()));
        return ((now.getTime() - startTime.getTime()) >= timeInMilli ? "true" : "false");


    }

    @RequestMapping(value = "/startTime", method = RequestMethod.GET)
    public @ResponseBody
    String setStarTime(HttpServletRequest request) {

        request.getSession().setAttribute("startTime", new Date());

        return "success";


    }
}
