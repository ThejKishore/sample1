package com.kish.learn.application.business.template;

import com.kish.learn.application.business.template.model.TemplateWrapper;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;

@JaversSpringDataAuditable
public interface TemplateWrapperRepository extends BaseJpaRepository<TemplateWrapper, Long> {
}
