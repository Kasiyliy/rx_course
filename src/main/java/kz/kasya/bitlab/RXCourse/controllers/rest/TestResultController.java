package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.TestResultDto;
import kz.kasya.bitlab.RXCourse.models.entities.TestResult;
import kz.kasya.bitlab.RXCourse.models.mappers.TestMapper;
import kz.kasya.bitlab.RXCourse.models.mappers.TestResultMapper;
import kz.kasya.bitlab.RXCourse.services.TestResultService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/test_result")
@AllArgsConstructor
public class TestResultController extends BaseController {

    private TestResultService testResultService;
    private TestResultMapper testResultMapper;

    @GetMapping
    @ApiOperation("Получение всех ответов тестов в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(testResultMapper.toDtoList(testResultService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение ответов теста по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(testResultMapper.toDto(testResultService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление ответов теста")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<?> add(@RequestBody TestResultDto testResultDto) throws ServiceException {
        TestResult testResult = testResultMapper.toEntity(testResultDto);
        return buildResponse(testResultMapper.toDto(testResultService.save(testResult)), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<?> update(@RequestBody TestResultDto testResultDto) throws ServiceException {
        TestResult testResult = testResultService.update(testResultMapper.toEntity(testResultDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(testResultMapper.toDto(testResult))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("Удаление ответов теста")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<?> delete(@RequestBody TestResultDto testResultDto) throws ServiceException {
        testResultService.delete(testResultMapper.toEntity(testResultDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление ответов теста по ID")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException {
        testResultService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

}
