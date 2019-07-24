package kz.kasya.bitlab.RXCourse.repositories;

import kz.kasya.bitlab.RXCourse.models.entities.TestResultsAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestResultsAnswerRepository extends JpaRepository<TestResultsAnswer,Long> {
    List<TestResultsAnswer> findAllByDeletedAtIsNull();
}
