package kz.kasya.bitlab.RXCourse.services;


import kz.kasya.bitlab.RXCourse.models.entities.Image;

/**
 * @author Assylkhan
 * on 28.02.2019
 * @project realq
 */
public interface ImageService {


    Image getById(Long id);

    Image getByImagePath(String path);

    boolean add(Image job);

    boolean update(Image job, String oldImage);

    boolean realDelete(Image job);

    boolean delete(Image job);

}
