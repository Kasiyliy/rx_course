package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.CourseDto;
import kz.kasya.bitlab.RXCourse.models.entities.Course;
import kz.kasya.bitlab.RXCourse.models.mappers.CourseMapper;
import kz.kasya.bitlab.RXCourse.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/courses")
public class CourseController extends BaseController {

    private CourseService courseService;
    private CourseMapper courseMapper;

    @Autowired
    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @GetMapping
    @ApiOperation("Получение всех курсы в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(courseMapper.toDtoList(courseService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение курсов по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(courseMapper.toDto(courseService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление курса")
    public ResponseEntity<?> add(@RequestBody CourseDto courseDto) throws ServiceException{
        Course course = courseMapper.toEntity(courseDto);
        return buildResponse(courseMapper.toDto(courseService.save(course)), HttpStatus.CREATED);
    }



}
