package kz.kasya.bitlab.RXCourse.models.entities;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Assylkhan
 * on 23.08.2019
 * @project AttendMe
 */
@Entity
@Table(name = "material_types")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_material_types",
        initialValue = 1,
        allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class MaterialType extends AuditModel {

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;
}
