package com.kish.learn.application.business.template.model;

import com.kish.learn.application.business.template.audit.AuditEntity;
import com.kish.learn.application.business.template.audit.AuditEntityHelper;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Builder(toBuilder = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TemplateWrapper implements AuditEntityHelper<TemplateWrapper> {

    @Transient
    @Builder.Default
    String tableName = "template_wrapper";

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

    @Type(JsonBinaryType.class)
    @Column(name = "template", columnDefinition = "jsonb")
    private Template template;

    @Override
    public List<AuditEntity> diffs(TemplateWrapper value,String tableName,String modifiedBy) {
        if(Objects.isNull(value)){
            LocalDate modifiedAt = LocalDate.now();
            List<AuditEntity> diffs = this.template.diffs(null, tableName, modifiedBy);
            diffs.addAll( List.of(
                    AuditEntity.builder().tableName(tableName).columnName("templateName").actionEvent("INSERT").oldValue(this.templateName).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("templateType").actionEvent("INSERT").oldValue(this.templateType.name()).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
            ));
            return diffs;
        }
        LocalDate modifiedAt= LocalDate.now();
        List<AuditEntity> diffs = this.template.diffs(value.template, tableName, modifiedBy);
        diffs.addAll( List.of(
                AuditEntity.builder().tableName(tableName).columnName("templateName").actionEvent("UPDATE").oldValue(this.templateName).newValue(value.templateName).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("templateType").actionEvent("UPDATE").oldValue(this.templateType.name()).newValue(value.templateType.name()).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
        ));
        return diffs;

    }
}