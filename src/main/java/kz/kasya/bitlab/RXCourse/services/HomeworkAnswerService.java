package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.HomeworkAnswer;

import java.util.List;

public interface HomeworkAnswerService {
    List<HomeworkAnswer> findAll();
    HomeworkAnswer findById(Long id) throws ServiceException;
    HomeworkAnswer save(HomeworkAnswer homeworkAnswer) throws ServiceException;
    HomeworkAnswer update(HomeworkAnswer homeworkAnswer) throws ServiceException;
    void delete(HomeworkAnswer homeworkAnswer) throws ServiceException ;
    void deleteById(Long id) throws ServiceException;
}
