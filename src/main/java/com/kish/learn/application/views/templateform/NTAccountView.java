package com.kish.learn.application.views.templateform;

import com.kish.learn.application.business.template.model.NTAccount;
import com.kish.learn.application.business.template.model.NTAccountExtended;
import com.kish.learn.application.views.component.SearchGrid;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.math.BigDecimal;
import java.util.List;

public class NTAccountView extends Composite<VerticalLayout> {

    List<NTAccount> accountList = List.of(
            NTAccount.builder().accntSk(23432l).srcType(1).accntName("Test1").accntDescription(" accnt type custody").build(),
            NTAccount.builder().accntSk(12343l).srcType(1).accntName("Test2").accntDescription(" accnt type banking").build(),
            NTAccount.builder().accntSk(92328l).srcType(1).accntName("Test3").accntDescription(" accnt type custody").build()
    );

    List<NTAccountExtended> accountListExtended = List.of(
            NTAccountExtended.builder().accntSk(23432l).srcType(1).accntName("Test1").accntDescription(" accnt type custody").amount(BigDecimal.ZERO).build(),
            NTAccountExtended.builder().accntSk(12343l).srcType(1).accntName("Test2").accntDescription(" accnt type banking").amount(BigDecimal.ZERO).build(),
            NTAccountExtended.builder().accntSk(92328l).srcType(1).accntName("Test3").accntDescription(" accnt type custody").amount(BigDecimal.ZERO).build()
    );

    public NTAccountView(String headerText) {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);

        H3 h3 = new H3();
        h3.setText(headerText);
        h3.setWidth("100%");

        SearchGrid select1 = new SearchGrid("NT Accounts", "Search with account number or names");
        Grid basicGrid = new Grid<NTAccountExtended>();
        basicGrid.setWidth("100%");
        basicGrid.setWidthFull();
        setGridSampleData(basicGrid);

        layoutColumn2.add(h3);
        layoutColumn2.add(select1);
        layoutColumn2.add(basicGrid);

        getContent().add(layoutColumn2);
    }

    private void setGridSampleData(Grid<NTAccountExtended> grid) {
        grid.addColumn(NTAccountExtended::accntSk).setHeader("Account Number").setAutoWidth(true);
        grid.addColumn(NTAccountExtended::accntName).setHeader("Account Name").setAutoWidth(true);
        grid.addColumn(NTAccountExtended::accntDescription).setHeader("Account Description").setAutoWidth(true);
        grid.addColumn(NTAccountExtended::srcType).setHeader("Account Type").setAutoWidth(true);
        grid.addColumn(new ComponentRenderer<>(nt -> {
            NumberField numberField = new NumberField();
            numberField.setValue(nt.amount().doubleValue());
            return numberField;
        })).setHeader("Amount").setAutoWidth(true);
        grid.setItems(accountListExtended);
        grid.setWidthFull();
        grid.setHeight("300px");
    }

}