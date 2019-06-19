package com.xmcc;

import com.google.common.collect.Lists;
import com.xmcc.entity.Student;
import com.xmcc.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxPayApplicationTests {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void contextLoads() {
//        List<Student> students = studentRepository.findAll();
//        students.stream().forEach(System.out::println);
//        Student s=new Student();
//        s.setId(1111);
//        s.setAge(222);
//        s.setName("lll");
//        s.setSex("nv");
//        studentRepository.save(s);
//        List<Student> allByIdIn = studentRepository.findAllByIdIn(Lists.newArrayList(1, 2, 3));
//        allByIdIn.stream().forEach(System.out::println);
//        List<Student> listId = studentRepository.getStudentListId(Lists.newArrayList(1, 2, 3));
//        listId.stream().forEach(System.out::println);
        List<Student> lists = studentRepository.getStudentLists(Lists.newArrayList(4, 2, 3));
        lists.stream().forEach(System.out::println);
    }

}
