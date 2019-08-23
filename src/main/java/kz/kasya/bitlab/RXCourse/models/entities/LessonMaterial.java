package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lesson_materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_lesson_materials",
        initialValue = 1,
        allocationSize = 1
)
public class LessonMaterial extends AuditModel {

    @ManyToOne
    @JoinColumn(name = "material_type_id")
    @NotNull(message = "type is required")
    private MaterialType materialType;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    @NotNull(message = "description is required")
    private String description;

    @ManyToOne
    @NotNull(message = "lesson is required")
    private Lesson lesson;
}
