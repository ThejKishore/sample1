package com.kish.learn.application.views.templateform;

import com.kish.learn.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Template Form")
@Route(value = "template-form", layout = MainLayout.class)
public class TemplateFormView extends VerticalLayout {


    public TemplateFormView() {
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
        templateBaseDetails.setWidthFull();
        templateBaseDetails.add(new TemplateBaseView());
        Button customDetailsButton = new Button("Continue", (e) -> accordion.open(1));
        customDetailsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        templateBaseDetails.add(customDetailsButton);

        return templateBaseDetails;
    }

    private VerticalLayout creatFromDetailsSection(Accordion accordion) {
        VerticalLayout fromDetails = new VerticalLayout();
        fromDetails.setWidthFull();
        fromDetails.add(new NTAccountView("NT Account - From Section"));
        Button customDetailsButton = new Button("Continue", (e) -> accordion.open(2));
        customDetailsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        fromDetails.setWidthFull();
        fromDetails.add(customDetailsButton);
        return fromDetails;
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
