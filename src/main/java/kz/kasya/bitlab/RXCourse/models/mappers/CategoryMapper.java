package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.CategoryDto;
import kz.kasya.bitlab.RXCourse.models.entities.Category;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper extends AbstractModelMapper<Category, CategoryDto>{

    private ModelMapper modelMapper;

    @Autowired
    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto toDto(Category Category) {
        CategoryDto CategoryDto = modelMapper.map(Category, CategoryDto.class);
        return CategoryDto;
    }

    @Override
    public Category toEntity(CategoryDto CategoryDto) {
        Category Category = modelMapper.map(CategoryDto, Category.class);
        return Category;
    }

    @Override
    public List<CategoryDto> toDtoList(List<Category> Categorys) {
        return Categorys.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> toEntityList(List<CategoryDto> CategoryDtos) {
        return CategoryDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
