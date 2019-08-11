package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_courses",
        initialValue = 1,
        allocationSize = 1
)
public class Category extends AuditModel {


    @Column(name = "name")
    @NotNull(message = "category name is required")
    private String name;

    @ManyToOne
    private Category parentCategory;

}