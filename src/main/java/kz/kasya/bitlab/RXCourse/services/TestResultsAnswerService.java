package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.TestResult;
import kz.kasya.bitlab.RXCourse.models.entities.TestResultsAnswer;
import kz.kasya.bitlab.RXCourse.models.responses.TestResultResponse;

import java.util.List;
import java.util.Map;

public interface TestResultsAnswerService {
    List<TestResultsAnswer> findAll();
    TestResultsAnswer findById(Long id) throws ServiceException;
    TestResultsAnswer save(TestResultsAnswer testResultsAnswer) throws ServiceException;
    TestResultsAnswer update(TestResultsAnswer testResultsAnswer) throws ServiceException;
    void delete(TestResultsAnswer testResultsAnswer) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
    TestResultResponse passTest(List<TestResultsAnswer> testResultsAnswers, TestResult testResult) throws ServiceException;
}
