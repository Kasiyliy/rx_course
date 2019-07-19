package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Course findById(Long id) throws ServiceException;
    Course update(Course course) throws ServiceException ;
    Course save(Course course) throws ServiceException;


}
