package kz.kasya.bitlab.RXCourse.modules.file.repositories;

import kz.kasya.bitlab.RXCourse.modules.file.models.entities.FileResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Assylkhan
 * on 10.08.2019
 * @project AttendMe
 */
@Repository
public interface FileResourceRepository extends JpaRepository<FileResource, Long> {

    Optional<FileResource> getByFileName(String fileName);

}
