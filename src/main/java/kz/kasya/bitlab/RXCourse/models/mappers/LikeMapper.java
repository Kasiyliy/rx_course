package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.LikeDto;
import kz.kasya.bitlab.RXCourse.models.entities.Like;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LikeMapper extends AbstractModelMapper<Like, LikeDto> {
    private ModelMapper modelMapper;
    private UserMapper userMapper;
    private CourseMapper courseMapper;

    @Autowired
    public LikeMapper(ModelMapper modelMapper, UserMapper userMapper, CourseMapper courseMapper) {
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
    }

    @Override
    public LikeDto toDto(Like like) {
        LikeDto likeDto = modelMapper.map(like, LikeDto.class);
        if (like.getUser() != null && like.getCourse() != null){
            likeDto.setUser(userMapper.toDto(like.getUser()));
            likeDto.setCourse(courseMapper.toDto(like.getCourse()));
        }
        return likeDto;
    }

    @Override
    public Like toEntity(LikeDto likeDto) {
        Like like = modelMapper.map(likeDto, Like.class);
        if (likeDto.getUser() != null && likeDto.getCourse() != null){
            like.setUser(userMapper.toEntity(likeDto.getUser()));
            like.setCourse(courseMapper.toEntity(likeDto.getCourse()));
        }
        return like;
    }

    @Override
    public List<LikeDto> toDtoList(List<Like> likes) {
        return likes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Like> toEntityList(List<LikeDto> likeDtos) {
        return likeDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
