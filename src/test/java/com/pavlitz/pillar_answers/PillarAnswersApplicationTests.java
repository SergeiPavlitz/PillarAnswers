package com.pavlitz.pillar_answers;

import com.pavlitz.pillar_answers.entities.Answer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PillarAnswersApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(PillarAnswersApplicationTests.class);

    private final String URL = "http://localhost:8070/answer";

    private final RestTemplate restTemplate = new RestTemplate();

    public static Long idForTests = 0L;

    @Test
    @Order(1)
    void create() {
        logger.info("test creation");
        Date d = Date.valueOf(LocalDate.now());
        Answer a = new Answer(d, "i would learn spring", "awareness");
        String creationURL = URL + "/";
        Answer fromDb = restTemplate.postForObject(creationURL, a, Answer.class);
        assertNotNull(fromDb);
        assertNotNull(fromDb.getId());
        logger.info("Answer created successfully: " + fromDb);
        // TODO: 14.11.2022 isn't very good. Find better solution
        idForTests = fromDb.getId();
    }

    @Test
    @Order(2)
    void findById() {
        logger.info("test findById");
        Long id = idForTests;
        Answer answer = restTemplate.getForObject(URL + "/{id}", Answer.class, id);
        assertNotNull(answer);
        assertSame(answer.getId(), id);
        logger.info("--> Retrieving answer by id successfully finished : " + answer);
    }

    @Test
    @Order(3)
    void findByType() {
        logger.info("test find answer by pillarType");
        String pillarType = "awareness";
        Answer[] list = restTemplate.getForObject(URL + "/?pillarType=" + pillarType, Answer[].class);
        assertNotNull(list);
        assertTrue(list.length>0);
        assertEquals(list[0].getPillarType(), pillarType);
        logger.info("--> Retrieving answer by pillarType successfully finished : " + list[0]);
    }

    @Test
    @Order(4)
    void updateAnswer() {
        logger.info("test update answer");
        logger.info("Retrieving answer by id");

        Answer answer = restTemplate.getForObject(URL + "/{id}", Answer.class, idForTests);
        assertNotNull(answer);
        logger.info("answer has been retrieved");
        String newBody = "changed body";
        answer.setAnswerBody(newBody);
        restTemplate.put(URL + "/", answer);

        Answer updated = restTemplate.getForObject(URL + "/{id}", Answer.class, idForTests);
        assertNotNull(updated);
        assertEquals(newBody, updated.getAnswerBody());

        logger.info("--> Updating answer by id successfully finished : " + answer);
    }

    @Test
    @Order(5)
    void findAllAndCount(){
        logger.info("test find all");
        logger.info("get table size");
        Long size = restTemplate.getForObject(URL+"/count", Long.class);
        logger.info("got table size " +size);
        Answer[] list = restTemplate.getForObject(URL+"/listData",Answer[].class);
        assertNotNull(list);
        assertEquals(size,list.length);
    }

    @Test
    @Order(6)
    void delete() {
        logger.info("test delete");
        logger.info("create answer for deleting");
        Date d = Date.valueOf(LocalDate.now());
        Answer a = new Answer(d, "i would learn spring", "awareness");
        String creationURL = URL + "/";
        Answer fromDb = restTemplate.postForObject(creationURL, a, Answer.class);
        assertNotNull(fromDb);
        assertNotNull(fromDb.getId());
        logger.info("Answer for deleting created : " + fromDb);

        Long id = fromDb.getId();
        restTemplate.delete(URL + "/{id}", id);
        Answer answer = restTemplate.getForObject(URL + "/{id}", Answer.class, id);
        assertNotNull(answer);
        assertNull(answer.getId());
        logger.info("Answer deleting successfully finished");
    }


}
