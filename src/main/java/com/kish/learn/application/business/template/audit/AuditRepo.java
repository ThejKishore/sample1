package com.kish.learn.application.business.template.audit;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;

public interface AuditRepo extends BaseJpaRepository<AuditEntity, Long> {
}
