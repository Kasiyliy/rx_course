package kz.kasya.bitlab.RXCourse.repositories;

import kz.kasya.bitlab.RXCourse.models.entities.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult,Long> {

}