package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "images")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_images",
        initialValue = 1,
        allocationSize = 1
)

public class Image extends AuditModel {

    @Column(name = "path")
    String path;

}
