package kz.kasya.bitlab.RXCourse.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestRequestQuestions {
    private Long id;
    private List<QuestionRequest> questionRequests;
}
