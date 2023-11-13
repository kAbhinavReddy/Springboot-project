package com.Teslusko.springbootquiz.Controller;

import com.Teslusko.springbootquiz.Service.QuizeService;
import com.Teslusko.springbootquiz.model.QuestionWrapper;
import com.Teslusko.springbootquiz.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizeConroller {
    @Autowired(required = false)
    QuizeService quizeService;
    @PostMapping("create")
    public ResponseEntity<String> createQuize(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
            return quizeService.createQuiz(category,numQ,title);
     }
     @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizeService.getQuizQuestions(id);
     }
     @PostMapping("submit/{id}")
    public ResponseEntity<Integer>submitQuize(@PathVariable Integer id,@RequestBody List<Response> responses){

        return quizeService.GetScore(id,responses);

     }

}
