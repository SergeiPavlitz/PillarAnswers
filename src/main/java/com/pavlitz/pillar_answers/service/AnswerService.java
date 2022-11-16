package com.pavlitz.pillar_answers.service;

import com.pavlitz.pillar_answers.entities.Answer;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;


public interface AnswerService {
    List<Answer> findAll();

    List<Answer> findByPillarType(String pillarType);

    List<Answer> findByPillarTimeInPeriod(String pillarType, Date start, Date end);

    Answer findById(Long id);

    Answer create(Answer answer);

    void update(Answer answer);

    void delete(Answer answer);

    Long countAnswers();

}
