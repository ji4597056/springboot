package com.study.spring.entity.jpa;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Jeffrey
 * @since 2017/07/21 16:05
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String userGroup;

    private String firstName;

    private String lastName;

    private Date birthDate;

    public Person() {
    }

    public Person(String userName, String userGroup, String firstName, String lastName,
        Date birthDate) {
        this.userName = userName;
        this.userGroup = userGroup;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", userGroup='").append(userGroup).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append('}');
        return sb.toString();
    }
}
