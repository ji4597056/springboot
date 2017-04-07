package com.study.spring.dao.redis;

import com.study.spring.entity.Person;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jeffrey
 * @since 2017/01/11 16:41
 */
@Mapper
public interface IPersonMapper {

    List<Person> findPersons();

    // 使用"collection"、"list"、"array"作为参数,否则不能生成自增主键
    void addPersons(@Param("list") List<Person> list);

    void deletePersonByIds(@Param("ids") List<Integer> ids);
}
