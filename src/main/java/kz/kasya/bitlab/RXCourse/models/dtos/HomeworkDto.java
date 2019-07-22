package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель домашнее задание")
public class HomeworkDto extends BaseDto{
    @ApiModelProperty(notes = "Домашнее задание", readOnly = true)
    private String task;

    @ApiModelProperty(notes = "Ссылка к файлу с заданием", readOnly = true)
    private String url;

    @ApiModelProperty(notes = "Урок к которому относится задание", readOnly = true)
    private LessonDto lessonDto;

}
