package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.LessonMaterial;
import kz.kasya.bitlab.RXCourse.repositories.LessonMaterialRepository;
import kz.kasya.bitlab.RXCourse.services.LessonMaterialService;
import kz.kasya.bitlab.RXCourse.services.LessonService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LessonMaterialServiceImpl implements LessonMaterialService{
    private LessonMaterialRepository lessonMaterialRepository;

    @Autowired
    public LessonMaterialServiceImpl(LessonMaterialRepository lessonMaterialRepository) {
        this.lessonMaterialRepository = lessonMaterialRepository;
    }

    @Override
    public List<LessonMaterial> findAll() {
        return lessonMaterialRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public LessonMaterial findById(Long id) throws ServiceException {
        Optional<LessonMaterial> courseOptional = lessonMaterialRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("lesson not found")
                .build());
    }

    @Override
    public LessonMaterial save(LessonMaterial lessonMaterial) throws ServiceException {
        if(lessonMaterial.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        return  lessonMaterialRepository.save(lessonMaterial);
    }

    @Override
    public LessonMaterial update(LessonMaterial lessonMaterial) throws ServiceException {
        if(lessonMaterial.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return lessonMaterialRepository.save(lessonMaterial);
    }

    @Override
    public void delete(LessonMaterial lessonMaterial) throws ServiceException {
        if(lessonMaterial.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        lessonMaterial = findById(lessonMaterial.getId());
        lessonMaterial.setDeletedAt(new Date());
        lessonMaterialRepository.save(lessonMaterial);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        LessonMaterial lessonMaterial = findById(id);
        lessonMaterial.setDeletedAt(new Date());
        lessonMaterialRepository.save(lessonMaterial);
    }

    @Override
    public List<LessonMaterial> findByLessonId(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        return lessonMaterialRepository.findAllByLessonId(id);
    }
}
