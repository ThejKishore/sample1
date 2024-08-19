package com.kish.learn.application.business.template.model;

import com.kish.learn.application.business.template.audit.AuditEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public final class TransferTemplate extends Template {
    String vendorName;
    String vendorType;
    List<NTAccount> from;
    List<NTAccount> to;

    @Override
    public List<AuditEntity> diffs(Template value, String tableName, String modifiedBy) {
        if (Objects.isNull(value)) {
            LocalDate modifiedAt = LocalDate.now();
            List<AuditEntity> diffs = new ArrayList<>();
            diffs.addAll(
                    List.of(
                            AuditEntity.builder().tableName(tableName).columnName("vendorName").actionEvent("INSERT").oldValue(this.vendorName).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                            AuditEntity.builder().tableName(tableName).columnName("vendorType").actionEvent("INSERT").oldValue(this.vendorType).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
                    )
            );
            return diffs;
        }
        LocalDate modifiedAt = LocalDate.now();
        TransferTemplate newValue = (TransferTemplate) value;
        List<AuditEntity> diffs = new ArrayList<>();
        diffs.addAll(
                List.of(
                        AuditEntity.builder().tableName(tableName).columnName("templateName").actionEvent("UPDATE").oldValue(this.vendorName).newValue(newValue.vendorName).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                        AuditEntity.builder().tableName(tableName).columnName("templateType").actionEvent("UPDATE").oldValue(this.vendorType).newValue(newValue.vendorType).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
                )
        );
        return diffs;
    }
}
