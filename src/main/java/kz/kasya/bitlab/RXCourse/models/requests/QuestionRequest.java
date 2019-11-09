package kz.kasya.bitlab.RXCourse.models.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionRequest {
    private String question;
    private String image;
    private Integer score;
    private List<OptionRequest> options;
}
