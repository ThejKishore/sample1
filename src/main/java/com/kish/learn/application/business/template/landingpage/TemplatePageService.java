package com.kish.learn.application.business.template.landingpage;


import com.kish.learn.application.business.template.landingpage.model.TemplateLandingPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemplatePageService {

    private final TemplatePageRepository templatePageRepository;

    public TemplatePageService(TemplatePageRepository templatePageRepository) {
        this.templatePageRepository = templatePageRepository;
    }

    public Optional<TemplateLandingPage> get(Long id) {
        return templatePageRepository.findById(id);
    }

    public TemplateLandingPage update(TemplateLandingPage entity) {
        return templatePageRepository.update(entity);
    }

    public void delete(Long id) {
        templatePageRepository.deleteById(id);
    }

    public Page<TemplateLandingPage> list(Pageable pageable) {
        return templatePageRepository.findAll(pageable);
    }

    public Page<TemplateLandingPage> list(Pageable pageable, Specification<TemplateLandingPage> filter) {
        return templatePageRepository.findAll(filter, pageable);
    }

    public int count() {
        return (int) templatePageRepository.count();
    }
}
