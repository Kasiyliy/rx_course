package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.LessonMaterialDto;
import kz.kasya.bitlab.RXCourse.models.entities.LessonMaterial;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LessonMaterialMapper extends AbstractModelMapper<LessonMaterial, LessonMaterialDto> {
    private ModelMapper modelMapper;
    private LessonMapper lessonMapper;

    @Autowired
    public LessonMaterialMapper(ModelMapper modelMapper, LessonMapper lessonMapper) {
        this.modelMapper = modelMapper;
        this.lessonMapper = lessonMapper;
    }

    @Override
    public LessonMaterialDto toDto(LessonMaterial lessonMaterial) {
        LessonMaterialDto lessonMaterialDto = modelMapper.map(lessonMaterial, LessonMaterialDto.class);
        if (lessonMaterial.getLesson() != null) {
            lessonMaterialDto.setLesson(lessonMapper.toDto(lessonMaterial.getLesson()));
        }
        return lessonMaterialDto;    }

    @Override
    public LessonMaterial toEntity(LessonMaterialDto lessonMaterialDto) {
        LessonMaterial lessonMaterial = modelMapper.map(lessonMaterialDto, LessonMaterial.class);
        if (lessonMaterialDto.getLesson() != null) {
            lessonMaterial.setLesson(lessonMapper.toEntity(lessonMaterialDto.getLesson()));
        }
        return lessonMaterial;
    }

    @Override
    public List<LessonMaterialDto> toDtoList(List<LessonMaterial> lessonMaterials) {
        return lessonMaterials.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LessonMaterial> toEntityList(List<LessonMaterialDto> lessonMaterialDtos) {
        return lessonMaterialDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
