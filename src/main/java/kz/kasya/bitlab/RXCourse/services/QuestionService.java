package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findAll();
    Question findById(Long id) throws ServiceException;
    Question save(Question question) throws ServiceException;
    Question update(Question question) throws ServiceException;
    void delete(Question question) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
    List<Question> findAllByTestId(Long id) throws ServiceException ;
}
