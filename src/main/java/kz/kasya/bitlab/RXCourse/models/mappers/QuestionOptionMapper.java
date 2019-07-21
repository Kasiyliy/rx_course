package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.QuestionDto;
import kz.kasya.bitlab.RXCourse.models.dtos.QuestionOptionDto;
import kz.kasya.bitlab.RXCourse.models.entities.Question;
import kz.kasya.bitlab.RXCourse.models.entities.QuestionOption;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionOptionMapper extends AbstractModelMapper<QuestionOption, QuestionOptionDto> {
    private ModelMapper modelMapper;
    private QuestionMapper questionMapper;

    public QuestionOptionMapper(ModelMapper modelMapper, QuestionMapper questionMapper) {
        this.modelMapper = modelMapper;
        this.questionMapper = questionMapper;
    }

    @Override
    public QuestionOptionDto toDto(QuestionOption questionOption) {
        QuestionOptionDto questionOptionDto = modelMapper.map(questionOption, QuestionOptionDto.class);
        if (questionOption.getQuestion() != null) {
            questionOptionDto.setQuestionDto(questionMapper.toDto(questionOption.getQuestion()));
        }
        return questionOptionDto;
    }

    @Override
    public QuestionOption toEntity(QuestionOptionDto questionOptionDto) {
        QuestionOption questionOption = modelMapper.map(questionOptionDto, QuestionOption.class);
        if (questionOptionDto.getQuestionDto() != null) {
            questionOption.setQuestion(questionMapper.toEntity(questionOptionDto.getQuestionDto()));
        }

        return questionOption;
    }

    @Override
    public List<QuestionOptionDto> toDtoList(List<QuestionOption> questionOptions) {
        return questionOptions.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionOption> toEntityList(List<QuestionOptionDto> questionOptionDtos) {
        return questionOptionDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
