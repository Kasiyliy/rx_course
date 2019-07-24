package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.TestResultsAnswer;

import java.util.List;

public interface TestResultsAnswerService {
    List<TestResultsAnswer> findAll();
    TestResultsAnswer findById(Long id) throws ServiceException;
    TestResultsAnswer save(TestResultsAnswer testResultsAnswer) throws ServiceException;
    TestResultsAnswer update(TestResultsAnswer testResultsAnswer) throws ServiceException;
    void delete(TestResultsAnswer testResultsAnswer) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
}
