package com.kish.learn.application.views.templateform;

import com.kish.learn.application.business.template.model.*;
import com.kish.learn.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@PageTitle("Template Form")
@Route(value = "template-form", layout = MainLayout.class)
public class TemplateFormView extends VerticalLayout {

    TemplateWrapper templateWrapper;

    public TemplateFormView() {
        this(TemplateWrapper.builder().build());
    }

    Binder<TemplateWrapper> templateWrapperBinder = new Binder<>(TemplateWrapper.class);

    public TemplateFormView(TemplateWrapper templateWrapper) {
        if(Objects.nonNull(templateWrapper)) {
            this.templateWrapper = templateWrapper;
        } else{
            this.templateWrapper = TemplateWrapper.builder().build();
        }
        addClassNames("template-form-view");
        add(createTemplateForm());
    }

    private Component createTemplateForm() {
        VerticalLayout templateLayout = new VerticalLayout();
        templateLayout.setWidthFull();
        H2 header = new H2("Create Template");
        templateLayout.add(header);
        Accordion accordion = new Accordion();
        accordion.setWidth("85%");
        accordion.add("Template details", createTemplateBaseDetailsSection(accordion));
        accordion.add("From Section" , creatFromDetailsSection(accordion));
        accordion.add("To Section",createPaymentInformationSection());
        templateLayout.add(accordion);
        return templateLayout;
    }


    private VerticalLayout createTemplateBaseDetailsSection(Accordion accordion) {
        VerticalLayout templateBaseDetails = new VerticalLayout();
        TemplateBaseView templateBaseView = new TemplateBaseView(templateWrapperBinder);
        templateBaseDetails.setWidthFull();
        templateBaseDetails.add(templateBaseView);
        Button customDetailsButton = new Button("Continue", (e) ->{
            templateWrapperBinder.validate();
            if(templateWrapperBinder.isValid() ) {
                accordion.open(1);
                this.templateWrapper.toBuilder()
                        .template(createTemplateBasedOnType(this.templateWrapper.getTemplateType()))
                        .build();
            }else{
                Notification.show("Invalid Template",3500, Notification.Position.TOP_STRETCH);
            }
        });
        customDetailsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        templateBaseDetails.add(customDetailsButton);

        return templateBaseDetails;
    }

    private Template createTemplateBasedOnType(TemplateType templateType) {
        return switch (templateType){
            case ACH -> AchTemplate.builder().build();
            case WIRE_IN -> WireInTemplate.builder().build();
            case TRANSFER -> TransferTemplate.builder().build();
            case WIRE_OUT -> WireTemplate.builder().build();
        };
    }
    NTAccountView ntAccountView;
    private VerticalLayout creatFromDetailsSection(Accordion accordion) {
        VerticalLayout fromDetails = new VerticalLayout();
        ntAccountView = new NTAccountView("NT Account - From Section",selectedAccounts(templateWrapper));
        fromDetails.setWidthFull();
        fromDetails.add(ntAccountView);
        Button customDetailsButton = new Button("Continue", (e) -> {
                accordion.open(2);
                log.info("{} ", ntAccountView.getSelectAccntList());
        });
        customDetailsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        fromDetails.setWidthFull();
        fromDetails.add(customDetailsButton);
        return fromDetails;
    }

    private List<NTAccount> selectedAccounts(TemplateWrapper templateWrapper){
        if(Objects.isNull(templateWrapper.getTemplate())){
            return Collections.emptyList();
        }
        return switch (templateWrapper.getTemplateType()){
            case WIRE_OUT -> Optional.of(((WireTemplate) templateWrapper.getTemplate())).map(WireTemplate::getFrom).orElse(Collections.emptyList());
            case ACH ->  Optional.of(((AchTemplate) templateWrapper.getTemplate())).map(AchTemplate::getFrom).orElse(Collections.emptyList());
            case TRANSFER ->  Optional.of(((TransferTemplate) templateWrapper.getTemplate())).map(TransferTemplate::getFrom).orElse(Collections.emptyList());
            case WIRE_IN -> Collections.emptyList();
        };
    }

    private VerticalLayout createPaymentInformationSection() {
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setWidthFull();
        formLayout.add(new RecipientView());
        formLayout.add(createFooter());
        return formLayout;
    }

    private HorizontalLayout createFooter() {
        HorizontalLayout  footer = new HorizontalLayout();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        Button cancel = new Button("Cancel");
        cancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_ERROR , ButtonVariant.LUMO_SMALL);

        Button pay = new Button("Create", new Icon(VaadinIcon.LOCK));
        pay.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SMALL);

        Span space = new Span();
        footer.add(cancel,space,pay);
        return footer;
    }

}
