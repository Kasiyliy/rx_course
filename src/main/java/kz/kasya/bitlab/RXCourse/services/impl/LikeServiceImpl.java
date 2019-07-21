package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Lesson;
import kz.kasya.bitlab.RXCourse.models.entities.Like;
import kz.kasya.bitlab.RXCourse.repositories.LikeRepository;
import kz.kasya.bitlab.RXCourse.services.LikeService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService{
    private LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public List<Like> findAll() {
        return likeRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Like findById(Long id) throws ServiceException {
        Optional<Like> courseOptional = likeRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("lesson not found")
                .build());
    }

    @Override
    public Like save(Like like) throws ServiceException {
        if(like.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        return  likeRepository.save(like);
    }

    @Override
    public Like update(Like like) throws ServiceException {
        if(like.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return likeRepository.save(like);
    }

    @Override
    public void delete(Like like) throws ServiceException {
        if(like.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        like = findById(like.getId());
        like.setDeletedAt(new Date());
        likeRepository.save(like);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Like like = findById(id);
        like.setDeletedAt(new Date());
        likeRepository.save(like);
    }
}
