package com.kish.learn.application.business.template.landingpage;

import com.kish.learn.application.business.template.landingpage.model.TemplateLandingPage;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import io.hypersistence.utils.spring.repository.HibernateRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@JaversSpringDataAuditable
public interface TemplatePageRepository extends HibernateRepository<TemplateLandingPage>,BaseJpaRepository<TemplateLandingPage,Long>, JpaSpecificationExecutor<TemplateLandingPage> {

    @Query("from TemplateLandingPage")
    @Override
    List<TemplateLandingPage> findAll();

    Page<TemplateLandingPage> findAll(Pageable pageable);
}
