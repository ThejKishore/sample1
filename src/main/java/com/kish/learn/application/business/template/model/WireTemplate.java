package com.kish.learn.application.business.template.model;

import com.kish.learn.application.business.template.audit.AuditEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public final class WireTemplate extends Template {
    String vendorName;
    String vendorType;
    List<NTAccount> from;
    WireRecipient to;

    @Override
    public List<AuditEntity> diffs(Template value,String tableName,String modifiedBy) {
        if (Objects.isNull(value)) {
            LocalDate modifiedAt = LocalDate.now();
            List<AuditEntity> diffs = to.diffs(null, tableName, modifiedBy);
            diffs.addAll(from.stream().map(accnt -> accnt.diffs(null,tableName,modifiedBy)).flatMap(Collection::stream).toList());
            diffs.addAll(
                    List.of(
                            AuditEntity.builder().tableName(tableName).columnName("vendorName").actionEvent("INSERT").oldValue(this.vendorName).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                            AuditEntity.builder().tableName(tableName).columnName("vendorType").actionEvent("INSERT").oldValue(this.vendorType).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
                            )
            );
            return diffs;
        }
        LocalDate modifiedAt = LocalDate.now();
        WireTemplate newValue = (WireTemplate) value;
        List<AuditEntity> diffs = to.diffs(newValue.to, tableName, modifiedBy);
//        List<NTAccount> updatedList = ListUtils.intersection(this.from, ((WireTemplate) value).from);
//        List<NTAccount> newlyInsert = ListUtils.removeAll(this.from,((WireTemplate) value).from);
//        diffs.addAll(from.stream().map(accnt -> accnt.diffs(null,tableName,modifiedBy)).flatMap(Collection::stream).toList());
//        List<NTAccount> deletedList = ListUtils.removeAll(((WireTemplate) value).from,this.from);
        //TODO
        diffs.addAll(
                        List.of(
                                AuditEntity.builder().tableName(tableName).columnName("templateName").actionEvent("UPDATE").oldValue(this.vendorName).newValue(newValue.vendorName).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                                AuditEntity.builder().tableName(tableName).columnName("templateType").actionEvent("UPDATE").oldValue(this.vendorType).newValue(newValue.vendorType).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
                        )
                );
        return diffs;
    }
}
