package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель  результатов теста")
public class TestResultDto extends BaseDto {

    @ApiModelProperty(notes = "Относящийся пользователь", readOnly = true)
    private UserDto user;

    @ApiModelProperty(notes = "Относящийся тест", readOnly = true)
    private TestDto test;

    @ApiModelProperty(notes = "Результат теста", readOnly = true)
    private Integer result;
}
