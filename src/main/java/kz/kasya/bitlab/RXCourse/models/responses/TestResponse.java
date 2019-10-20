package kz.kasya.bitlab.RXCourse.models.responses;

import kz.kasya.bitlab.RXCourse.models.dtos.CourseDto;
import kz.kasya.bitlab.RXCourse.models.dtos.LessonDto;
import kz.kasya.bitlab.RXCourse.models.entities.Course;
import kz.kasya.bitlab.RXCourse.models.entities.Lesson;
import kz.kasya.bitlab.RXCourse.models.entities.QuestionOption;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class TestResponse {
    private Long id;
    private String title;
    private String description;
    private Lesson lesson;
    private Course course;

    private List<QuestionResponse> questions;

    public TestResponse(Long id, String title, String description, Lesson lesson,  Course course, List<QuestionResponse> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lesson = lesson;
        this.questions = questions;
        this.course = course;
    }
}
