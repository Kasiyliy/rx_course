package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Course;
import kz.kasya.bitlab.RXCourse.repositories.CourseRepository;
import kz.kasya.bitlab.RXCourse.services.CourseService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll(){
        return courseRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Course findById(Long id) throws ServiceException {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("course not found")
                .build());
    }

    @Override
    public Course save(Course course) throws ServiceException {
        if(course.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("course already exists")
                    .build();
        }
        return  courseRepository.save(course);
    }


    @Override
    public Course update(Course course) throws ServiceException {
        if(course.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("course is null")
                    .build();
        }
        return courseRepository.save(course);
    }

    @Override
    public void delete(Course course) throws ServiceException {
        if(course.getId() == null){
                throw ServiceException.builder()
                        .errorCode(ErrorCode.SYSTEM_ERROR)
                        .message("course is null")
                        .build();
        }
        course = findById(course.getId());
        course.setDeletedAt(new Date());
        courseRepository.save(course);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Course course = findById(id);
        course.setDeletedAt(new Date());
        courseRepository.save(course);
    }

    @Override
    public Page<Course> findAllPageable(Optional<Integer> page, Optional<Integer> size, Optional<String[]> sortBy) {
        Sort sort = null;
        if(sortBy.isPresent()){
            String[] sorters = sortBy.get();
            List<Sort.Order> sorts = Arrays.stream(sorters)
                    .map(s -> s.split("-")[0].trim().equalsIgnoreCase("asc")
                            ? Sort.Order.asc( s.split("-")[1]) : Sort.Order.desc( s.split("-")[1]))
                    .collect(Collectors.toList());
            sort = Sort.by(sorts);
        }else{
            sort = Sort.by("id");
        }
        return courseRepository.findAllPageableByDeletedAtIsNull(PageRequest.of(page.orElse(0),size.orElse(5),sort));}
}
