package com.kish.learn.application.business.template.audit;

import java.util.List;

public interface AuditEntityHelper<T>  {
    List<AuditEntity> diffs(T value,String tableName,String updatedBy);
}
