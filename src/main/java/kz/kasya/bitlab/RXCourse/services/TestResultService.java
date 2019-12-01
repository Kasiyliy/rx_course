package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.TestResult;
import kz.kasya.bitlab.RXCourse.models.entities.TestResultsAnswer;

import java.util.List;

public interface TestResultService {
    List<TestResult> findAll();
    TestResult findById(Long id) throws ServiceException;
    TestResult save(TestResult testResult) throws ServiceException;
    TestResult update(TestResult testResult) throws ServiceException;
    void delete(TestResult testResult) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
}
