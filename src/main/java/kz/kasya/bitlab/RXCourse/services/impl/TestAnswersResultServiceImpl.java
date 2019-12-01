package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.QuestionOption;
import kz.kasya.bitlab.RXCourse.models.entities.TestResult;
import kz.kasya.bitlab.RXCourse.models.entities.TestResultsAnswer;
import kz.kasya.bitlab.RXCourse.repositories.TestResultsAnswerRepository;
import kz.kasya.bitlab.RXCourse.services.QuestionOptionService;
import kz.kasya.bitlab.RXCourse.services.TestResultsAnswerService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TestAnswersResultServiceImpl implements TestResultsAnswerService {
    @Autowired
    private TestResultsAnswerRepository testResultsAnswerRepository;
    @Autowired
    private QuestionOptionService questionOptionService;

    @Override
    public List<TestResultsAnswer> findAll() {
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
        if (testResultsAnswer.getId() != null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        return testResultsAnswerRepository.save(testResultsAnswer);
    }

    @Override
    public TestResultsAnswer update(TestResultsAnswer testResultsAnswer) throws ServiceException {
        if (testResultsAnswer.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return testResultsAnswerRepository.save(testResultsAnswer);
    }

    @Override
    public void delete(TestResultsAnswer testResultsAnswer) throws ServiceException {
        if (testResultsAnswer.getId() == null) {
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
        if (id == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        TestResultsAnswer testResultsAnswer = findById(id);
        testResultsAnswer.setDeletedAt(new Date());
        testResultsAnswerRepository.save(testResultsAnswer);
    }

    @Override
    public Integer passTest(List<TestResultsAnswer> testResultsAnswers, TestResult testResult) throws ServiceException {
        List<Long> ids = new ArrayList<>();
        HashMap<Long, Boolean> hashMap = new HashMap<>();
        int result = 0;
        for (TestResultsAnswer testResultsAnswer : testResultsAnswers) {
            ids.add(testResultsAnswer.getQuestionOption().getId());
        }
        List<QuestionOption> questionOptions = questionOptionService.findAllByIds(ids);
        for (QuestionOption option : questionOptions) {
            hashMap.put(option.getId(), option.getRightAnswer());

        }

        for (TestResultsAnswer testResultsAnswer : testResultsAnswers) {
            Long questionOptionId = testResultsAnswer.getQuestionOption().getId();
            testResultsAnswer.setAnswer(hashMap.get(questionOptionId));
            testResultsAnswer.setTestResult(testResult);
        }
        testResultsAnswerRepository.saveAll(testResultsAnswers);
        for (TestResultsAnswer testResultsAnswer : testResultsAnswers) {
            if(testResultsAnswer.isAnswer()){
                result += testResultsAnswer.getQuestion().getScore();
            }
        }
        return result;
    }
}
