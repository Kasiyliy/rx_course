package kz.kasya.bitlab.RXCourse.models.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Assylkhan
 * on 28.11.2019
 * @project AttendMe
 */
@Data
public class UserRoleUpdateRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long roleId;

}
