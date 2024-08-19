package com.kish.learn.application.views.templateform;

import com.kish.learn.application.business.template.model.NTAccount;
import com.kish.learn.application.views.component.SearchGrid;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NTAccountView extends Composite<VerticalLayout> {



    List<NTAccount> selectAccntList;
    Grid basicGrid = new Grid<NTAccount>();

    public NTAccountView(String headerText,  List<NTAccount> selectAccntList) {
        this(headerText);
        this.selectAccntList = selectAccntList;
    }
    SearchGrid select1 ;

    public NTAccountView(String headerText) {
        selectAccntList = new ArrayList<>();
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

        select1 = new SearchGrid("NT Accounts", "Search with account number or names", selectAccntList,basicGrid);

        basicGrid.setWidth("100%");
        basicGrid.setWidthFull();
        setGridSampleData(basicGrid);
        layoutColumn2.add(h3);
        layoutColumn2.add(select1);
        layoutColumn2.add(basicGrid);
        getContent().add(layoutColumn2);
    }

    private void setGridSampleData(Grid<NTAccount> grid) {
        grid.addColumn(NTAccount::getAccntSk).setHeader("Account Number").setAutoWidth(true);
        grid.addColumn(NTAccount::getAccntName).setHeader("Account Name").setAutoWidth(true);
        grid.addColumn(NTAccount::getAccntDescription).setHeader("Account Description").setAutoWidth(true);
        grid.addColumn(NTAccount::getSrcType).setHeader("Account Type").setAutoWidth(true);
        grid.addColumn(new ComponentRenderer<>(nt -> {
            NumberField numberField = new NumberField();
            numberField.setValue(nt.getPercentage().doubleValue());
            numberField.addValueChangeListener(event -> {
                double updatedValue = event.getValue().doubleValue();
                nt.setPercentage(BigDecimal.valueOf(updatedValue));
            });
            return numberField;
        })).setHeader("Percentage").setAutoWidth(true);
        grid.setItems(selectAccntList);
        grid.setWidthFull();
        grid.setHeight("300px");
    }

    public List<NTAccount> getSelectAccntList() {
        return this.select1.getSelectedAccounts();
    }
}