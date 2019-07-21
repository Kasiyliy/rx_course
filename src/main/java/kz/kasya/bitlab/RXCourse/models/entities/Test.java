package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_tests",
        initialValue = 1,
        allocationSize = 1
)
public class Test extends AuditModel{

    @Column(name = "title")
    @NotNull(message = "title is required")
    private String title;

    @Column(name="description")
    @NotNull(message = "description is required")
    private String description;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Lesson lesson;

}
