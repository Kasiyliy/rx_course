package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель ответы на домашнее задание")
public class HomeworkAnswerDto extends BaseDto{

    @ApiModelProperty(notes = "Ответ на домашнее задание", readOnly = true)
    private String answer;

    @ApiModelProperty(notes = "Ссылка на файл ответа", readOnly = true)
    private String url;

    @ApiModelProperty(notes = "Домашнее задание к которому относится ответ", readOnly = true)
    private HomeworkDto homeworkDto;

    @ApiModelProperty(notes = "Студент который выполнил задание", readOnly = true)
    private UserDto userDto;
}
