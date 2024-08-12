package com.kish.learn.application.views.templateform;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.ArrayList;
import java.util.List;

public class RecipientView extends Composite<VerticalLayout> {

    public RecipientView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        H3 h3 = new H3();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        Select select = new Select();
        Select select2 = new Select();
        HorizontalLayout layoutRow = new HorizontalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);

        h3.setText("Recipient");
        h3.setWidth("min-content");
        textField.setLabel("Recipient Name");
        textField.setWidth("100%");
        textField2.setLabel("Bank Name");
        textField2.setWidth("100%");
        formLayout2Col.setWidth("100%");
        textField3.setLabel("Address");
        textField3.setWidth("min-content");
        textField4.setLabel("City");
        textField4.setWidth("min-content");
        select.setLabel("State");
        select.setWidth("min-content");
        setSelectSampleData(select);
        select2.setLabel("Country");
        select2.setWidth("min-content");
        setSelectSampleData(select2);
        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(textField);
        layoutColumn2.add(textField2);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField3);
        formLayout2Col.add(textField4);
        formLayout2Col.add(select);
        formLayout2Col.add(select2);
        layoutColumn2.add(layoutRow);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setSelectSampleData(Select select) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("state1", "State 1", null));
        sampleItems.add(new SampleItem("state2", "State 2", null));
        sampleItems.add(new SampleItem("state3", "State 3", null));
        select.setItems(sampleItems);
        select.setItemLabelGenerator(item -> ((SampleItem) item).label());
        select.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }
}

