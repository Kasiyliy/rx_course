package kz.kasya.bitlab.RXCourse.repositories;

import kz.kasya.bitlab.RXCourse.models.entities.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {
    List<QuestionOption> findAllByDeletedAtIsNull();
    List<QuestionOption> findAllByDeletedAtNullAndQuestion_IdIn(List<Long> ids);
    List<QuestionOption> findAllByDeletedAtIsNullAndIdIn(List<Long> ids);
}
