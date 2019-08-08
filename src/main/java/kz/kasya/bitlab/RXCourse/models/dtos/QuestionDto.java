package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import kz.kasya.bitlab.RXCourse.models.entities.Test;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto extends BaseDto {

    @ApiModelProperty(notes = "Вопрос", readOnly = true)
    private String question;

    @ApiModelProperty(notes = "Вопрос с рисунком", readOnly = true)
    private String image;

    @ApiModelProperty(notes = "Балл сколько весит", readOnly = true)
    private Integer score;

    @ApiModelProperty(notes = "Тест вопроса", readOnly = true)
    private TestDto test;
}
