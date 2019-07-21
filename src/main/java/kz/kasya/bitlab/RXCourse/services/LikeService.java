package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Lesson;
import kz.kasya.bitlab.RXCourse.models.entities.Like;

import java.util.List;

public interface LikeService {
    List<Like> findAll();
    Like findById(Long id) throws ServiceException;
    Like save(Like like) throws ServiceException;
    Like update(Like like) throws ServiceException;
    void delete(Like like) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
}
