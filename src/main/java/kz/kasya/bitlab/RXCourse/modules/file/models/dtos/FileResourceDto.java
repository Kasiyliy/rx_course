package kz.kasya.bitlab.RXCourse.modules.file.models.dtos;

import kz.kasya.bitlab.RXCourse.models.audits.AuditModel;
import kz.kasya.bitlab.RXCourse.models.dtos.base.BaseDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Assylkhan
 * on 10.08.2019
 * @project AttendMe
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResourceDto extends BaseDto {

    private String fileName;

    private String fileDownloadUri;

    private String fileType;

    private Long size;

}
