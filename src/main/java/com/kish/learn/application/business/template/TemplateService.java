package com.kish.learn.application.business.template;

import com.kish.learn.application.business.template.model.TemplateWrapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemplateService {

    private final TemplateWrapperRepository templateWrapperRepository;

    public TemplateService(TemplateWrapperRepository templateWrapperRepository) {
        this.templateWrapperRepository = templateWrapperRepository;
    }

    public TemplateWrapper saveTemplate(Optional<TemplateWrapper> templateWrapper){
        return templateWrapper.map(templateWrapperRepository::persist).orElseThrow(IllegalArgumentException::new);
    }

    public TemplateWrapper updateTemplate(Optional<TemplateWrapper> templateWrapper){
        return templateWrapper.map(templateWrapperRepository::update).orElseThrow(IllegalArgumentException::new);
    }

    public void deleteTemplateById(Optional<Long> templateId){
        templateId.ifPresentOrElse(templateWrapperRepository::deleteById, IllegalArgumentException::new);
    }
}
