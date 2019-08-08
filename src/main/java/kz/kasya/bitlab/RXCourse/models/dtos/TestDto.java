package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель теста")
public class TestDto extends BaseDto {

    @ApiModelProperty(notes = "Название теста", readOnly = true)
    private String title;

    @ApiModelProperty(notes = "Описание теста", readOnly = true)
    private String description;

    @ApiModelProperty(notes = "Относящий курс", readOnly = true)
    private CourseDto course;

    @ApiModelProperty(notes = "Относящийся урок", readOnly = true)
    private LessonDto lesson;
}
