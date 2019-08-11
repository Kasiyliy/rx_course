package kz.kasya.bitlab.RXCourse.models.entities;


import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import kz.kasya.bitlab.RXCourse.models.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_courses",
        initialValue = 1,
        allocationSize = 1
)
public class Course extends AuditModel {

    @ManyToOne
    @NotNull(message = "user is required")
    private User user;

    @Column(name = "course_name")
    @NotNull(message = "course name is required")
    private String courseName;

    @Column(name = "description")
    @NotNull(message = "description is required")
    private String description;

    @Column(name = "price")
    @NotNull(message = "price is required")
    private Double price;

    @Column(name = "grade")
    @NotNull(message = "grade is required")
    private Integer grade;

    @Column(name = "teacher_support")
    @NotNull(message = "teacher support is required")
    private Boolean teacherSupport;

    @Column(name = "image")
    @NotNull(message = "image is required")
    private String image;

    @ManyToOne
    @NotNull(message = "category is required")
    private Category category;

}
