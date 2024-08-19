package com.kish.learn.application.views.templateform;

import com.kish.learn.application.business.template.model.TemplateType;
import com.kish.learn.application.business.template.model.TemplateWrapper;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TemplateBaseView extends Composite<VerticalLayout> {



    public TemplateBaseView(Binder<TemplateWrapper> templateWrapperBinder) {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        H3 h3 = new H3();
        h3.setWidthFull();
        TextField templateNameTF = new TextField("Template Name");
        templateNameTF.setRequiredIndicatorVisible(true);
        templateNameTF.setWidthFull();
        templateNameTF.setPattern("[\\p{L} \\-]+");
        templateNameTF.addClassNames(LumoUtility.Margin.Bottom.SMALL);

        Select<TemplateType> templateTypeSelect = new Select<>();
        templateTypeSelect.setWidth("100%");
        templateTypeSelect.setLabel("Template Type");
        templateTypeSelect.setRequiredIndicatorVisible(true);
        templateTypeSelect.addClassNames(LumoUtility.Margin.Bottom.SMALL);
        templateTypeSelect.setItems(TemplateType.values());
        templateTypeSelect.setItemLabelGenerator(TemplateType::name);
        HorizontalLayout layoutRow = new HorizontalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);

        h3.setText("Template Details");
        h3.setWidth("100%");
        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(templateNameTF);
        layoutColumn2.add(templateTypeSelect);
        layoutColumn2.add(layoutRow);

        templateWrapperBinder.forField(templateNameTF).asRequired().bind(TemplateWrapper::getTemplateName , TemplateWrapper::setTemplateName);
        templateWrapperBinder.forField(templateTypeSelect).asRequired().bind(TemplateWrapper::getTemplateType , TemplateWrapper::setTemplateType);

    }

}

