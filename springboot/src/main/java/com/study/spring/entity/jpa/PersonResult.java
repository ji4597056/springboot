package com.study.spring.entity.jpa;

/**
 * @author Jeffrey
 * @since 2017/12/19 18:11
 */
public class PersonResult {

    private Long id;

    private String firstName;

    private String lastName;

    private String description;

    private String evaluate;

    public PersonResult() {
    }

    public PersonResult(Long id, String firstName, String lastName, String description,
        String evaluate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.evaluate = evaluate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        final StringBuilder sb = new StringBuilder("PersonResult{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", evaluate='").append(evaluate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
