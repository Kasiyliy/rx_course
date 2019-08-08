package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptionDto extends BaseDto {

    @ApiModelProperty(notes = "Ответ", readOnly = true)
    private String answer;

    @ApiModelProperty(notes = "Вопрос ответа", readOnly = true)
    private QuestionDto question;

    @ApiModelProperty(notes = "Правильность", readOnly = true)
    private Boolean rightAnswer;
}
