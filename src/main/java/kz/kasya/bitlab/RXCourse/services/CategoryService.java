package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Long id) throws ServiceException;

    Category save(Category category) throws ServiceException;

    Category update(Category category) throws ServiceException;

    void delete(Category category) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<Category> findAllByParentCategoryId(Long id);

}
