package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.TestResultsAnswer;
import kz.kasya.bitlab.RXCourse.repositories.TestResultsAnswerRepository;
import kz.kasya.bitlab.RXCourse.services.TestResultsAnswerService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TestAnswersResultServiceImpl implements TestResultsAnswerService {
    private TestResultsAnswerRepository testResultsAnswerRepository;

    @Autowired
    public TestAnswersResultServiceImpl(TestResultsAnswerRepository testResultsAnswerRepository){
        this.testResultsAnswerRepository = testResultsAnswerRepository;
    }

    @Override
    public List<TestResultsAnswer> findAll(){
        return testResultsAnswerRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public TestResultsAnswer findById(Long id) throws ServiceException {
        Optional<TestResultsAnswer> courseOptional = testResultsAnswerRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("lesson not found")
                .build());
    }

    @Override
    public TestResultsAnswer save(TestResultsAnswer testResultsAnswer) throws ServiceException {
        if(testResultsAnswer.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        return  testResultsAnswerRepository.save(testResultsAnswer);
    }

    @Override
    public TestResultsAnswer update(TestResultsAnswer testResultsAnswer) throws ServiceException {
        if(testResultsAnswer.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return testResultsAnswerRepository.save(testResultsAnswer);
    }

    @Override
    public void delete(TestResultsAnswer testResultsAnswer) throws ServiceException {
        if(testResultsAnswer.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        testResultsAnswer = findById(testResultsAnswer.getId());
        testResultsAnswer.setDeletedAt(new Date());
        testResultsAnswerRepository.save(testResultsAnswer);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        TestResultsAnswer testResultsAnswer = findById(id);
        testResultsAnswer.setDeletedAt(new Date());
        testResultsAnswerRepository.save(testResultsAnswer);
    }

}
