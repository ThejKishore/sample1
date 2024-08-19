package com.kish.learn.application.business.template.model;

import com.kish.learn.application.business.template.audit.AuditEntity;
import com.kish.learn.application.business.template.audit.AuditEntityHelper;
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
public final class WireRecipient implements AuditEntityHelper<WireRecipient> {
    Long recipientId;
    String recipientName;
    String recipientEmail ;
    String bankName;
    String address;
    String city;
    String state;
    String country;

    @Override
    public List<AuditEntity> diffs(WireRecipient value, String tableName, String modifiedBy) {
        if(Objects.isNull(value)){
            LocalDate modifiedAt = LocalDate.now();
            return new ArrayList<>(List.of(
                    AuditEntity.builder().tableName(tableName).columnName("recipientName").actionEvent("INSERT").oldValue(this.recipientName).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("recipientEmail").actionEvent("INSERT").oldValue(this.recipientEmail).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("bankName").actionEvent("INSERT").oldValue(this.bankName).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("address").actionEvent("INSERT").oldValue(this.address).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("city").actionEvent("INSERT").oldValue(this.city).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("state").actionEvent("INSERT").oldValue(this.state).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("country").actionEvent("INSERT").oldValue(this.country).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()

            ));
        }
        LocalDate modifiedAt= LocalDate.now();
        return new ArrayList<>( List.of(
                AuditEntity.builder().tableName(tableName).columnName("recipientName").actionEvent("UPDATE").oldValue(this.recipientName).newValue(value.recipientName).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("recipientEmail").actionEvent("UPDATE").oldValue(this.recipientEmail).newValue(value.recipientEmail).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("bankName").actionEvent("UPDATE").oldValue(this.bankName).newValue(value.bankName).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("address").actionEvent("UPDATE").oldValue(this.address).newValue(value.address).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("city").actionEvent("UPDATE").oldValue(this.city).newValue(value.city).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("state").actionEvent("UPDATE").oldValue(this.state).newValue(value.state).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("country").actionEvent("UPDATE").oldValue(this.country).newValue(value.country).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
        ));
    }
}
