package com.kish.learn.application.business.template.model;

import com.kish.learn.application.business.template.audit.AuditEntity;
import com.kish.learn.application.business.template.audit.AuditEntityHelper;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder(toBuilder = true)
public record AchRecipient(Long recipientId, String recipientName,String recipientEmail , String bankDetails) implements AuditEntityHelper<AchRecipient> {
    @Override
    public List<AuditEntity> diffs(AchRecipient value, String tableName, String modifiedBy) {
        if(Objects.isNull(value)){
            LocalDate modifiedAt = LocalDate.now();
            return new ArrayList<>(List.of(
                    AuditEntity.builder().tableName(tableName).columnName("recipientName").actionEvent("INSERT").oldValue(this.recipientName).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("recipientEmail").actionEvent("INSERT").oldValue(this.recipientEmail).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("bankDetails").actionEvent("INSERT").oldValue(this.bankDetails).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()

            ));
        }
        LocalDate modifiedAt= LocalDate.now();
        return new ArrayList<>( List.of(
                AuditEntity.builder().tableName(tableName).columnName("recipientName").actionEvent("UPDATE").oldValue(this.recipientName).newValue(value.recipientName).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("recipientEmail").actionEvent("UPDATE").oldValue(this.recipientEmail).newValue(value.recipientEmail).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("bankDetails").actionEvent("UPDATE").oldValue(this.bankDetails).newValue(value.bankDetails).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
        ));
    }
}