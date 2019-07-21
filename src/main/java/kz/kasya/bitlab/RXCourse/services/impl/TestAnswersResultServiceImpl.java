package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.models.entities.TestResultsAnswer;
import kz.kasya.bitlab.RXCourse.services.TestResultsAnswerService;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAnswersResultServiceImpl implements TestResultsAnswerService {
    private TestResultsAnswer testResultsAnswer;

    @Autowired
    public TestAnswersResultServiceImpl(TestResultsAnswer testResultsAnswer){
        this.testResultsAnswer = testResultsAnswer;
    }
}
