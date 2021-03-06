package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель роли")
public class RoleDto extends BaseDto {

    @ApiModelProperty(notes = "Наименование", readOnly = true)
    private String name;

}
