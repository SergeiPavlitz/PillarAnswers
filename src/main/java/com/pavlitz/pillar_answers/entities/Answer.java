package com.pavlitz.pillar_answers.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "pillaranswers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "creationdate")
    private Date creationDate;
    @Column(name = "answerbody")
    private String answerBody;
    @Column(name = "pillartype")
    private String pillarType;

    public Answer() {
    }

    public Answer(Date creationDate, String answerBody, String pillarType) {
        this.creationDate = creationDate;
        this.answerBody = answerBody;
        this.pillarType = pillarType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
    }

    public String getPillarType() {
        return pillarType;
    }

    public void setPillarType(String pillarType) {
        this.pillarType = pillarType;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", answerBody='" + answerBody + '\'' +
                ", pillarType='" + pillarType + '\'' +
                '}';
    }
}
