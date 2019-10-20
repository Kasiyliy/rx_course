package kz.kasya.bitlab.RXCourse.models.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class QuestionResponse {
    private Long id;
    private String question;
    private String image;
    private Integer score;
    private List<QuestionOptionResponse> questionOptions;

    public QuestionResponse(Long id, String question, String image, Integer score, List<QuestionOptionResponse> questionOptions) {
        this.id = id;
        this.question = question;
        this.image = image;
        this.score = score;
        this.questionOptions = questionOptions;
    }
}
