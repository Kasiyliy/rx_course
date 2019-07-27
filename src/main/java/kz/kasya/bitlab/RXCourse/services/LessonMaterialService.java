package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.LessonMaterial;

import java.util.List;

public interface LessonMaterialService {
    List<LessonMaterial> findAll();
    LessonMaterial findById(Long id) throws ServiceException;
    LessonMaterial save(LessonMaterial lessonMaterial) throws ServiceException;
    LessonMaterial update(LessonMaterial lessonMaterial) throws ServiceException;
    void delete(LessonMaterial lessonMaterial) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
    List<LessonMaterial> findByLessonId(Long id) throws ServiceException;
}
