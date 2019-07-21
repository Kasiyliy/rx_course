package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель лайки")
public class LikeDto extends BaseDto{
    @ApiModelProperty(notes = "Шкала от 1 до 5(оценка курса)", readOnly = true)
    private Integer scale;

    @ApiModelProperty(notes = "Студент который поставил оценку", readOnly = true)
    private UserDto user;

    @ApiModelProperty(notes = "Курс к которому поставили оценку", readOnly = true)
    private CourseDto course;

}
