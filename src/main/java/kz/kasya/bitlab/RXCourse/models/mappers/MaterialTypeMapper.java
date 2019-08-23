package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.MaterialTypeDto;
import kz.kasya.bitlab.RXCourse.models.entities.MaterialType;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class MaterialTypeMapper extends AbstractModelMapper<MaterialType, MaterialTypeDto> {

    private ModelMapper modelMapper;

    @Autowired
    public MaterialTypeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MaterialTypeDto toDto(MaterialType materialType) {
        return modelMapper.map(materialType, MaterialTypeDto.class);
    }

    @Override
    public MaterialType toEntity(MaterialTypeDto materialTypeDto) {
        return modelMapper.map(materialTypeDto, MaterialType.class);
    }

    @Override
    public List<MaterialTypeDto> toDtoList(List<MaterialType> materialTypes) {
        return materialTypes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialType> toEntityList(List<MaterialTypeDto> materialTypeDtos) {
        return materialTypeDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
