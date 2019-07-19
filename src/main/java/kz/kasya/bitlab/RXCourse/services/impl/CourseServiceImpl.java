package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Course;
import kz.kasya.bitlab.RXCourse.repositories.CourseRepository;
import kz.kasya.bitlab.RXCourse.services.CourseService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
