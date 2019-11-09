package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.TestResultsAnswerDto;
import kz.kasya.bitlab.RXCourse.models.entities.TestResultsAnswer;
import kz.kasya.bitlab.RXCourse.models.mappers.TestResultsAnswerMapper;
import kz.kasya.bitlab.RXCourse.services.TestResultsAnswerService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/test_result/answer")
@AllArgsConstructor
public class TestResultsAnswerController extends BaseController {

    private TestResultsAnswerService testResultsAnswerService;
    private TestResultsAnswerMapper testResultsAnswerMapper;

    @GetMapping
    @ApiOperation("Получение всех результатов тестов в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(testResultsAnswerMapper.toDtoList(testResultsAnswerService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение результатов теста по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(testResultsAnswerMapper.toDto(testResultsAnswerService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление результатов теста")
    public ResponseEntity<?> add(@RequestBody TestResultsAnswerDto testResultsAnswerDto) throws ServiceException {
        TestResultsAnswer testResultsAnswer = testResultsAnswerMapper.toEntity(testResultsAnswerDto);
        return buildResponse(testResultsAnswerMapper.toDto(testResultsAnswerService.save(testResultsAnswer)), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody TestResultsAnswerDto testResultsAnswerDto) throws ServiceException {
        TestResultsAnswer testResultsAnswer = testResultsAnswerService.update(testResultsAnswerMapper.toEntity(testResultsAnswerDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(testResultsAnswerMapper.toDto(testResultsAnswer))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("Удаление результатов теста")
    public ResponseEntity<?> delete(@RequestBody TestResultsAnswerDto testResultsAnswerDto) throws ServiceException {
        testResultsAnswerService.delete(testResultsAnswerMapper.toEntity(testResultsAnswerDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление результатов теста по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException {
        testResultsAnswerService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }
}
