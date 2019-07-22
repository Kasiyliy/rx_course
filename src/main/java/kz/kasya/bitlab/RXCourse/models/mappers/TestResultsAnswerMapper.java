package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.TestResultsAnswerDto;
import kz.kasya.bitlab.RXCourse.models.entities.Question;
import kz.kasya.bitlab.RXCourse.models.entities.TestResultsAnswer;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestResultsAnswerMapper extends AbstractModelMapper<TestResultsAnswer, TestResultsAnswerDto> {
    private ModelMapper modelMapper;
    private TestResultMapper testResultMapper;
    private QuestionMapper questionMapper;
    private QuestionOptionMapper questionOptionMapper;

    public TestResultsAnswerMapper(ModelMapper modelMapper, TestResultMapper testResultMapper, QuestionMapper questionMapper, QuestionOptionMapper questionOptionMapper) {
        this.modelMapper = modelMapper;
        this.testResultMapper = testResultMapper;
        this.questionMapper = questionMapper;
        this.questionOptionMapper = questionOptionMapper;
    }

    @Override
    public TestResultsAnswerDto toDto(TestResultsAnswer testResultsAnswer) {
        TestResultsAnswerDto testResultsAnswerDto = modelMapper.map(testResultsAnswer, TestResultsAnswerDto.class);
        if (testResultsAnswer.getTestResult() != null) {
            testResultsAnswerDto.setTestResultDto(testResultMapper.toDto(testResultsAnswer.getTestResult()));
        }
        if (testResultsAnswer.getQuestion() != null) {
                testResultsAnswerDto.setQuestionDto(questionMapper.toDto(testResultsAnswer.getQuestion()));
        }
        if (testResultsAnswer.getQuestionOption() != null) {
            testResultsAnswerDto.setQuestionOptionDto(questionOptionMapper.toDto(testResultsAnswer.getQuestionOption()));
        }
        return testResultsAnswerDto;
    }

    @Override
    public TestResultsAnswer toEntity(TestResultsAnswerDto testResultsAnswerDto) {
        TestResultsAnswer testResultsAnswer = modelMapper.map(testResultsAnswerDto, TestResultsAnswer.class);
        if (testResultsAnswerDto.getTestResultDto() != null) {
            testResultsAnswer.setTestResult(testResultMapper.toEntity(testResultsAnswerDto.getTestResultDto()));
        }

        if (testResultsAnswerDto.getQuestionDto() != null) {
            testResultsAnswer.setQuestion(questionMapper.toEntity(testResultsAnswerDto.getQuestionDto()));
        }
        if (testResultsAnswerDto.getQuestionOptionDto() != null) {
            testResultsAnswer.setQuestionOption(questionOptionMapper.toEntity(testResultsAnswerDto.getQuestionOptionDto()));
        }
        return testResultsAnswer;
    }

    @Override
    public List<TestResultsAnswerDto> toDtoList(List<TestResultsAnswer> testResultsAnswers) {
        return testResultsAnswers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TestResultsAnswer> toEntityList(List<TestResultsAnswerDto> testResultsAnswerDtos) {
        return testResultsAnswerDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
