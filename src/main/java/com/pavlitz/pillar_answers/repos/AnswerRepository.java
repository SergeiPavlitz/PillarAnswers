package com.pavlitz.pillar_answers.repos;

import com.pavlitz.pillar_answers.entities.Answer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    List<Answer> findByPillarType(String type);
}
