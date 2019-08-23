package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_lessons",
        initialValue = 1,
        allocationSize = 1
)
public class Lesson extends AuditModel {

    @Column(name = "name")
    @NotNull(message = "name is required")
    private String name;

    @Column(name = "order_number")
    @NotNull(message = "order is required")
    private Integer order;

    @ManyToOne
    @NotNull(message = "course is required")
    private Course course;
}
