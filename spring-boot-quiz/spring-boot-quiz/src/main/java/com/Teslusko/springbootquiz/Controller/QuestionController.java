package com.Teslusko.springbootquiz.Controller;

import com.Teslusko.springbootquiz.model.Question;
import com.Teslusko.springbootquiz.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired(required = false)
    QuestionService questionService;
    @GetMapping("allquestions")
    public ResponseEntity<List<Question> >getAllQuestions(){

        return questionService.getAllQuestions() ;
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){

            return questionService.getQuestionsByCategory(category);
    }
@PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
    @PutMapping("update/{id}")
    public String Updatequestion(@PathVariable int id,@RequestBody Question question){
         questionService.Updatequestion(id,question);
         return "This "+id+" was updated with question "+question.getQuestionTitle();
    }
@DeleteMapping("delete/{id}")
public String DeleteQuestion(@PathVariable int id){
        return  questionService.DeleteQuestion(id);
}








}
