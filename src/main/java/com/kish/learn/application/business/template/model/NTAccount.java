package com.kish.learn.application.business.template.model;

import com.kish.learn.application.business.template.audit.AuditEntity;
import com.kish.learn.application.business.template.audit.AuditEntityHelper;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class NTAccount implements AuditEntityHelper<NTAccount> {

    @EqualsAndHashCode.Include
    Long accntSk ;

    String accntName ;

    String accntDescription ;

    int srcType ;

    Long coaSk;

    BigDecimal percentage;

    @Override
    public List<AuditEntity> diffs(NTAccount value, String tableName, String modifiedBy) {
        if(Objects.isNull(value)){
            LocalDate modifiedAt = LocalDate.now();
            return new ArrayList<>(List.of(
                    AuditEntity.builder().tableName(tableName).columnName("accntSk").actionEvent("INSERT").oldValue(this.accntSk.toString()).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("accntName").actionEvent("INSERT").oldValue(this.accntName).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("accntDescription").actionEvent("INSERT").oldValue(this.accntDescription).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                    AuditEntity.builder().tableName(tableName).columnName("srcType").actionEvent("INSERT").oldValue(Integer.toString(this.srcType)).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
//                    AuditEntity.builder().tableName(tableName).columnName("coaSk").actionEvent("INSERT").oldValue(this.coaSk.toString()).newValue(StringUtils.EMPTY).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
            ));
        }
        LocalDate modifiedAt= LocalDate.now();
        return new ArrayList<>(List.of(
                AuditEntity.builder().tableName(tableName).columnName("accntSk").actionEvent("UPDATE").oldValue(this.accntSk.toString()).newValue(value.accntSk.toString()).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("accntName").actionEvent("UPDATE").oldValue(this.accntName).newValue(value.accntName).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("accntDescription").actionEvent("UPDATE").oldValue(this.accntDescription).newValue(value.accntDescription).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("srcType").actionEvent("UPDATE").oldValue(Integer.toString(this.srcType)).newValue(Integer.toString(value.srcType)).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build(),
                AuditEntity.builder().tableName(tableName).columnName("coaSk").actionEvent("UPDATE").oldValue(this.coaSk.toString()).newValue(value.coaSk.toString()).modifiedBy(modifiedBy).modifiedAt(modifiedAt).build()
        ));
    }
}
