package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.models.entities.Image;
import kz.kasya.bitlab.RXCourse.repositories.ImageRepository;
import kz.kasya.bitlab.RXCourse.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Assylkhan
 * on 9.08.2019
 * @project AttendMe
 */
@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image getById(Long id) {
        return imageRepository.getOne(id);
    }

    @Override
    public Image getByImagePath(String path) {
        return imageRepository.findImageByPath(path);
    }

    @Override
    public boolean add(Image image) {
        try {
            imageRepository.save(image);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Image image, String oldImage) {
        return false;
    }

    @Override
    public boolean realDelete(Image image) {
        return false;
    }

    @Override
    public boolean delete(Image image) {
        return false;
    }
}
