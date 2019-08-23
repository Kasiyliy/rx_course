package kz.kasya.bitlab.RXCourse.repositories;

import kz.kasya.bitlab.RXCourse.models.entities.MaterialType;
import kz.kasya.bitlab.RXCourse.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialTypeRepository extends JpaRepository<MaterialType, Long>{

    List<MaterialType> findAllByDeletedAtIsNull();

    MaterialType findByDeletedAtIsNullAndId(Long id);

}
