package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Course;
import kz.kasya.bitlab.RXCourse.models.entities.Lesson;
import kz.kasya.bitlab.RXCourse.repositories.LessonRepository;
import kz.kasya.bitlab.RXCourse.services.LessonService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService{

    private LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> findAll(){
        return lessonRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Lesson findById(Long id) throws ServiceException {
        Optional<Lesson> courseOptional = lessonRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("lesson not found")
                .build());
    }

    @Override
    public Lesson save(Lesson lesson) throws ServiceException {
        if(lesson.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        return  lessonRepository.save(lesson);
    }


    @Override
    public Lesson update(Lesson lesson) throws ServiceException {
        if(lesson.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return lessonRepository.save(lesson);
    }

    @Override
    public void delete(Lesson lesson) throws ServiceException {
        if(lesson.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        lesson = findById(lesson.getId());
        lesson.setDeletedAt(new Date());
        lessonRepository.save(lesson);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Lesson lesson = findById(id);
        lesson.setDeletedAt(new Date());
        lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> findByCourseId(Long id) throws ServiceException {
        if(id == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        return lessonRepository.findAllByCourseId(id);

    }

}
