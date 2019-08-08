package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.HomeworkDto;
import kz.kasya.bitlab.RXCourse.models.entities.Homework;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HomeworkMapper extends AbstractModelMapper<Homework, HomeworkDto> {

    private ModelMapper modelMapper;
    private LessonMapper lessonMapper;

    @Autowired
    public HomeworkMapper(ModelMapper modelMapper, LessonMapper lessonMapper) {
        this.modelMapper = modelMapper;
        this.lessonMapper = lessonMapper;
    }

    @Override
    public HomeworkDto toDto(Homework homework) {
        HomeworkDto homeworkDto= modelMapper.map(homework, HomeworkDto.class);
        if (homework.getLesson() != null) {
            homeworkDto.setLesson(lessonMapper.toDto(homework.getLesson()));
        }
        return homeworkDto;
    }

    @Override
    public Homework toEntity(HomeworkDto homeworkDto) {
        Homework homework = modelMapper.map(homeworkDto, Homework.class);
        if (homeworkDto.getLesson() != null) {
            homework.setLesson(lessonMapper.toEntity(homeworkDto.getLesson()));
        }
        return homework;
    }

    @Override
    public List<HomeworkDto> toDtoList(List<Homework> homework) {
        return homework.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Homework> toEntityList(List<HomeworkDto> homeworkDtos) {
        return homeworkDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
