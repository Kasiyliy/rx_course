package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_users",
        initialValue = 1,
        allocationSize = 1
)
public class User extends AuditModel {

    @Column(name = "first_name")
    @NotNull(message = "first_name is required")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "last_name is required")
    private String lastName;

    @Column(name = "phone_number")
    @NotNull(message = "phone_number is required")
    private String phoneNumber;

    @Column(unique = true, name = "login")
    @NotNull(message = "login is required")
    private String login;

    @NotNull(message = "password is required")
    private String password;

    @ManyToOne
    @NotNull(message = "role is required")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Role role;

    @Transient
    public boolean isAdmin() {
        return Objects.nonNull(this.role) && Objects.equals(this.role.getId(), Role.ROLE_ADMIN_ID);
    }


    @Transient
    public boolean isStudent() {
        return Objects.nonNull(this.role) && Objects.equals(this.role.getId(), Role.ROLE_STUDENT_ID);
    }

    @Transient
    public boolean isTeacher() {
        return Objects.nonNull(this.role) && Objects.equals(this.role.getId(), Role.ROLE_TEACHER_ID);
    }

    @Transient
    public boolean isManager() {
        return Objects.nonNull(this.role) && Objects.equals(this.role.getId(), Role.ROLE_MANAGER_ID);
    }
}
