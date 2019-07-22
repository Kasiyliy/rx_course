package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Homework;

import java.util.List;

public interface HomeworkService {
    List<Homework> findAll();
    Homework findById(Long id) throws ServiceException;
    Homework save(Homework homework) throws ServiceException;
    Homework update(Homework homework) throws ServiceException;
    void delete(Homework homework) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
}
