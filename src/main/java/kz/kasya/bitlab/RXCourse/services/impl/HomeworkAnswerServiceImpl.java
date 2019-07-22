package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Homework;
import kz.kasya.bitlab.RXCourse.models.entities.HomeworkAnswer;
import kz.kasya.bitlab.RXCourse.repositories.HomeworkAnswerRepository;
import kz.kasya.bitlab.RXCourse.services.HomeworkAnswerService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class HomeworkAnswerServiceImpl implements HomeworkAnswerService {
    private HomeworkAnswerRepository homeworkAnswerRepository;

    @Autowired
    public HomeworkAnswerServiceImpl(HomeworkAnswerRepository homeworkAnswerRepository) {
        this.homeworkAnswerRepository = homeworkAnswerRepository;
    }

    @Override
    public List<HomeworkAnswer> findAll() {
        return homeworkAnswerRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public HomeworkAnswer findById(Long id) throws ServiceException {
        Optional<HomeworkAnswer> courseOptional = homeworkAnswerRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("lesson not found")
                .build());
    }

    @Override
    public HomeworkAnswer save(HomeworkAnswer homeworkAnswer) throws ServiceException {
        if(homeworkAnswer.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        return  homeworkAnswerRepository.save(homeworkAnswer);
    }

    @Override
    public HomeworkAnswer update(HomeworkAnswer homeworkAnswer) throws ServiceException {
        if(homeworkAnswer.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return homeworkAnswerRepository.save(homeworkAnswer);
    }

    @Override
    public void delete(HomeworkAnswer homeworkAnswer) throws ServiceException {
        if(homeworkAnswer.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        homeworkAnswer = findById(homeworkAnswer.getId());
        homeworkAnswer.setDeletedAt(new Date());
        homeworkAnswerRepository.save(homeworkAnswer);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        HomeworkAnswer homeworkAnswer = findById(id);
        homeworkAnswer.setDeletedAt(new Date());
        homeworkAnswerRepository.save(homeworkAnswer);
    }
}
