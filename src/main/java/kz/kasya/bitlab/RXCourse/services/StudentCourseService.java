package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.StudentCourse;
import kz.kasya.bitlab.RXCourse.models.entities.Test;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentCourseService {
    StudentCourse save(StudentCourse studentCourse) throws ServiceException;
    List<StudentCourse> findByUserId(Long id) throws ServiceException;
    Page<StudentCourse> findAllPageableByUserId(Optional<Integer> page,
                                         Optional<Integer> size,
                                         Optional<String[]> sortBy,
                                         Long userId);
    List<Test> getTests(Long userId) throws ServiceException;
}
