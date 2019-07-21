package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.LessonDto;
import kz.kasya.bitlab.RXCourse.models.entities.Lesson;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LessonMapper extends AbstractModelMapper<Lesson, LessonDto> {
    private ModelMapper modelMapper;
    private CourseMapper courseMapper;

    @Autowired
    public LessonMapper(ModelMapper modelMapper, CourseMapper courseMapper) {
        this.modelMapper = modelMapper;
        this.courseMapper = courseMapper;
    }

    @Override
    public LessonDto toDto(Lesson lesson) {
        LessonDto lessonDto = modelMapper.map(lesson, LessonDto.class);
        if (lesson.getCourse() != null) {
            lessonDto.setCourseDto(courseMapper.toDto(lesson.getCourse()));
        }
        return lessonDto;
    }

    @Override
    public Lesson toEntity(LessonDto lessonDto) {
        Lesson lesson = modelMapper.map(lessonDto, Lesson.class);
        if (lessonDto.getCourseDto() != null) {
            lesson.setCourse(courseMapper.toEntity(lessonDto.getCourseDto()));
        }
        return lesson;
    }

    @Override
    public List<LessonDto> toDtoList(List<Lesson> lessons) {
        return lessons.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Lesson> toEntityList(List<LessonDto> lessonDtos) {
        return lessonDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
