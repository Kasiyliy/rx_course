package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "question_options")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_question_options",
        initialValue = 1,
        allocationSize = 1
)
public class QuestionOption extends AuditModel {

    @Column(name = "answer")
    @NotNull(message = "answer is required")
    private String answer;

    @ManyToOne
    private Question question;

    @Column(name = "right_answer")
    @NotNull(message = "answer is required")
    private Boolean rightAnswer;
}
