package kz.kasya.bitlab.RXCourse.models.dtos;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель курсы")
public class CourseDto extends BaseDto{

    @ApiModelProperty(notes = "Наименование", readOnly = true)
    private String courseName;

    @ApiModelProperty(notes = "Описание курса", readOnly = true)
    private String description;

    @ApiModelProperty(notes = "Цена курса", readOnly = true)
    private Double price;

    @ApiModelProperty(notes = "Номер класса курса", readOnly = true)
    private Integer grade;

    @ApiModelProperty(notes = "Поддержка преподователя", readOnly = true)
    private Boolean teacherSupport;

    @ApiModelProperty(notes = "Пользователь", readOnly = true)
    private UserDto user;


    @ApiModelProperty(notes = "Фото, картинка", readOnly = true)
    private String image;
}
