package com.study.spring.entity.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Jeffrey
 * @since 2017/12/19 18:09
 */
@Entity
public class PersonExt {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private String evaluate;

    public PersonExt() {
    }

    public PersonExt(String description, String evaluate) {
        this.description = description;
        this.evaluate = evaluate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonExt{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", evaluate='").append(evaluate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
