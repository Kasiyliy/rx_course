package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.models.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}
