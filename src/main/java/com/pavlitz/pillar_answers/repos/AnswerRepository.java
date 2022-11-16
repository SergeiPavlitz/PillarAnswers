package com.pavlitz.pillar_answers.repos;

import com.pavlitz.pillar_answers.entities.Answer;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    List<Answer> findByPillarType(String type);
    List<Answer> findByPillarTypeAndCreationDateBetween(String type, Date start, Date end);
}
