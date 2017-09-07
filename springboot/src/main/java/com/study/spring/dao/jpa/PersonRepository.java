package com.study.spring.dao.jpa;

import com.study.spring.entity.jpa.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jeffrey
 * @since 2017/07/21 16:10
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUserName(String userName);

    List<Person> findByUserGroup(String userGroup);

}
