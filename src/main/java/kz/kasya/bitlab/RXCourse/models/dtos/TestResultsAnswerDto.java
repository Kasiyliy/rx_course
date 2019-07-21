package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель ответов вопросов теста")
public class TestResultsAnswerDto extends BaseDto {
    @ApiModelProperty(notes = "Относящийся конечный результат теста", readOnly = true)
    private TestResultDto testResultDto;

    @ApiModelProperty(notes = "Относящийся вопрос", readOnly = true)
    private QuestionDto questionDto;

    @ApiModelProperty(notes = "Относяший вариант вопросв", readOnly = true)
    private QuestionOptionDto questionOptionDto;

    @ApiModelProperty(notes = "Ответ", readOnly = true)
    private boolean answer;
}
