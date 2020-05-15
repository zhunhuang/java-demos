package com.example.nolan.springtransaction2.service;

import com.example.nolan.springtransaction2.dao.student.StudentRepository;
import com.example.nolan.springtransaction2.model.student.StudentDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 *
 * @author zhunhuang, 2020/4/18
 */
@Service
public class StudentService {

    @Resource
    private StudentRepository studentRepository;

    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public void saveStudents(List<StudentDO> userDOS) {
        for (StudentDO userDO : userDOS) {
            if (userDO == null || StringUtils.isEmpty(userDO.getName())
                    || StringUtils.isEmpty(userDO.getAge())) {
                throw new IllegalArgumentException("参数错误");
            }
            studentRepository.save(userDO);
            System.out.println("落地数据库：" + userDO.getName());
        }
    }

    public StudentDO getByName(String name) {
        return studentRepository.findByName(name);
    }

    public void saveUsersSelfCall(List<StudentDO> userDOS) {
        saveStudents(userDOS);
    }
}
