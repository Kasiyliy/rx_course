package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_questions",
        initialValue = 1,
        allocationSize = 1
)
public class Question extends AuditModel {
    @Column(name = "question")
    private String question;

    @Column(name = "image")
    private String image;

    @Column(name = "score")
    @NotNull(message = "score is required")
    private Integer score;

    @ManyToOne
    private Test test;
}
