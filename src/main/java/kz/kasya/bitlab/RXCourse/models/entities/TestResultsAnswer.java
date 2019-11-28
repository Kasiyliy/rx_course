package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "test_result_answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_test_results_answers",
        initialValue = 1,
        allocationSize = 1
)
public class TestResultsAnswer extends AuditModel {
    @ManyToOne
    private TestResult testResult;

    @ManyToOne
    private Question question;

    @ManyToOne
    private QuestionOption questionOption;

    @Column(name = "answer")
    @NotNull(message = "answer is required")
    private boolean answer;
}
