package com.kish.learn.application.business.template.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record NTAccountExtended (Long accntSk ,String accntName , String accntDescription , int srcType , Long coaSk, BigDecimal amount){}
