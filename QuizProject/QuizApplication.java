package com.example.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@SpringBootApplication
@RestController
public class QuizApplication {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(QuizApplication.class, args);
    }

  
    @PostMapping("/start")
    public String startQuiz(@RequestParam String studentId) {

        String sql = "SELECT COUNT(*) FROM student WHERE student_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, studentId);

        if (count == null || count == 0) {
            return "Student ID not found!";
        }

        return generateQuiz(studentId);
    }


    private String generateQuiz(String studentId) {

        char lastDigit = studentId.charAt(studentId.length() - 1);
        String topic = "";

        List<String> questions = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        switch (lastDigit) {

            case '1':
                topic = "গণিত (Arithmetic)";
                questions.add("১) ৫ + ৭ = ?");
                questions.add("২) ১২ ÷ ৩ = ?");
                answers.add("১২");
                answers.add("৪");
                break;

            case '2':
                topic = "বাংলাদেশ বিষয়ক সাধারণ জ্ঞান";
                questions.add("১) বাংলাদেশের রাজধানী কী?");
                questions.add("২) বাংলাদেশের জাতীয় ফুল কী?");
                answers.add("ঢাকা");
                answers.add("শাপলা");
                break;

            case '4':
                topic = "পদার্থবিজ্ঞান";
                questions.add("১) বলের একক কী?");
                questions.add("২) আলোর গতি কত?");
                answers.add("নিউটন");
                answers.add("৩×১০^৮ মিটার/সেকেন্ড");
                break;

            default:
                topic = "সাধারণ জ্ঞান";
                questions.add("১) পৃথিবীর বৃহত্তম মহাসাগর কোনটি?");
                questions.add("২) জাতিসংঘ প্রতিষ্ঠিত হয় কত সালে?");
                answers.add("প্রশান্ত মহাসাগর");
                answers.add("১৯৪৫");
        }

        StringBuilder quiz = new StringBuilder();
        quiz.append("বিষয়: ").append(topic).append("\n\n");

        for (String q : questions) {
            quiz.append(q).append("\n");
            quiz.append("A) অপশন ১\n");
            quiz.append("B) অপশন ২\n");
            quiz.append("C) অপশন ৩\n");
            quiz.append("D) অপশন ৪\n\n");
        }

        quiz.append("Submit answer using /submit endpoint.");

        return quiz.toString();
    }


    @PostMapping("/submit")
    public String submitQuiz(@RequestParam String studentId,
                             @RequestParam String ans1,
                             @RequestParam String ans2) {

        char lastDigit = studentId.charAt(studentId.length() - 1);

        int score = 0;

        if (lastDigit == '1') {
            if (ans1.equals("১২")) score++;
            if (ans2.equals("৪")) score++;
        }

        if (lastDigit == '2') {
            if (ans1.equals("ঢাকা")) score++;
            if (ans2.equals("শাপলা")) score++;
        }

        if (lastDigit == '4') {
            if (ans1.equals("নিউটন")) score++;
            if (ans2.equals("৩×১০^৮ মিটার/সেকেন্ড")) score++;
        }

        if (lastDigit == '0') {
            if (ans1.equals("প্রশান্ত মহাসাগর")) score++;
            if (ans2.equals("১৯৪৫")) score++;
        }

        jdbcTemplate.update("INSERT INTO result(student_id, score) VALUES(?, ?)",
                studentId, score);

        return "Your Score: " + score + " out of 2";
    }
}
