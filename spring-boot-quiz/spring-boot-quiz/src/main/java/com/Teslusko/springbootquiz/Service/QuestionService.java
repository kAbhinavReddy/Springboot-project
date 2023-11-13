package com.Teslusko.springbootquiz.Service;

import com.Teslusko.springbootquiz.model.Question;
import com.Teslusko.springbootquiz.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
   @Autowired(required = true)
    QuestionDao questionDao;

      public ResponseEntity<List<Question>> getAllQuestions() {
          try{
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    }
     catch(Exception e){
                    e.printStackTrace();
      }
      return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
      }
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<String> addQuestion( Question question) {
           questionDao.save(question);
           return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public String DeleteQuestion(int id) {
          questionDao.deleteById(id);
          return "deleted "+id+" successfully";
    }

    public String Updatequestion(int id, Question question) {
       questionDao.deleteById(id);
        questionDao.save(question); // |
        // if this was not passed(question object) then you need to write query in questionDao using @Query(value="...", nativeQuery=true)
        return "done successfully";



    }
}
