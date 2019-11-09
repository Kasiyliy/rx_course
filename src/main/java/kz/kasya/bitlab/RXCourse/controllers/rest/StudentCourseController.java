package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.CourseDto;
import kz.kasya.bitlab.RXCourse.models.dtos.StudentCourseDto;
import kz.kasya.bitlab.RXCourse.models.entities.Course;
import kz.kasya.bitlab.RXCourse.models.entities.StudentCourse;
import kz.kasya.bitlab.RXCourse.models.entities.User;
import kz.kasya.bitlab.RXCourse.models.mappers.StudentCourseMapper;
import kz.kasya.bitlab.RXCourse.services.StudentCourseService;
import kz.kasya.bitlab.RXCourse.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/course")
@AllArgsConstructor
public class StudentCourseController extends BaseController {
    private StudentCourseService studentCourseService;
    private StudentCourseMapper studentCourseMapper;
    private UserService userService;

    @GetMapping("my")
    @ApiOperation("Получение по userId")
    public ResponseEntity<?> getAllByUserId(Authentication authentication) throws ServiceException {
        User user = userService.findByLogin(authentication.getName());
        return buildResponse(studentCourseMapper.toDtoList(studentCourseService.findByUserId(user.getId())), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление курса")
    public ResponseEntity<?> add(Authentication authentication, @RequestBody StudentCourseDto studentCourseDto) throws ServiceException{
        User user = userService.findByLogin(authentication.getName());
        StudentCourse studentCourse = studentCourseMapper.toEntity(studentCourseDto);
        studentCourse.setUser(user);
        return buildResponse(studentCourseMapper.toDto(studentCourseService.save(studentCourse)), HttpStatus.CREATED);
    }

}
