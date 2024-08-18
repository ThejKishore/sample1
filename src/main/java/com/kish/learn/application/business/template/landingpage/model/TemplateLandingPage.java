package com.kish.learn.application.business.template.landingpage.model;

import com.kish.learn.application.business.template.audit.AuditEntity;
import com.kish.learn.application.business.template.audit.AuditEntityHelper;
import com.kish.learn.application.business.template.model.TemplateType;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Builder(toBuilder = true)
@Table(name = "template_landing_page")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TemplateLandingPage implements AuditEntityHelper<TemplateLandingPage> {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long templateId;

        String templateName;

        @Enumerated(EnumType.STRING)
        TemplateType templateType;

        LocalDate createdDate;

        String createBy;

        LocalDate updatedDate;

        String updateBy;

        String sponsorCode;

        String recipient;

        @Type(ListArrayType.class)
        @Column(
                name = "ntAccnts",
                columnDefinition = "bigint[]"
        )
        List<Long> ntAccntSk;

        @Override
        public List<AuditEntity> diffs(TemplateLandingPage value,String tableName,String modifiedBy) {
                if(Objects.isNull(value)){
                        LocalDate modifiedAt = LocalDate.now();
                        return List.of(
                                AuditEntity.builder().tableName(tableName).columnName("templateName").actionEvent("INSERT").oldValue(this.templateName).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                                AuditEntity.builder().tableName(tableName).columnName("templateType").actionEvent("INSERT").oldValue(this.templateType.name()).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                                AuditEntity.builder().tableName(tableName).columnName("recipient").actionEvent("INSERT").oldValue(this.recipient).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                                AuditEntity.builder().tableName(tableName).columnName("accounts").actionEvent("INSERT").oldValue(this.ntAccntSk.toString()).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
                        );
                }
                LocalDate modifiedAt= LocalDate.now();
                return List.of(
                        AuditEntity.builder().tableName(tableName).columnName("templateName").actionEvent("UPDATE").oldValue(this.templateName).newValue(value.templateName).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                        AuditEntity.builder().tableName(tableName).columnName("templateType").actionEvent("UPDATE").oldValue(this.templateType.name()).newValue(value.templateType.name()).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                        AuditEntity.builder().tableName(tableName).columnName("recipient").actionEvent("UPDATE").oldValue(this.recipient).newValue(value.recipient).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                        AuditEntity.builder().tableName(tableName).columnName("accounts").actionEvent("UPDATE").oldValue(this.ntAccntSk.toString()).newValue(value.ntAccntSk.toString()).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
                );
        }
}
