package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "homeworks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_homeworks",
        initialValue = 1,
        allocationSize = 1
)
public class Homework extends AuditModel{

    @Column(name = "task")
    @NotNull(message = "task is required")
    private String task;

    @Column(name = "url")
    @NotNull(message = "url is required")
    private String url;

    @ManyToOne
    @NotNull(message = "lesson is required")
    private Lesson lesson;

}
