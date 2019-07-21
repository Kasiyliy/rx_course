package kz.kasya.bitlab.RXCourse.controllers.rest;

import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.models.mappers.TestMapper;
import kz.kasya.bitlab.RXCourse.models.mappers.TestResultMapper;
import kz.kasya.bitlab.RXCourse.services.TestResultService;

public class TestResultController extends BaseController {
    private TestResultService testResultService;
    private TestResultMapper testResultMapper;

    public TestResultController(TestResultService testResultService, TestResultMapper testResultMapper){
        this.testResultMapper = testResultMapper;
        this.testResultService = testResultService;
    }

}
