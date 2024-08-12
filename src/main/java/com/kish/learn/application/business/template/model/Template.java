package com.kish.learn.application.business.template.model;

public sealed interface Template permits WireTemplate,TransferTemplate , WireInTemplate,AchTemplate{
    String vendorName();
    String vendorType();
}
