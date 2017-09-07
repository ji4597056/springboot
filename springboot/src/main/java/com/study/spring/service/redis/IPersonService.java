package com.study.spring.service.redis;

import com.study.spring.entity.Student;
import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/01/11 16:09
 */
public interface IPersonService {

    List<Student> findPersonsNoCache();

    List<Student> findPersonsWithCache();

    void addPersons(List<Student> students);

    void deletePersonsById(List<Integer> ids);
}
