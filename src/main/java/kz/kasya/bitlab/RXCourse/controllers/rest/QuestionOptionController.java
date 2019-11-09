package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.QuestionOptionDto;
import kz.kasya.bitlab.RXCourse.models.entities.QuestionOption;
import kz.kasya.bitlab.RXCourse.models.mappers.QuestionOptionMapper;
import kz.kasya.bitlab.RXCourse.services.QuestionOptionService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tests/questions/answers")
@AllArgsConstructor
public class QuestionOptionController extends BaseController {

    private QuestionOptionService questionOptionService;
    private QuestionOptionMapper questionOptionMapper;

    @GetMapping
    @ApiOperation("Получение всех вариантов вопросов в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(questionOptionMapper.toDtoList(questionOptionService.findAll()), HttpStatus.OK);
    }

//    @GetMapping("/by-question-id/{id}")
//    @ApiOperation("Получение всех вариантов вопросов в грязном виде")
//    public ResponseEntity<?> getAllByQuestionId(@PathVariable Long id) {
//        return buildResponse(questionOptionMapper.toDtoList(questionOptionService.findAllByQuestionIds()), HttpStatus.OK);
//    }

    @GetMapping("{id}")
    @ApiOperation("Получение вариантов вопроса по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(questionOptionMapper.toDto(questionOptionService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление вариантов вопроса")
    public ResponseEntity<?> add(@RequestBody QuestionOptionDto questionOptionDto) throws ServiceException {
        QuestionOption questionOption = questionOptionMapper.toEntity(questionOptionDto);
        return buildResponse(questionOptionMapper.toDto(questionOptionService.save(questionOption)), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody QuestionOptionDto questionOptionDto) throws ServiceException {
        QuestionOption questionOption = questionOptionService.update(questionOptionMapper.toEntity(questionOptionDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(questionOptionMapper.toDto(questionOption))
                .build(), HttpStatus.OK);
    }


    @DeleteMapping
    @ApiOperation("Удаление вариантов вопроса")
    public ResponseEntity<?> delete(@RequestBody QuestionOptionDto questionOptionDto) throws ServiceException{
        questionOptionService.delete(questionOptionMapper.toEntity(questionOptionDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление вариантов вопроса по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException{
        questionOptionService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }
}
