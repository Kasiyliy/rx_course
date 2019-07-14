package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    @ApiModelProperty(notes = "Имя", readOnly = true)
    private String firstName;

    @ApiModelProperty(notes = "Фамилия", readOnly = true)
    private String lastName;

    @ApiModelProperty(notes = "Мобильный номер", readOnly = true)
    private String phoneNumber;

    @ApiModelProperty(notes = "Логин", readOnly = true)
    private String login;

    @ApiModelProperty(notes = "Пароль", readOnly = true)
    private String password;

    @ApiModelProperty(notes = "Роль", readOnly = true)
    private RoleDto role;

}
