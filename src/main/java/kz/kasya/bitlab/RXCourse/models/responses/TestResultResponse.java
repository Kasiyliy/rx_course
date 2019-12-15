package kz.kasya.bitlab.RXCourse.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestResultResponse {
    private Integer result;
    private Integer allScore;
}
