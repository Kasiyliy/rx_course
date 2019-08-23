package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.MaterialType;

import java.util.List;


public interface MaterialTypeService {

    MaterialType findById(Long id) throws ServiceException;
    List<MaterialType> findAll();
    List<MaterialType> findAllWithDeleted();
    MaterialType update(MaterialType materialType) throws ServiceException ;
    MaterialType save(MaterialType materialType) throws ServiceException ;
    void delete(MaterialType materialType) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}
