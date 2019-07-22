package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "homework_answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_homework_answers",
        initialValue = 1,
        allocationSize = 1
)
public class HomeworkAnswer extends AuditModel {
    @Column(name = "answer")
    @NotNull(message = "answer is required")
    private String answer;

    @Column(name = "url")
    @NotNull(message = "url is required")
    private String url;

    @ManyToOne
    @NotNull(message = "homework is required")
    private Homework homework;

    @ManyToOne
    @NotNull(message = "user is required")
    private User user;
}
