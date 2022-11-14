package com.pavlitz.pillar_answers.controller;

import com.pavlitz.pillar_answers.entities.Answer;
import com.pavlitz.pillar_answers.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/answer")
public class AnswerController {

    private final AnswerService service;

    @Autowired
    public AnswerController(AnswerService service) {
        this.service = service;
    }

    @GetMapping(value = "/listData")
    public List<Answer> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/", params = {"pillarType"})
    public List<Answer> findByPillarType(@RequestParam("pillarType") String pillarType) {
        return service.findByPillarType(pillarType);
    }

    @GetMapping(value = "/{id}")
    public Answer findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/count")
    public Long count(){
        return service.countAnswers();
    }

    @PostMapping(value = "/")
    public Answer create(@RequestBody Answer answer) {
        return service.create(answer);
    }

    @PutMapping(value = "/")
    public void update(@RequestBody Answer answer) {
        service.update(answer);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        Answer a = service.findById(id);
        service.delete(a);
    }


}
