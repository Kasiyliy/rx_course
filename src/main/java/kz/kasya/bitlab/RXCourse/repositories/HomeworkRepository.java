package kz.kasya.bitlab.RXCourse.repositories;

import kz.kasya.bitlab.RXCourse.models.entities.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,Long> {
    List<Homework> findAllByDeletedAtIsNull();
}
