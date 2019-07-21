package kz.kasya.bitlab.RXCourse.controllers.rest;

import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.models.mappers.TestResultsAnswerMapper;
import kz.kasya.bitlab.RXCourse.services.TestResultsAnswerService;

public class TestResultsAnswerController extends BaseController {
    private TestResultsAnswerService testResultsAnswerService;
    private TestResultsAnswerMapper testResultsAnswerMapper;

    public TestResultsAnswerController(TestResultsAnswerService testResultsAnswerService, TestResultsAnswerMapper testResultsAnswerMapper){
        this.testResultsAnswerMapper = testResultsAnswerMapper;
        this.testResultsAnswerService = testResultsAnswerService;
    }
}
