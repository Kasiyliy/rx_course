package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Test;

import java.util.List;

public interface TestService {
    List<Test> findAll();
    Test findById(Long id) throws ServiceException;
    Test save(Test lesson) throws ServiceException;
    Test update(Test lesson) throws ServiceException;
    void delete(Test lesson) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
}
