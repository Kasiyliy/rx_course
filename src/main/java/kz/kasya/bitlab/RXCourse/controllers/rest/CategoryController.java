package kz.kasya.bitlab.RXCourse.controllers.rest;

import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.models.entities.Category;
import kz.kasya.bitlab.RXCourse.models.mappers.CategoryMapper;
import kz.kasya.bitlab.RXCourse.models.responses.CategoryResponse;
import kz.kasya.bitlab.RXCourse.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends BaseController {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Category> categoryList = categoryService.findAll();
        List<CategoryResponse> categoryResponses = categoryList.stream().map((e) ->
        CategoryResponse.builder()
                .parentCategoryId(e.getParentCategory() != null ? e.getParentCategory().getId() : null)
                .name(e.getName())
                .id(e.getId())
                .build())
                    .collect(Collectors.toList());
        return buildResponse(categoryResponses, HttpStatus.OK);
    }
}
