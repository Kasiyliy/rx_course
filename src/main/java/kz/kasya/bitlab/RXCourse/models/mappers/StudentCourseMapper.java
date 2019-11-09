package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.StudentCourseDto;
import kz.kasya.bitlab.RXCourse.models.entities.Question;
import kz.kasya.bitlab.RXCourse.models.entities.StudentCourse;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentCourseMapper extends AbstractModelMapper<StudentCourse, StudentCourseDto> {

    private ModelMapper modelMapper;
    private CourseMapper courseMapper;
    private UserMapper userMapper;

    @Autowired
    public StudentCourseMapper(ModelMapper modelMapper, CourseMapper courseMapper, UserMapper userMapper) {
        this.modelMapper = modelMapper;
        this.courseMapper = courseMapper;
        this.userMapper = userMapper;
    }

    @Override
    public StudentCourseDto toDto(StudentCourse studentCourse) {
        StudentCourseDto studentCourseDto = modelMapper.map(studentCourse, StudentCourseDto.class);
        if (studentCourse.getUser() != null) {
            studentCourseDto.setUser(userMapper.toDto(studentCourse.getUser()));
        }
        if (studentCourse.getCourse() != null) {
            studentCourseDto.setCourse(courseMapper.toDto(studentCourse.getCourse()));
        }
        return studentCourseDto;
    }

    @Override
    public StudentCourse toEntity(StudentCourseDto studentCourseDto) {
        StudentCourse studentCourse = modelMapper.map(studentCourseDto, StudentCourse.class);
        if (studentCourseDto.getCourse() != null) {
            studentCourse.setCourse(courseMapper.toEntity(studentCourseDto.getCourse()));
        }
        if (studentCourseDto.getUser() != null) {
            studentCourse.setUser(userMapper.toEntity(studentCourseDto.getUser()));
        }
        return studentCourse;
    }

    @Override
    public List<StudentCourseDto> toDtoList(List<StudentCourse> studentCourses) {
        return studentCourses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentCourse> toEntityList(List<StudentCourseDto> studentCourseDtos) {
        return studentCourseDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
