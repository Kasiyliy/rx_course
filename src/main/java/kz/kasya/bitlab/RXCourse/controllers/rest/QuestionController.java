package kz.kasya.bitlab.RXCourse.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.QuestionDto;
import kz.kasya.bitlab.RXCourse.models.entities.Question;
import kz.kasya.bitlab.RXCourse.models.mappers.QuestionMapper;
import kz.kasya.bitlab.RXCourse.models.requests.QuestionRequest;
import kz.kasya.bitlab.RXCourse.models.requests.TestRequestQuestions;
import kz.kasya.bitlab.RXCourse.services.QuestionService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests/questions")
public class QuestionController extends BaseController {
    private QuestionService questionService;
    private QuestionMapper questionMapper;
    private ObjectMapper objectMapper;

    public QuestionController(QuestionService questionService, QuestionMapper questionMapper) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
    }

    @GetMapping
    @ApiOperation("Получение всех вопросов в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(questionMapper.toDtoList(questionService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение вопроса по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(questionMapper.toDto(questionService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление вопроса")
    public ResponseEntity<?> add(@RequestBody QuestionDto questionDto) throws ServiceException {
        Question question = questionMapper.toEntity(questionDto);
        return buildResponse(questionMapper.toDto(questionService.save(question)), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody QuestionDto questionDto) throws ServiceException {
        Question question = questionService.update(questionMapper.toEntity(questionDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(questionMapper.toDto(question))
                .build(), HttpStatus.OK);
    }


    @DeleteMapping
    @ApiOperation("Удаление вопроса")
    public ResponseEntity<?> delete(@RequestBody QuestionDto questionDto) throws ServiceException{
        questionService.delete(questionMapper.toEntity(questionDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление вопроса по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException{
        questionService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @PostMapping("add")
    @ApiOperation("Добавление вопросов и ответов")
    public ResponseEntity<?> addQuestions(@RequestBody TestRequestQuestions testRequestQuestions) throws ServiceException {
        questionService.addQuestions(testRequestQuestions.getQuestionRequests(), testRequestQuestions.getId());
        return buildResponse(SuccessResponse.builder().message("Added").build(), HttpStatus.OK);
    }

}
