package com.Teslusko.springbootquiz.Service;


import com.Teslusko.springbootquiz.dao.QuestionDao;
import com.Teslusko.springbootquiz.dao.QuizeDao;
import com.Teslusko.springbootquiz.model.Question;
import com.Teslusko.springbootquiz.model.QuestionWrapper;
import com.Teslusko.springbootquiz.model.Quize;
import com.Teslusko.springbootquiz.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizeService {
    @Autowired
    QuizeDao quizeDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions= questionDao.findRandomQuestionByCategory(category,numQ);
        Quize quize= new Quize();
        quize.setTitle(title);
        quize.setQuestion(questions);
        quizeDao.save(quize);
        return new ResponseEntity<>("Success",HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quize> quize= quizeDao.findById(id);
        List<Question> questionsFromDB =quize.get().getQuestion();
        List<QuestionWrapper> questionsForUser=new ArrayList<>();
        for(Question q: questionsFromDB){
            QuestionWrapper questionWrapper= new QuestionWrapper(id,q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
             questionsForUser.add(questionWrapper);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> GetScore(Integer id, List<Response> responses) {

       Optional<Quize> quize= quizeDao.findById(id);  //optionl to handle null values or
        // Quize quize = quizeDaa.findById(id).get() cn be used no problem if so next step should with  .getQuestions();
        List<Question> questions= quize.get().getQuestion();
        int i=0; /// to iterate questions;
        int score=0;
        for(Response response:responses ){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                 score++;
            i++;
        }

        return new  ResponseEntity<>(score,HttpStatus.OK);
    }
}
