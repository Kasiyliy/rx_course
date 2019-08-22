package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Category;
import kz.kasya.bitlab.RXCourse.repositories.CategoryRepository;
import kz.kasya.bitlab.RXCourse.services.CategoryService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<Category> findAllByParentCategoryId(Long id) {
        return categoryRepository.findAllByDeletedAtIsNullAndParentCategory_Id(id);
    }

    @Override
    public Category findById(Long id) throws ServiceException {
        return categoryRepository.findOneByDeletedAtIsNullAndId(id).orElseThrow(ServiceException::new);
    }

    @Override
    public Category save(Category category) throws ServiceException {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) throws ServiceException {
        if (category.getId() != null && categoryRepository.findOneByDeletedAtIsNullAndId(category.getId()).orElse(null) != null) {
            categoryRepository.save(category);
        } else {
            throw new ServiceException("There is no category with id " + category.getId(), ErrorCode.RESOURCE_NOT_FOUND);
        }

        return category;
    }

    @Override
    public void delete(Category category) throws ServiceException {
        if (category.getId() != null && categoryRepository.findOneByDeletedAtIsNullAndId(category.getId()).orElse(null) != null) {
            if(findAllByParentCategoryId(category.getId()).size() > 0){
                throw new ServiceException("There category with id " + category.getId() + " has children", ErrorCode.NOT_ALLOWED);
            }

            category.setDeletedAt(new Date());
            categoryRepository.save(category);
        } else {
            throw new ServiceException("There is no category with id " + category.getId(), ErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        Category category = categoryRepository.findOneByDeletedAtIsNullAndId(id).orElse(null);

        if (category != null) {
            if(findAllByParentCategoryId(category.getId()).size() > 0){
                throw new ServiceException("There category with id " + category.getId() + " has children", ErrorCode.NOT_ALLOWED);
            }
            category.setDeletedAt(new Date());
            categoryRepository.save(category);
        } else {
            throw new ServiceException("There is no category with id " + id, ErrorCode.RESOURCE_NOT_FOUND);
        }
    }
}
