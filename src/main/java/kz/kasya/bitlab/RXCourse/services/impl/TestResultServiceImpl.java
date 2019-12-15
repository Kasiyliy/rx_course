package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.TestResult;
import kz.kasya.bitlab.RXCourse.models.entities.TestResultsAnswer;
import kz.kasya.bitlab.RXCourse.repositories.TestResultRepository;
import kz.kasya.bitlab.RXCourse.services.TestResultService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TestResultServiceImpl implements TestResultService {
    private TestResultRepository testResultRepository;

    @Autowired
    public TestResultServiceImpl(TestResultRepository testResultRepository){
        this.testResultRepository = testResultRepository;
    }

    @Override
    public List<TestResult> findAll(){
        return testResultRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public TestResult findById(Long id) throws ServiceException {
        Optional<TestResult> courseOptional = testResultRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("lesson not found")
                .build());
    }

    @Override
    public TestResult save(TestResult testResult) throws ServiceException {
        if(testResult.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        return  testResultRepository.save(testResult);
    }

    @Override
    public TestResult update(TestResult testResult) throws ServiceException {
        if(testResult.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return testResultRepository.save(testResult);
    }

    @Override
    public void delete(TestResult testResult) throws ServiceException {
        if(testResult.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        testResult = findById(testResult.getId());
        testResult.setDeletedAt(new Date());
        testResultRepository.save(testResult);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        TestResult testResult = findById(id);
        testResult.setDeletedAt(new Date());
        testResultRepository.save(testResult);
    }

    @Override
    public List<TestResult> getMyResults(Long userId) {
        return testResultRepository.findAllByDeletedAtIsNullAndUser_Id(userId);
    }
}
