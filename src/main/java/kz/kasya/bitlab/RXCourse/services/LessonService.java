package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> findAll();
    Lesson findById(Long id) throws ServiceException;
    Lesson save(Lesson lesson) throws ServiceException;
    Lesson update(Lesson lesson) throws ServiceException;
    void delete(Lesson lesson) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
    List<Lesson> findByCourseId(Long id) throws ServiceException;


}
