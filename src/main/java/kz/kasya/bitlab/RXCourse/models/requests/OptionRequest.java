package kz.kasya.bitlab.RXCourse.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionRequest {
    private Long id;
    private String optionText;
    private Boolean rightAnswer;
}
