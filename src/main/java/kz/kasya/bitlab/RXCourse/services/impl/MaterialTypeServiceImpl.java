package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.MaterialType;
import kz.kasya.bitlab.RXCourse.repositories.MaterialTypeRepository;
import kz.kasya.bitlab.RXCourse.services.MaterialTypeService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Assylkhan
 * on 23.08.2019
 * @project AttendMe
 */
@Service
public class MaterialTypeServiceImpl implements MaterialTypeService {

    private MaterialTypeRepository materialTypeRepository;

    @Autowired
    public MaterialTypeServiceImpl(MaterialTypeRepository materialTypeRepository) {
        this.materialTypeRepository = materialTypeRepository;
    }

    @Override
    public MaterialType findById(Long id) throws ServiceException {
        return materialTypeRepository.getOne(id);
    }

    @Override
    public List<MaterialType> findAll() {
        return materialTypeRepository.findAll();
    }

    @Override
    public List<MaterialType> findAllWithDeleted() {
        return materialTypeRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public MaterialType update(MaterialType materialType) throws ServiceException {
        if (materialType.getId() == null || !materialTypeRepository.findById(materialType.getId()).isPresent()) {
            throw new ServiceException("there is no such material type", ErrorCode.RESOURCE_NOT_FOUND);
        }
        return materialTypeRepository.save(materialType);
    }

    @Override
    public MaterialType save(MaterialType materialType) throws ServiceException {
        if (materialType.getId() != null || materialTypeRepository.findById(materialType.getId()).isPresent()) {
            throw new ServiceException("such material type already exists", ErrorCode.ALREADY_EXISTS);
        }

        return materialTypeRepository.save(materialType);
    }

    @Override
    public void delete(MaterialType materialType) throws ServiceException {
        MaterialType materialTypeToDelete = findById(materialType.getId());
        materialTypeToDelete.setDeletedAt(new Date());
        materialTypeRepository.save(materialTypeToDelete);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        MaterialType materialType = findById(id);
        materialType.setDeletedAt(new Date());
        materialTypeRepository.save(materialType);
    }
}
