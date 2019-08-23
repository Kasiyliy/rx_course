package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель типа материала")
public class MaterialTypeDto extends BaseDto {

    @ApiModelProperty(notes = "Наименование", readOnly = true)
    private String name;

    @ApiModelProperty(notes = "Значение", readOnly = true)
    private String value;

}
