package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.HomeworkDto;
import kz.kasya.bitlab.RXCourse.models.entities.Homework;
import kz.kasya.bitlab.RXCourse.models.mappers.HomeworkMapper;
import kz.kasya.bitlab.RXCourse.services.HomeworkService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/homeworks")
public class HomeworkController extends BaseController {
    private HomeworkService homeworkService;
    private HomeworkMapper homeworkMapper;

    @Autowired
    public HomeworkController(HomeworkService homeworkService, HomeworkMapper homeworkMapper) {
        this.homeworkService = homeworkService;
        this.homeworkMapper = homeworkMapper;
    }

    @GetMapping
    @ApiOperation("Получение всех домашних заданий в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(homeworkMapper.toDtoList(homeworkService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение домашних заданий по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(homeworkMapper.toDto(homeworkService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление домашнего задания")
    public ResponseEntity<?> add(@RequestBody HomeworkDto homeworkDto) throws ServiceException {
        Homework homework = homeworkMapper.toEntity(homeworkDto);
        return buildResponse(homeworkMapper.toDto(homeworkService.save(homework)), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody HomeworkDto homeworkDto) throws ServiceException {
        Homework homework = homeworkService.update(homeworkMapper.toEntity(homeworkDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(homeworkMapper.toDto(homework))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("Удаление домашнего задания")
    public ResponseEntity<?> delete(@RequestBody HomeworkDto homeworkDto) throws ServiceException{
        homeworkService.delete(homeworkMapper.toEntity(homeworkDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление домашнего задания по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException{
        homeworkService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

}
