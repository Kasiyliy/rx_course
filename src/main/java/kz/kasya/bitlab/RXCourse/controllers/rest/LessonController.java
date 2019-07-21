package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.models.mappers.LessonMapper;
import kz.kasya.bitlab.RXCourse.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
