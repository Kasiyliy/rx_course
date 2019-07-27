package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.LessonMaterialDto;
import kz.kasya.bitlab.RXCourse.models.entities.LessonMaterial;
import kz.kasya.bitlab.RXCourse.models.mappers.LessonMaterialMapper;
import kz.kasya.bitlab.RXCourse.services.LessonMaterialService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessonMaterials")
public class LessonMaterialController extends BaseController {
    private LessonMaterialService lessonMaterialService;
    private LessonMaterialMapper lessonMaterialMapper;

    @Autowired
    public LessonMaterialController(LessonMaterialService lessonMaterialService, LessonMaterialMapper lessonMaterialMapper) {
        this.lessonMaterialService = lessonMaterialService;
        this.lessonMaterialMapper = lessonMaterialMapper;
    }

    @GetMapping
    @ApiOperation("Получение всех lessonMaterials в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(lessonMaterialMapper.toDtoList(lessonMaterialService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение lessonMaterial по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(lessonMaterialMapper.toDto(lessonMaterialService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление lessonMaterial")
    public ResponseEntity<?> add(@RequestBody LessonMaterialDto lessonMaterialDto) throws ServiceException {
        LessonMaterial lessonMaterial = lessonMaterialMapper.toEntity(lessonMaterialDto);
        return buildResponse(lessonMaterialMapper.toDto(lessonMaterialService.save(lessonMaterial)), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody LessonMaterialDto lessonMaterialDto) throws ServiceException {
        LessonMaterial lessonMaterial = lessonMaterialService.update(lessonMaterialMapper.toEntity(lessonMaterialDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(lessonMaterialMapper.toDto(lessonMaterial))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("Удаление lessonMaterial")
    public ResponseEntity<?> delete(@RequestBody LessonMaterialDto lessonMaterialDto) throws ServiceException{
        lessonMaterialService.delete(lessonMaterialMapper.toEntity(lessonMaterialDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление lessonMaterial по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException{
        lessonMaterialService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @GetMapping("lesson/{id}")
    @ApiOperation("Материалы по уроку")
    public ResponseEntity<?> materialsByLesson(@PathVariable Long id) throws  ServiceException {
        return buildResponse(lessonMaterialMapper.toDtoList(lessonMaterialService.findByLessonId(id)), HttpStatus.OK);
    }
}
