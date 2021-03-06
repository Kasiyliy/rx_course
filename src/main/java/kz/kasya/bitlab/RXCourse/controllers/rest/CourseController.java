package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.CourseDto;
import kz.kasya.bitlab.RXCourse.models.entities.Course;
import kz.kasya.bitlab.RXCourse.models.entities.User;
import kz.kasya.bitlab.RXCourse.models.mappers.CourseMapper;
import kz.kasya.bitlab.RXCourse.services.CourseService;
import kz.kasya.bitlab.RXCourse.services.UserService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController extends BaseController {

    private CourseService courseService;
    private CourseMapper courseMapper;
    private UserService userService;

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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<?> add(@RequestBody CourseDto courseDto) throws ServiceException {
        Course course = courseMapper.toEntity(courseDto);
        return buildResponse(courseMapper.toDto(courseService.save(course)), HttpStatus.CREATED);
    }

    @DeleteMapping
    @ApiOperation("Удаление курса")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<?> delete(@RequestBody CourseDto courseDto) throws ServiceException {
        courseService.delete(courseMapper.toEntity(courseDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление курса по ID")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException {
        courseService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody CourseDto courseDto) throws ServiceException {
        Course course = courseService.update(courseMapper.toEntity(courseDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(courseMapper.toDto(course))
                .build(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    @ApiOperation("Получение всех курсы в грязном виде")
    public ResponseEntity<?> getAllPagination(@RequestParam Optional<Integer> page,
                                              @RequestParam Optional<Integer> size,
                                              @RequestParam Optional<String[]> sortBy) {
        return buildResponse(courseService.findAllPageable(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/pagination/my")
    @ApiOperation("Получение всех курсы в грязном виде")
    public ResponseEntity<?> getAllPaginationByUserId(Authentication authentication, @RequestParam Optional<Integer> page,
                                                      @RequestParam Optional<Integer> size,
                                                      @RequestParam Optional<String[]> sortBy) {
        User user = userService.findByLogin(authentication.getName());
        return buildResponse(courseService.findAllPageableByUserId(page, size, sortBy, user.getId()), HttpStatus.OK);
    }


}
