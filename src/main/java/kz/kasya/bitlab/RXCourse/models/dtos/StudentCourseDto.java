package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель теста")
public class StudentCourseDto extends BaseDto {

    @ApiModelProperty(notes = "Курс", readOnly = true)
    private CourseDto course;

    @ApiModelProperty(notes = "Пользователь", readOnly = true)
    private UserDto user;

    @ApiModelProperty(notes = "Цена", readOnly = true)
    private Double price;

    @ApiModelProperty(notes = "С учителем", readOnly = true)
    private Boolean teacherSupport;
}
