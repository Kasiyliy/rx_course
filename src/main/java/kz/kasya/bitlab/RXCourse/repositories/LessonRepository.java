package kz.kasya.bitlab.RXCourse.repositories;

import kz.kasya.bitlab.RXCourse.models.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findAllByDeletedAtIsNull();

    List<Lesson> findAllByCourseIdAndDeletedAtIsNullOrderByOrderAsc(Long id);
}
