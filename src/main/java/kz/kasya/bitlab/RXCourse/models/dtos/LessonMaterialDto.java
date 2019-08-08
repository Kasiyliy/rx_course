package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Таблица которая описывает модель lessonMaterials")
public class LessonMaterialDto extends BaseDto{
    @ApiModelProperty(notes = "Тип файла(фото, видео, pdf)", readOnly = true)
    private String type;

    @ApiModelProperty(notes = "Ссылка к файлу", readOnly = true)
    private String url;

    @ApiModelProperty(notes = "Описание файла", readOnly = true)
    private String description;

    @ApiModelProperty(notes = "Урок к которому относится файл", readOnly = true)
    private LessonDto lesson;
}
