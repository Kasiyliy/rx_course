package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.CourseDto;
import kz.kasya.bitlab.RXCourse.models.entities.Course;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper extends AbstractModelMapper<Course, CourseDto> {

    private ModelMapper modelMapper;
    private UserMapper userMapper;

    @Autowired
    public CourseMapper(ModelMapper modelMapper,
                      UserMapper userMapper) {
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
    }

    @Override
    public CourseDto toDto(Course course) {
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        if (course.getUser() != null) {
            courseDto.setUser(userMapper.toDto(course.getUser()));
        }
        return courseDto;
    }

    @Override
    public Course toEntity(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        if (courseDto.getUser() != null) {
            course.setUser(userMapper.toEntity(courseDto.getUser()));
        }
        return course;
    }

    @Override
    public List<CourseDto> toDtoList(List<Course> courses) {
        return courses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> toEntityList(List<CourseDto> courseDtos) {
        return courseDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
