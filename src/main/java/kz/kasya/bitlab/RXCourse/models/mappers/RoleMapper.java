package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.RoleDto;
import kz.kasya.bitlab.RXCourse.models.entities.Role;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class RoleMapper extends AbstractModelMapper<Role, RoleDto> {

    private ModelMapper modelMapper;

    @Autowired
    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto toDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public Role toEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }

    @Override
    public List<RoleDto> toDtoList(List<Role> roles) {
        return roles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> toEntityList(List<RoleDto> roleDtos) {
        return roleDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
