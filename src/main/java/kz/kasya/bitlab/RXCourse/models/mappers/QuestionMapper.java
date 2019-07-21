package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.QuestionDto;
import kz.kasya.bitlab.RXCourse.models.entities.Question;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper extends AbstractModelMapper<Question, QuestionDto> {

    private ModelMapper modelMapper;
    private TestMapper testMapper;

    public QuestionMapper(ModelMapper modelMapper, TestMapper testMapper) {
        this.modelMapper = modelMapper;
        this.testMapper = testMapper;
    }

    @Override
    public QuestionDto toDto(Question question) {
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        if (question.getTestLesson() != null) {
            questionDto.setTestDto(testMapper.toDto(question.getTestLesson()));
        }
        return questionDto;
    }

    @Override
    public Question toEntity(QuestionDto questionDto) {
        Question question = modelMapper.map(questionDto, Question.class);
        if (questionDto.getTestDto() != null) {
            question.setTestLesson(testMapper.toEntity(questionDto.getTestDto()));
        }

        return question;
    }

    @Override
    public List<QuestionDto> toDtoList(List<Question> questions) {
        return questions.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Question> toEntityList(List<QuestionDto> questionDtos) {
        return questionDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
