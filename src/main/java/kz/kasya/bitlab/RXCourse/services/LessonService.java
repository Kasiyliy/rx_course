package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.models.entities.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> findAll();
}
