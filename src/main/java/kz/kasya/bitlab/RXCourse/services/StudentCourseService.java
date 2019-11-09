package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.StudentCourse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentCourseService {
    StudentCourse save(StudentCourse studentCourse) throws ServiceException;
    List<StudentCourse> findByUserId(Long id) throws ServiceException;
}
