package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Course;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();
    Course findById(Long id) throws ServiceException;
    Course update(Course course) throws ServiceException;
    Course save(Course course) throws ServiceException;
    void delete(Course course) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
    Page<Course> findAllPageable(Optional<Integer> page,
                                 Optional<Integer> size,
                                 Optional<String[]> sortBy);
    Page<Course> findAllPageableByUserId(Optional<Integer> page,
                                 Optional<Integer> size,
                                 Optional<String[]> sortBy,
                                         Long userId);

}
