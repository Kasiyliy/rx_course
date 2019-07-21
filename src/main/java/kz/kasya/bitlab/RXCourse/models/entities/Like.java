package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_likes",
        initialValue = 1,
        allocationSize = 1
)
public class Like extends AuditModel{
    @Column(name = "scale")
    @NotNull(message = "scale is required")
    private Integer scale;

    @ManyToOne
    @NotNull(message = "user is required")
    private User user;

    @ManyToOne
    @NotNull(message = "course is required")
    private Course course;
}
