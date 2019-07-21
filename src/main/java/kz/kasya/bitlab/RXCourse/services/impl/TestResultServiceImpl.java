package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.repositories.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TestResultServiceImpl {
    private TestResultRepository testResultRepository;

    @Autowired
    public TestResultServiceImpl(TestResultRepository testResultRepository){
        this.testResultRepository = testResultRepository;
    }
}
