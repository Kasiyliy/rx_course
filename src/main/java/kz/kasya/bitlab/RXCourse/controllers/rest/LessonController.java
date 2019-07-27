package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.LessonDto;
import kz.kasya.bitlab.RXCourse.models.entities.Lesson;
import kz.kasya.bitlab.RXCourse.models.mappers.LessonMapper;
import kz.kasya.bitlab.RXCourse.services.LessonService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
public class LessonController extends BaseController {

    private LessonService lessonService;
    private LessonMapper lessonMapper;

    @Autowired
    public LessonController(LessonService lessonService, LessonMapper lessonMapper) {
        this.lessonService = lessonService;
        this.lessonMapper = lessonMapper;
    }

    @GetMapping
    @ApiOperation("Получение всех уроков в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(lessonMapper.toDtoList(lessonService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение уроков по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(lessonMapper.toDto(lessonService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление урока")
    public ResponseEntity<?> add(@RequestBody LessonDto lessonDto) throws ServiceException {
        Lesson lesson = lessonMapper.toEntity(lessonDto);
        return buildResponse(lessonMapper.toDto(lessonService.save(lesson)), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody LessonDto lessonDto) throws ServiceException {
        Lesson lesson = lessonService.update(lessonMapper.toEntity(lessonDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(lessonMapper.toDto(lesson))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("Удаление урока")
    public ResponseEntity<?> delete(@RequestBody LessonDto lessonDto) throws ServiceException{
        lessonService.delete(lessonMapper.toEntity(lessonDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление урока по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException{
        lessonService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @GetMapping("course/{id}")
    @ApiOperation("Уроки по курсам")
    public ResponseEntity<?> lessonsByCourseId(@PathVariable Long id) throws ServiceException{
        return buildResponse(lessonMapper.toDtoList(lessonService.findByCourseId(id)), HttpStatus.OK);
    }

}
