package kz.kasya.bitlab.RXCourse.repositories;

import kz.kasya.bitlab.RXCourse.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByDeletedAtIsNull();

    Optional<Category> findOneByDeletedAtIsNullAndId(Long id);

    List<Category> findAllByDeletedAtIsNullAndParentCategory_Id(Long id);

}
