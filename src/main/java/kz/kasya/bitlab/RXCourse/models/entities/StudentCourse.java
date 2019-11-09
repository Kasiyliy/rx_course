package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student_courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_student_courses",
        initialValue = 1,
        allocationSize = 1
)
public class StudentCourse extends AuditModel {
    @ManyToOne
    @NotNull(message = "user is required")
    private User user;

    @ManyToOne
    @NotNull(message = "course is required")
    private Course course;

    private Double price;
    private Boolean teacherSupport;
}
