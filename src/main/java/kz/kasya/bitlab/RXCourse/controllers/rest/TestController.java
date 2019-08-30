package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.TestDto;
import kz.kasya.bitlab.RXCourse.models.entities.Question;
import kz.kasya.bitlab.RXCourse.models.entities.QuestionOption;
import kz.kasya.bitlab.RXCourse.models.entities.Test;
import kz.kasya.bitlab.RXCourse.models.mappers.TestMapper;
import kz.kasya.bitlab.RXCourse.models.responses.QuestionOptionResponse;
import kz.kasya.bitlab.RXCourse.models.responses.QuestionResponse;
import kz.kasya.bitlab.RXCourse.models.responses.TestResponse;
import kz.kasya.bitlab.RXCourse.services.QuestionOptionService;
import kz.kasya.bitlab.RXCourse.services.QuestionService;
import kz.kasya.bitlab.RXCourse.services.TestService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tests")
public class TestController extends BaseController {
    private TestService testService;
    private QuestionService questionService;
    private QuestionOptionService questionOptionService;
    private TestMapper testMapper;

    public TestController(TestService testService,
                          QuestionService questionService,
                          QuestionOptionService questionOptionService,
                          TestMapper testMapper) {
        this.testService = testService;
        this.questionOptionService = questionOptionService;
        this.questionService = questionService;
        this.testMapper = testMapper;
    }

    @GetMapping
    @ApiOperation("Получение всех тестов в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(testMapper.toDtoList(testService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение тестов по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(testMapper.toDto(testService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/course/{id}")
    @ApiOperation("Получение тестов по course ID")
    public ResponseEntity<?> getAllByCourseId(@ApiParam("ID курса элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(testMapper.toDtoList(testService.findAllByCourseId(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление теста")
    public ResponseEntity<?> add(@RequestBody TestDto testDto) throws ServiceException {
        Test test = testMapper.toEntity(testDto);
        return buildResponse(testMapper.toDto(testService.save(test)), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody TestDto testDto) throws ServiceException {
        Test test = testService.update(testMapper.toEntity(testDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(testMapper.toDto(test))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("Удаление теста")
    public ResponseEntity<?> delete(@RequestBody TestDto testDto) throws ServiceException {
        testService.delete(testMapper.toEntity(testDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление урока по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException {
        testService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @GetMapping("/full/{testId}")
    @ApiOperation("Вывод фулл теста")
    public ResponseEntity<?> testQuestions(@PathVariable Long testId) throws ServiceException {
        Test test = testService.findById(testId);
        List<Question> questions = questionService.findAllByTestId(test.getId());
        List<Long> questionIds = new ArrayList<>();
        for (Question question : questions) {
            questionIds.add(question.getId());
        }
        List<QuestionOption> questionOptions = questionOptionService.findAllByQuestionIds(questionIds);

        List<QuestionOptionResponse> questionOptionResponses = questionOptions.stream().map((e) ->
                QuestionOptionResponse.builder()
                        .id(e.getId())
                        .questionId(e.getQuestion().getId())
                        .optionText(e.getAnswer())
                        .build())
                .collect(Collectors.toList());

        TestResponse testResponse = TestResponse.builder()
                .description(test.getDescription())
                .id(testId)
                .questions(questions.stream().map((e) ->
                        QuestionResponse.builder()
                                .image(e.getImage())
                                .score(e.getScore())
                                .questionText(e.getQuestion())
                                .id(e.getId())
                                .build()).collect(Collectors.toList()))
                .testName(test.getTitle())
                .build();
        testResponse.getQuestions().parallelStream().forEach(e -> {
            e.setQuestionOptions(questionOptionResponses.stream().
                    filter(qoE -> qoE.getQuestionId().equals(e.getId()))
                    .collect(Collectors.toList()));
        });
        return buildResponse(testResponse, HttpStatus.OK);
    }


}
