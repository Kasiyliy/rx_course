package kz.kasya.bitlab.RXCourse.repositories;

import kz.kasya.bitlab.RXCourse.models.entities.TestResultsAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultsAnswerRepository extends JpaRepository<TestResultsAnswer,Long> {
    List<TestResultsAnswer> findAllByDeletedAtIsNull();
}
