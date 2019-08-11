package kz.kasya.bitlab.RXCourse.models.responses;

import kz.kasya.bitlab.RXCourse.models.dtos.CourseDto;
import kz.kasya.bitlab.RXCourse.models.dtos.LessonDto;
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
    private String testName;
    private String description;
    private LessonDto lessonDto;

    private List<QuestionResponse> questions;

    public TestResponse(Long id, String testName, String description, LessonDto lessonDto, List<QuestionResponse> questions) {
        this.id = id;
        this.testName = testName;
        this.description = description;
        this.lessonDto = lessonDto;
        this.questions = questions;
    }
}
