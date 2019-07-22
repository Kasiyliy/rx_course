package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Homework;
import kz.kasya.bitlab.RXCourse.repositories.HomeworkRepository;
import kz.kasya.bitlab.RXCourse.services.HomeworkService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HomeworkServiceImpl implements HomeworkService{
    private HomeworkRepository homeworkRepository;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public List<Homework> findAll() {
        return homeworkRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Homework findById(Long id) throws ServiceException {
        Optional<Homework> courseOptional = homeworkRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("lesson not found")
                .build());
    }

    @Override
    public Homework save(Homework homework) throws ServiceException {
        if(homework.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        return  homeworkRepository.save(homework);
    }

    @Override
    public Homework update(Homework homework) throws ServiceException {
        if(homework.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return homeworkRepository.save(homework);
    }

    @Override
    public void delete(Homework homework) throws ServiceException {
        if(homework.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        homework = findById(homework.getId());
        homework.setDeletedAt(new Date());
        homeworkRepository.save(homework);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Homework homework = findById(id);
        homework.setDeletedAt(new Date());
        homeworkRepository.save(homework);
    }

}
