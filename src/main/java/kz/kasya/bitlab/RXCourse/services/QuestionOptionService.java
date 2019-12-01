package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.QuestionOption;

import java.util.List;

public interface QuestionOptionService {
    List<QuestionOption> findAll();
    QuestionOption findById(Long id) throws ServiceException;
    QuestionOption save(QuestionOption questionOption) throws ServiceException;
    QuestionOption update(QuestionOption questionOption) throws ServiceException;
    void delete(QuestionOption questionOption) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
    List<QuestionOption> findAllByQuestionIds(List<Long> ids) throws ServiceException;
    List<QuestionOption> findAllByQuestionId(Long id) throws ServiceException;
    List<QuestionOption> findAllByIds(List<Long> ids) throws ServiceException;
}
