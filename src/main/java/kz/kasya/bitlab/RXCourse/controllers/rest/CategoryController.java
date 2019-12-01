package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.CategoryDto;
import kz.kasya.bitlab.RXCourse.models.entities.Category;
import kz.kasya.bitlab.RXCourse.models.mappers.CategoryMapper;
import kz.kasya.bitlab.RXCourse.models.responses.CategoryResponse;
import kz.kasya.bitlab.RXCourse.services.CategoryService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController extends BaseController {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryResponse> categoryResponses = categoryList.stream().map((e) ->
                CategoryResponse.builder()
                        .parentCategoryId(e.getParentCategory() != null ? e.getParentCategory().getId() : null)
                        .name(e.getName())
                        .parentCategory(e.getParentCategory())
                        .id(e.getId())
                        .build())
                .collect(Collectors.toList());
        return buildResponse(categoryResponses, HttpStatus.OK);
    }

    @GetMapping("/children/{id}")
    @ApiOperation("Получение по ID")
    public ResponseEntity<?> getAllByParentCategoryId(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(categoryMapper.toDtoList(categoryService.findAllByParentCategoryId(id)), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(categoryMapper.toDto(categoryService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Создание категории")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> add(@RequestBody CategoryDto categoryDto) throws ServiceException {
        Category category = categoryMapper.toEntity(categoryDto);
        category = categoryService.save(category);
        return buildResponse(categoryMapper.toDto(category), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException {
        categoryService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<?> delete(@RequestBody CategoryDto categoryDto) throws ServiceException {
        categoryService.delete(categoryMapper.toEntity(categoryDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody CategoryDto categoryDto) throws ServiceException {
        Category category = categoryService.update(categoryMapper.toEntity(categoryDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(categoryMapper.toDto(category))
                .build(), HttpStatus.OK);
    }
}
