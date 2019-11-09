package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.StudentCourse;
import kz.kasya.bitlab.RXCourse.repositories.StudentCourseRepository;
import kz.kasya.bitlab.RXCourse.repositories.TestResultRepository;
import kz.kasya.bitlab.RXCourse.services.StudentCourseService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    public StudentCourseServiceImpl(StudentCourseRepository studentCourseRepository){
        this.studentCourseRepository = studentCourseRepository;
    }

    @Override
    public StudentCourse save(StudentCourse studentCourse) throws ServiceException {
        if(studentCourse.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("Student course already exists")
                    .build();
        }
        return  studentCourseRepository.save(studentCourse);    }

    @Override
    public List<StudentCourse> findByUserId(Long id) {
        return studentCourseRepository.findByUser_Id(id);
    }
}
