package kz.kasya.bitlab.RXCourse.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kasya.bitlab.RXCourse.models.entities.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class CategoryResponse {

    private Long id;
    private String name;

    private Category parentCategory;
    private Long parentCategoryId;



    public CategoryResponse(Long id, String name, Category parentCategory, Long parentCategoryId) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.parentCategoryId = parentCategoryId;
    }
}
