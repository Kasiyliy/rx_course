package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.TestDto;
import kz.kasya.bitlab.RXCourse.models.entities.Test;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestMapper extends AbstractModelMapper<Test, TestDto> {

    private ModelMapper modelMapper;
    private CourseMapper courseMapper;
    private LessonMapper lessonMapper;

    public TestMapper(ModelMapper modelMapper, CourseMapper courseMapper, LessonMapper lessonMapper) {
        this.modelMapper = modelMapper;
        this.courseMapper = courseMapper;
        this.lessonMapper = lessonMapper;
    }
    @Override
    public TestDto toDto(Test test) {
        TestDto testDto = modelMapper.map(test, TestDto.class);
        if (test.getCourse() != null) {
            testDto.setCourse(courseMapper.toDto(test.getCourse()));
        }
        if (test.getLesson() != null) {
            testDto.setLesson(lessonMapper.toDto(test.getLesson()));
        }
        return testDto;
    }

    @Override
    public Test toEntity(TestDto testDto) {
        Test test = modelMapper.map(testDto, Test.class);
        if (testDto.getCourse() != null) {
            test.setCourse(courseMapper.toEntity(testDto.getCourse()));
        }

        if (testDto.getLesson() != null) {
            test.setLesson(lessonMapper.toEntity(testDto.getLesson()));
        }
        return test;
    }

    @Override
    public List<TestDto> toDtoList(List<Test> tests) {
        return tests.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Test> toEntityList(List<TestDto> testDtos) {
        return testDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
