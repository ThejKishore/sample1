package com.kish.learn.application.views.templateform;

import com.kish.learn.application.business.template.model.NTAccountExtended;
import com.kish.learn.application.views.component.SearchGrid;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.util.ArrayList;
import java.util.List;

public class NTAccountView extends Composite<VerticalLayout> {

    List<NTAccountExtended> accountListExtended = new ArrayList<>();
    Grid basicGrid = new Grid<NTAccountExtended>();

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

        SearchGrid select1 = new SearchGrid("NT Accounts", "Search with account number or names",accountListExtended,basicGrid);

        basicGrid.setWidth("100%");
        basicGrid.setWidthFull();
        setGridSampleData(basicGrid);
        layoutColumn2.add(h3);
        layoutColumn2.add(select1);
        layoutColumn2.add(basicGrid);
        getContent().add(layoutColumn2);
    }

    private void setGridSampleData(Grid<NTAccountExtended> grid) {
        grid.addColumn(NTAccountExtended::getAccntSk).setHeader("Account Number").setAutoWidth(true);
        grid.addColumn(NTAccountExtended::getAccntName).setHeader("Account Name").setAutoWidth(true);
        grid.addColumn(NTAccountExtended::getAccntDescription).setHeader("Account Description").setAutoWidth(true);
        grid.addColumn(NTAccountExtended::getSrcType).setHeader("Account Type").setAutoWidth(true);
        grid.addColumn(new ComponentRenderer<>(nt -> {
            NumberField numberField = new NumberField();
            numberField.setValue(nt.getPercentage().doubleValue());
            return numberField;
        })).setHeader("Amount").setAutoWidth(true);
        grid.setItems(accountListExtended);
        grid.setWidthFull();
        grid.setHeight("300px");
    }

}