package com.kish.learn.application.views.component;

import com.kish.learn.application.business.template.model.NTAccount;
import com.vaadin.componentfactory.Popup;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;

public class SearchGrid extends Composite<Div> {

    List<NTAccount> accountList = List.of(
            NTAccount.builder().accntSk(23432l).srcType(1).accntName("Test1").accntDescription(" accnt type custody").build(),
            NTAccount.builder().accntSk(12343l).srcType(1).accntName("Test2").accntDescription(" accnt type banking").build(),
            NTAccount.builder().accntSk(92328l).srcType(1).accntName("Test3").accntDescription(" accnt type custody").build(),
            NTAccount.builder().accntSk(23432l).srcType(1).accntName("Test1").accntDescription(" accnt type custody").build(),
            NTAccount.builder().accntSk(12343l).srcType(1).accntName("Test2").accntDescription(" accnt type banking").build(),
            NTAccount.builder().accntSk(23432l).srcType(1).accntName("Test1").accntDescription(" accnt type custody").build(),
            NTAccount.builder().accntSk(12343l).srcType(1).accntName("Test2").accntDescription(" accnt type banking").build(),
            NTAccount.builder().accntSk(23432l).srcType(1).accntName("Test1").accntDescription(" accnt type custody").build(),
            NTAccount.builder().accntSk(12343l).srcType(1).accntName("Test2").accntDescription(" accnt type banking").build()
    );


    Popup popup = new Popup();
    Grid<NTAccount> basicGrid = new Grid(NTAccount.class, false);
    public SearchGrid(String label , String placeholder) {
        TextField search = new TextField();
        search.setAutocomplete(Autocomplete.OFF);
        search.setMaxWidth("650px");
        search.setId("search");
        search.setPlaceholder(placeholder);
        search.setLabel(label);
        search.setWidthFull();
        popup.setTarget(search.getElement());
        basicGrid.setItems(new ArrayList<>(accountList));
        basicGrid.addColumn(NTAccount::getAccntSk).setHeader("Account Identifier").setAutoWidth(true);
        basicGrid.addColumn(NTAccount::getAccntName).setHeader("Account Name").setAutoWidth(true);
        basicGrid.addColumn(NTAccount::getSrcType).setHeader("Account Type").setAutoWidth(true);
        basicGrid.addColumn(NTAccount::getAccntDescription).setHeader("Acount Description").setAutoWidth(true);
        basicGrid.setWidth("650px");
        basicGrid.setHeight("150px");
        popup.add(basicGrid);
        getContent().add(search);
        getContent().add(popup);
        getContent().setWidthFull();
        search.addValueChangeListener(event -> {
            String enteredValue = event.getValue();
            if(enteredValue!=null && enteredValue.length() > 3) {
                List<NTAccount> filterList = accountList.stream()
                        .filter(nt -> (nt.getAccntName().toLowerCase().contains(enteredValue.toLowerCase()) || nt.getAccntSk().toString().contains(enteredValue)))
                        .toList();
                basicGrid.setItems(filterList);
            } else{
                basicGrid.setItems(accountList);
            }
        });
    }
}
