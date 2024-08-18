package com.kish.learn.application.business.template.audit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditEntity{

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long auditId;
        String tableName;
        String columnName;
        String oldValue;
        String newValue;
        String modifiedBy;
        String actionEvent;
        LocalDate modifiedAt;
}
