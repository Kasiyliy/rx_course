package kz.kasya.bitlab.RXCourse.repositories;

import kz.kasya.bitlab.RXCourse.models.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Assylkhan
 * on 28.02.2019
 * @project realq
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findImageByPath(String path);

    List<Image> findAllByDeletedAtIsNull();

}