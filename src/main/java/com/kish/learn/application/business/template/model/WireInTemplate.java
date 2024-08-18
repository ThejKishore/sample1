package com.kish.learn.application.business.template.model;

import com.kish.learn.application.business.template.audit.AuditEntity;
import com.kish.learn.application.business.template.audit.AuditEntityHelper;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Builder(toBuilder = true)
public record WireInTemplate(
        String vendorName,
        String vendorType,
        WireRecipient from,
        List<NTAccount> to
        )
        implements Template, AuditEntityHelper<Template> {

        @Override
        public List<AuditEntity> diffs(Template value, String tableName, String modifiedBy) {
                if (Objects.isNull(value)) {
                        LocalDate modifiedAt = LocalDate.now();
                        List<AuditEntity> diffs = from.diffs(null, tableName, modifiedBy);
                        diffs.addAll(
                                List.of(
                                        AuditEntity.builder().tableName(tableName).columnName("vendorName").actionEvent("INSERT").oldValue(this.vendorName).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                                        AuditEntity.builder().tableName(tableName).columnName("vendorType").actionEvent("INSERT").oldValue(this.vendorType).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
                                )
                        );
                        return diffs;
                }
                LocalDate modifiedAt = LocalDate.now();
                WireInTemplate newValue = (WireInTemplate) value;
                List<AuditEntity> diffs = from.diffs(newValue.from, tableName, modifiedBy);
                diffs.addAll(
                        List.of(
                                AuditEntity.builder().tableName(tableName).columnName("templateName").actionEvent("UPDATE").oldValue(this.vendorName).newValue(newValue.vendorName).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                                AuditEntity.builder().tableName(tableName).columnName("templateType").actionEvent("UPDATE").oldValue(this.vendorType).newValue(newValue.vendorType).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
                        )
                );
                return diffs;
        }
}
