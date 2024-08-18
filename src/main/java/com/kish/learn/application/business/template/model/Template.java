package com.kish.learn.application.business.template.model;

import com.kish.learn.application.business.template.audit.AuditEntity;

import java.util.List;

public sealed interface Template permits WireTemplate,TransferTemplate , WireInTemplate,AchTemplate {
    String vendorName();
    String vendorType();
    List<AuditEntity> diffs(Template value,String tableName,String modifiedBy);
}
