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
    private String questionText;
    private String image;
    private Integer score;
    private List<QuestionOptionResponse> questionOptions;

    public QuestionResponse(Long id, String questionText, String image, Integer score, List<QuestionOptionResponse> questionOptions) {
        this.id = id;
        this.questionText = questionText;
        this.image = image;
        this.score = score;
        this.questionOptions = questionOptions;
    }
}
