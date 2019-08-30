package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Test;

import java.util.List;

public interface TestService {
    List<Test> findAll();
    List<Test> findAllByCourseId(Long courseId);
    Test findById(Long id) throws ServiceException;
    Test save(Test test) throws ServiceException;
    Test update(Test test) throws ServiceException;
    void delete(Test test) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
}
