package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель уроки")
public class LessonDto extends BaseDto {

    @ApiModelProperty(notes = "Наименование урока", readOnly = true)
    private String name;

    @ApiModelProperty(notes = "Очередность урока", readOnly = true)
    private Integer order;

    @ApiModelProperty(notes = "Курс к которому относится урок", readOnly = true)
    private CourseDto course;
}
