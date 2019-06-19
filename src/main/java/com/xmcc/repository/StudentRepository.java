package com.xmcc.repository;


import com.xmcc.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-19 17:35
 */
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findAllByIdIn(List<Integer> ids);


    @Query(value = "select * from student where id in ?1",nativeQuery = true)
    List<Student> getStudentListId(List<Integer> ids);

    @Query(value = "select s from Student s where id in ?1")
    List<Student> getStudentLists(List<Integer> ids);
}
