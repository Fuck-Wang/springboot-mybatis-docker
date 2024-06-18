package com.example.serviceImp;

import com.example.dao.GradeDao;
import com.example.dao.StudentDao;
import com.example.dao.SubjectAndStudentDao;
import com.example.dao.SubjectDao;
import com.example.entity.Grade;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.entity.SubjectAndStudent;
import com.example.entity.domain.Page;
import com.example.entity.exception.BadRequestException;
import com.example.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Resource
    private GradeDao gradeDao;

    @Resource
    private SubjectDao subjectDao;

    @Resource
    private  SubjectAndStudentDao subjectAndStudentDao;

    @Override
    @Transactional
    public List<Grade> saveAll(List<Grade> grades) {
        grades.forEach(grade -> {
            Long gradeId = grade.getId();
            if (null == gradeId || gradeDao.findOne(gradeId) == null) {
                throw new BadRequestException(400, "studentServiceImp", "请选择班级！");
            }
            List<Student> students = grade.getStudents();
            students.forEach(student -> {
                student.setGrade(new Grade().setId(gradeId));
                studentDao.save(student);
                List<SubjectAndStudent> subjectAndStudents = student.getSubjectAndStudents();
                subjectAndStudents.forEach(subjectAndStudent -> {
                    Optional.ofNullable(subjectAndStudent.getSubject())
                            .ifPresent(subject -> {
                                Long subjectId = subject.getId();
                                if (subjectId != null && subjectDao.findOne(subjectId) != null) {
                                    subjectAndStudent.setStudent(new Student().setId(student.getId()));

                                }
                            });
                });
                subjectAndStudentDao.saveAll(subjectAndStudents);

            });
        });
        return grades;
    }

    @Override
    @Transactional
    public Page<Student> findByPageIndex(Integer startIndex, Integer offset) {
        List<Student> students = studentDao.findByPageIndex((startIndex - 1) * offset, offset);
        int count = studentDao.count();
        int totalPages = count / offset + 1;
        Page<Student> pageInfo = new Page<>();
        pageInfo.setPageNum(startIndex)
                .setPageSize(offset)
                .setContent(students)
                .setTotalElements(count)
                .setTotalPages(totalPages);
        return pageInfo;
    }

}
