package kz.kasya.bitlab.RXCourse.controllers.rest;

import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.models.mappers.QuestionOptionMapper;
import kz.kasya.bitlab.RXCourse.services.QuestionOptionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests/questions/answers")
public class QuestionOptionController extends BaseController {
    private QuestionOptionService questionOptionService;
    private QuestionOptionMapper questionOptionMapper;

    public QuestionOptionController(QuestionOptionService questionOptionService, QuestionOptionMapper questionOptionMapper) {
        this.questionOptionService = questionOptionService;
        this.questionOptionMapper = questionOptionMapper;
    }
}
