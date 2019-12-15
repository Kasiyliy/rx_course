package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "test_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_test_results",
        initialValue = 1,
        allocationSize = 1
)
public class TestResult extends AuditModel {
    @ManyToOne
    private User user;

    @ManyToOne
    private Test test;

    @Column(name = "result")
    @NotNull(message = "result is required")
    private Integer result;

    @Column(name = "all_score")
    private Integer allScore;
}
