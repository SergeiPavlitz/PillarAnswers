package com.pavlitz.pillar_answers.service;

import com.pavlitz.pillar_answers.entities.Answer;
import com.pavlitz.pillar_answers.repos.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService{

    private AnswerRepository repository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Answer> findAll() {
        Iterable<Answer> iterable = repository.findAll();
        List<Answer> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    @Override
    public List<Answer> findByPillarType(String pillarType) {
        /// TODO: 14.11.2022 check it
        return repository.findByPillarType(pillarType);
    }

    @Override
    public List<Answer> findByPillarTimeInPeriod(String pillarType, Date start, Date end) {
        return repository.findByPillarTypeAndCreationDateBetween(pillarType, start, end);
    }

    @Override
    public Answer findById(Long id) {
        Optional<Answer> opt = repository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }else{
//            throw new NoAnswerWIthIdException(id);
            return new Answer();
        }
    }

    @Override
    public Answer create(Answer answer) {
        return repository.save(answer);
    }

    @Override
    public void update(Answer answer) {
        repository.save(answer);
    }

    @Override
    public void delete(Answer answer) {
        repository.delete(answer);
    }

    @Override
    public Long countAnswers() {
        return repository.count();
    }
}
