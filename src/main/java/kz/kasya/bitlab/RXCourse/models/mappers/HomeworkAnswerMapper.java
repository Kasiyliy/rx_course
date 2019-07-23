package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.HomeworkAnswerDto;
import kz.kasya.bitlab.RXCourse.models.entities.HomeworkAnswer;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HomeworkAnswerMapper extends AbstractModelMapper<HomeworkAnswer, HomeworkAnswerDto> {
    private ModelMapper modelMapper;
    private HomeworkMapper homeworkMapper;
    private UserMapper userMapper;

    @Autowired
    public HomeworkAnswerMapper(ModelMapper modelMapper, HomeworkMapper homeworkMapper, UserMapper userMapper) {
        this.modelMapper = modelMapper;
        this.homeworkMapper = homeworkMapper;
        this.userMapper = userMapper;
    }

    @Override
    public HomeworkAnswerDto toDto(HomeworkAnswer homeworkAnswer) {
        HomeworkAnswerDto homeworkAnswerDto= modelMapper.map(homeworkAnswer, HomeworkAnswerDto.class);
        if (homeworkAnswer.getHomework() != null && homeworkAnswer.getUser() != null) {
            homeworkAnswerDto.setHomeworkDto(homeworkMapper.toDto(homeworkAnswer.getHomework()));
            homeworkAnswerDto.setUserDto(userMapper.toDto(homeworkAnswer.getUser()));
        }
        return homeworkAnswerDto;
    }

    @Override
    public HomeworkAnswer toEntity(HomeworkAnswerDto homeworkAnswerDto) {
        HomeworkAnswer homeworkAnswer= modelMapper.map(homeworkAnswerDto, HomeworkAnswer.class);
        if (homeworkAnswerDto.getHomeworkDto() != null && homeworkAnswerDto.getUserDto() != null) {
            homeworkAnswer.setHomework(homeworkMapper.toEntity(homeworkAnswerDto.getHomeworkDto()));
            homeworkAnswer.setUser(userMapper.toEntity(homeworkAnswerDto.getUserDto()));
        }
        return homeworkAnswer;
    }

    @Override
    public List<HomeworkAnswerDto> toDtoList(List<HomeworkAnswer> homeworkAnswers) {
        return homeworkAnswers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HomeworkAnswer> toEntityList(List<HomeworkAnswerDto> homeworkAnswerDtos) {
        return homeworkAnswerDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
