package kz.kasya.bitlab.RXCourse.models.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionOptionResponse {
    private Long id;
    private Long questionId;
    private String optionText;
    private Boolean rightAnswer;

    public QuestionOptionResponse() {
    }

    public QuestionOptionResponse(Long id, Long questionId, String optionText, Boolean rightAnswer) {
        this.id = id;
        this.questionId = questionId;
        this.optionText = optionText;
        this.rightAnswer = rightAnswer;
    }
}

