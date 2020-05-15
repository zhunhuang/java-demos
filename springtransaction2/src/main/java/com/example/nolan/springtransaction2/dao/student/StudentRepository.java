package com.example.nolan.springtransaction2.dao.student;

import com.example.nolan.springtransaction2.model.student.StudentDO;
import org.springframework.data.repository.CrudRepository;

/**
 * description:
 *
 * @author zhunhuang, 2020/4/18
 */
public interface StudentRepository extends CrudRepository<StudentDO, Long> {

    StudentDO findByName(String name);
}
