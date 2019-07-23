package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.TestDto;
import kz.kasya.bitlab.RXCourse.models.entities.Test;
import kz.kasya.bitlab.RXCourse.models.mappers.TestMapper;
import kz.kasya.bitlab.RXCourse.services.TestService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tests")
public class TestController extends BaseController {
    private TestService testService;
    private TestMapper testMapper;

    public TestController(TestService testService, TestMapper testMapper) {
        this.testService = testService;
        this.testMapper = testMapper;
    }

    @GetMapping
    @ApiOperation("Получение всех тестов в грязном виде")
    public ResponseEntity<?> getAll(){
        return buildResponse(testMapper.toDtoList(testService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение тестов по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(testMapper.toDto(testService.findById(id)), HttpStatus.OK);
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
    public ResponseEntity<?> delete(@RequestBody TestDto testDto) throws ServiceException{
        testService.delete(testMapper.toEntity(testDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление урока по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException{
        testService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }




}
