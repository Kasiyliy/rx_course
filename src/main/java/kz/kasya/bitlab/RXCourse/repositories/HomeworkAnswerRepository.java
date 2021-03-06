package kz.kasya.bitlab.RXCourse.repositories;

import kz.kasya.bitlab.RXCourse.models.entities.HomeworkAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkAnswerRepository extends JpaRepository<HomeworkAnswer,Long> {
    List<HomeworkAnswer> findAllByDeletedAtIsNull();
}
