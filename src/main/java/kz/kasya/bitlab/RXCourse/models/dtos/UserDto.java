package kz.kasya.bitlab.RXCourse.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    @ApiModelProperty(notes = "Имя", readOnly = true)
    @NotNull
    @NotEmpty
    private String firstName;

    @ApiModelProperty(notes = "Фамилия", readOnly = true)
    @NotNull
    @NotEmpty
    private String lastName;

    @ApiModelProperty(notes = "Мобильный номер", readOnly = true)
    @NotNull
    @NotEmpty
    private String phoneNumber;

    @ApiModelProperty(notes = "Логин", readOnly = true)
    @NotNull
    @NotEmpty
    private String login;

    @ApiModelProperty(notes = "Пароль", readOnly = true)
    @NotNull
    @NotEmpty
    private String password;

    @ApiModelProperty(notes = "Роль", readOnly = true)
    private RoleDto role;

}
