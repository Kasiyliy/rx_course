package kz.kasya.bitlab.RXCourse.controllers.rest;

import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.mappers.MaterialTypeMapper;
import kz.kasya.bitlab.RXCourse.services.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/material_types")
public class MaterialTypeController extends BaseController {

    private MaterialTypeService materialTypeService;
    private MaterialTypeMapper materialTypeMapper;

    @Autowired
    public MaterialTypeController(MaterialTypeService materialTypeService, MaterialTypeMapper materialTypeMapper) {
        this.materialTypeService = materialTypeService;
        this.materialTypeMapper = materialTypeMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return buildResponse(materialTypeMapper.toDtoList(materialTypeService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(materialTypeMapper.toDto(materialTypeService.findById(id)), HttpStatus.OK);
    }

}
